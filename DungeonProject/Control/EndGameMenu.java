package DungeonProject.Control;

import DungeonProject.Model.MenuInputException;
import DungeonProject.View.MenuPrinter;

import java.util.Scanner;

/**
 * End Game Menu allows user to control options when player is dead or quitting the game.
 */
public class EndGameMenu {

    public boolean accessMenu(){
        MenuPrinter endMenu = new MenuPrinter();
        endMenu.printEndGameMenu();
        Scanner in = new Scanner(System.in);
        int choice;
        boolean choose = false;
        try{
            choice = (in.nextInt());
            tryInput(choice);
        }catch(MenuInputException invalidInput){
            choice = (in.nextInt());
        }
        switch(choice){
            case 1 -> {
                choose = true;
            }
            case 2 -> {
                break;
            }
        }
        return choose;
    }

    public void tryInput(int choice) throws MenuInputException {
        if (choice < 1 || choice > 2) {
            throw new MenuInputException();
        }
    }
    public EndGameMenu(){

    }
}
