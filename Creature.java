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
    private int valueResult;
    private int timeResult;
    private Point gravity;
    private Point origin;
    private World world;

    public Creature(TurtleFrame turtleFrame, Pivot pivot, int limbs, Point gravity, World world){
        for(int i = 0; i < limbs; i++){
            this.limbs.add(new Limb(turtleFrame, pivot.getLocation(), world.x2));
        }
        this.pivot = pivot;
        this.origin = new Point(pivot);
        this.gravity = gravity;
        this.world = world;
        this.valueResult = 0;
        this.timeResult = Integer.MAX_VALUE;
    }

    public Creature(TurtleFrame turtleFrame, Pivot pivot, Point gravity, World world){
        this(turtleFrame, pivot, defaultLimbs, gravity, world);
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

        valueResult = pivot.x;

        for (Limb limb : limbs) {
            if (limb.getEndPoint().x > valueResult) {
                valueResult = limb.getEndPoint().x;
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
                Point tempDelta = new Point(limb.linearCollision(delta));
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
