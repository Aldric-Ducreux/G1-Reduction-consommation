package sample.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

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

    public String Serialize() {
        String json = "{";
        json += "\"name\":\"" + name + "\",";
        json += "\"tag\":\"" + tag + "\",";
        json += "\"quantity\":" + quantity + ",";
        json += "\"expiryDate\":" + expiryDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        json += "}";
        return json;
    }

    public static Item Deserialize(String json) {
        String name = null;
        String tag = null;
        int quantity = 0;
        LocalDate expiryDate = null;

        json = json.substring(1, json.length()-1);
        String[] attributes = json.split(",");
        for(String attribute: attributes) {
            String[] keyValuePair = attribute.split(":");
            switch(keyValuePair[0]) {
                case "\"name\"": name = keyValuePair[1].substring(1, keyValuePair[1].length()-1); break;
                case "\"tag\"": tag = keyValuePair[1].substring(1, keyValuePair[1].length()-1); break;
                case "\"quantity\"": quantity = Integer.parseInt(keyValuePair[1]); break;
                case "\"expiryDate\"": expiryDate = LocalDate.parse(keyValuePair[1], DateTimeFormatter.BASIC_ISO_DATE); break;
            }
        }
        return new Item(name, tag, quantity, expiryDate);
    }

}
