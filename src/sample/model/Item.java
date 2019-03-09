package sample.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Item {

    private String name;
    private String tag;
    private int quantity;
    private LocalDate expiryDate;

    public static final Comparator<Item> NameAscComparator = (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName());
    public static final Comparator<Item> NameDescComparator = (o1, o2) -> -o1.getName().compareToIgnoreCase(o2.getName());
    public static final Comparator<Item> TagAscComparator = (o1, o2) -> o1.getTag().compareToIgnoreCase(o2.getTag());
    public static final Comparator<Item> TagDescComparator = (o1, o2) -> -o1.getTag().compareToIgnoreCase(o2.getTag());
    public static final Comparator<Item> QuantityAscComparator = Comparator.comparingInt(Item::getQuantity);
    public static final Comparator<Item> QuantityDescComparator = (o1, o2) -> o2.getQuantity() - o1.getQuantity();
    public static final Comparator<Item> DateAscComparator = Comparator.comparing(Item::getExpiryDate);
    public static final Comparator<Item> DateDescComparator = (o1, o2) -> -o1.getExpiryDate().compareTo(o2.getExpiryDate());

    public Item(String name, String tag, int quantity, LocalDate expiryDate) {
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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }


}
