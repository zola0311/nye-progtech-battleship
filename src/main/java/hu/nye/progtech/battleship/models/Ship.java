package hu.nye.progtech.battleship.models;

import java.util.Arrays;

/** Implement ship object. */
public class Ship {

    private int[] position;

    public Ship(int x, int y) {
        this.position = new int[] {x, y};
    }

    public int[] getPosition() {
        return this.position;
    }

    public boolean isAtPosition(int x, int y) {
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
