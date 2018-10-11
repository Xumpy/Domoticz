package com.domoticz.services.components.living;

import com.domoticz.services.components.SwitchComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LinksSwitch1 extends SwitchComponent{
    @Autowired RechtsSwitch2 rechtsSwitch2;

    @Override
    public Integer getIdx() {
        return 79;
    }

    @Override
    public void onAction() {
        rechtsSwitch2.turnSwitchOn();
        rechtsSwitch2.sendDeviceToDomoticzServer();
    }

    @Override
    public void offAction() {
        rechtsSwitch2.turnSwitchOff();
        rechtsSwitch2.sendDeviceToDomoticzServer();
    }
}
