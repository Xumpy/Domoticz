return {
    on = {
        devices = { '*' }
    },
    execute = function(domoticz, device)
        domoticz.log('Send device idx ' .. device.idx .. ' to backend', domoticz.LOG_INFO)
        domoticz.openURL({
                 url = 'http://localhost:8888/domoticz?idx' .. device.idx,
                 method = 'POST',
                 postData = {
                        idx = device.idx,
                        name = device.name,
                        deviceType = device.deviceType,
                        deviceSubType = device.deviceSubType,
                        state = device.state,
                        changed = device.changed,
                        batteryLevel = device.batteryLevel,
                        rawData = device.rawData
                }
        })
    end
}

