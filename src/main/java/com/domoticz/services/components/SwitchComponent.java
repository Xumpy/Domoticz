package com.domoticz.services.components;


import com.domoticz.model.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public abstract class SwitchComponent extends DomoticzComponent {
    private static final String ON_STATE = "On";
    private static final String OFF_STATE = "Off";
    private static final String TOGGLE_STATE = "Toggle";

    private static final Logger logger = LogManager.getLogger(SwitchComponent.class);

    @Override
    public Map<String, String> deviceToDomoticzMapForURL(Device device) {

        Map<String, String> defaultValues = new LinkedHashMap<>();
        defaultValues.put("type", "command");
        defaultValues.put("param", "switchlight");
        defaultValues.put("idx", getIdx().toString());
        defaultValues.put("switchcmd", device.getState());

        return defaultValues;
    }

    @Override
    public void executeAction(Device device){
        if(device.getState().equals(ON_STATE)){
            onAction();
        } else if(device.getState().equals(OFF_STATE)){
            offAction();
        } else {
            logger.info("Device state unknown: " + device.getState());
        }
    }

    public void turnSwitchOn(){
        super.getDevice().setState(ON_STATE);
    }

    public void turnSwitchOff(){
        super.getDevice().setState(OFF_STATE);
    }

    public void turnSwitchToggle(){
        super.getDevice().setState(TOGGLE_STATE);
    }

    public abstract void onAction();
    public abstract void offAction();
}
