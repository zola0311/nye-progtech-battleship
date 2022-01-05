package hu.nye.progtech.battleship.models;

import java.util.HashSet;
import hu.nye.progtech.battleship.services.Generator;

/** Implement the computer player. */
public class ComputerPlayer extends Player {

    public ComputerPlayer() {
        super();
        this.guesses = new HashSet<>();
    }

    /** Add ships to Computer player. */
    public void addShip(int x, int y) {
        this.ships = new ShipSet();
        var ship = new Ship(x, y);
        if (!this.ships.contains(ship)) {
            this.ships.add(ship);
        }
    }

    /** Add ships to Computer player. */
    public void addGuess(int x, int y) {
        Integer[] guess = new Integer[2];
        guess[0] = y;
        guess[1] = x;
        if (!this.guesses.contains(guess)) {
            this.guesses.add(guess);
        }
    }

    @Override
    public Integer[] guess() {
        System.out.println("A számítógép játékos felkészül a tüzelésre.");
        Integer[] guess = new Integer[2];
        while (true) {
            guess[0] = Generator.generateInteger();
            guess[1] = Generator.generateInteger();
            if (!this.guesses.contains(guess)) {
                this.guesses.add(guess);
                break;
            }
        }
        return guess;
    }

    @Override
    public void deployShips() {
        System.out.println("A számítógép játékos elhelyezi a hajóit.");
        int count = 1;
        this.ships = new ShipSet();
        while (this.ships.size() != 5) {
            int x = Generator.generateInteger();
            int y = Generator.generateInteger();
            var ship = new Ship(x, y);
            if (this.ships.contains(ship)) {
                continue;
            } else {
                this.ships.add(ship);
                System.out.println("A számítógép játékos lerakta a(z) " + count + ". hajóját.");
                count += 1;
            }
        }
        System.out.println("A számítógép játékos lerakta az összes hajóját.");
    }
}
