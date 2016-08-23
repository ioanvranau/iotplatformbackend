package com.platform.iot.dao;

import org.springframework.data.repository.CrudRepository;
import com.platform.iot.model.Sensor;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
public interface SensorRepository extends CrudRepository<Sensor, Long> {
}
