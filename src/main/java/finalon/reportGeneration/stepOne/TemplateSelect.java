package finalon.reportGeneration.stepOne;

import finalon.database.template.DbItemHandler;
import finalon.defaultData.DefaultTemplate;
import finalon.entities.Item;
import finalon.entities.ItemConverter;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.SettingsStorage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class TemplateSelect {

    public static ComboBox get() {
        ComboBox<Item> templatesBox = new ComboBox<Item>();
        templatesBox.setConverter(new ItemConverter());
        ObservableList<Item> items = getTpls();
        templatesBox.setItems(items);
        Item item = getDefaultTemplate(items);
        if (item != null) {
            templatesBox.setValue(item);
        } else {
            templatesBox.getSelectionModel().selectFirst();
        }

        templatesBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> arg0, Item arg1, Item arg2) {
                if (arg2 != null) {
                    SettingsStorage.replace("template", Integer.toString(arg2.getId()));
                    ObservableList<Item> dbItems = DbItemHandler.getItems(arg2.getId());
                    if (dbItems.size() == 0) {
                        dbItems = FXCollections.observableArrayList(DefaultTemplate.getTpl());
                    } else {
                        dbItems.add(DbItemHandler.getItem(arg2.getId()));
                    }
                    ItemsStorage.setItems(dbItems);
                }
            }
        });
        return templatesBox;
    }

    private static ObservableList<Item> getTpls() {
        return DbItemHandler.getTemplates();
    }

    private static Item getDefaultTemplate(ObservableList<Item> items) {
        String val = SettingsStorage.get("template");
        if (val != null && val.length() > 0) {
            Item item = DbItemHandler.getItem(Integer.parseInt(val));
            if (item != null) {
                return item;
            }
        } else {
            if (items.size() > 0) {
                Item item = items.get(0);
                SettingsStorage.put("template", Integer.toString(item.getId()));

            } else {
                SettingsStorage.put("template", "1");
                Item item = new Item(1, "Default Template", "DefaultTemplate", true, false, 0, 0, 0);
                items.add(item);
                return item;
            }
        }
        return null;
    }

}


