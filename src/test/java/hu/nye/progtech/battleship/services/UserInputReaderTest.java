package hu.nye.progtech.battleship.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

public class UserInputReaderTest {

    private UserInputReader underTest;

    private static final String INPUT = "fromInput";

    public static BufferedReader bufferedReader;

    @BeforeEach
    public void init(){
        bufferedReader = Mockito.mock(BufferedReader.class);
        underTest = new UserInputReader(bufferedReader);
    }

    @Test
    public void testReadInputShouldReturnTheInputReadByBufferedReader() throws IOException {
        // given
        given(bufferedReader.readLine()).willReturn(INPUT);

        // when
        String result = underTest.readInput();

        // then
        assertEquals(INPUT, result);
    }

    @Test
    public void testReadInputShouldReturnNullWhenBufferedReaderThrowsException() throws IOException {
        // given
        doThrow(IOException.class).when(bufferedReader).readLine();

        // when
        String result = underTest.readInput();

        // then
        assertNull(result);
    }

}
