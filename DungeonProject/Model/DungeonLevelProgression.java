package DungeonProject.Model;

import DungeonProject.View.ScriptPrinter;
import ManualLinkedList.ChristianHolder;

/**
 * Dungeon Level Progression is used to create a new "floor" of the dungeon when the
 * player finds the exit.
 */
public class DungeonLevelProgression {

    /**
     * progressNextLevel method creates a new dungeon object when the player finds the exit,
     * and populates the new dungeon "level" with the player, enemies, items, etc.
     */
    public Dungeon progressNextLevel(Dungeon d, Player p) {
        Dungeon newDungeon = new Dungeon();
        //if new floor is < 99
        d.setCurrentFloor(d.getCurrentFloor() + 1);
        if(d.getCurrentFloor() != 99) {
            //print continueScript
            ScriptPrinter cont = new ScriptPrinter();
            cont.printExitFoundScript();
            cont.printSectionScript(d.getCurrentFloor());
            cont.printContinueScript();
            /* create a new dungeon using player level and floor number
             * as the multiplier for rooms, monsters, and items */
            DungeonBuilder nextFloor = new DungeonBuilder();
            newDungeon = nextFloor.createNewFloor(d, p);
        } else {
            //else if new floor == 99
            //create a floor with two rooms
            ChristianHolder<Room> lastRooms = new ChristianHolder<>();
            for(int i = 0; i < 2; i++){
                Room aRoom = new Room();
                lastRooms.add(aRoom);
            }
            Monster theBoss = new Monster(MonsterType.Reflection, p);
            Item thePotion = new Item(ItemType.USABLE, "Max Potion", d.getCurrentFloor());
            lastRooms.getHead().getE().setThePlayer(p);
            newDungeon.setPlayerPosition(0);
            lastRooms.getHead().getE().setTreasure(thePotion);
            lastRooms.getHead().getNext().getE().setEnemy(theBoss);
            lastRooms.getTail().getE().setTheExit(true);
            newDungeon.setCurrentFloor(99);
            newDungeon.setDungeonRooms(lastRooms);
            ScriptPrinter finalScript = new ScriptPrinter();
            finalScript.printFinalLevelScript();
        }
        return newDungeon;
    }
}
