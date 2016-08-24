package com.platform.iot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import com.platform.iot.dao.AccessRightRepository;
import com.platform.iot.model.AccessRight;
import com.platform.iot.utils.IotException;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
@Component
public class AccessRightsService {

    @Autowired
    AccessRightRepository accessRightRepository;

    public List<AccessRight> getAllAccessRights() {
        return Lists.newArrayList(accessRightRepository.findAll());
    }

    public AccessRight getAccessRight(Long id) {
        return accessRightRepository.findOne(id);
    }

    public AccessRight getAccessRightByName(String name) {
        return accessRightRepository.findByName(name);
    }

    public AccessRight addAccessRight(AccessRight accessRight) {
        if (accessRight != null) {
            return accessRightRepository.save(accessRight);
        } else {
            throw new IotException("No accessRight provided!");
        }
    }

    public void deleteAccessRight(long accessRightId) {
        AccessRight accessRight = accessRightRepository.findOne(accessRightId);
        if (accessRight != null) {
            accessRightRepository.delete(accessRightId);
        } else {
            throw new IotException("No accessRight provided!");
        }
    }
}
