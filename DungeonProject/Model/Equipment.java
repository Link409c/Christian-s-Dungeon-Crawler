package DungeonProject.Model;

import RomanNumeralConverter.IntegerToRoman;

/**
 * Equipment is an Item that can be "equipped" by the player to enhance their stats.
 */
public class Equipment extends Item{

    /**
     * categorizeEClassByName sets equipment class according to the item's name.
     * @param name the name of the item.
     */
    public void categorizeEClassByName(String name){
        //weapons
        if(name.equalsIgnoreCase("Wooden Sword") || name.equalsIgnoreCase("Sword")
                || name.equalsIgnoreCase("Dagger") || name.equalsIgnoreCase("Greatsword")) {
            setEquipmentClass(EquipmentClass.WEAPON);
        }
        //armor
        else if(name.equalsIgnoreCase("Woolen Shirt") ||
                name.equalsIgnoreCase("Leather Tunic") ||
                name.equalsIgnoreCase("Mail Hauberk") ||
                name.equalsIgnoreCase("Plate Mail")){
            setEquipmentClass(EquipmentClass.ARMOR);
        }
        //accessories
        else if(name.equalsIgnoreCase("Ring of Might") ||
                name.equalsIgnoreCase("Ring of Defense") ||
                name.equalsIgnoreCase("Ring of Speed") ||
                name.equalsIgnoreCase("Vital Pendant")){
            setEquipmentClass(EquipmentClass.ACCESSORY);
        }
    }
    private EquipmentClass equipmentClass;

    public Equipment(String name, int currentFloor) {
        super(ItemType.EQUIPMENT, name, currentFloor);
        categorizeEClassByName(name);
        //roman numeral denotes equipment power / quality
        IntegerToRoman itr = new IntegerToRoman();
        String rank;
        if(currentFloor < 11){
            rank = "0";
        }
        else {
            rank = itr.intToRoman(Math.floorDiv(currentFloor, 10));
        }
        setName(getName() + " " + rank);
    }

    public void setEquipmentClass(EquipmentClass equipmentClass) {
        this.equipmentClass = equipmentClass;
    }

    public EquipmentClass getEquipmentClass() {
        return equipmentClass;
    }
}
