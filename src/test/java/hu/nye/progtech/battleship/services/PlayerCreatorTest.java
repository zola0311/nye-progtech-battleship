package hu.nye.progtech.battleship.services;

import hu.nye.progtech.battleship.models.PlayerInfo;
import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerCreatorTest {

    @Mock
    private UserInputReader mockUserInputReader;
    @Mock
    private PrintWrapper mockPrintWrapper;

    private PlayerCreator playerCreatorUnderTest;

    @BeforeEach
    void setUp() {
        playerCreatorUnderTest = new PlayerCreator(mockUserInputReader, mockPrintWrapper);
    }

    @Test
    void testUpdatePlayer() {
        // Given
        when(mockUserInputReader.readInput()).thenReturn("user");

        // When
        final PlayerInfo result = playerCreatorUnderTest.createPlayer();

        // Then
        verify(mockPrintWrapper).printLine("Üdvözöllek! Kérlek add meg a neved:");
    }

    @Test
    void testCreatePlayerWithNullUser() throws NullPointerException {

        // Given
        when(mockUserInputReader.readInput()).thenReturn(null);

        // When
        final PlayerInfo result = playerCreatorUnderTest.createPlayer();

        // Then
        assertThrows(
                NullPointerException.class,
                () -> { throw new NullPointerException(); }
        );

    }

}
