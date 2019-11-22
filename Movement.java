package com.company;


public class Movement {
    final double amplitude;
    final double frequency;
    final double phase;
    private double currentValue;

    Movement (double amplitude, double frequency, double phase) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.phase = currentValue = phase;
    }

    public double update () {
        currentValue = Math.sin((currentValue + frequency * amplitude) % 360);
        return currentValue;
    }

    public double getCurrentValue () {
        return currentValue;
    }

}
