package DungeonProject.Control;

import DungeonProject.Model.*;
import DungeonProject.View.InventoryPrinter;
import DungeonProject.View.MenuPrinter;
import ManualLinkedList.ChristianHolder;
import java.util.Scanner;


/**
 * Inventory Menu allows the user to control player options when accessing the inventory object.
 */
public class InventoryMenu {

    /**
     * inventoryMenu method will control the player's inventory in the game. Player will be
     * prompted for input, and can view the inventory, check item descriptions, use and drop
     * Items, and return to the previous menu.
     * @param p the player object currently in the game.
     */
    public Player accessMenu(Player p) {
        MenuPrinter m = new MenuPrinter();
        m.printInventoryMenu();
        Scanner in = new Scanner(System.in);
        int choice;
        try{
            choice = in.nextInt();
            tryInput(choice);
        }catch(MenuInputException invalidInput){
            choice = in.nextInt();
        }
        Inventory currInventory = p.getInventory();
        ChristianHolder<Item> subInventory;
        String itemName;
        //player action according to input
        switch (choice) {
            case 1 -> {
                subInventory = currInventory.subdivideInventory(ItemType.USABLE);
                InventoryPrinter invPr = new InventoryPrinter();
                invPr.printItems(subInventory);
                m.printUsableMenu();
                try{
                    choice = in.nextInt();
                    tryInput(choice);
                }catch(MenuInputException invalidInput){
                    choice = in.nextInt();
                }
                switch (choice) {
                    case 1 -> {
                        System.out.println("Which Item? :: ");
                        in.nextLine();
                        itemName = in.nextLine();
                        p.useItem(subInventory, itemName, "use");
                    }
                    case 2 -> {
                        System.out.println("Which Item? :: ");
                        in.nextLine();
                        itemName = in.nextLine();
                        p.useItem(subInventory, itemName, "description");
                    }
                    case 3 -> {
                        System.out.println("Which Item? :: ");
                        in.nextLine();
                        itemName = in.nextLine();
                        p.useItem(subInventory, itemName, "drop");
                    }
                    case 4 -> {
                        break;
                    }
                }

            }
            case 2 -> {
                subInventory = currInventory.subdivideInventory(ItemType.EQUIPMENT);
                InventoryPrinter invPr = new InventoryPrinter();
                invPr.printItems(subInventory);
                m.printEquipmentMenu();
                try{
                    choice = in.nextInt();
                    tryInput(choice);
                }catch(MenuInputException invalidInput){
                    choice = in.nextInt();
                }
                switch (choice) {
                    case 1 -> {
                        System.out.println("Which Item? :: ");
                        in.nextLine();
                        itemName = in.nextLine();
                        p.useItem(subInventory, itemName, "equip");
                    }
                    case 2 -> {
                        System.out.println("Which Item? :: ");
                        in.nextLine();
                        itemName = in.nextLine();
                        p.useItem(subInventory, itemName, "description");
                    }
                    case 3 -> {
                        System.out.println("Which Item? :: ");
                        in.nextLine();
                        itemName = in.nextLine();
                        p.useItem(subInventory, itemName, "drop");
                    }
                    case 4 -> {
                        break;
                    }
                }

            }
            case 3 -> {
                subInventory = currInventory.subdivideInventory(ItemType.KEY);
                InventoryPrinter invPr = new InventoryPrinter();
                invPr.printItems(subInventory);
                m.printKeyItemsMenu();
                try{
                    choice = in.nextInt();
                    tryInput(choice);
                }catch(MenuInputException invalidInput){
                    choice = in.nextInt();
                }
                switch (choice) {
                    case 1 -> {
                        System.out.println("Which Item? :: ");
                        in.nextLine();
                        itemName = in.nextLine();
                        p.useItem(subInventory, itemName, "use");
                    }
                    case 2 -> {
                        System.out.println("Which Item? :: ");
                        in.nextLine();
                        itemName = in.nextLine();
                        p.useItem(subInventory, itemName, "description");
                    }
                    case 3 -> {
                        System.out.println("Which Item? :: ");
                        in.nextLine();
                        itemName = in.nextLine();
                        p.useItem(subInventory, itemName, "drop");
                    }
                    case 4 -> {
                        break;
                    }
                }
            }
            case 4 -> {
                break;
            }
        }
        return p;
    }

    public void tryInput(int choice) throws MenuInputException {
        if (choice < 1 || choice > 4) {
            throw new MenuInputException();
        }
    }
}