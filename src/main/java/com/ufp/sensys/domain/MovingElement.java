package com.ufp.sensys.domain;

public class MovingElement extends Element {
    private double direction;
    private double speed;

    public MovingElement() {
        super("moving");
    }

    public MovingElement(float latitude, float longitude) {
        this();
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public double getDirection() {
        return this.direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}