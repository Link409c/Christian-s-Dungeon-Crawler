package DungeonProject.Model;

import ManualLinkedList.ChristianHolder;

/**
 * playerMovement moves the player object through the dungeon rooms.
 * @author Christian Simpson
 * @version 11/10/22
 */
public class PlayerMovement {

    /**
     * movePlayer places player object in new Room in the list of rooms and returns that updated list.
     * @param d the Dungeon object
     * @param direction the direction the player wants to move.
     * @return returns an updated list of rooms to be set as the Game's Dungeon's rooms list variable.
     */
    public ChristianHolder<Room> movePlayer(Dungeon d, Player p, char direction){
        ChristianHolder<Room> rooms = d.getDungeonRooms();
        if(direction == 'l') {
            if(rooms.findNodeAtPosition(d.getPlayerPosition()).equals(rooms.getHead())){
                rooms.findNodeAtPosition(d.getPlayerPosition()).getE().setThePlayer(null);
                rooms.getTail().getE().setThePlayer(p);
                rooms.getHead().getE().setThePlayer(null);
            }else {
                rooms.findNodeAtPosition(d.getPlayerPosition()).getPrev().getE().setThePlayer(p);
                rooms.findNodeAtPosition(d.getPlayerPosition()).getE().setThePlayer(null);
            }
        }
        else if(direction == 'r'){
            if(rooms.findNodeAtPosition(d.getPlayerPosition()).equals(rooms.getTail())){
                rooms.findNodeAtPosition(d.getPlayerPosition()).getE().setThePlayer(null);
                rooms.getHead().getE().setThePlayer(p);
                rooms.getTail().getE().setThePlayer(null);
            }else {
                rooms.findNodeAtPosition(d.getPlayerPosition()).getNext().getE().setThePlayer(p);
                rooms.findNodeAtPosition(d.getPlayerPosition()).getE().setThePlayer(null);
            }
        }
        return rooms;
    }

    public PlayerMovement(){

    }

}
