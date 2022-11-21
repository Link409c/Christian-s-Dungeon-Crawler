package DungeonProject.Model;

/**
 * An object that the player can pick up and put in their inventory. The enumerated type variable
 * includes usable, equipment, and key items.
 */
public class Item{

    /**
     * conjure changes the variables of an item based on its name and / or equipment class.
     * Equipment scales with player level and floor.
     * @param name the name of the item.
     * @param currentFloor the current floor of the dungeon.
     */

    public void conjure(String name, int currentFloor){
        switch(getType()) {
            //usables
            case USABLE -> {
                switch(name) {
                    //Potion
                    case "Potion" -> {
                        setDescription("A simple healing Item. Restores 20% of total HP.");
                        setAttack(0);
                        setDefense(0);
                        setSpeed(0);
                        setMaxHealthPoints(0);
                        setRestore(.2);
                    }
                    //Hi-Potion
                    case "Hi-Potion" -> {
                        setDescription("A great healing Item. Restores 50% of total HP.");
                        setAttack(0);
                        setDefense(0);
                        setSpeed(0);
                        setMaxHealthPoints(0);
                        setRestore(.5);
                    }
                    //Max Potion
                    case "Max Potion" -> {
                        setDescription("A fantastic healing Item. Restores all HP.");
                        setAttack(0);
                        setDefense(0);
                        setSpeed(0);
                        setMaxHealthPoints(0);
                        setRestore(1);
                    }
                }
            }
            //equipment
            case EQUIPMENT -> {
                switch(name) {
                    //weapons
                    case "Wooden Sword" -> {
                        setDescription("A training weapon. Still useful in the hands " +
                                "of an experienced adventurer.");
                        setAttack(2*currentFloor);
                        setDefense(0);
                        setSpeed(currentFloor);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    case "Sword" -> {
                        setDescription("A straight and sturdy blade.");
                        setAttack(5*currentFloor);
                        setDefense(0);
                        setSpeed(0);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    case "Dagger" -> {
                        setDescription("A small weapon, favoring speed over power.");
                        setAttack(3*currentFloor);
                        setDefense(0);
                        setSpeed(2*currentFloor);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    case "Greatsword" -> {
                        setDescription("A large and unruly weapon that requires great strength to" +
                                "wield properly.");
                        setAttack(10*currentFloor);
                        setDefense(0);
                        setSpeed(-2*currentFloor);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    //armor
                    case "Woolen Shirt" -> {
                        setDescription("Thick and durable wool makes up this woven armor, which favors" +
                                "ease of movement over total defense.");
                        setAttack(0);
                        setDefense(currentFloor);
                        setSpeed(2*currentFloor);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    case "Leather Harness" -> {
                        setDescription("Strong, yet light straps of leather afford some protection while" +
                                "leaving the wearer unburdened by its weight.");
                        setAttack(0);
                        setDefense(5*currentFloor);
                        setSpeed(currentFloor);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    case "Mail Hauberk" -> {
                        setDescription("Thousands of links of chain make up this durable and protective armor.");
                        setAttack(0);
                        setDefense(10*currentFloor);
                        setSpeed(-currentFloor);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    case "Plate Mail" -> {
                        setDescription("Armor made of hard and flexible metal, which affords great protection" +
                                "at the cost of the wearer's mobility.");
                        setAttack(0);
                        setDefense(15*currentFloor);
                        setSpeed(-3*currentFloor);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    //accessories
                    case "Ring of Might" -> {
                        setDescription("A ring that gives the wearer increased strength when worn.");
                        setAttack(2*currentFloor);
                        setDefense(0);
                        setSpeed(0);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    case "Ring of Defense" -> {
                        setDescription("A ring that increases the wearer's natural durability.");
                        setAttack(0);
                        setDefense(2*currentFloor);
                        setSpeed(0);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    case "Ring of Speed" -> {
                        setDescription("A unique ring that makes the wearer light on their feet.");
                        setAttack(0);
                        setDefense(0);
                        setSpeed(2*currentFloor);
                        setMaxHealthPoints(0);
                        setRestore(0);
                    }
                    case "Vital Pendant" -> {
                        setDescription("A curious necklace that invigorates the wearer.");
                        setAttack(0);
                        setDefense(0);
                        setSpeed(0);
                        setMaxHealthPoints((int)((getMaxHealthPoints()*.10) + currentFloor + 2));
                        setRestore(0);
                    }
                }
            }
            //key items
            case KEY -> {
                //TODO to be implemented for narrative purposes
            }
        }
    }
    private ItemType type;

    private String name;

    private String description;

    private int attack;

    private int defense;

    private int speed;

    private int maxHealthPoints;

    private double restore;

    public Item(){
    }

    public Item(ItemType type, String name, int currentFloor) {
        this.type = type;
        setName(name);
        conjure(getName(), currentFloor);
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public double getRestore() {
        return restore;
    }

    public void setRestore(double restore) {
        this.restore = restore;
    }
}
