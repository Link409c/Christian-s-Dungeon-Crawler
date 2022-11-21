package DungeonProject.Model;

import DungeonProject.Control.BattleMenu;
import DungeonProject.View.MenuPrinter;
import DungeonProject.View.ScriptPrinter;
import DungeonProject.View.WindowPrinter;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A Battle object is created whenever the player enters a room containing an enemy.
 */
public class Battle {

    /**
     * runBattle method is responsible for operations of the battle object. The method will
     * pass in the player and monster objects from the room, and call the battleMenu method to
     * accept player input. The user will battle with the monster until one of their HP hits zero.
     * Then, if the player wins, they will gain experience and (if possible) an item dropped by the enemy.
     *
     * @param r the room object.
     * @return returns the updated room object.
     */
    public Room runBattle(Room r) {
        Player p = r.getThePlayer();
        Monster m = r.getEnemy();
        ScriptPrinter battleScript = new ScriptPrinter();
        if(m.getMonsterType() != MonsterType.Reflection) {
            battleScript.printBattleScript(m);
        }
        else{
            battleScript.printReflectionScript();
        }
        WindowPrinter w = new WindowPrinter();
        //variables to hold defense stat
        int playerActualDefense = p.getDefensePoints();
        //while the player and monster are alive, battle
        BattleMenu battleMenu = new BattleMenu();
            while (p.isAlive() && m.isAlive()) {
                //print the battle window
                w.printBattleWindow(p, m);
                //if player is faster they go first
                if (p.getSpeed() > m.getSpeed()) {
                    r = battleMenu.accessMenu(r);
                    if (m.isAlive()) {
                        p.setCurrHealthPoints(p.getCurrHealthPoints() - m.attack(p));
                    }

                } else {
                    p.setCurrHealthPoints(p.getCurrHealthPoints() - m.attack(p));
                    if (p.isAlive()) {
                        r = battleMenu.accessMenu(r);
                    }
                }
                //reset defense if either being defended
                if (p.getDefensePoints() != playerActualDefense) {
                    p.setDefensePoints(playerActualDefense);
                }
            }
        return r;
    }

    public Battle(){

    }
}