import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;

public class Basket implements BasketInterface {
    ArrayList<ItemInterface> items;
    ArrayList<Integer> quantities;

    public Basket() {
        items = new ArrayList<>();
        quantities = new ArrayList<>();
    }

    public OptionalInt itemIndex(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getInventoryTableRow().getColumnOne().equalsIgnoreCase(itemName)) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

    public ArrayList<CartTableRow> getRowData() {
        ArrayList<CartTableRow> data = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            data.add(items.get(i).getCartRow(quantities.get(i) + ""));
        }

        return data;
    }

    @Override
    public void setItemQuantity(String itemName, int qty) {
        OptionalInt index = itemIndex(itemName);
        if (index.isPresent()) {
            quantities.set(index.getAsInt(), qty);
        }
    }

    public void add(ItemInterface item) {
        OptionalInt index = itemIndex(item.getInventoryTableRow().getColumnOne());
        if (index.isPresent()) {
            quantities.set(index.getAsInt(), quantities.get(index.getAsInt()) + 1);
        } else {
            items.add(item);
            quantities.add(1);
        }
    }

    @Override
    public void remove(String itemName) {
        OptionalInt index = itemIndex(itemName);

        if (index.isPresent()) {
            items.remove(index.getAsInt());
            quantities.remove(index.getAsInt());
        }
    }

    @Override
    public void processTransaction(Player from, Seller to) {
        ArrayList<ItemInterface> transactionItems = new ArrayList<>();
        boolean rollback = false;
        // Remove/sell items from the `from` parameter
        for (int i = 0; i < items.size() && !rollback; i++) {
            for (int q = 0; q < quantities.get(i); q++) {
                Optional<ItemInterface> saleItemOptional = from.sell(items.get(i).getInventoryTableRow().getColumnOne());
                if (saleItemOptional.isEmpty()) {
                    rollback = true;
                    break;  // Trigger transaction rollback
                }
                ItemInterface saleItem = saleItemOptional.get();
                transactionItems.add(saleItem);
            }
        }

        if (rollback) {
            for (ItemInterface item : transactionItems) {
                from.buy(item);  // Return to `from`
            }
        } else {
            for (ItemInterface item : transactionItems) {
                to.buy(item);  // Have `to` buy each of the transaction items
            }
        }
    }

    @Override
    public void processTransaction(Seller from, Player to) {
        ArrayList<ItemInterface> transactionItems = new ArrayList<>();
        boolean rollback = false;
        // Remove/sell items from the `from` parameter
        for (int i = 0; i < items.size() && !rollback; i++) {
            for (int q = 0; q < quantities.get(i); q++) {
                //change type of saleItem to match OptionalInt of sell method
                Optional<ItemInterface> saleItemOptional = from.sell(items.get(i).getInventoryTableRow().getColumnOne());
                if (saleItemOptional.isEmpty()) {
                    rollback = true;
                    break;  // Trigger transaction rollback
                }
                //retrieve value of saleItem, match type
                ItemInterface saleItem = saleItemOptional.get();
                transactionItems.add(saleItem);
            }
        }
        if (rollback) {
            for (ItemInterface item : transactionItems) {
                from.buy(item);  // Return to `from`
            }
        } else {
            for (ItemInterface item : transactionItems) {
                to.buy(item);  // Have `to` buy each of the transaction items
            }
        }
    }

}
