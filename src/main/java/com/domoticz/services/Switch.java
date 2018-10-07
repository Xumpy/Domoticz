package com.domoticz.services;

import com.domoticz.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Switch {
    @Autowired DomoticzCall domoticzCall;

    public void withLink(Device device, List<List<Integer>> mappedIdxList){
        for(List<Integer> mappedIdx: mappedIdxList){
            if (mappedIdx.contains(device.getIdx())){
                for(Integer idx: mappedIdx){
                    if (!idx.equals(device.getIdx())){
                        domoticzCall.makeCallSwitch(idx, device.getState());
                    }
                }
            }
        }
    }
}
