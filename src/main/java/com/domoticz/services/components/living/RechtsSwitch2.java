package com.domoticz.services.components.living;

import com.domoticz.services.components.SwitchComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RechtsSwitch2 extends SwitchComponent{
    @Autowired LinksSwitch1 linksSwitch1;

    @Override
    public Integer getIdx() {
        return 82;
    }

    @Override
    public void onAction() {
        linksSwitch1.turnSwitchOn();
        linksSwitch1.sendDeviceToDomoticzServer();
    }

    @Override
    public void offAction() {
        linksSwitch1.turnSwitchOff();
        linksSwitch1.sendDeviceToDomoticzServer();
    }
}
