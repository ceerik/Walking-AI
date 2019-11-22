package com.company;

import ch.aplu.turtle.TurtleFrame;
import java.awt.*;
import java.util.ArrayList;

public class World implements Runnable {
    private ArrayList<Creature> inhabitants;
    private Point gravity;
    private TurtleFrame turtleFrame;
    private static final int defaultPopulation = 100;
    private static final Point defaultGravity = new Point(0,-1);
    final Point origin = new Point(0,0);
    static final int x1 = -500;
    static final int x2 = 500;
    static final int y1 = -200;
    static final int y2 = 200;

    public World(int population, Point gravity) {
        turtleFrame = new TurtleFrame();
        Dimension tempDimension = turtleFrame.getPlayground().getSize();
        origin.x = - ((int)tempDimension.getWidth() / 2);
        origin.y = - ((int)tempDimension.getHeight() / 2);
        this.gravity = gravity;
        for(int i = 0; i < population; i++){
            inhabitants.add(new Creature(turtleFrame, new Pivot(origin), gravity, this));
        }
    }

    public World() {
        this(defaultPopulation, defaultGravity);
    }

    //TODO: Actually implement this method.
    @Override
    public void run() {

    }
}
