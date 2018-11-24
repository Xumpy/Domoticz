package com.domoticz.services.components;

import com.domoticz.model.Device;
import com.domoticz.services.DomoticzCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public abstract class DomoticzComponent {
    @Autowired Map<Integer, Device> receivedDevices;
    @Autowired DomoticzCall domoticzCall;

    private Boolean changed = false;
    private Device device;

    public DomoticzComponent(){
        device = new Device();
        device.setIdx(getIdx());
    }

    public Boolean getChanged() {
        return changed;
    }

    public void setChanged(Boolean changed) {
        this.changed = changed;
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

        return mapToStringUrl(domoticzUrlMap);
    }

    public abstract Integer getIdx();
    public abstract Map<String, String> deviceToDomoticzMapForURL(Device device);

    public abstract void executeAction(Device device);

    public void sendDeviceToDomoticzServer(){
        domoticzCall.makeCall(buildDomoticzUrl());
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
