package com.domoticz.services;

import com.domoticz.model.Device;
import com.domoticz.services.components.DomoticzComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DomoticzCall {
    private static final Logger logger = LogManager.getLogger(DomoticzComponent.class);
    private static final String DOMOTICZ_BASE_URL = "http://192.168.1.31:8080/json.htm";
    private static final String USERNAME = "xumpy";
    private static final String PASSWORD = "Pc@t3900!";

    public void makeCall(String domoticzUrl){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(USERNAME, PASSWORD));
        System.out.println(domoticzUrl);
        logger.debug(DOMOTICZ_BASE_URL + domoticzUrl);
        logger.debug(restTemplate.getForObject(DOMOTICZ_BASE_URL + domoticzUrl, String.class));
    }
}
