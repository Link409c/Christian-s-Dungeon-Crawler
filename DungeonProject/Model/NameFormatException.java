package DungeonProject.Model;

import java.util.InputMismatchException;

/**
 * Name Format Exception is thrown when the user inputs an invalid name when creating
 * their Player object.
 */
public class NameFormatException extends InputMismatchException {

    public NameFormatException(){
        super("Enter a First and Last name, with a 16-character limit (excluding spaces.)");
        System.out.println(getMessage());
    }
}
