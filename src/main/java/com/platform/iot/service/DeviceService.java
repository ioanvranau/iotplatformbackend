package com.platform.iot.service;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.google.common.collect.Lists;
import com.platform.iot.dao.DeviceRepository;
import com.platform.iot.model.AccessRight;
import com.platform.iot.model.Device;
import com.platform.iot.model.Location;
import com.platform.iot.model.Sensor;
import com.platform.iot.utils.IotException;
import static com.platform.iot.utils.DeviceUtils.randLong;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
@Component
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;
    private Logger log = Logger.getLogger(DeviceService.class);
    @Autowired
    private LocationService locationService;

    @Autowired
    private AccessRightsService accessRightsService;

    public List<Device> getAllDevices() {
        return Lists.newArrayList(deviceRepository.findAll());
    }

    public Device getDeviceById(String id) {
        return deviceRepository.findOne(id);
    }

    @Transactional
    public Device addDevice(Device device) {
        if (device != null) {
            String deviceId = device.getId();
            if (deviceId == null || StringUtils.isEmpty(deviceId.trim())) {
                final String newDeviceId = generateNewDeviceId(null);
                device.setId(newDeviceId);
            }

            if (device.getLocation() != null) {
                Location location = locationService.getLocation(device.getLocation().getId());
                if (location == null) {
                    locationService.addLocation(device.getLocation());
                }
            }

            final Set<AccessRight> accessRights = device.getAccessRights();
            final Set<AccessRight> validAccessRights = new HashSet<>();
            if (accessRights != null) {
                for (AccessRight accessRight : accessRights) {
                    AccessRight existingAccessRight = accessRightsService.getAccessRightByName(accessRight.getName());
                    if (existingAccessRight != null) {
                        validAccessRights.add(existingAccessRight);
                    }
                }
            }
            device.setAccessRights(validAccessRights);
            return deviceRepository.save(device);
        } else {
            throw new IotException("No device provided!");
        }
    }

    @Transactional
    public Device updateDevice(String deviceId, Sensor sensor) throws UnknownHostException {
        final Device deviceById = getDeviceById(deviceId);
        deviceById.getSensors().add(sensor);
        return deviceRepository.save(deviceById);
    }

    public void deleteDevice(String deviceId) {
        Device device = deviceRepository.findOne(deviceId);
        if (device != null) {
            deviceRepository.delete(deviceId);
        } else {
            throw new IotException("No device provided!");
        }
    }

    public String generateNewDeviceId(final String type) {
        String deviceType = type;
        if (StringUtils.isEmpty(type)) {
            deviceType = "IoTDevice";
        }
        String deviceId;

        long max = 10;
        long min = 0;
        deviceId = deviceType + randLong(min, max);
        Device databaseDevice = deviceRepository.findOne(deviceId);
        int i = 1;

        while (databaseDevice != null) {
            deviceId = deviceType + randLong(min, max);
            databaseDevice = deviceRepository.findOne(deviceId);
            log.info("Trying to generate a new device id: " + i++);
            if (i > max * 5) {
                if(max * 10 > Long.MAX_VALUE) {
                    return null;
                }
                min = max;
                max *= 10;
                i = 0;
            }
        }
        log.info("New device id generated: " + deviceId);

        return deviceId;
    }
}
