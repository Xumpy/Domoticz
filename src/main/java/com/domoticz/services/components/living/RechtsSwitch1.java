package com.domoticz.services.components.living;

import com.domoticz.services.components.SwitchComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RechtsSwitch1 extends SwitchComponent{
    @Autowired LinksSwitch2 linksSwitch2;

    @Override
    public Integer getIdx() {
        return 81;
    }

    @Override
    public void onAction() {
        linksSwitch2.turnSwitchOn();
    }

    @Override
    public void offAction() {
        linksSwitch2.turnSwitchOff();
    }
}
