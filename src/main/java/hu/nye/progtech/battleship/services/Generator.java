package hu.nye.progtech.battleship.services;

/** Random integer generator class. */
public class Generator {

    /** Generate a random integer between 0-9. */
    public static int generateInteger() {
        int min = 0;
        int max = 9;
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }
}
