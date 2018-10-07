package com.domoticz.services;

import com.domoticz.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DomoticzCall {
    @Autowired Map<Integer, Device> receivedDevices;
    private static String domoticzHome="http://192.168.1.31:8080/json.htm?type=command&";

    private void makeDomoticzCall(String url){
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(url);
        System.out.println(restTemplate.getForObject(url, String.class));
    }

    public void makeCallSwitch(Integer idx, String state){
        if (!(receivedDevices.containsKey(idx) && receivedDevices.get(idx).getState().equals(state))){
            makeDomoticzCall(domoticzHome + "param=switchlight&idx=" + idx + "&switchcmd=" + state);
        } else {
            System.out.println("State not send cause of the same state exists in the log");
        }
    }
}
