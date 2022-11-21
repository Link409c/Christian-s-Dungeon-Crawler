package DungeonProject.View;

import DungeonProject.Model.Item;
import ManualLinkedList.ChristianHolder;
import ManualLinkedList.Node;

/**
 * Inventory Printer displays the player inventory and the player equipment.
 */
public class InventoryPrinter {
	
	/**
     * printItems prints all the items in a given inventory.
     * @param anInventory the inventory to be printed.
     */
    public void printItems(ChristianHolder<Item> anInventory) {
    	if(anInventory.getHead() == null) {
			System.out.println("You have no items of this type in your inventory.");
		}else{
			Node<Item> curr = anInventory.getHead();
			Item item = curr.getE();
			int thisSize = anInventory.getSize();
			String itemType = item.getType().toString();
			WindowPrinter w = new WindowPrinter();
			w.printItemsHeader(itemType);
			for (int i = 0; i < thisSize; i++) {
				System.out.println(item.getName());
				item = curr.getNext().getE();
			}
		}
    }
    
    public InventoryPrinter() {
    	
    }
}
