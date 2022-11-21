package DungeonProject.Model;

public interface GameInterface {

    //run the game
    void play();

    //create the objects needed and start a new game
    void newGame();

    //create a new dungeon when the player continues after dying
    void continueGame();

    //end the game when the player dies or quits
    boolean endGame(Player p);
}
