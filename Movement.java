package com.company;


public class Movement {
    final double amplitude;
    final double frequency;
    final double initPhase;
    private int time = 0;

    Movement (double amplitude, double frequency, double initPhase) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.initPhase = initPhase;
    }

    public double project (double currentValue) {
        return currentValue + amplitude * Math.sin(frequency * (time + 1));
    }

    public double update (double currentValue) {
        time++;
        return project(currentValue);
    }

    public int getTime() {
        return time;
    }
}
