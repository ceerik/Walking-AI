package com.company;

import ch.aplu.turtle.*;

import java.awt.*;
import java.util.ArrayList;

public class Creature implements Runnable {
    private Pivot pivot;
    private ArrayList<Limb> limbs;
    private Limb anchoredLimb = null;
    private static final int defaultLimbs = 2;
    private int moves = 100;
    private boolean done = false;
    private int result;
    private Point gravity;
    private Point origin;

    public Creature(TurtleFrame turtleFrame, Pivot pivot, int limbs, Point gravity){
        for(int i = 0; i < limbs; i++){
            this.limbs.add(new Limb(turtleFrame, pivot.getLocation()));
        }
        this.pivot = pivot;
        this.origin = new Point(pivot);
        this.gravity = gravity;
    }

    public Creature(TurtleFrame turtleFrame, Pivot pivot, Point gravity){
        this(turtleFrame, pivot, defaultLimbs, gravity);
    }

    @Override
    public void run() {
        fallByGravity();

        while (moves > 0) {
            ArrayList<Limb> tempLimbs = new ArrayList<>(limbs);
            tempLimbs.remove(anchoredLimbMove());
            recursiveLimbMoves(tempLimbs);
            moves--;
        }

        result = pivot.x;

        for (Limb limb : limbs) {
            if (limb.getEndPoint().x > result) {
                result = limb.getEndPoint().x;
            }
        }

        done = true;
    }

    private void recursiveLimbMoves(ArrayList<Limb> tempLimbs) {
        Limb tempLimb = tempLimbs.get(0);
        if(!tempLimb.isAnchored()) {
            tempLimb.move();
            if (tempLimb.getEndPoint().y == anchoredLimb.getEndPoint().y &&
                tempLimb.getEndPoint().x > anchoredLimb.getEndPoint().x) {
                changeAnchor(tempLimb);
            }
        }

        tempLimbs.remove(tempLimb);

        if(tempLimbs.size() > 0) {
            recursiveLimbMoves(tempLimbs);
        }
    }

    private Limb anchoredLimbMove(){
        Point delta = anchoredLimb.deltaProject();
        Limb collider = anchoredLimb;
        Point maxDelta = delta;

        for (Limb limb : limbs) {
            if (!limb.isAnchored()) {
                Point tempDelta = new Point(limb.collision(delta));
                if (tempDelta.y < maxDelta.y) {
                    maxDelta = tempDelta;
                    collider = limb;
                }
            }
        }

        movePivot(Pivot.invert(maxDelta));

        Limb oldAnchoredLimb = anchoredLimb;

        if(collider.getEndPoint().x > anchoredLimb.getEndPoint().x) {
            changeAnchor(collider);
        }

        return oldAnchoredLimb;
    }

    public void movePivot(Point delta){
        pivot.pointMove(delta);
        for (Limb limb : limbs){
            limb.deltaMove(delta);
        }
    }

    private void fallByGravity() {
        while(true){}
    }

    private void changeAnchor (Limb newAnchor) {
        anchoredLimb.setAnchor(false);
        anchoredLimb = newAnchor;
        anchoredLimb.setAnchor(true);
    }
}
