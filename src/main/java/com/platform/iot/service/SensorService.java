package com.platform.iot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import com.platform.iot.dao.SensorRepository;
import com.platform.iot.model.Sensor;
import com.platform.iot.utils.IotException;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
@Component
public class SensorService {

    @Autowired
    SensorRepository sensorRepository;

    public List<Sensor> getAllSensors() {
        return Lists.newArrayList(sensorRepository.findAll());
    }

    public Sensor getSensor(Long id) {
        return sensorRepository.findOne(id);
    }

    public Sensor getSensorByName(String name) {
        return sensorRepository.findByName(name);
    }

    public Sensor addSensor(Sensor sensor) {
        if (sensor != null) {
            return sensorRepository.save(sensor);
        } else {
            throw new IotException("No sensor provided!");
        }
    }

    public void deleteSensor(long sensorId) {
        Sensor sensor = sensorRepository.findOne(sensorId);
        if (sensor != null) {
            sensorRepository.delete(sensorId);
        } else {
            throw new IotException("No sensor provided!");
        }
    }
}
