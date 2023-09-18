import java.util.ArrayList;
import java.util.OptionalInt;

public class Inventory {
    protected ArrayList<ItemInterface> stock;

    public Inventory() {
        stock = new ArrayList<>();
    }

    public Inventory(ArrayList<ItemInterface> startingStock) {
        stock = startingStock;
    }

    /**
     * Create an Inventory that is a view of `copy`. The held "stock" of the resulting
     * Inventory will be references to the instances referred to by the `copy` constructor.
     * @param copy
     */
    public Inventory(Inventory copy) {
        stock = new ArrayList<>(copy.stock.size());
        for (ItemInterface item : copy.stock) {
            stock.add(item);
        }
    }

    public ArrayList<ItemInterface> getStock() {
        return stock;
    }

    /**
     * Adds an Item instance to the inventories stock.
     * Sort is called using the current/existing sort strategy.
     * @param item
     */
    public void addOne(ItemInterface item) {
        stock.add(item);
    }

    /**
     * Remove and return an item with a specified name.
     * @param itemName
     * @return An Item matching the `itemName`
     */
    public ItemInterface removeOne(String itemName) {   
        OptionalInt removeFromIdx = indexOfItemByName(itemName);
        if (!removeFromIdx.isPresent()) {
            return null;
        }

        return stock.remove(removeFromIdx.getAsInt());
    }

    /**
     * Find the index of an item by name.
     * @param itemName
     */
    private OptionalInt indexOfItemByName(String itemName) {
        for (int i = 0; i < stock.size(); i++) {
            ItemInterface cur = stock.get(i);
            
            if (cur.getInventoryTableRow().getColumnOne().equals(itemName)) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

}
