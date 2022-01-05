package hu.nye.progtech.battleship.configurations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import hu.nye.progtech.battleship.services.UserInputReader;
import hu.nye.progtech.battleship.services.PlayerCreator;
import hu.nye.progtech.battleship.services.Battleship;
import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configure the spring framework for application. */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public UserInputReader userInputReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInputReader(bufferedReader);
    }

    @Bean
    public Battleship battleShip(PrintWrapper printWrapper) {
        return new Battleship(printWrapper).build();
    }

    @Bean
    public PlayerCreator playerCreator(UserInputReader userInputReader, PrintWrapper printWrapper) {
        return new PlayerCreator(userInputReader, printWrapper);
    }

    @Bean
    public PrintWrapper printWrapper() {
        return new PrintWrapper();
    }

}
