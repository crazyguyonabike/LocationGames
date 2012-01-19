package com.ufp.sensys.domain;

import org.joda.time.Instant;
import java.util.UUID;

public class Element implements Comparable<Element> {
    private String type;
    private String name;
    private String id;
    private float latitude, longitude;
    private Instant placedAt;
    
    public Element(String type) {
        this.type = type;
        id = UUID.randomUUID().toString();
    }
    
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getPlacedAt() {
        return this.placedAt;
    }

    public void setPlacedAt(Instant placedAt) {
        this.placedAt = placedAt;
    }

    public int compareTo(Element that) {
        return this.getId().compareTo(that.getId());
    }

    public boolean equals(Element that) {
        return this.getId().equals(that.getId());
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        final String variableSeparator = ", ";
        final StringBuffer sb = new StringBuffer();

        sb.append("latitude=").append(latitude);
        sb.append(variableSeparator);
        sb.append("longitude=").append(longitude);
        sb.append(variableSeparator);
        sb.append("type=").append(type);
        sb.append(variableSeparator);
        sb.append("name=").append(name);
        sb.append(variableSeparator);
        sb.append("id=").append(id);
        sb.append(variableSeparator);
        sb.append("placedAt=").append(placedAt);

        return sb.toString();
    }

}
