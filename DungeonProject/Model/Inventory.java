package DungeonProject.Model;

import ManualLinkedList.ChristianHolder;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The inventory object holds the player's items.
 */
public class Inventory {

    /**
     * subdivideInventory method gets the player inventory, and based on the player input,
     * creates a new list of only those items to be used by other methods.
     * @param itemType the type of item to print the inventory of.
     * @return Returns the inventory of the selected items.
     */

    public ChristianHolder<Item> subdivideInventory(ItemType itemType) {
        //make a new item list
        ChristianHolder<Item> newInventory = new ChristianHolder<>();
        int inventorySize = getItems().getSize();
        for (int i = 0; i < inventorySize; i++) {
            if(getItems().findNodeAtPosition(i).getE().getType() == itemType) {
                Item temp = getItems().findNodeAtPosition(i).getE();
                //populate it with item objects of the given type
                newInventory.add(temp);
            }
        }
        //return the section of the inventory to be used by other methods
        return newInventory;
    }

    private ChristianHolder<Item> inventory;

    public ChristianHolder<Item> getItems() {
        return inventory;
    }

    public void setItems(ChristianHolder<Item> theItems){
        inventory = theItems;
    }

    public Inventory(){
    }

    public Inventory(ChristianHolder<Item> inventory) {
        this.inventory = inventory;
    }
}