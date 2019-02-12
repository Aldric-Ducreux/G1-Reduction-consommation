package sample;

import java.util.Date;

public class Item {

    private String name;
    private String tag;
    private int quantity;
    private Date expiryDate;

    public Item(String name, String tag, int quantity, Date expiryDate) {
        this.name = name;
        this.tag = tag;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
