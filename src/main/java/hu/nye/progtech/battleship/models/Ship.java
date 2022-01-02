package hu.nye.progtech.battleship.models;

import java.util.Arrays;

/** Implement ship object. */
public class Ship {

    private char[] position;

    public Ship(char x, char y) {
        this.position = new char[] {x, y};
    }

    public char[] getPosition() {
        return this.position;
    }

    public boolean isAtPosition(char x, char y) {
        return this.position[0] == x && this.position[1] == y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        return Arrays.equals(position, ship.position);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(position);
    }

}
