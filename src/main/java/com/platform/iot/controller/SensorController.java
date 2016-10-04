package com.platform.iot.controller;

/**
 * Created by ioan.vranau on 1/4/2016.
 */

import java.net.UnknownHostException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.platform.iot.model.Device;
import com.platform.iot.model.Sensor;
import com.platform.iot.service.DeviceService;
import com.platform.iot.service.SensorService;

@RestController
public class SensorController {

    private Logger log = Logger.getLogger(SensorController.class);

    @Autowired
    private SensorService sensorsService;

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/sensor")
    public
    @ResponseBody
    List<Sensor> getAllSensors() {
        return sensorsService.getAllSensors();
    }

    @RequestMapping(value = "/sensor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<Sensor> addSensor(@RequestBody Sensor sensor) throws UnknownHostException {
        if (sensor != null) {
            log.info(sensor);
            final String deviceId = sensor.getDeviceId();
            if(deviceId == null) {
                return new ResponseEntity<Sensor>(new Sensor("No device selected for sensor!", null, null), HttpStatus.BAD_REQUEST);
            }

            sensorsService.addSensor(sensor);

            deviceService.updateDevice(deviceId, sensor);

            return new ResponseEntity<Sensor>(sensor, HttpStatus.OK);
        } else {
            return new ResponseEntity<Sensor>(new Sensor("No name provided for sensor", null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/sensor", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<Sensor> deleteSensor(@RequestParam("id") long id) {
        log.info(id);
        sensorsService.deleteSensor(id);
        Sensor sensor = new Sensor();
        sensor.setId(id);
        return new ResponseEntity<Sensor>(sensor, HttpStatus.OK);
    }
}