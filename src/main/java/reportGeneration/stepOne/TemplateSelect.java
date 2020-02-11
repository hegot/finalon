package reportGeneration.stepOne;

import database.template.DbItemHandler;
import defaultData.DefaultTemplate;
import entities.Item;
import entities.ItemConverter;
import globalReusables.StandardAndIndustry;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.util.Duration;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.SettingsStorage;

public class TemplateSelect {

    private static ComboBox<Item> tplSelect = createTemplateSelect();

    public static ComboBox getTpl() {
        return tplSelect;
    }

    public static void reInit() {
        ObservableList<Item> items = getTpls();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> {
                    tplSelect.setItems(items);
                    tplSelect.getSelectionModel().selectFirst();
                }));
        timeline.play();
    }

    private static ComboBox<Item> createTemplateSelect() {
        ComboBox<Item> templatesBox = new ComboBox<Item>();
        templatesBox.setConverter(new ItemConverter());
        ObservableList<Item> items = getTpls();
        templatesBox.setItems(items);
        templatesBox.getSelectionModel().selectFirst();
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
        int industryId = StandardAndIndustry.getIndustryId();
        ObservableList<Item> tpls = DbItemHandler.getTemplateForIndustry(industryId);
        if (tpls.size() > 0) {
            return tpls;
        } else {
            ObservableList<Item> items = FXCollections.observableArrayList();
            items.add(new Item(1, "Default Template", "DefaultTemplate", true, false, 0, 0, 0));
            return items;
        }
    }


}


