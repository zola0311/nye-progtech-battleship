package hu.nye.progtech.battleship.persistences;

import hu.nye.progtech.battleship.models.Player;
import hu.nye.progtech.battleship.models.PlayerInfo;

/** Implement the PlayerRepository interface */
public interface PlayerRepository {

    void createPlayer(PlayerInfo player);

    void updatePlayer(PlayerInfo player);

    PlayerInfo getPlayer();
}
