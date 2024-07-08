package project.customer;

import java.sql.Timestamp;

public class PurchaseHistoryVO {
    private String itemName;
    private int quantity;
    private int price;
    private Timestamp purchaseDate;

    public PurchaseHistoryVO(String itemName, int quantity, int price, Timestamp purchaseDate) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

}
