package hu.nye.progtech.battleship.services;

import java.sql.SQLException;
import java.util.Objects;

import hu.nye.progtech.battleship.models.PlayerInfo;
import hu.nye.progtech.battleship.persistences.impl.MySQLPlayerRepository;
import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Implementation of PlayerCreator. */
public class PlayerCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerCreator.class);

    private static final String WELCOME_MESSAGE = "Üdvözöllek! Kérlek add meg a neved:";

    private final UserInputReader userInputReader;
    private final PrintWrapper printWrapper;

    public PlayerCreator(UserInputReader userInputReader, PrintWrapper printWrapper) {
        this.userInputReader = userInputReader;
        this.printWrapper = printWrapper;
    }

    /** With this method we create the player. **/
    public PlayerInfo createPlayer() {

        printWrapper.printLine(WELCOME_MESSAGE);
        String playerName = userInputReader.readInput();
        PlayerInfo player = new PlayerInfo(null, 0, 0);
        player.setPlayerName(playerName);
        if (!Objects.equals(player.getPlayerName(), "")) {
            player.setPlayerName(playerName);
            try {
                MySQLPlayerRepository playerRepository = new MySQLPlayerRepository(player);

                int playerPlayedGamesInDatabase = playerRepository.getPlayer().getPlayerPlayedGames();

                if (playerPlayedGamesInDatabase == 0) {
                    player.setPlayerPlayedGames(1);
                    playerRepository.createPlayer(player);
                    LOGGER.info("Add a new player to the database.: " + playerName);
                } else {
                    player.setPlayerPlayedGames(playerPlayedGamesInDatabase + 1);
                    playerRepository.updatePlayer(player);
                    LOGGER.info("Query and modify player information.: " + playerName);
                }
            } catch (SQLException error) {
                error.printStackTrace();
            }
        } else {
            printWrapper.printLine("Hibás játékosnév.");

        }
        return player;
    }

}
