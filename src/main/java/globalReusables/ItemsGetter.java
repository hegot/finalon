package globalReusables;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemsGetter {
    private ObservableList<Item> items;
    private ObservableList<Item> outputItems;
    private Boolean checkEmpty;

    public ItemsGetter(
            Item root,
            ObservableList<Item> items,
            Boolean checkEmpty
    ) {
        this.items = items;
        this.outputItems = FXCollections.observableArrayList();
        this.checkEmpty = checkEmpty;
        loopItems(root.getId());
        outputItems.add(root);
    }

    public ItemsGetter(
            int id,
            ObservableList<Item> items
    ) {
        this.items = items;
        this.checkEmpty = false;
        this.outputItems = FXCollections.observableArrayList();
        loopItems(id);
    }


    public ItemsGetter(
            int id,
            ObservableList<Item> items,
            Boolean checkEmpty
    ) {
        this.items = items;
        this.checkEmpty = checkEmpty;
        this.outputItems = FXCollections.observableArrayList();
        loopItems(id);
    }


    private void loopItems(int parentId) {
        for (Item item : items) {
            if (item.getParent() == parentId) {
                if (checkEmpty) {
                    if (item.getValues().size() > 0) {
                        loopItems(item.getId());
                        outputItems.add(item);
                    }
                } else {
                    loopItems(item.getId());
                    outputItems.add(item);
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
