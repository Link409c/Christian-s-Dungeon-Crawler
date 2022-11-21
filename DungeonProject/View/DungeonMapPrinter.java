package DungeonProject.View;

import DungeonProject.Model.Dungeon;
import DungeonProject.Model.Player;
import DungeonProject.Model.StringDisplayFormatter;

/**
 * The Dungeon Map Printer formats and prints the layout of the current floor of the dungeon for the player.
 * @author simpso61
 * @version 11/14/22
 */
public class DungeonMapPrinter {

    public void mapInfoFormatter(Player p, Dungeon d) {
        int gp = p.getGamesPlayed();
        int cf = d.getCurrentFloor();
        StringBuilder gpS = new StringBuilder(), cfS = new StringBuilder();
        if (gp < 10) {
            gpS.append('0').append(' ').append(gp);
        } else {
            char[] gpA = String.valueOf(gp).toCharArray();
            for (int i = 0; i < gpA.length - 1; i++) {
                gpS.append(gpA[i]).append(' ');
            }
            gpS.append(gpA[gpA.length - 1]);
        }
        if (cf < 10) {
            cfS.append('0').append(' ').append(cf);
        } else {
            char[] cfA = String.valueOf(cf).toCharArray();
            for (int i = 0; i < cfA.length - 1; i++) {
                cfS.append(cfA[i]).append(' ');
            }
            cfS.append(cfA[cfA.length - 1]);
        }
        setCurrentFloor(cfS.toString());
        setGamesPlayed(gpS.toString());
    }

    public String mapLayoutFormatter(Dungeon d) {
        //TODO format the map layout for use in the printFloorMap method
        StringBuilder map = new StringBuilder();
        int totalRooms = d.getDungeonRooms().getSize();
        for (int i = 0; i < totalRooms - 1; i++) {
            if (i == d.getPlayerPosition()) {
                map.append("[*]=");
            } else {
                map.append("[ ]=");
            }
        }
        if (totalRooms - 1 == d.getPlayerPosition()) {
            map.append("[*]");
        } else {
            map.append("[ ]");
        }
        String theMap = map.toString();
        StringDisplayFormatter sdf = new StringDisplayFormatter();
        theMap = sdf.centerString(62, theMap);
        return theMap;
    }


    /**
     * printFloorMap prints a visual representation of the current dungeon object,
     * showing player location, exit(if found), and items(if found).
     */
    //64 char length inside box
    public void printFloorMap(Dungeon d) {
        String printMap = mapLayoutFormatter(d);
        System.out.println("\n{}================================================================{}");
        System.out.println("{}===   D U N G E O N :: " + getGamesPlayed() + "       " +
                "         F L O O R :: " + getCurrentFloor() + "   ==={}");
        System.out.println("{}================================================================{}");
        System.out.println("{}                                                                {}");
        System.out.println("{} " + printMap + " {}");
        System.out.println("{}                                                                {}");
        System.out.println("{}================================================================{}");
    }

    private String currentFloor;

    private String gamesPlayed;

    public String getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(String currentFloor) {
        this.currentFloor = currentFloor;
    }

    public String getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(String gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}

