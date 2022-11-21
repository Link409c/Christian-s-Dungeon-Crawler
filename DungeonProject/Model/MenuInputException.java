package DungeonProject.Model;

import java.util.InputMismatchException;

/**
 * MenuInputException is thrown whenever the user inputs a choice in the
 * menu that is not listed.
 */
public class MenuInputException extends InputMismatchException {

    public MenuInputException(){
        super("Enter a number listed for any option in this menu.");
    }

}
