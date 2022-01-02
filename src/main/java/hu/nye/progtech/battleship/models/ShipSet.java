package hu.nye.progtech.battleship.models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/** Implement Shipset. */
public class ShipSet implements Iterable<Ship> {

    private Set<Ship> shipSet;

    public ShipSet() {
        this.shipSet = new HashSet<>();
    }

    /** Get the information about the selected ship is at the point. */
    public boolean shipIsPresentAt(char x, char y) {
        for (var ship : shipSet) {
            if (ship.isAtPosition(x, y)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.shipSet.size();
    }

    public boolean add(Ship ship) {
        return this.shipSet.add(ship);
    }

    public boolean contains(Ship ship) {
        return this.shipSet.contains(ship);
    }

    /** Remove ship method. */
    public boolean remove(char x, char y) {
        Ship removedShip = null;
        for (var ship : this.shipSet) {
            if (ship.isAtPosition(x, y)) {
                removedShip = ship;
            }
        }
        return removedShip != null && this.shipSet.remove(removedShip);
    }

    @Override
    public Iterator<Ship> iterator() {
        return this.shipSet.iterator();
    }
}
