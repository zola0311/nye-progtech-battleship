package hu.nye.progtech.battleship.services;

import java.sql.SQLException;
import java.util.Objects;

import hu.nye.progtech.battleship.models.HumanPlayer;
import hu.nye.progtech.battleship.models.Player;
import hu.nye.progtech.battleship.models.PlayerInfo;
import hu.nye.progtech.battleship.persistences.impl.MySQLPlayerRepository;
import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/** Implement the battleship. */
public class Battleship {

    private Player player1;
    private Player player2;
    private String[][] playerOneOcean = new String[10][10];
    private String[][] playerTwoOcean = new String[10][10];

    PrintWrapper printWrapper;

    public Battleship(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
        for (int i = 0; i < this.playerOneOcean.length; i++) {
            for (int j = 0; j < this.playerOneOcean[i].length; j++) {
                this.playerOneOcean[i][j] = " ";
            }
        }
        for (int i = 0; i < this.playerTwoOcean.length; i++) {
            for (int j = 0; j < this.playerTwoOcean[i].length; j++) {
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

    public Battleship build() {
        return new Battleship(this.printWrapper);
    }

    /** Handle the guesses and sink ship. */
    public void playTurn(Player player, Player other) {
        var guess = player.guess();
        int x = guess[0];
        int y = guess[1];
        boolean isHuman = player.getClass() == HumanPlayer.class;
        if (other.getShips().shipIsPresentAt(x, y)) {
            this.printWrapper.printLine(
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
            this.printWrapper.printLine(
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

    /** Display the player and computer ocean. */
    public String displayOcean() {
        StringBuilder str = new StringBuilder();

        str.append("Játékos óceánja: ");
        str.append("\n");
        str.append("   0123456789   ");
        str.append("\n");
        for (int i = 0; i < this.playerOneOcean.length; i++) {
            String row = i + " |";
            for (int j = 0; j < this.playerOneOcean[i].length; j++) {
                row += this.playerOneOcean[i][j];
            }
            row += "| " + i;
            str.append(row);
            str.append("\n");
        }
        str.append("   0123456789   ");
        str.append("\n");
        str.append("Számítógép óceánja: ");
        str.append("\n");
        str.append("   0123456789   ");
        str.append("\n");
        for (int i = 0; i < this.playerTwoOcean.length; i++) {
            String row = i + " |";
            for (int j = 0; j < this.playerTwoOcean[i].length; j++) {
                row += this.playerTwoOcean[i][j];
            }
            row += "| " + i;
            str.append(row);
            str.append("\n");
        }
        str.append("   0123456789   ");
        str.append("\n");
        String player1Name = this.player1.getClass() == HumanPlayer.class ? "Játékos 1" : "Számítógép";
        String player2Name = this.player2.getClass() == HumanPlayer.class ? "Játékos 2" : "Számítógép";
        str.append(
                player1Name + " hajók: " + this.player1.getShipsRemaining() +
                        " | " +
                        player2Name + " hajók: " + this.player2.getShipsRemaining()
        );
        return str.toString();
    }

    /** Display players ship. */
    public void displayShips() {
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
        String playerName = "";
        ApplicationContext applicationConfiguration = new AnnotationConfigApplicationContext("hu.nye.progtech.battleship");
        PlayerCreator playerCreator = applicationConfiguration.getBean(PlayerCreator.class);
        PlayerInfo playerInfo = null;
        while (Objects.equals(playerName, "")) {
            playerInfo = playerCreator.createPlayer();
            playerName = playerInfo.getPlayerName();
        }
        this.player1.deployShips();
        this.player2.deployShips();
        this.displayShips();
        boolean playerOneTurn = true;
        while (this.player1.getShipsRemaining() > 0 && this.player2.getShipsRemaining() > 0) {
            this.playTurn(
                    playerOneTurn ? this.player1 : this.player2,
                    !playerOneTurn ? this.player1 : this.player2
            );
            this.printWrapper.printLine(this.displayOcean());
            playerOneTurn = !playerOneTurn;
        }
        if (player1.getShipsRemaining() == 0) {
            this.printWrapper.printLine("Az ellenfél győzött!");
        } else {
            this.printWrapper.printLine("Megnyerted a csatát!");
            int playerWinsInDatabase = playerInfo.getPlayerWins();
            playerInfo.setPlayerWins(playerWinsInDatabase + 1);
            MySQLPlayerRepository playerRepository = null;
            try {
                playerRepository = new MySQLPlayerRepository(playerInfo);
                playerRepository.updatePlayer(playerInfo);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
