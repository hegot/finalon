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
    public static ComboBox get(ObservableMap<String, String> settings) {
        DbItemHandler dbItem = new DbItemHandler();
        ObservableList<Item> items = dbItem.getTemplates();
        ComboBox<Item> templatesBox = new ComboBox<Item>();
        templatesBox.setConverter(new ItemConverter());
        if(items.size() > 0){
            settings.put("template", Integer.toString(items.get(0).getId()));
        }else{
            settings.put("template", "1");
            items.add(new Item(1, "Default Template", "DefaultTemplate", true, false, 0, 0));
        }
        templatesBox.setItems(items);
        templatesBox.getSelectionModel().selectFirst();
        templatesBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> arg0, Item arg1, Item arg2) {
                if (arg2 != null) {
                    settings.replace("template", Integer.toString(arg2.getId()));
                }
            }
        });
        return templatesBox;
    }
}
