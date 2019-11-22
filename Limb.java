package com.company;

import ch.aplu.turtle.*;
import java.awt.*;

public class Limb implements Pivotable {
    private int length = 50;
    double angle = 180; // The turtle originally faced north by default, this offset is used to make it face south by default.
    private Movement movement;
    private Turtle turtle;
    private Point originPoint;
    private Point endPoint;
    private boolean anchored = false;
    final int goal;

    public Limb (TurtleFrame turtleFrame, Point originPoint, int goal){
        this.turtle = new Turtle(turtleFrame, false);
        this.originPoint = originPoint;
        this.goal = goal;
    }

    public void move() {

    }

    //Moves the entire limb (both origin and end) by a given delta.
    public void deltaMove(Point delta){
        originPoint.x += delta.x;
        originPoint.y += delta.y;
        endPoint.x += delta.x;
        endPoint.y += delta.y;
    }

    //Returns the maximum delta before the linear movement (simple delta x and y, rather than using the angle and length of the limb)
    // from endPoint to expectedMovePoint results in a collision with the x-axis or the goal,
    // returns the expectedMovePoint if there was no collision limiting the movement.
    public Point linearCollision (Point expectedMoveEndPoint) {
        if ((expectedMoveEndPoint.x > 0) && (expectedMoveEndPoint.y < goal)) {
            return expectedMoveEndPoint;
        }

        Point tempDeltaPoint = deltaProject();
        double angle = tempDeltaPoint.x / tempDeltaPoint.y;
        Point interruptedEndPoint = new Point(expectedMoveEndPoint);

        if (expectedMoveEndPoint.x < 0 ) {

        }

        if (expectedMoveEndPoint.y > goal) {

        }

        return interruptedEndPoint;
    }

    //As linearCollision, although with the interruptedEndPoint calculated using a sinus function and limb length rather than delta x and y.
    public Point curvedCollision (Point expectedMoveEndPoint) {
        Point interruptedEndPoint = null;

        return interruptedEndPoint;
    }

    public void setOriginPoint (Point originPoint) {
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

    //This method "projects" the next move of the limb, and returns the expected movement.
    public Point deltaProject() {
        Point returnPoint = new Point();
        Point tempPoint = new Point(project());
        returnPoint.x = tempPoint.x - endPoint.x;
        returnPoint.y = tempPoint.y - endPoint.y;
        return returnPoint;
    }

    //This method projects the next move of the limb, and returns the expected endPoint;
    public Point project() {
        Point tempPoint = new Point();
        double newAngle = movement.project(angle);
        tempPoint.x = (int)(length * Math.sin(newAngle));
        tempPoint.y = (int)(length * Math.cos(newAngle));
        return tempPoint;
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
