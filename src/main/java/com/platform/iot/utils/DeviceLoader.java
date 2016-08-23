package com.platform.iot.utils;

/**
 * Created by ioan.vranau on 4/28/2016.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.platform.iot.dao.AccessRightRepository;
import com.platform.iot.dao.DeviceRepository;
import com.platform.iot.dao.LocationRepository;
import com.platform.iot.dao.SensorRepository;
import com.platform.iot.dao.TagRepository;
import com.platform.iot.model.AccessRight;
import com.platform.iot.model.Device;
import com.platform.iot.model.Location;
import com.platform.iot.model.Metadata;
import com.platform.iot.model.Sensor;
import com.platform.iot.model.Tag;

@Component
public class DeviceLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AccessRightRepository accessRightRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SensorRepository sensorRepository;


    private Logger log = Logger.getLogger(DeviceLoader.class);

    public void onApplicationEvent(ContextRefreshedEvent event) {

        Location location = new Location(46.757372, 23.585699);
        locationRepository.save(location);

        Set<AccessRight> accessRights = new HashSet<AccessRight>();
        accessRights.add(new AccessRight("public"));
        accessRightRepository.save(accessRights);

        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag("mobile"));
        tags.add(new Tag("android"));
        tags.add(new Tag("smartphone"));
        tagRepository.save(tags);

        List<Metadata> sensorMetadataList = new ArrayList<Metadata>();
        sensorMetadataList.add(new Metadata("metadataName", "metadataValue"));
        sensorMetadataList.add(new Metadata("metadataName1", "metadataValue1"));


        List<Sensor> sensors = new ArrayList<Sensor>();
        sensors.add(new Sensor("accelerometer", "speed", sensorMetadataList));
        sensorRepository.save(sensors);


        Device device1 = DeviceBuilder.build("localhost", "My phone", location, accessRights, tags, sensors);
        log.info("--------- " + device1);
        deviceRepository.save(device1);

        log.info("Saved device - name: " + device1.getName());

        Device device2 = DeviceBuilder.build("127.0.0.1", "My new phone", location, accessRights, tags, sensors);
        log.info("--------- " + device2);
        deviceRepository.save(device2);
        log.info("Saved device - name: " + device2.getName());
    }
}