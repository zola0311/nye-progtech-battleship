package hu.nye.progtech.battleship.models;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Implement the human player. */
public class HumanPlayer extends Player {

    Scanner input;

    public HumanPlayer(Scanner input) {
        super();
        this.input = input;
        this.guesses = new HashSet<>();
    }

    @Override
    public Character[] guess() {
        Character[] guess = new Character[2];
        System.out.println("Lövésre felkészülni!");
        while (true) {
            try {
                System.out.print("Add meg a lövés X koordinátáját: ");
                guess[0] = this.input.next().charAt(0);
                System.out.print("Add meg a lövés Y koordinátáját: ");
                guess[1] = this.input.next().charAt(0);
                if (!this.guesses.contains(guess)) {
                    this.guesses.add(guess);
                    break;
                } else {
                    System.out.println("A megadott koordinátákra már lőttél. Próbáld meg mégegyszer.");
                }

            } catch (InputMismatchException ime) {
                System.out.println("Helytelen koordináta bemenet. Használj karakterket.");
                this.input.next();
            }

        }
        return guess;
    }

    @Override
    public void deployShips() {
        System.out.println("Helyezd el a hajóidat!");

        int count = 1;
        this.ships = new ShipSet();
        while (this.ships.size() != 5) {
            try {
                System.out.print("Add meg a(z) " + count + ". hajód X koordinátáját: ");
                char x = this.input.next().charAt(0);
                System.out.print("Add meg a(z) " + count + ". hajód Y koordinátáját: ");
                char y = this.input.next().charAt(0);
                if (x < 'A' || x > 'J') {
                    System.out.println("Helytelen koordináták, próbáld újra.");
                    continue;
                }
                if (y < '0' || y > '9') {
                    System.out.println("Helytelen koordináták, próbáld újra.");
                    continue;
                }
                var ship = new Ship(x, y);
                if (this.ships.contains(ship)) {
                    System.out.println("Ide már korábban helyeztél hajót, próbáld újra.");
                } else {
                    this.ships.add(ship);
                    count += 1;
                }

            } catch (InputMismatchException ime) {
                System.out.println("Helytelen koordináták. Használj karaktereket.");
            }
        }
        System.out.println("Hajóidat sikeresen elhelyezted.");
    }
}
