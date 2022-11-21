package DungeonProject.Model;

import DungeonProject.Control.DungeonMenu;
import DungeonProject.Control.EndGameMenu;
import DungeonProject.Control.MainMenu;
import DungeonProject.View.DungeonMapPrinter;
import DungeonProject.View.ScriptPrinter;
import DungeonProject.View.WindowPrinter;

import java.util.Scanner;


/**
 * Game is an object containing a player and dungeon objects. This class will use associated
 * methods to run the gameplay of Christian's Dungeon Crawler.
 *
 * @author Christian Simpson
 * @version 11/21/22
 */
public class Game implements GameInterface {

    /**
     * Play is a container method for starting the game. It is responsible for overall gameplay
     * and calling methods to start or continue a game.
     */
    public void play() {
        boolean play = true;
        while (play) {
            MainMenu mainMenu = new MainMenu();
            int option = mainMenu.accessMenu();
            switch (option) {
                case 1 -> newGame();
                case 2 -> continueGame();
            }
            System.out.println("\nEntering the Dungeon...");
            DungeonMapPrinter p = new DungeonMapPrinter();
            p.mapInfoFormatter(getThePlayer(), getDungeon());
            p.printFloorMap(getDungeon());
            DungeonMenu d = new DungeonMenu();
            boolean action = false;
            while (getThePlayer().isAlive() && !getDungeon().isEndGame()) {
                int newPlayerRoom, currPlayerRoom;
                Dungeon updatedD;
                while (!action) {
                    //give player control
                    currPlayerRoom = getDungeon().getPlayerPosition();
                    updatedD = d.accessMenu(getDungeon(), getThePlayer());
                    newPlayerRoom = updatedD.getPlayerPosition();
                    if (currPlayerRoom != newPlayerRoom || updatedD.isEndGame()) {
                        setDungeon(updatedD);
                        action = true;
                    }
                }
                if(!getDungeon().isEndGame()){
                    //action is taken when player moves to a new room
                    action = false;
                    RoomChecker checkCurrRoom = new RoomChecker();
                    //get the room to check which should contain player
                    Room curr = getDungeon().getDungeonRooms().findNodeAtPosition(getDungeon().getPlayerPosition()).getE();
                    //check the room, which will trigger battle or item acquisition
                    curr = checkCurrRoom.checkRoom(getDungeon(), curr);
                    //update player after winning battle or acquiring item
                    setThePlayer(curr.getThePlayer());
                    //continue until exit
                    //when exit found, progress to next level
                    if (curr.isTheExit()) {
                            getThePlayer().setFloorsCleared(getThePlayer().getFloorsCleared() + 1);
                            DungeonLevelProgression dLP = new DungeonLevelProgression();
                            setDungeon(dLP.progressNextLevel(getDungeon(), getThePlayer()));
                            p.mapInfoFormatter(getThePlayer(), getDungeon());
                            p.printFloorMap(getDungeon());
                    } else {
                        //otherwise update the current room after battles / item acquisition
                        getDungeon().getDungeonRooms().findNodeAtPosition(getDungeon().getPlayerPosition()).setE(curr);
                    }
                }
            }
            play = endGame(getThePlayer());
        }
        System.exit(0);
    }

    /**
     * newGame method begins a new Game, starting the player at level one. This method will be called
     * when 'New Game' is selected by the user at the main menu. The method prompts the user for their
     * character name, creates a new player object, sets the current floor to 1, and calls the createGame
     * method to construct the dungeon and begin gameplay.
     */
    @Override
    public void newGame(){
        //clear the player and dungeon object if any
        setThePlayer(null);
        //print the intro script
        ScriptPrinter newScript = new ScriptPrinter();
        newScript.printIntroScript();
        System.out.println("\nStarting a new game.");
        //prompt the player for name input
        Scanner in = new Scanner(System.in);
        String yourName;
        System.out.println("\nEnter your hero's name.\nYou can have a first and last name; 16-Character Limit: ");
        try {
            yourName = in.nextLine();
            tryName(yourName);
        }catch(NameFormatException n){
            yourName = in.nextLine();
        }
        //create a new player object using the name
        //set it as the player
        setThePlayer(new Player(yourName));
        DungeonBuilder db = new DungeonBuilder();
        //call createDungeon method
        setDungeon(db.createNewDungeon(getThePlayer()));
    }

    /**
     * continueGame method is called when the player chooses to continue
     * after dying or quitting the game. It allows the player to continue
     * the game starting in a new dungeon, using their previously
     * attained level, stats, items, equipment, etc.
     */
    @Override
    public void continueGame() {
        if(getThePlayer() == null && getDungeon() == null){
            System.out.println("\nThere is no current game in progress.\nStart a new game.");
            MainMenu mainMenu = new MainMenu();
            mainMenu.accessMenu();
        }
        else {
            //clear the dungeon if any
            setDungeon(null);
            //set player health to max to avoid death bugs
            getThePlayer().setCurrHealthPoints(getThePlayer().getMaxHealthPoints());
            //print continue script
            ScriptPrinter continueScript = new ScriptPrinter();
            continueScript.printContinueScript();
            //using the existing player object, continue in a new dungeon
            DungeonBuilder db = new DungeonBuilder();
            //call createDungeon method
            setDungeon(db.createNewDungeon(getThePlayer()));
        }
    }

    /**
     * endGame method ends the game when the player's HP reaches zero or the user quits. If the
     * player died, the amount of floors the player has cleared, monsters killed, and items picked up
     * are printed. If they quit, the results are not printed. It prompts the user to continue, with
     * the current player object being used to create a new randomized dungeon if the user continues.
     */
    public boolean endGame(Player p) {
        WindowPrinter endWindow = new WindowPrinter();
        if(!p.isAlive()) {
            endWindow.printDeathWindow();
            ScriptPrinter end = new ScriptPrinter();
            end.printEndGameScript(p, getDungeon().getCurrentFloor());
        }
        else{
            endWindow.printQuitWindow();

        }
        EndGameMenu endGameMenu = new EndGameMenu();
        return endGameMenu.accessMenu();
    }

    /**
     * tryName checks player name field and throws custom exception if incorrect format.
     * @param aName the name to check.
     * @throws NameFormatException an exception detailing correct format to user.
     */
    public void tryName(String aName) throws NameFormatException{
        if(aName.length() > 17){
            throw new NameFormatException();
        }
    }

    private Dungeon dungeon;

    private Player thePlayer;

    public Game(){

    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Player getThePlayer() {
        return thePlayer;
    }

    public void setThePlayer(Player thePlayer) {
        this.thePlayer = thePlayer;
    }

}
