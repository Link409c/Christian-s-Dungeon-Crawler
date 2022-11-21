package DungeonProject.Control;

import DungeonProject.Model.*;
import DungeonProject.View.MenuPrinter;

import java.util.Scanner;

/**
 * Battle Menu allows the user to control player actions in battle.
 */
public class BattleMenu{

    /**
     * accessMenu method will control the player actions when in battle. Uses player
     * input passed to a switch statement to either attack, defend, or use items.
     * @param r the room object.
     * @return returns the updated room object.
     */

    public Room accessMenu(Room r){
        Player p = r.getThePlayer();
        Monster m = r.getEnemy();
        //print the battle menu
        MenuPrinter b = new MenuPrinter();
        //prompt the player for input
        Scanner in = new Scanner(System.in);
        //variable for input
        int choice;
        boolean act = false;
        while(!act) {
            b.printBattleMenu();
            try{
                choice = (in.nextInt());
                tryInput(choice);
            }catch(MenuInputException invalidInput){
                System.out.println("Choose Attack (1), Defend (2), or Items (3).");
                choice = (in.nextInt());
            }
            //player action according to input
            switch (choice) {
                case 1 -> {
                    m.setCurrHealthPoints(m.getCurrHealthPoints() - p.attack(m));
                    act = true;
                }
                case 2 -> {
                    p.defend();
                    act = true;
                }
                case 3 -> {
                    InventoryMenu inv = new InventoryMenu();
                    Player newP = inv.accessMenu(p);
                    //action is taken if a healing item is used
                    if(newP.getCurrHealthPoints() != p.getCurrHealthPoints()){
                        act = true;
                    }
                    p = newP;
                }
            }
        }
        r.setThePlayer(p);
        r.setEnemy(m);
        return r;
    }

    public void tryInput(int choice) throws MenuInputException {
        if (choice < 1 || choice > 3) {
            throw new MenuInputException();
        }
    }
    
    public BattleMenu() {
    	
    }
}
