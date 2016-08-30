package com.platform.iot.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.platform.iot.model.Device;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
public interface DeviceRepository extends CrudRepository<Device, Long> {
}
