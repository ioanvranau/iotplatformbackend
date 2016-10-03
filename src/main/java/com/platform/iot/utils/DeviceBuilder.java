package com.platform.iot.utils;

import java.util.List;
import java.util.Set;
import com.platform.iot.model.AccessRight;
import com.platform.iot.model.Device;
import com.platform.iot.model.Location;
import com.platform.iot.model.Sensor;
import com.platform.iot.model.Tag;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
public class DeviceBuilder {

    public static Device build(String id, String ip, String name, Location location, Set<AccessRight> accessRights, List<Tag> tags, List<Sensor> sensors) {
        return new Device(id, name, "phone", location, ip, accessRights, tags, sensors);
    }
}
