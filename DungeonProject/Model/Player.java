package DungeonProject.Model;

import ManualLinkedList.ChristianHolder;
import ManualLinkedList.Node;

import java.util.Objects;
import java.util.Scanner;

/**
 * Player is the user's character in the Dungeon Crawler game.
 */
public class Player extends Being implements PlayerInterface {
    /**
     * checkLevelUp Method should run at the end of every Battle.
     * Once the encounter ends, it checks the player experience against
     * the threshold, and if it meets or exceeds it, the player gains
     * a level.
     *
     * @param experience The experience amount to be checked.
     */
    @Override
    public void checkLevelUp(int experience) {
        if (getLevel() < 99) {
            while (experience >= getLevelUpThreshold()) {
                levelUp(experience);
            }
        }
    }

    /**
     * levelUp method is called when experience threshold is reached by the
     * player. it increases the player stats based on their level and restores
     * the Player's HP to full.
     *
     * @param experience the experience gained at the end of a battle.
     */
    @Override
    public void levelUp(int experience) {
        System.out.println("Level up! (" + getLevel() + " --> " + (getLevel() + 1) + ")");
        setExperience(experience - getLevelUpThreshold());
        setLevel(getLevel() + 1);
        setMaxHealthPoints(getLevel() * 100);
        setCurrHealthPoints(getMaxHealthPoints());
        setAttackPoints(getLevel() * 10);
        setDefensePoints(getLevel() * 2);
        setSpeed(getLevel() * 5);
        setLevelUpThreshold(getLevelUpThreshold() + getLevel() * 100);
    }

    /**
     * useItem method gets a usable item from the player inventory and based
     * on the mode of the method, either gets the description, uses the item,
     * equips the item, or drops the item.
     * @param items the list of items.
     * @param aName the name of the item to use.
     * @param mode the mode for the method to use.
     */
    @Override
    public void useItem(ChristianHolder<Item> items, String aName, String mode){
        if(items.getHead() != null) {
            Node<Item> temp = items.getHead();
            Item theItem;
            int thisSize = items.getSize();
            for (int i = 0; i < thisSize; i++) {
                if (!Objects.equals(temp.getE().getName(), aName) && temp.getNext() != null) {
                    temp = temp.getNext();
                }
            }
            theItem = temp.getE();

            if (mode.equalsIgnoreCase("use")) {
                if (getCurrHealthPoints() != getMaxHealthPoints()) {
                    System.out.println("You recover " + getMaxHealthPoints()*theItem.getRestore() + " HP.");
                    setCurrHealthPoints(Math.min(getMaxHealthPoints(),
                            ((int) (getCurrHealthPoints() + getMaxHealthPoints()*theItem.getRestore()))));
                    getInventory().getItems().removeValue(theItem);
                } else {
                    System.out.println("Your HP is at Max.");
                }
            } else if (mode.equalsIgnoreCase("description")) {
                System.out.println(theItem.getDescription());
            } else if (mode.equalsIgnoreCase("drop")) {
                Scanner in = new Scanner(System.in);
                System.out.println("Are you sure? Y/N: ");
                String choice = in.nextLine();
                if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("yes")) {
                    System.out.println("You drop a " + theItem.getName() + ".");
                    getInventory().getItems().removeValue(theItem);
                }
            } else if (mode.equalsIgnoreCase("equip")) {
                //determine which slot the item goes in
                if (theItem.getType() == ItemType.EQUIPMENT) {
                    Equipment[] e = getEquipment();
                    int theIndex = 0;
                    if (((Equipment) theItem).getEquipmentClass() == EquipmentClass.ARMOR) {
                        theIndex = 1;
                    } else if (((Equipment) theItem).getEquipmentClass() == EquipmentClass.ACCESSORY) {
                        theIndex = 2;
                    }
                    //if player has equipment in this slot, put it back in the inventory
                    if (e[theIndex] != null) {
                        getInventory().getItems().add(e[theIndex]);
                        System.out.println("Unequipped " + e[theIndex].getName() + ".");
                    }
                    //equip the item
                    e[theIndex] = (Equipment) theItem;
                    setEquipment(e);
                    System.out.println("Equipped " + theItem.getName() + ".");
                    getInventory().getItems().removeValue(theItem);
                } else {
                    System.out.println("You cannot equip this item.");
                }
            } else if (mode.equalsIgnoreCase("unequip")) {
                //get equipment array
                Equipment[] e = getEquipment();
                //display equipped items to player
                System.out.println("|----- Currently Equipped -----|");
                for (Equipment value : e) {
                    System.out.println(value.getEquipmentClass().toString() + ": " + value.getName());
                }
                //get input for which item to remove
                System.out.println("Unequip which item?\n1. Weapon\n2. Armor\n3. Accessory");
                Scanner in = new Scanner(System.in);
                int choice = in.nextInt();
                //if the slot has an item, put it back in the inventory
                if (e[choice - 1] != null) {
                    getInventory().getItems().add(e[choice]);
                    System.out.println("Unequipped " + e[choice].getName() + ".");
                }
                //else tell the player there is no item equipped
                else {
                    System.out.println("You do not have any equipment in the " +
                            e[choice].getEquipmentClass().toString() + " slot.");
                }
            }
        }else{
            System.out.println("Your inventory is empty.");
        }
    }

    //getter methods for player stats check equipment for any stat boosts.
    //player stats are capped at 9999.

    @Override
    public int getMaxHealthPoints(){
        Equipment[] equipped = getEquipment();
        int newMaxHP = super.getMaxHealthPoints();
        for(int i = 0; i < equipped.length; i++){
            if(equipped[i] != null){
                newMaxHP += equipped[i].getMaxHealthPoints();
            }
        }
        return Math.min(newMaxHP, getSTAT_CAP());
    }

    @Override
    public int getAttackPoints(){
        Equipment[] equipped = getEquipment();
        int newMaxAtk = super.getAttackPoints();
        for(int i = 0; i < equipped.length; i++){
            if(equipped[i] != null){
                newMaxAtk += equipped[i].getAttack();
            }
        }
        return Math.min(newMaxAtk, getSTAT_CAP());
    }
    @Override
    public int getDefensePoints(){
        Equipment[] equipped = getEquipment();
        int newMaxDef = super.getDefensePoints();
        for(int i = 0; i < equipped.length; i++){
            if(equipped[i] != null){
                newMaxDef += equipped[i].getDefense();
            }
        }
        return Math.min(newMaxDef, getSTAT_CAP());
    }
    @Override
    public int getSpeed(){
        Equipment[] equipped = getEquipment();
        int newMaxSpd = super.getSpeed();
        for(int i = 0; i < equipped.length; i++){
            if(equipped[i] != null){
                newMaxSpd += equipped[i].getSpeed();
            }
        }
        return Math.min(newMaxSpd, getSTAT_CAP());
    }

    private Inventory inventory;

    private Equipment[] equipment;

    private final int STAT_CAP = 9999;

    private int experience;

    private int levelUpThreshold;

    private int killCount;

    private int floorsCleared;

    private int itemsAcquired;

    private int gamesPlayed;

    //constructor creates a level 1 default hero
    public Player(String name) {

        Equipment[] heroEquipment = new Equipment[3];
        Equipment woodenSword = new Equipment("Wooden Sword", 1);
        heroEquipment[0] = woodenSword;
        setEquipment(heroEquipment);

        setName(name);
        setLevel(1);
        setMaxHealthPoints(getLevel() * 100);
        setCurrHealthPoints(getMaxHealthPoints());
        setAttackPoints(getLevel() * 10);
        setDefensePoints(getLevel() * 2);
        setSpeed(getLevel() * 5);
        setExperience(0);
        setLevelUpThreshold(getLevel() * 100);

        Inventory playerInventory = new Inventory();
        ChristianHolder<Item> items = new ChristianHolder<>();
        Item potion = new Item(ItemType.USABLE, "Potion", 1);
        items.add(potion);
        playerInventory.setItems(items);
        setInventory(playerInventory);

        setKillCount(0);
        setFloorsCleared(0);
        setItemsAcquired(0);
        setGamesPlayed(1);
    }

    public Player(String name, int healthPoints, int attackPoints, int defensePoints,
                  int speed, int level, int experience, int levelUpThreshold, int killCount, Equipment[] equipped,
                  Inventory inventory) {

        super(name, healthPoints, attackPoints, defensePoints, speed, level);
        this.experience = experience;
        this.levelUpThreshold = levelUpThreshold;
        this.killCount = killCount;
        this.equipment = equipped;
        this.inventory = inventory;
    }

    public int getSTAT_CAP(){
        return STAT_CAP;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevelUpThreshold() {
        return levelUpThreshold;
    }

    public void setLevelUpThreshold(int levelUpThreshold) {
        this.levelUpThreshold = levelUpThreshold;
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public int getFloorsCleared() {
        return floorsCleared;
    }

    public void setFloorsCleared(int floorsCleared) {
        this.floorsCleared = floorsCleared;
    }

    public int getItemsAcquired() {
        return itemsAcquired;
    }

    public void setItemsAcquired(int itemsAcquired) {
        this.itemsAcquired = itemsAcquired;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Equipment[] getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment[] equipment) {
        this.equipment = equipment;
    }
}