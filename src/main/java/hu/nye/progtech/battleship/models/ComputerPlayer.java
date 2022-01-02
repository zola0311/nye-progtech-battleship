package hu.nye.progtech.battleship.models;

import java.util.HashSet;
import hu.nye.progtech.battleship.services.Generator;

/** Implement the computer player. */
public class ComputerPlayer extends Player {

    public ComputerPlayer() {
        super();
        this.guesses = new HashSet<>();
    }

    @Override
    public Character[] guess() {
        System.out.println("A számítógép játékos felkészül a tüzelésre.");
        Character[] guess = new Character[2];
        while (true) {
            guess[0] = Generator.generateCharacter();
            guess[1] = (char) Generator.generateInteger();
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
            char x = Generator.generateCharacter();
            char y = (char) Generator.generateInteger();
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
