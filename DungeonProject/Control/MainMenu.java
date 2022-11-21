package DungeonProject.Control;

import DungeonProject.Model.MenuInputException;
import DungeonProject.View.MenuPrinter;

import java.util.Scanner;

/**
 * Main Menu allows the user to control options when starting the game.
 */
public class MainMenu{

    /**
     * accessMenu method controls the game outside of dungeon gameplay.
     * User can start a new game or continue a game.
     */
   
    public int accessMenu() {
        //print main menu
        MenuPrinter m = new MenuPrinter();
        m.printMainMenu();
        Scanner in = new Scanner(System.in);
        //new game or continue
        int choice;
        try{
            choice = in.nextInt();
            tryInput(choice);
        }catch(MenuInputException invalidInput){
            choice = in.nextInt();
        }
        return choice;
    }

    public void tryInput(int choice) throws MenuInputException {
        if (choice < 1 || choice > 2) {
            throw new MenuInputException();
        }
    }
    public MainMenu() {

    }
}
