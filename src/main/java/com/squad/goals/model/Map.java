package com.squad.goals.model;

public class Map {
    private String name;
    private double corner0x;
    private double corner0y;
    private double corner1x;
    private double corner1y;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCorner0x() {
        return corner0x;
    }

    public void setCorner0x(double corner0x) {
        this.corner0x = corner0x;
    }

    public double getCorner0y() {
        return corner0y;
    }

    public void setCorner0y(double corner0y) {
        this.corner0y = corner0y;
    }

    public double getCorner1x() {
        return corner1x;
    }

    public void setCorner1x(double corner1x) {
        this.corner1x = corner1x;
    }

    public double getCorner1y() {
        return corner1y;
    }

    public void setCorner1y(double corner1y) {
        this.corner1y = corner1y;
    }
}
