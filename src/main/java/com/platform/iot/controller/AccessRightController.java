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
import com.platform.iot.model.AccessRight;
import com.platform.iot.service.AccessRightsService;

@RestController
public class AccessRightController {

    private Logger log = Logger.getLogger(AccessRightController.class);

    @Autowired
    private AccessRightsService accessRightsService;

    @RequestMapping("/accessRight")
    public
    @ResponseBody
    List<AccessRight> getAllAccessRights() {
        return accessRightsService.getAllAccessRights();
    }

    @RequestMapping(value = "/accessRight", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<AccessRight> addAccessRight(@RequestBody AccessRight accessRight) throws UnknownHostException {
        if (accessRight != null) {
            log.info(accessRight);
            AccessRight addedAccessRight;
            addedAccessRight = accessRightsService.addAccessRight(accessRight);
            return new ResponseEntity<AccessRight>(addedAccessRight, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccessRight>(new AccessRight("No name provided for access right"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/accessRight", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<AccessRight> deleteAccessRight(@RequestParam("id") long id) {
        log.info(id);
        accessRightsService.deleteAccessRight(id);
        AccessRight accessRight = new AccessRight();
        accessRight.setId(id);
        return new ResponseEntity<AccessRight>(accessRight, HttpStatus.OK);
    }
}