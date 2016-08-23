package com.platform.iot.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by ioan.vranau on 8/22/2016.
 */
@Entity
public class Sensor {

    private long id;
    private String name;
    private String type;
    private List<Metadata> metadata;

    public Sensor(String name, String type, List<Metadata> metadata) {
        this.name = name;
        this.type = type;
        this.metadata = metadata;
    }

    public Sensor() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Metadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        final String sensor = "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
        StringBuilder meta = new StringBuilder();
        for (Metadata metadata1 : metadata) {
            meta.append(metadata1);
        }
        return sensor + meta;
    }
}
