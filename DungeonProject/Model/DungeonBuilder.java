package DungeonProject.Model;

import ManualLinkedList.ChristianHolder;

import java.util.Random;

import static DungeonProject.Model.MonsterType.*;

/**
 * Dungeon Builder is a class that generates linked lists of rooms, monsters, and items to
 * instantiate a dungeon object.
 */
public class DungeonBuilder {

    /**
     * generateRooms method creates the room objects that comprise the dungeon.
     * it will use the current floor to determine the maximum number of rooms the
     * dungeon can have. the number of rooms will be randomized in order to create
     * a different experience each time the game is played.
     *
     * @return returns a list of room objects.
     */
    public ChristianHolder<Room> generateRooms(Dungeon d) {
        //create list to return
        ChristianHolder<Room> theRooms = new ChristianHolder<>();
        //create variable for max rooms
        int maxRooms;
        //get current floor of the dungeon
        int aFloor = d.getCurrentFloor();
        //if first floor, make limit 10
        if (aFloor == 1) {
            maxRooms = 10;
        }
        //beyond that, increase the rooms size 1 each floor
        else {
            maxRooms = Math.min(10 + aFloor, 50);
        }
        //create random object to use for room number generation
        Random r = new Random();
        //we want minimum rooms to be half of max and max to be upper bound
        //randomize the number with a minimum and maximum
        int rooms = r.nextInt(maxRooms / 2, maxRooms + 1);
        //use the room number to generate that many rooms
        for (int i = 0; i < rooms; i++) {
            //place each room into the dungeon builder
            Room room = new Room();
            theRooms.add(room);
        }
        return theRooms;
    }

    /**
     * generateItems method creates the item objects that can be found by the player
     * in the dungeon, either as rewards from battle or on the ground. Key items will
     * be added in a later version for narrative purposes.
     */
    public void generateItems(Dungeon d) {
        ChristianHolder<Monster> theMonsters = getMonsters();
        ChristianHolder<Item> anItems = new ChristianHolder<>();
        //get current floor of the dungeon
        int aFloor = d.getCurrentFloor();
        //get dungeon size
        int dungeonSize = d.getDungeonRooms().getSize();
        //create variable for max items
        int maxItems;
        //to avoid an overabundance of items, limit the amount
        maxItems = (dungeonSize / 2);
        //generate random items, limited by amount
        //key items should be placed intentionally so are not included in random generation
        String[] itemNames = {"Potion", "Hi-Potion", "Max Potion", "Wooden Sword", "Sword", "Dagger",
                "Greatsword", "Woolen Shirt", "Leather Tunic", "Mail Hauberk", "Plate Mail", "Ring of Might",
                "Ring of Defense", "Ring of Speed", "Vital Pendant"};
        ItemType[] itemTypes = {ItemType.USABLE, ItemType.USABLE, ItemType.EQUIPMENT};
        Random r = new Random();
        int typesIndex, namesIndex, rewardChance, rewardIndex;
        for (int i = 0; i < maxItems; i++) {
            Item item;
            typesIndex = r.nextInt(0, 3);
            //generate item based on type
            if(typesIndex < 2){
                namesIndex = r.nextInt(0, 3);
                item = new Item(itemTypes[typesIndex], itemNames[namesIndex], aFloor);
            }
            else {
                namesIndex = r.nextInt(4, 14);
                item = new Item(itemTypes[typesIndex], itemNames[namesIndex], aFloor);
                item = new Equipment(item.getName(), aFloor);
            }
            rewardChance = r.nextInt(2);
            if(rewardChance == 1){
                rewardIndex = i;
                if(theMonsters.getSize() < rewardIndex){
                    if (!theMonsters.findNodeAtPosition(i).getE().hasReward()){
                        theMonsters.findNodeAtPosition(i).getE().setReward(item);
                    }
                }
            }
            else {
                anItems.add(item);
            }
        }
        setMonsters(theMonsters);
        setTreasures(anItems);
    }

    /**
     * generateMonsters method creates the monster objects that will populate the dungeon's
     * Rooms. the method will create a number of monsters dependent on the current floor, the
     * player level, and the number of rooms, with a random amount generated not to exceed the
     * room count of the floor.
     */
    public void generateMonsters(Dungeon d, Player p) {
        //create list to return
        ChristianHolder<Monster> theMonsters = new ChristianHolder<>();
        //create variable for max monsters
        int maxMonsters;
        //get current floor of the dungeon
        int aFloor = d.getCurrentFloor();
        //get player level
        int monsterScale = p.getLevel();
        //get dungeon size
        int dungeonSize = d.getDungeonRooms().getSize();
        //the smaller the room count the more difficult the monsters can be
        if(dungeonSize <= monsterScale/2){
            monsterScale += 2;
        }
        //to avoid an overabundance of monsters, limit the amount
        //we want to give the player a chance (until we don't)
        maxMonsters = (dungeonSize / 2) + aFloor/3;
        //limit monster types by section (scarier monsters deeper in the dungeon)
        //with floor limit of 99, about a third of the way sounds good for all the monster types
        Random r = new Random();
        int theType;
        MonsterType[] types = {Slime, Bat, Skeleton, Zombie, Goblin, Lizardman, Orc,
                Minotaur, Dragon, TreasureLizard};
        Monster m;
        for(int i = 0; i < maxMonsters; i++) {
            //treasure lizards can spawn on any floor but are rare
            //if floor <= 10, slimes, bats, and skeletons can spawn
            if(aFloor <= 10){
                theType = r.nextInt(3);
                m = new Monster(types[theType], d.getCurrentFloor(), monsterScale);
                theMonsters.add(m);
            }
            //if floor >= 11 and < 20, goblins, lizardmen, and orcs can also spawn
            else if(aFloor <= 20){
                theType = r.nextInt(6);
                m = new Monster(types[theType], d.getCurrentFloor(), monsterScale);
                theMonsters.add(m);
            }
            //if floor >= 21, minotaurs and dragons can also spawn
            else {
                theType = r.nextInt(10);
                m = new Monster(types[theType], d.getCurrentFloor(), monsterScale);
                theMonsters.add(m);
            }
        }
        setMonsters(theMonsters);
    }

    /**
     * the populateDungeon method uses the player object, items list, and monsters list to
     * randomly assign the objects to rooms in the dungeon. The player will always enter a
     * new floor in the middle of the dungeon. The exit will also be placed randomly, and
     * the method accounts for player position so that there will not be a monster at the
     * entry room and that room will also never be the exit.
     * @param items the linked list of items passed in.
     * @param monsters the linked list of monsters passed in.
     */
    public void populateDungeon(Dungeon d, Player p, ChristianHolder<Item> items, ChristianHolder<Monster> monsters) {
        //calculate the middle room of the dungeon
        int playerEntry = d.getDungeonRooms().getSize() / 2;
        //place the player object into the middle of the dungeon
        d.getDungeonRooms().findNodeAtPosition(playerEntry).getE().setThePlayer(p);
        d.setPlayerPosition(playerEntry);
        //make variable for upper bound of random int generation for exit
        int bound = d.getDungeonRooms().getSize() + 1;
        //make a random room the exit
        Random r = new Random();
        int exitPos = r.nextInt(0, bound);
        /* the exit should not be at the player's starting position, and should be at least
         * two rooms away in either direction as long as size allows. */
        while(exitPos == playerEntry || exitPos == playerEntry + 1 || exitPos == playerEntry - 1){
            exitPos = r.nextInt(0, bound);
        }
        //set the exit
        d.getDungeonRooms().findNodeAtPosition(exitPos).getE().setTheExit(true);
        //variable for room number to set objects in
        int objectPos;
        //variable for room
        Room temp;
        //array holds room numbers to exclude from random generation after they are used
        int[] roomNumbers = new int[monsters.getSize()];
        //for loop places monsters in rooms
        for(int i = 0; i < monsters.getSize(); i++){
            objectPos = r.nextInt(0, bound);
            //put this generated position in the array
            roomNumbers[i] = objectPos;
            /* during each add step check to make sure this room's enemy
             * object has not already been populated */
            for (int roomNumber : roomNumbers) {
                //if it was already, generate a new random object position number
                if (roomNumber == objectPos) {
                    objectPos = r.nextInt(0, bound);
                }
            }
            //generate temp node to check if it is this floor's entrance
            temp = d.getDungeonRooms().findNodeAtPosition(objectPos).getE();
            //do not place a monster in the floor's entrance room
            if(temp.getThePlayer() == null){
                //populate the enemy object in this room
                d.getDungeonRooms().findNodeAtPosition(objectPos).getE().setEnemy(monsters.removeEnd().getE());
            }
        }
        roomNumbers = new int[items.getSize()];
        for(int i = 0; i < items.getSize(); i++){
            roomNumbers[i] = i;
        }
        //for loop places items in rooms
        //items are fine to put at the start.
        for(int i = 0; i < items.getSize(); i++){
            objectPos = r.nextInt(0, bound);
            //put this generated position in the array
            roomNumbers[i] = objectPos;
            /* during each add step check to make sure this room's reward
             * object has not already been populated */
            for (int roomNumber : roomNumbers) {
                //if it was already, generate a new random object position number
                if (roomNumber == objectPos) {
                    objectPos = r.nextInt(0, bound);
                }
            }
            //populate the reward object in this room
            d.getDungeonRooms().findNodeAtPosition(objectPos).getE().setTreasure(items.removeEnd().getE());
        }
    }

    /**
     * createNewDungeon method will construct the dungeon object, construct the Monsters List,
     * and construct the Item List. It will populate the starting room with the player
     * object, randomly populate the rooms of the dungeon with items and monsters, and
     * assign one room as the exit.
     *
     */
    public Dungeon createNewDungeon(Player p){
        //create the dungeon
        Dungeon newDungeon = new Dungeon();
        System.out.println("\n.   .   .     . . .  ...Loading...  . . .     .   .   .");
        //create dungeon rooms
        ChristianHolder<Room> rooms = generateRooms(newDungeon);
        newDungeon.setDungeonRooms(rooms);
        //randomly generate monsters to add to the dungeon
        generateMonsters(newDungeon, p);
        //randomly generate items to add to the dungeon
        generateItems(newDungeon);
        //populate the dungeon with the created objects and player
        System.out.println("\n.   .   .     . . .  ...Loading...  . . .     .   .   .");
        populateDungeon(newDungeon, p, getTreasures(), getMonsters());
        return newDungeon;
    }

    /**
     * createNewFloor is called by the DungeonLevelProgression class to create a new "level" of
     * the dungeon when the player progresses during the game.
     * @param d the current dungeon object
     * @param p the current player object
     * @return returns a new dungeon object.
     */
    public Dungeon createNewFloor(Dungeon d, Player p){
        //create the dungeon
        Dungeon newDungeon = new Dungeon(d);
        System.out.println("\n.   .   .     . . .  ...Loading...  . . .     .   .   .");
        //create dungeon rooms
        ChristianHolder<Room> rooms = generateRooms(newDungeon);
        newDungeon.setDungeonRooms(rooms);
        //randomly generate monsters to add to the dungeon
        generateMonsters(newDungeon, p);
        //randomly generate items to add to the dungeon
        generateItems(newDungeon);
        //populate the dungeon with the created objects and player
        System.out.println("\n.   .   .     . . .  ...Loading...  . . .     .   .   .");
        populateDungeon(newDungeon, p, getTreasures(), getMonsters());
        return newDungeon;
    }

    private ChristianHolder<Item> treasures;

    private ChristianHolder<Monster> monsters;
    
    public DungeonBuilder() {
    	
    }

    public ChristianHolder<Item> getTreasures() {
        return treasures;
    }

    public void setTreasures(ChristianHolder<Item> treasures) {
        this.treasures = treasures;
    }

    public ChristianHolder<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ChristianHolder<Monster> monsters) {
        this.monsters = monsters;
    }

}
