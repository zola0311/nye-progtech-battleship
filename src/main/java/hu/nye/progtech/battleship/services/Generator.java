package hu.nye.progtech.battleship.services;

import java.util.Random;

/** Random character and integer generator class. */
public class Generator {

    /** Generate a random character between A-J. */
    public static char generateCharacter() {
        Random r = new Random();
        return (char) (r.nextInt(10) + 'A');
    }

    /** Generate a random integer between 0-9. */
    public static int generateInteger() {
        int min = 0;
        int max = 9;
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }
}
