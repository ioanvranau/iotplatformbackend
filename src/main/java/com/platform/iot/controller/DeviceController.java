package com.platform.iot.controller;

/**
 * Created by ioan.vranau on 1/4/2016.
 */

import java.net.UnknownHostException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.platform.iot.model.Device;
import com.platform.iot.service.DeviceService;
import com.platform.iot.utils.DeviceBuilder;

@RestController
public class DeviceController {

    private Logger log = Logger.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/devices")
    public
    @ResponseBody
    List<Device> devices(@RequestParam(value = "name", defaultValue = "World") String name) {
        return deviceService.getAllDevices();
    }

    @RequestMapping(value = "/devices", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<Device> addDevice(@RequestBody Device device) throws UnknownHostException {
        //
        // Code processing the input parameters
        //
        if (device != null) {
            log.info(device);
            Device addedDevice;
            try {
                addedDevice = deviceService.addDevice(device);
            } catch (UnknownHostException e) {
                throw e;
            }
            return new ResponseEntity<Device>(addedDevice, HttpStatus.OK);
        } else {
            return new ResponseEntity<Device>(DeviceBuilder.build("no ip provided", "no name provided", null, null, null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/devices", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<Device> deleteDevice(@RequestParam("id") long id) {
        //
        // Code processing the input parameters
        //
        log.info(id);
        deviceService.deleteDevice(id);
        Device device = new Device();
        device.setId(id);
        return new ResponseEntity<Device>(device, HttpStatus.OK);
    }
}