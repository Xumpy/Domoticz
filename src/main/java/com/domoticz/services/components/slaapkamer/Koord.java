package com.domoticz.services.components.slaapkamer;

import com.domoticz.services.components.SwitchComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Koord extends SwitchComponent{
    @Autowired Knop1 knop1;

    @Override
    public Integer getIdx() {
        return 83;
    }

    @Override
    public void onAction() {
        knop1.turnSwitchToggle();
        knop1.sendDeviceToDomoticzServer();
    }

    @Override
    public void offAction() {
        knop1.turnSwitchToggle();
        knop1.sendDeviceToDomoticzServer();
    }
}
