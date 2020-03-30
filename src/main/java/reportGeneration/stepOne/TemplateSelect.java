package reportGeneration.stepOne;

import database.template.DbItemHandler;
import entities.Item;
import entities.ItemConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.SettingsStorage;

public class TemplateSelect {

    public ComboBox<Item> get() {
        ComboBox<Item> templatesBox = new ComboBox<Item>();
        templatesBox.setConverter(new ItemConverter());
        Item root = getRoot();
        ObservableList<Item> items = DbItemHandler.getTemplates();
        Item toSelect = getSelected(items, root);
        templatesBox.setItems(items);
        templatesBox.getSelectionModel().select(toSelect);
        templatesBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> arg0, Item arg1, Item arg2) {
                if (arg2 != null) {
                    int id = arg2.getId();
                    SettingsStorage.replace("template", Integer.toString(id));
                    Item newRoot = DbItemHandler.getItem(id);
                    populateItems(newRoot);
                }
            }
        });

        populateItems(root);
        return templatesBox;
    }

    private Item getSelected(ObservableList<Item> items, Item root) {
        for (Item item : items) {
            if (item.getId() == root.getId()) {
                return item;
            }
        }
        return root;
    }


    private Item getRoot() {
        Integer id = SettingsStorage.getInt("template");
        int finalId = (id != null) ? id : 1;
        return DbItemHandler.getItem(finalId);
    }

    private void addAllChilds(int Id, ObservableList<Item> itemsList) {
        ObservableList<Item> items = DbItemHandler.getItems(Id);
        if (items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                addAllChilds(item.getId(), itemsList);
                itemsList.add(item);
            }
        }
    }


    private void populateItems(Item root) {
        ObservableList<Item> dbItems = FXCollections.observableArrayList();
        dbItems.add(root);
        addAllChilds(root.getId(), dbItems);
        ItemsStorage.setItems(dbItems);
    }

}


