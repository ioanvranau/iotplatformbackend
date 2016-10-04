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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.platform.iot.model.Device;
import com.platform.iot.model.ServerResponse;
import com.platform.iot.service.DeviceService;
import com.platform.iot.utils.DeviceBuilder;

@RestController
public class DeviceController {

    private Logger log = Logger.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/device")
    public
    @ResponseBody
    List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @RequestMapping(value = "/device", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<Device> addDevice(@RequestBody Device device) throws UnknownHostException {
        if (device != null) {
            log.info(device);
            Device addedDevice;
            addedDevice = deviceService.addDevice(device);
            return new ResponseEntity<>(addedDevice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(DeviceBuilder.build("", "No device provided", "No name provided", null, null, null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/device", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<Device> deleteDevice(@RequestParam("id") String id) {
        log.info(id);
        deviceService.deleteDevice(id);
        Device device = new Device();
        device.setId(id);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @RequestMapping(value = "/newDeviceId")
    public
    @ResponseBody
    ResponseEntity<ServerResponse> generateNewDeviceId(@RequestParam(value = "type", defaultValue = "") String type) {
        final String generateNewDeviceId = deviceService.generateNewDeviceId(type);
        if (StringUtils.isEmpty(generateNewDeviceId)) {
            return new ResponseEntity<>(new ServerResponse("Cannot generate id"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ServerResponse(generateNewDeviceId), HttpStatus.OK);
    }
}