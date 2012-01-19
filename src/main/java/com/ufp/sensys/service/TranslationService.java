package com.ufp.sensys.service;

import java.util.Properties;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;

import org.springframework.beans.factory.annotation.Required;

import com.ufp.sensys.domain.Element;
import com.ufp.sensys.domain.MovingElement;

public class TranslationService {
    private Properties vehicleProperties;
    private Properties sensorProperties;
    private static  DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-DD'T'HH:mm:ss.SSS'Z'");

    /**
     * the lines we expect look like:
     * timestamp,vehicleId,sensorId
     */
    public Element getTranslatedElement(String line) {
        String [] parts = line.split(",");

        // handle sensor location
        String sensorLocation = sensorProperties.getProperty(parts[2]);
        String [] sParts = sensorLocation.split(",");
        MovingElement movingElement = new MovingElement(new Float(sParts[0]), new Float(sParts[1]));
        movingElement.setPlacedAt(dateTimeFormatter.parseDateTime(parts[0]).toInstant());
        movingElement.setId(parts[1]);
        movingElement.setName(vehicleProperties.getProperty(parts[1]));
        return movingElement;
    }

    @Required
    public void setVehicleProperties(Properties vehicleProperties) {
        this.vehicleProperties = vehicleProperties;
    }

    @Required
    public void setSensorProperties(Properties sensorProperties) {
        this.sensorProperties = sensorProperties;
    }
}