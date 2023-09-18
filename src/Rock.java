public class Rock implements ItemInterface {
    ItemDetails detail;

    public Rock(ItemDetails detail) {
        this.detail = detail;
    }

    @Override
    public InventoryTableRow getInventoryTableRow() {
        return new InventoryTableRow(
            detail.getName(),
            detail.getDescription(),
            String.valueOf(detail.getValue()),
            detail.getExpiration() == -1 ? "" : String.valueOf(detail.getExpiration())
        );
    }

    @Override
    public CartTableRow getCartRow(String column3) {
        return new CartTableRow(detail.getName(), String.valueOf(detail.getValue()), column3);
    }
}
