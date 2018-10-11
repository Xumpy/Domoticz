package com.domoticz.services.components.living;

import com.domoticz.services.components.SwitchComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LinksSwitch2 extends SwitchComponent{
    @Autowired RechtsSwitch1 rechtsSwitch1;

    @Override
    public Integer getIdx() {
        return 78;
    }

    @Override
    public void onAction() {
        rechtsSwitch1.turnSwitchOn();
        rechtsSwitch1.sendDeviceToDomoticzServer();
    }

    @Override
    public void offAction() {
        rechtsSwitch1.turnSwitchOff();
        rechtsSwitch1.sendDeviceToDomoticzServer();
    }

}
