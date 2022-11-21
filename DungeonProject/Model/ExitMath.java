package DungeonProject.Model;

/**
 * Exit Math determines player proximity to the dungeon exit.
 */
public class ExitMath {

    public int doExitMath(Dungeon d){
        //get player position
        int playerPos = d.getPlayerPosition();
        //get exit position
        int exitPos = 0;
        Room theExit;
        int theSize = d.getDungeonRooms().getSize();
        for(int i = 0; i < theSize; i++){
            theExit = d.getDungeonRooms().findNodeAtPosition(i).getE();
            if(theExit.isTheExit()){
                exitPos = i;
                break;
            }
        }
        exitPos = playerPos - exitPos;
        //calculate absolute value of distance from exit
        return Math.abs(exitPos);
    }

    public ExitMath(){

    }
}
