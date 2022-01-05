package hu.nye.progtech.battleship.services;

import hu.nye.progtech.battleship.models.ComputerPlayer;
import hu.nye.progtech.battleship.models.HumanPlayer;
import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mock;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BattleshipTest {

    @Mock
    private Battleship mockBattleship;
    @Mock
    private PrintWrapper mockPrintWrapper;
    @Mock
    private HumanPlayer mockHumanPlayer;
    @Mock
    private HumanPlayer mockHumanPlayer2;
    @Mock
    private ComputerPlayer mockComputerPlayer;

    Scanner inputUnderTest = new Scanner(System.in);


    @BeforeEach
    void setUp() {
        mockBattleship = new Battleship(mockPrintWrapper);
        mockHumanPlayer = new HumanPlayer(inputUnderTest);
        mockHumanPlayer2 = new HumanPlayer(inputUnderTest);
        mockComputerPlayer = new ComputerPlayer();
    }

    @Test
    public void afterSetPlayersWithNullPlayersTheReturnedBattleshipIsEqualsWithTheOld() {
        //When
        final Battleship result = mockBattleship.setPlayers(mockHumanPlayer, mockComputerPlayer);
        //Then
        assertEquals(result, mockBattleship);
    }

    @Test
    public void displayOceanWithNullShipListDisplayTheOceanSuccess() {
        //Given
        final Battleship battleShipUnderTest = mockBattleship.build().setPlayers(mockHumanPlayer, mockComputerPlayer);
        //When
        String result = battleShipUnderTest.displayOcean();
        //Then
        assertNotNull(result);
    }

    @Test
    public void displayShipsForTwoHumanPlayerDontThrowsNullPointerException() throws NullPointerException {
        //Given
        mockHumanPlayer.addShip(1, 2);
        mockHumanPlayer.addShip(2, 1);
        mockHumanPlayer2.addShip(3, 4);
        mockHumanPlayer2.addShip(7, 8);
        //When
        mockBattleship.build().setPlayers(mockHumanPlayer, mockHumanPlayer2).displayShips();
        //Then
    }

}
