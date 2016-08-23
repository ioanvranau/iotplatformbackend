package com.platform.iot.service;

import java.net.UnknownHostException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.google.common.collect.Lists;
import com.platform.iot.dao.DeviceRepository;
import com.platform.iot.model.Device;
import com.platform.iot.utils.IotException;
import com.platform.iot.utils.IpValidator;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
@Component
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return Lists.newArrayList(deviceRepository.findAll());
    }

    public Device addDevice(Device device) throws UnknownHostException {
        if (device != null) {
            String deviceIp = device.getIp();
            if (deviceIp == null || StringUtils.isEmpty(deviceIp.trim())) {
                throw new IotException("No device ip provided!");
            }

            IpValidator.validate(deviceIp);

            return deviceRepository.save(device);
        } else {
            throw new IotException("No device provided!");
        }
    }

    public void deleteDevice(long deviceId) {
        Device device = deviceRepository.findOne(deviceId);
        if (device != null) {
            deviceRepository.delete(deviceId);
        } else {
            throw new IotException("No device provided!");
        }
    }
}
