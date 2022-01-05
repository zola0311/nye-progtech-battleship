package hu.nye.progtech.battleship.models;

/** Implement player info for storing data in database. */
public class PlayerInfo {

    private String playerName;
    private int playerWins;
    private int playerPlayedGames;

    public PlayerInfo(String playerName, int playerWins, int playerPlayedGames) {
        this.playerName = playerName;
        this.playerWins = playerWins;
        this.playerPlayedGames = playerPlayedGames;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String newPlayerName) throws NullPointerException {
        this.playerName = newPlayerName;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public int getPlayerPlayedGames() {
        return playerPlayedGames;
    }

    public void setPlayerPlayedGames(int newPlayedGames) {
        this.playerPlayedGames = newPlayedGames;
    }

    public void setPlayerWins(int newPlayerWins) {
        this.playerWins = newPlayerWins;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + this.playerName + '\'' +
                ", playerWins=" + this.playerWins +
                ", playerPlayedGames=" + this.playerPlayedGames +
                '}';
    }
}
