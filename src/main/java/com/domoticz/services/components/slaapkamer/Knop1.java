package com.domoticz.services.components.slaapkamer;

import com.domoticz.services.components.SwitchComponent;
import org.springframework.stereotype.Component;

@Component
public class Knop1 extends SwitchComponent{
    @Override
    public Integer getIdx() {
        return 85;
    }

    @Override
    public void onAction() {

    }

    @Override
    public void offAction() {

    }
}
