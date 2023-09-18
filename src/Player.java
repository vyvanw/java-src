import java.util.ArrayList;
import java.util.Optional;

public class Player extends Transaction{
    private String name;
    private Inventory inventory;
    private double money;
    private Basket shoppingBasket;
    private Inventory viewOfStoreInventory;

    public Player(String playerName, double startingMoney, Inventory startingInventory) {
        super(playerName, startingInventory);
        this.inventory = startingInventory;
        money = startingMoney;
        shoppingBasket = new Basket();
    }

    /**
     * Attempts to perform a purchase of the parameter item.
     * If the player has enough money for the item, their money is reduced,
     * and the item is purchased. Otherwise, no changes are made.
     * @param item
     */

    @Override
    public void buy(ItemInterface item) {
        if (Double.valueOf(item.getInventoryTableRow().getColumnThree().trim()) > money) {
            return;
        }
        super.buy(item);
        money -= Double.valueOf(item.getInventoryTableRow().getColumnThree().trim());
    }

    /**
     * Attempt to sell an item by name. If an item with a matching name is
     * found, the players money is increased by the value of the item, and
     * the item is removed and returned.
     * @param itemName
     */

    @Override
    public Optional<ItemInterface> sell(String itemName) {
        Optional<ItemInterface> item = super.sell(itemName);
        if (item.isPresent()) {
            money += Double.valueOf(item.get().getInventoryTableRow().getColumnThree().trim());
            return item;
        }
        return Optional.empty();
    }

    /**
     * Adds an item to the held Inventory.
     * @param item
     */
    public void addItem(ItemInterface item) {
        inventory.addOne(item);
    }

    /**
     * Removes and returns an item from the held Inventory that matches
     * the `itemName` parameter.
     * @param itemName
     */
    public ItemInterface removeItem(String itemName) {
        return inventory.removeOne(itemName);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public Basket getShoppingBasket() {
        return shoppingBasket;
    }

    public void addToShoppingBasket(ItemInterface itemDataRow) {
        shoppingBasket.add(itemDataRow);
    }

    public void removeFromShoppingBasket(String itemName) {
        shoppingBasket.remove(itemName);
    }

    public ArrayList<ItemInterface> getStoreInventoryView() {
        return viewOfStoreInventory.getStock();
    }

    public Inventory getStoreView() {
        return viewOfStoreInventory;
    }

    public void setStoreView(Inventory storeInventory) {
        viewOfStoreInventory = storeInventory;
    }

    public double getMoney() {
        return money;
    }
    
}
