package com.company;



import ch.aplu.turtle.*;

import java.awt.*;

public class Limb implements Pivotable {
    private int length = 50;
    double angle = 180;
    private Movement movement;
    private Turtle turtle;
    private Point originPoint;
    private Point endPoint;
    private boolean anchored = false;

    public Limb(TurtleFrame turtleFrame){
        this.turtle = new Turtle(turtleFrame, false);
    }

    public Limb (TurtleFrame turtleFrame, Point originPoint){
        this(turtleFrame);
        this.originPoint = originPoint;
    }

    public void move() {

    }

    public void deltaMove(Point delta){
        originPoint.x += delta.x;
        originPoint.y += delta.y;
        endPoint.x += delta.x;
        endPoint.y += delta.y;
    }

    public Point collision (Point expectedMoveEndPoint){
        Point interruptedEndPoint = null;

        return interruptedEndPoint;
    }

    public void setOriginPoint(Point originPoint) {
        this.originPoint = originPoint;
    }

    @Override
    public void pivots(double angle) {
        this.angle += angle;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public double getAngle() {
        return angle;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setAnchor(boolean anchored){
        this.anchored = anchored;
    }

    public boolean isAnchored() {
        return anchored;
    }

    //This method "projects" the next move of the limb, and returns the maximum possible movement within the range of the standard movement for this limb, as dictated by the limits in terms of collisions.
    public Point deltaProject() {
        return new Point();
    }

    public Point project() {
        return new Point();
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public Movement getMovement () {
        return movement;
    }

    //delta: expected movement
    //maxDelta: maximum allowable movement
    //return: remaining movement
    public static Point carryDelta(Point delta,Point maxDelta) {
        return new Point((delta.x - maxDelta.x), (delta.y - maxDelta.y));
    }

}
