package com.domoticz;

import com.domoticz.model.Device;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MappingConfig {
    @Bean
    public Map<Integer, Device> receivedDevices(){
        return new HashMap<>();
    }
}
