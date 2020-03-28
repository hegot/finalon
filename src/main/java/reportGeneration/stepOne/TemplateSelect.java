package reportGeneration.stepOne;

import database.template.DbItemHandler;
import defaultData.DefaultTemplate;
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

    private static ComboBox<Item> tplSelect = createTemplateSelect();

    public static ComboBox getTpl() {
        return tplSelect;
    }


    private static ComboBox<Item> createTemplateSelect() {
        ComboBox<Item> templatesBox = new ComboBox<Item>();
        templatesBox.setConverter(new ItemConverter());
        ObservableList<Item> items = DbItemHandler.getTemplates();
        templatesBox.setItems(items);
        templatesBox.getSelectionModel().selectFirst();
        templatesBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> arg0, Item arg1, Item arg2) {
                if (arg2 != null) {
                    int id  = arg2.getId();
                    SettingsStorage.replace("template", Integer.toString(id));
                    ObservableList<Item> dbItems = FXCollections.observableArrayList();
                    dbItems.add(DbItemHandler.getItem(id));
                    addAllChilds(id, dbItems);
                    ItemsStorage.setItems(dbItems);
                }
            }
        });
        return templatesBox;
    }

    private static void addAllChilds(int Id, ObservableList<Item> itemsList) {
        ObservableList<Item> items = DbItemHandler.getItems(Id);
        if (items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                addAllChilds(item.getId(), itemsList);
                itemsList.add(item);
            }
        }
    }



}


