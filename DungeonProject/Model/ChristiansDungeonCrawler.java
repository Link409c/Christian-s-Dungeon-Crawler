package DungeonProject.Model;

/***********************************************************************************************************
 * Christian's Dungeon Crawler is an attempt at a text-driven, roguelike, dungeon crawling game with
 * turn-based combat. The User will create a character using prompts, and then brave an ever-changing
 * dungeon with the goal of reaching the final, bottom floor. Uses a manually implemented Linked List
 * structure to handle dungeon rooms, monsters, and player inventory in the game.
 *
 * @author Christian Simpson
 * @version 11/7/2022
 *
 ***********************************************************************************************************/
public class ChristiansDungeonCrawler {
    public static void main(String[] args){

        Game theGame = new Game();
        theGame.play();

    }
}
