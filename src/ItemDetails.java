public class ItemDetails {
    private String name;
    private String description;
    private double value;
    private int expiration;

    public ItemDetails(String name, String description, double value, int expiration) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.expiration = expiration;
    }

    // Getter for the item's name
    public String getName() {
        return name;
    }

    // Setter for the item's name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the item's description
    public String getDescription() {
        return description;
    }

    // Setter for the item's description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for the item's value
    public double getValue() {
        return value;
    }

    // Setter for the item's value
    public void setValue(double value) {
        this.value = value;
    }

    // Getter for the item's expiry
    public int getExpiration() {
        return expiration;
    }

    // Setter for the item's expiry
    public void setExpiry(int expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "ItemDetail [name=" + name + ", description=" + description + ", value=" + value + ", expiry=" + expiration + "]";
    }

    public InventoryTableRow toInventoryTableRow() {
        return new InventoryTableRow(name, description, String.valueOf(value), expiration == -1 ? "" : String.valueOf(expiration));
    }

}
