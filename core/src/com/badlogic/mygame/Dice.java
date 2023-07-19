package com.badlogic.mygame;

import java.util.Random;

public class Dice {
    private final int numberOfFaces;

    public Dice(int numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

    public int rollTheDice() {
        return new Random().nextInt(1, numberOfFaces);
    }
}
