package com.domoticz;


import com.domoticz.controller.DomoticzCtrl;
import com.domoticz.model.Device;
import com.domoticz.services.DomoticzCall;
import com.domoticz.services.components.DomoticzComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DomoticzCtrlTest {
    @Autowired DomoticzCtrl domoticzCtrl;
    @Autowired List<? extends DomoticzComponent> domoticzComponents;

    @MockBean
    private DomoticzCall domoticzCall;

    private Device dummyDevice(Integer idx, String state){
        Device device = new Device();
        device.setIdx(idx);
        device.setState(state);
        device.setName("Test");
        device.setDeviceType("Test");
        device.setDeviceSubType("Test");
        return device;
    }

    private void mockDomoticzCall(Integer idx, String switchCmd){
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                domoticzCtrl.enterDomoticzBackend(dummyDevice(idx, switchCmd));
                return null;
            }
        }).when(domoticzCall).makeCall("?type=command&param=switchlight&idx=" + idx + "&switchcmd=" + switchCmd);
    }

    private void mockAllDomoticzCalls(){
        for (DomoticzComponent domoticzComponent: domoticzComponents){
            mockDomoticzCall(domoticzComponent.getIdx(), "On");
            mockDomoticzCall(domoticzComponent.getIdx(), "Off");
        }
    }

    private void assertEnterDomoticz(Integer idx, String switchCmd){
        new Thread() {
            @Override
            public void run(){
                assertThat(domoticzCtrl.enterDomoticzBackend(dummyDevice(idx, switchCmd))).isEqualTo("201");
            }
        }.start();
    }

    @Test
    public void testDeviceSend(){
        mockAllDomoticzCalls();
        assertEnterDomoticz(78, "On");
        assertEnterDomoticz(78, "Off");
        assertEnterDomoticz(78, "On");
        assertEnterDomoticz(78, "Off");
        assertEnterDomoticz(78, "On");
        assertEnterDomoticz(78, "Off");

    }
}
