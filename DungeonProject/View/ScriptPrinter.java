package DungeonProject.View;

import java.util.Random;

import DungeonProject.Model.Monster;
import DungeonProject.Model.Player;

/**
 * A ScriptPrinter is an object that holds some amount of text to be printed in different
 * parts of the game. Includes the game introduction, messages that print between
 * floors, the end game messages, and the victory message.
 */
public class ScriptPrinter{

    /**
     * introScript method holds the introduction for the game and prints it
     * when the new game method is called.
     */
    public void printIntroScript(){
        System.out.println("\nWelcome to Christian's Dungeon Crawler.");
        System.out.println("This is a dungeon exploration game, where the player");
        System.out.println("will challenge the monsters within, floor by floor, and");
        System.out.println("see just how deep their skills can take them. Can you");
        System.out.println("reach the darkest depths and live to tell the tale?");
        System.out.println("Adventure Awaits...");
    }

    /**
     * continueScript method holds a variety of scripts to print randomly when
     * a player continues the game after a game over occurs, or when they progress
     * to a new floor of the dungeon.
     */
    public void printContinueScript(){
        //generate a random number
        Random r = new Random();
        int getScript = r.nextInt(1, 6);
        switch(getScript){
            case 1 ->{
                System.out.println("\nMaybe this time, you'll get lucky.");
            }
            case 2 ->{
                System.out.println("\nThey say if you're encountering enemies, " +
                        "you're headed in the right direction.");
            }
            case 3 ->{
                System.out.println("\nI hope there's some floor chicken on the next level.");
            }
            case 4 ->{
                System.out.println("\nIf you're having trouble with the skeletons, " +
                        "try asking them what their favorite snack is.");
            }
            case 5 ->{
                System.out.println("\nCall the Nintendo Power Hotline for live gaming tips at " +
                        "856-371-0583! ($1.49 per minute)");
            }
            case 6 ->{
                System.out.println("\nAren't there any cheats for this game?");
            }
        }
    }

    /**
     * sectionScript method holds a script enticing the player to keep playing. it
     * prints the script based on the floor the player has reached when they move past
     * that section of the dungeon.
     * @param currentFloor the current floor of the dungeon.
     */

    public void printSectionScript(int currentFloor) {
        if(currentFloor == 10){
            System.out.println("\nThe depth you've achieved is but the surface of this " +
                    "deep and twisting labyrinth.");
        }
        else if(currentFloor == 30){
            System.out.println("\nYou fought well, and delved far. But much lies " +
                    "deeper within the dungeon.");
        }
        else if(currentFloor == 50){
            System.out.println("\nYour skills have brought you far, your strength led you " +
                    "\nto victory against many enemies, and your sense of adventure uncovered "
                    + "\nmuch. However, still more awaits.");
        }
        else if(currentFloor == 70){
            System.out.println("\nThere is little more to say of your success! A great adventurer " +
                    "\nsuch as you has what it takes to reach what lies at the very core of these "
                    + "\ncatacombs. Soon, it will be within your reach...");
        }
        else if(currentFloor == 90){
            System.out.println("\nYou're nearly there! The nadir of this labyrinth is just below you.");
        }
        else if(currentFloor == 100){
            System.out.println("\nYou continue ever downward, past what was once the deepest part" +
                    "\nof an ever-growing labyrinth. Good Luck, Hero. From here, none will aid you, but" +
                    "\nyour feats will be heard of in places far and wide.");
        }
    }

    public void printExitFoundScript(){
        System.out.println("You found the Exit! Descending to the next floor...");
    }

    /**
     * battleScript prints the encounter notification when a battle occurs.
     * @param m the monster the player is battling.
     */
    public void printBattleScript(Monster m){
        System.out.println("Battle! You face a " + m.getName() + ".");
    }

    /**
     * reflectionScript prints the encounter notification when fighting the boss at the last
     * level of the dungeon.
     */
    public void printReflectionScript(){
        System.out.println("Battle! You face... yourself?");
    }
    /**
     * finalLevelScript prints a script detailing the final encounter of the game.
     */
    public void printFinalLevelScript(){
        System.out.println("\nAt last, you have reached what lies beneath everything.");
        System.out.println("\nAdmire the strength you've gained; The battles you've won;" +
                "The hardships you've faced.");
        System.out.println("\nWas this adventure worth it?");
        System.out.println("\nLook deep within and ask yourself this...");
        System.out.println("\n... and as you turn your eyes forward, face this last trial.\n");
        System.out.println("\nA foreboding presence, eerie yet familiar, lies ahead of you.");
    }

    /**
     * victoryScript method holds a script that is printed when the player clears the
     * final floor of the dungeon.
     */
    public void printVictoryScript() {
        System.out.println("Congratulations! Against great odds, you have conquered all 99 Floors of \n");
        System.out.println("Christian's Dungeon Crawler. You even defeated yourself at the end!\n");
        System.out.println("The Game will continue for as long as you wish after this point.");
        System.out.println("Thank you for playing!!!");
    }
    
    public void printEndGameScript(Player p, int currentFloor){
        if(!p.isAlive()){
            System.out.println("\nYou've braved the dungeon " + p.getGamesPlayed() + " times.");
            System.out.println("You reached floor " + currentFloor + ".");
            System.out.println("Enemies Killed: " + p.getKillCount());
            System.out.println("Items collected: " + p.getItemsAcquired());
            System.out.println("Floors Cleared: " + p.getFloorsCleared());
            //quitting via the menu does not print results
        }else{
            System.out.println("\nYou Quit.");
        }
    }
}
