package globalReusables;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.Comparator;

public class ItemsGetter {
    private ObservableList<Item> items;
    private ObservableList<Item> outputItems;

    public ItemsGetter(
            Item root,
            ObservableList<Item> items
    ) {
        this.items = items;
        this.outputItems = FXCollections.observableArrayList();
        loopItems(root.getId());
        outputItems.add(root);
    }

    public ItemsGetter(
            int id,
            ObservableList<Item> items
    ) {
        this.items = items;
        this.outputItems = FXCollections.observableArrayList();
        loopItems(id);
    }


    private void loopItems(int parentId) {
        ObservableMap<String, Double> vals;
        for (Item item : items) {
            if (item.getParent() == parentId) {
                    if (item.getValues().size() > 0) {
                        vals = item.getValues();
                        boolean isAllZero = true;
                        for (String key : vals.keySet()) {
                            if (vals.get(key) != 0.0) {
                                isAllZero = false;
                                break;
                            }
                        }
                        if (!isAllZero) {
                            loopItems(item.getId());
                            outputItems.add(item);
                        }
                    }
            }
        }
    }

    public ObservableList<Item> getItems() {
        boolean sorted = true;
        for (Item outputItem : outputItems) {
            if (outputItem.getWeight() == -1) {
                sorted = false;
            }
        }
        if (!sorted) {
            for (int i = 0; i < outputItems.size(); i++) {
                Item sortItem = outputItems.get(i);
                sortItem.setWeight(i);
            }
        }
        return outputItems;
    }

}
