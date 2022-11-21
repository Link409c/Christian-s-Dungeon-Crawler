package DungeonProject.Model;

/**
 * Room Checker checks the current room the player is in for enemies and items. If an enemy exists in the room,
 * a battle occurs.
 *
 * @version 11/14/22
 */
public class RoomChecker {

    public Room checkRoom(Dungeon d, Room r){
        //if room has enemy,
        if(r.getEnemy() != null) {
            //make a new battle object
            Battle b = new Battle();
            //pass in the enemy and player
            b.runBattle(r);
            //if the player wins
            if(r.getThePlayer().isAlive()) {
                //update player object with battle result
                BattleResult br = new BattleResult();
                r.setThePlayer(br.battleRewards(r.getThePlayer(), r.getEnemy()));
                r.setEnemy(null);
            }else {
                return r;
            }
        }
        //if room has treasure
        if(r.getTreasure() != null) {
            //add it to the player inventory
            r.getThePlayer().getInventory().getItems().add(r.getTreasure());
            System.out.println("You found a " + r.getTreasure().getName() + "!");
            r.setTreasure(null);
        }
        ExitMath distanceFromExit = new ExitMath();
        int whereIsExit = distanceFromExit.doExitMath(d);
        //otherwise, tell the player the room is empty and the distance to the exit
        if(r.getTreasure() == null && r.getEnemy() == null){
            System.out.println("This room is empty. You are " + whereIsExit + " rooms away from the exit.");
        }else {
            System.out.println("You are " + whereIsExit + " rooms away from the exit.");
        }
        return r;
    }

    public RoomChecker(){

    }
}
