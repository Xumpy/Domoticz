package com.domoticz.model;

import java.util.Map;

/**
 * Created by nico on 06.10.18.
 */
public class Device {
    private Integer idx;
    private String name;
    private String deviceType;
    private String deviceSubType;
    private String state;
    private Boolean changed;
    private Number batteryLevel;
    private String[] rawData;

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceSubType() {
        return deviceSubType;
    }

    public void setDeviceSubType(String deviceSubType) {
        this.deviceSubType = deviceSubType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getChanged() {
        return changed;
    }

    public void setChanged(Boolean changed) {
        this.changed = changed;
    }

    public Number getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Number batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String[] getRawData() {
        return rawData;
    }

    public void setRawData(String[] rawData) {
        this.rawData = rawData;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Device){
            Device device = (Device) object;
            if (idx.equals(device.getIdx()) &&
                    name.equals(device.getName()) &&
                    deviceType.equals(device.getDeviceType()) &&
                    deviceSubType.equals(device.getDeviceSubType()) &&
                    state.equals(device.getState()))
                return true;
        }

        return false;
    }
}
