package hu.nye.progtech.battleship;

import java.util.Scanner;
import hu.nye.progtech.battleship.models.ComputerPlayer;
import hu.nye.progtech.battleship.models.HumanPlayer;
import hu.nye.progtech.battleship.services.Battleship;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/** In this class we initialize the game. This class run first if we start the game. */
public class Main {

    /** Here we start the game. */
    public static void main(String[] args) {
        ApplicationContext applicationConfiguration = new AnnotationConfigApplicationContext("hu.nye.progtech.battleship");
        Scanner input = new Scanner(System.in);
        Battleship battleShip = applicationConfiguration.getBean(Battleship.class);
        battleShip.build().setPlayers(new HumanPlayer(input), new ComputerPlayer()).playGame();
        input.next();
        input.close();
    }
}
