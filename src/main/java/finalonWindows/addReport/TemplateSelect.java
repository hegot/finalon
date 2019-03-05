package finalonWindows.addReport;

import database.template.DbItemHandler;
import entities.Item;
import entities.ItemConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

public class TemplateSelect {
    public static ComboBox get(ObservableMap<String, Object> settings) {
        DbItemHandler dbItem = new DbItemHandler();
        ObservableList<Item> items = dbItem.getTemplates();
        ComboBox<Item> templatesBox = new ComboBox<Item>();
        templatesBox.setConverter(new ItemConverter());
        templatesBox.getSelectionModel().selectFirst();
        settings.put("template", items.get(0));
        templatesBox.setItems(items);
        templatesBox.getSelectionModel().selectFirst();
        templatesBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> arg0, Item arg1, Item arg2) {
                if (arg2 != null) {
                    settings.replace("template", arg2);
                }
            }
        });
        return templatesBox;
    }
}
