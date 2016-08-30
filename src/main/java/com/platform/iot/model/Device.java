package com.platform.iot.model;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Created by ioan.vranau on 12/15/2015.
 */

@Entity
public class Device {


    private long id;
    private String name;
    private String ip;
    private Location location;
    private String type;
    private Set<AccessRight> accessRights;
    private List<Tag> tags;
    private List<Sensor> sensors;

    public Device(String name, String type, Location location, String ip,
                  Set<AccessRight> accessRights, List<Tag> tags, List<Sensor> sensors) {
        this.name = name;
        this.ip = ip;
        this.type = type;
        this.location = location;
        this.accessRights = accessRights;
        this.tags = tags;
        this.sensors = sensors;
    }

    public Device() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @ManyToOne
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToMany
    public Set<AccessRight> getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(Set<AccessRight> accessRights) {
        this.accessRights = accessRights;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @ManyToMany
    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public String toString() {
        final String device = "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", location=" + location +
                ", type='" + type + '\'' +
                '}';
        StringBuilder accessR = new StringBuilder();
        if (accessRights != null) {
            for (AccessRight accessRight : accessRights) {
                accessR.append(accessRight);
            }
        }

        StringBuilder tags = new StringBuilder();
        if (this.tags != null) {
            for (Tag tag : this.tags) {
                tags.append(tag);
            }
        }
        StringBuilder sensors = new StringBuilder();
        if (this.sensors != null) {
            for (Sensor deviceSensor : this.sensors) {
                sensors.append(deviceSensor);
            }
        }
        return device + "  " + accessR + "  " + tags + " " + sensors;
    }
}
