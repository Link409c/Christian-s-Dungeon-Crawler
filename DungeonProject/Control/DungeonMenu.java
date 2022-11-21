package DungeonProject.Control;

import DungeonProject.Model.Dungeon;
import DungeonProject.Model.MenuInputException;
import DungeonProject.Model.Player;
import DungeonProject.Model.PlayerMovement;
import DungeonProject.View.DungeonMapPrinter;
import DungeonProject.View.MenuPrinter;
import DungeonProject.View.WindowPrinter;

import java.util.Scanner;

/**
 * The dungeonMenu controls the player actions when outside of battle. Uses the
 * player input passed to a switch statement to either move left, right, check status,
 * check inventory, or quit.
 */
public class DungeonMenu{

    public Dungeon accessMenu(Dungeon d, Player p) {
        MenuPrinter dm = new MenuPrinter();
        //prompt the player for input
        Scanner in = new Scanner(System.in);
        //variable for input
        int choice;
        boolean loopEnd = false;
        while (!loopEnd){
            dm.printDungeonMenu();
            try {
                choice = in.nextInt();
                tryInput(choice);
            } catch (MenuInputException invalidInput) {
                choice = in.nextInt();
            }
            switch (choice) {
                //move left or right
                case 1 -> {
                    PlayerMovement pm = new PlayerMovement();
                    d.setDungeonRooms(pm.movePlayer(d, p, 'l'));
                    if(d.getPlayerPosition()-1 < 0){
                        d.setPlayerPosition(d.getDungeonRooms().getSize()-1);
                    }else {
                        d.setPlayerPosition(d.getPlayerPosition() - 1);
                    }
                    loopEnd = true;
                }
                case 2 -> {
                    PlayerMovement pm = new PlayerMovement();
                    d.setDungeonRooms(pm.movePlayer(d, p, 'r'));
                    if(d.getPlayerPosition()+1 == d.getDungeonRooms().getSize()){
                        d.setPlayerPosition(0);
                    }else {
                        d.setPlayerPosition(d.getPlayerPosition() + 1);
                    }
                    loopEnd = true;
                }
                //check map
                case 3 -> {
                    DungeonMapPrinter mapPrinter = new DungeonMapPrinter();
                    mapPrinter.mapInfoFormatter(p, d);
                    mapPrinter.mapLayoutFormatter(d);
                    mapPrinter.printFloorMap(d);
                }
                //check status
                case 4 -> {
                    WindowPrinter winP = new WindowPrinter();
                    winP.printPlayerStatus(p);
                }
                //open inventory
                case 5 -> {
                    InventoryMenu inv = new InventoryMenu();
                    inv.accessMenu(p);
                }
                //or quit
                case 6 -> {
                    d.setEndGame(true);
                    loopEnd = true;
                }
            }
        }
        return d;
    }

    public void tryInput(int choice) throws MenuInputException {
        if (choice < 1 || choice > 6) {
            throw new MenuInputException();
        }
    }

}
