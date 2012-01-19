package com.ufp.sensys.domain;

public class StationaryElement extends Element {
    private String description;
    
    public StationaryElement() {
        super("stationary");
    }

    public StationaryElement(float latitude, float longitude) {
        this();
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * {@inheritDoc}
     */
    public String toString() {
        final String variableSeparator = ", ";
        final StringBuffer sb = new StringBuffer();

        sb.append(super.toString());
        sb.append(variableSeparator);
        sb.append("description=").append(description);
        return sb.toString();
    }
}
