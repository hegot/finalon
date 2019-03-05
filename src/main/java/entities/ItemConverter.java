package entities;

import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

public class ItemConverter  extends StringConverter<Item> {
    private Map<String, Item> itemMap = new HashMap<String, Item>();

    @Override
    public String toString(Item item) {
        return item == null ? null : item.getName();
    }

    // Method to convert a String to a Person-Object
    @Override
    public Item fromString(String name) {
        return itemMap.get(name);
    }
}