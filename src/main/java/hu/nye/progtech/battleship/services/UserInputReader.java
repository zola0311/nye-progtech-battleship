package hu.nye.progtech.battleship.services;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Implement and configure the UserInputReader. With this we can listen for the input from the user. **/
public class UserInputReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInputReader.class);

    private final BufferedReader reader;

    public UserInputReader(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Reads the user input and returns it as a string.
     *
     * @return the user input as a string
     */
    public String readInput() {
        String input = null;

        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("Exception occurred while reading user input", e);
        }

        return input;
    }
}
