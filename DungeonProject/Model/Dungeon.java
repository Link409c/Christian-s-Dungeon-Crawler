package DungeonProject.Model;

import ManualLinkedList.ChristianHolder;
import ManualLinkedList.Node;

/**
 * Dungeon class is the environment the Dungeon Crawler Game will be played in. Each instance
 * of the object is considered a floor, with each floor containing a linked list of rooms for
 * the player to traverse.
 */
public class Dungeon {

    /**
     * toString puts the rooms and their contents in a list.
     * @return returns a list of the dungeon's level, size, and room objects and their contents.
     */
    @Override
    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        int size = getDungeonRooms().getSize();
        if(size == 0){
            toReturn = new StringBuilder("This dungeon is empty.");
            return toReturn.toString();
        }
        Node<Room> currNode = getDungeonRooms().getHead();
        Room curr = currNode.getE();
        toReturn.append("Current Floor: ").append(getCurrentFloor()).append("\n");
        toReturn.append("Size: ").append(size).append("\n");
        toReturn.append("Player Position: ").append(getPlayerPosition()+1).append("\n");
        for(int i = 0; i < size; i++){
            toReturn.append("Room ").append(i+1).append(": ");
            if(curr.getThePlayer() != null){
                toReturn.append("Player").append(" | ");
            }
            if(curr.getEnemy() != null) {
                toReturn.append(curr.getEnemy().getName()).append(" | ");
            }
            if(curr.getTreasure() != null){
                toReturn.append(curr.getTreasure().getName()).append(" | ");
            }
            if(curr.isTheExit()){
                toReturn.append("Exit");
            }
            toReturn.append("\n");
            currNode = currNode.getNext();
            curr = currNode.getE();
        }
        return toReturn.toString();
    }

    private ChristianHolder<Room> dungeonRooms;

    private int currentFloor;

    private int playerPosition;

    private boolean endGame;

    //used for new game
    public Dungeon(){
        setCurrentFloor(1);
        setEndGame(false);
    }

    //used for new floors
    public Dungeon(Dungeon d){
        setCurrentFloor(d.getCurrentFloor());
        setEndGame(false);
        setDungeonRooms(null);
        setPlayerPosition(0);
    }

    public ChristianHolder<Room> getDungeonRooms() {
        return dungeonRooms;
    }

    public void setDungeonRooms(ChristianHolder<Room> dungeonRooms) {
        this.dungeonRooms = dungeonRooms;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}
