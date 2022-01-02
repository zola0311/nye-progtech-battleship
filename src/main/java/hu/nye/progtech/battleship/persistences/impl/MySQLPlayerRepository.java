package hu.nye.progtech.battleship.persistences.impl;

import hu.nye.progtech.battleship.models.Player;
import hu.nye.progtech.battleship.models.PlayerInfo;
import hu.nye.progtech.battleship.persistences.PlayerRepository;

import java.sql.*;

public class MySQLPlayerRepository implements PlayerRepository {

    private static final String INSERT_STATEMENT = "INSERT INTO players (playerName, playerWins, playerPlayedGames) VALUES (?, ?, ?);";
    private static final String SELECT_STATEMENT = "SELECT * FROM players Where playerName = ?;";
    private static final String UPDATE_STATEMENT = "UPDATE players SET playerWins = ?, playerPlayedGames = ? Where playerName = ?;";
    private Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/progtechdatabase", "root", "");
    private final PlayerInfo player;

    public MySQLPlayerRepository(PlayerInfo player) throws SQLException {
        this.player = player;
    }

    @Override
    public void createPlayer(PlayerInfo player) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(1, player.getPlayerName());
            preparedStatement.setString(2, String.valueOf(player.getPlayerWins()));
            preparedStatement.setString(3, String.valueOf(player.getPlayerPlayedGames()));
            preparedStatement.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    @Override
    public void updatePlayer(PlayerInfo player) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATEMENT)) {
            preparedStatement.setString(3, player.getPlayerName());
            preparedStatement.setString(1, String.valueOf(player.getPlayerWins()));
            preparedStatement.setString(2, String.valueOf(player.getPlayerPlayedGames()));
            preparedStatement.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    @Override
    public PlayerInfo getPlayer() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATEMENT)) {
            preparedStatement.setString(1, player.getPlayerName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                player.setPLayerWins(resultSet.getInt("PlayerWins"));
                player.setPlayerPlayedGames(resultSet.getInt("PlayerPlayedGames"));
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return player;
    }

}
