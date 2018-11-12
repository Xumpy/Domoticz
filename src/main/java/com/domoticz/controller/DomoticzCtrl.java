package com.domoticz.controller;


import com.domoticz.model.Device;
import com.domoticz.services.components.DomoticzComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class DomoticzCtrl {
    @Autowired Map<Integer, Device> receivedDevices;
    @Autowired List<? extends DomoticzComponent> domoticzComponents;

    private static final Logger LOGGER = LogManager.getLogger(DomoticzCtrl.class);

    private Boolean isDeviceStateChanged(Device device){
        if (receivedDevices.containsKey(device.getIdx()) && receivedDevices.get(device.getIdx()).equals(device)){
            return false;
        }
        return true;
    }

    @RequestMapping("/domoticz")
    public String enterDomoticzBackend(@RequestBody Device device){
        if (isDeviceStateChanged(device)) {
            LOGGER.info("Received idx: " + device.getIdx() + " with type " + device.getDeviceType());
            receivedDevices.put(device.getIdx(), device);

            for (DomoticzComponent domoticzComponent: domoticzComponents){
                if (domoticzComponent.getIdx().equals(device.getIdx())){
                    domoticzComponent.setDevice(device);
                    domoticzComponent.executeAction(device);
                }
            }
            for (DomoticzComponent domoticzComponent: domoticzComponents){
                if (domoticzComponent.getChanged()){
                    domoticzComponent.sendDeviceToDomoticzServer();
                }
            }
        }

        return "201";
    }

}
