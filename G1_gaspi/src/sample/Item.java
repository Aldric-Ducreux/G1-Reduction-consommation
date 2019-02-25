package sample;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Item {

    private String name;
    private String tag;
    private int quantity;
    private LocalDate expiryDate;

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

    public static String SerializeCollection(Collection<Item> items) {
        String json = "{[";
        Iterator<Item> iterator = items.iterator();
        while(iterator.hasNext()) {
            json += iterator.next().Serialize();
            if (iterator.hasNext())
                json += ";";
        }
        json += "]}";
        return json;
    }

    public static Collection<sample.Item> DeserializeCollection(String json) {
        Collection<sample.Item> items = new ArrayList<>();
        json = json.substring(2, json.length()-2); // remove the brackets
        for(String item: json.split(";")) {
            items.add(sample.Item.Deserialize(item));
        }
        return items;
    }
}
