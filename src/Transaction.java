import java.util.Optional;

public abstract class Transaction {
    String name;
    Inventory inventory;

    public Transaction(String name, Inventory inventory){
        this.name = name;
        this.inventory = inventory;
    }

    public void buy(ItemInterface item) {
        inventory.addOne(item);
    }

    public Optional<ItemInterface> sell(String itemName) {
        return Optional.ofNullable(removeItem(itemName));
    }

    public ItemInterface removeItem(String itemName) {
        return inventory.removeOne(itemName);
    }

    public void addItem(ItemInterface item) {
        inventory.addOne(item);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }
}
