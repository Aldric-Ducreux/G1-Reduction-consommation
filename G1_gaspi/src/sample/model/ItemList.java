package sample.model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ItemList {

    private List<Item> items;
    private String path;

    private ItemList() {
        this.items = new ArrayList<>();
    }

    private ItemList(Collection<Item> items) {
        this.items = new ArrayList<>(items);
    }
    
    public List<Item> sortedBy(Comparator<Item> comparator) {
        return items.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public static ItemList loadFromFile(String path) {
        ItemList itemList = null;
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String json = reader.readLine();
            reader.close();
            itemList = deserialize(json);
        } catch(IOException e) {
            itemList = new ItemList();
            e.printStackTrace();
        }
        itemList.setPath(path);
        return itemList;
    }

    public boolean saveToFile() {
        boolean errorOccured = false;
        try {
            FileWriter writer = new FileWriter(path);
            String json = serialize();
            System.out.println(json);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            errorOccured = true;
        }
        return errorOccured;
    }

    private String serialize() {
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

    private static ItemList deserialize(String json) {
        List<Item> list = new ArrayList<>();
        try {
            json = json.substring(2, json.length() - 2); // remove the brackets
            for (String item : json.split(";")) {
                list.add(Item.Deserialize(item));
            }
        } catch (StringIndexOutOfBoundsException ignored) {}
        return new ItemList(list);
    }
}
