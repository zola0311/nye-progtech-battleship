package hu.nye.progtech.battleship.services;

import hu.nye.progtech.battleship.models.HumanPlayer;
import hu.nye.progtech.battleship.models.Player;

/** Implement the battleship. */
public class Battleship {

    private Player player1;
    private Player player2;
    private String[][] playerOneOcean = new String[10][10];
    private String[][] playerTwoOcean = new String[10][10];

    private Battleship() {
        for (int i = 0; i < this.playerOneOcean.length; i++) {
            for (int j = 0; i < this.playerOneOcean[i].length; j++) {
                this.playerOneOcean[i][j] = " ";
            }
        }
        for (int i = 0; i < this.playerTwoOcean.length; i++) {
            for (int j = 0; i < this.playerTwoOcean[i].length; j++) {
                this.playerTwoOcean[i][j] = " ";
            }
        }
    }

    /** Set the players of battleship. */
    public Battleship setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        return this;
    }

    public static Battleship build() {
        return new Battleship();
    }

    private void playTurn(Player player, Player other) {
        var guess = player.guess();
        char x = guess[0];
        char y = guess[1];
        boolean isHuman = player.getClass() == HumanPlayer.class;
        if (other.getShips().shipIsPresentAt(x, y)) {
            System.out.println(
                    isHuman ?
                            "Boom!  Elsüllyesztetted az ellenség egy hajóját!" :
                            "Az ellenfél elsüllyesztette az egyik hajódat!"
            );
            other.sinkShip(x, y);
            if (isHuman) {
                this.playerTwoOcean[x][y] = " ";
            } else {
                this.playerOneOcean[x][y] = " ";
            }
        } else {
            System.out.println(
                    isHuman ?
                            "Nem talált!" :
                            "Az ellenfél találata nem talált!"
            );
            if (isHuman) {
                this.playerTwoOcean[x][y] = "-";
            } else {
                this.playerOneOcean[x][y] = " ";
            }
        }
    }

    private void displayOcean() {
        //need finnish
    }

    private void displayShips() {
        if (this.player1.getClass() == HumanPlayer.class) {
            for (var ship : this.player1.getShips()) {
                int x = ship.getPosition()[0];
                int y = ship.getPosition()[1];
                this.playerOneOcean[x][y] = "S";
            }
        }
        if (this.player2.getClass() == HumanPlayer.class) {
            for (var ship : this.player1.getShips()) {
                int x = ship.getPosition()[0];
                int y = ship.getPosition()[1];
                this.playerTwoOcean[x][y] = "S";
            }
        }
    }

    /** Start the game and loop till the end. */
    public void playGame() {
        System.out.println("Üdvözöllek! Add meg a neved: ");
        this.player1.deployShips();
        this.player2.deployShips();
        this.displayShips();
        boolean playerOneTurn = true;
        while (this.player1.getShipsRemaining() > 0 && this.player2.getShipsRemaining() > 0) {
            this.playTurn(
                    playerOneTurn ? this.player1 : this.player2,
                    !playerOneTurn ? this.player1 : this.player2
            );
            this.displayOcean();
            playerOneTurn = !playerOneTurn;
        }
        if (player1.getShipsRemaining() == 0) {
            System.out.println("Az ellenfél győzött!");
        } else {
            System.out.println("Megnyerted a csatát!");
        }
    }
}
