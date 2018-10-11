package com.domoticz.services.components;

import com.domoticz.model.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public abstract class DomoticzComponent {
    @Autowired Map<Integer, Device> receivedDevices;

    private Device device;
    private static final String DOMOTICZ_BASE_URL = "http://192.168.1.31:8080/json.htm";
    private static final Logger logger = LogManager.getLogger(DomoticzComponent.class);

    public DomoticzComponent(){
        device = new Device();
        device.setIdx(getIdx());
    }

    private String mapToStringUrl(Map<String, String> map){
        String url = new String();
        Boolean firstElement = true;

        for (Map.Entry<String, String> entry: map.entrySet()){
            url = url + (firstElement ? "?" : "&");
            url = url + entry.getKey() + "=" + entry.getValue();
            firstElement = false;
        }

        return url;
    }

    private String buildDomoticzUrl(){
        Map<String, String> domoticzUrlMap = new LinkedHashMap<>();
        if (device == null){
            throw new RuntimeException("Device state is not set!");
        }
        domoticzUrlMap.putAll(deviceToDomoticzMapForURL(device));

        return DOMOTICZ_BASE_URL + mapToStringUrl(domoticzUrlMap);
    }

    public abstract Integer getIdx();
    public abstract Map<String, String> deviceToDomoticzMapForURL(Device device);

    public abstract void executeAction(Device device);

    public void sendDeviceToDomoticzServer(){
        RestTemplate restTemplate = new RestTemplate();
        logger.debug(buildDomoticzUrl());
        logger.debug(restTemplate.getForObject(buildDomoticzUrl(), String.class));
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Device getDevice() {
        return this.device;
    }

    public Map<Integer, Device> getReceivedDevices() {
        return receivedDevices;
    }
}
