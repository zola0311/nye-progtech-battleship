package hu.nye.progtech.battleship.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratorTest {

    @Test
    public void theGeneratedIntegerIsInRange() {
        //Given
        final int generatedInteger = Generator.generateInteger();
        //When
        final boolean result;
        if (generatedInteger >= 0 && generatedInteger <= 9) {
            result = true;
        } else {
            result = false;
        }
        //Then
        assertTrue(result);
    }
}
