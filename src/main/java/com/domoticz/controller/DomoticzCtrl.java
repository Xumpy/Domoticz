package com.domoticz.controller;


import com.domoticz.model.Device;
import com.domoticz.services.Switch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DomoticzCtrl {
    @Autowired Map<Integer, Device> receivedDevices;
    @Autowired Switch switchDevice;

    private List<List<Integer>> linkedIdxMap(){
        List<List<Integer>> linkedSwitches = new ArrayList<>();

        List<Integer> livingLinks = new ArrayList<>();
        livingLinks.add(79);
        livingLinks.add(82);

        List<Integer> livingRechts = new ArrayList<>();
        livingRechts.add(78);
        livingRechts.add(81);

        linkedSwitches.add(livingLinks);
        linkedSwitches.add(livingRechts);

        return linkedSwitches;
    }

    @RequestMapping("/domoticz")
    public String enterDomoticzBackend(@RequestBody Device device){
        System.out.println("Received idx: " + device.getIdx() + " with type " + device.getDeviceType());
        receivedDevices.put(device.getIdx(), device);

        switchDevice.withLink(device, linkedIdxMap());

        return "201";
    }

}
