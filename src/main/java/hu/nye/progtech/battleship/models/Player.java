package hu.nye.progtech.battleship.models;

import java.util.Set;

/** Implement abstract Player class. */
public abstract class Player {

    private int shipsRemaining = 5;
    protected ShipSet ships;
    protected Set<Integer[]> guesses;

    public abstract Integer[] guess();

    public abstract void deployShips();

    public int getShipsRemaining() {
        return this.shipsRemaining;
    }

    public ShipSet getShips() {
        return this.ships;
    }

    public void sinkShip(int x, int y) {
        this.shipsRemaining -= 1;
        this.ships.remove(x, y);
    }
}
