package com.company;

import java.awt.*;

public class Pivot extends Point{
    private Point location;
    private double angle;
    private static final Point defaultLocation = new Point(0,0);
    private static final double defaultAngle = 180;

    public Pivot () {
        this(defaultLocation, defaultAngle);
    }

    public Pivot (Point location){
        this(location, defaultAngle);

    }

    public Pivot (Point location, double angle) {
        this.location.x = location.x;
        this.location.y = location.y;
        this.angle = angle;
    }

    public void setLocation(Point location) {
        super.setLocation(location);
    }

    public Point getLocation() {
        return super.getLocation();
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public Pivot copy(){
        Pivot newPivot = new Pivot();
        newPivot.setAngle(this.getAngle());
        newPivot.setLocation(this.getLocation());
        return newPivot;
    }

    public void pointMove(Point deltaPoint){
        location.x += deltaPoint.x;
        location.y += deltaPoint.y;
    }

    public static Point invert(Point point) {
        return new Point(-point.x, -point.y);
    }
}
