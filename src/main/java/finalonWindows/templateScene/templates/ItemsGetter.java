package finalonWindows.templateScene.templates;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemsGetter {
    private ObservableList<Item> items;
    private ObservableList<Item> outputItems;

    public ItemsGetter(
            int id,
            ObservableList<Item> items
    ) {
        this.items = items;
        this.outputItems = FXCollections.observableArrayList();
        loopItems(id);
    }


    private void loopItems(int parentId) {
        for (Item item : items) {
            int par = item.getParent();
            if (par == parentId) {
                loopItems(item.getId());
                outputItems.add(item);
            }
        }
    }

    public ObservableList<Item> getItems() {
        return outputItems;
    }

}
