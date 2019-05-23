package reportGeneration.stepTwo;

import database.template.DbItemHandler;
import defaultData.DefaultTemplate;
import entities.Item;
import finalonWindows.SceneBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;
import reportGeneration.ItemsStorage;
import reportGeneration.SettingsStorage;

public class StepTwo extends SceneBase {
    private ObservableList<Item> items;

    public StepTwo() {
        this.items = ItemsStorage.getItems();
    }

    public TabPane show() {
        setItems();
        return new ReportEditable().getTemplateEditable();
    }

    private void setItems() {
        if (items.size() == 0) {
            int tpl = Integer.parseInt(SettingsStorage.getSettings().get("template"));
            DbItemHandler itemsHandler = new DbItemHandler();
            ObservableList<Item> dbItems = itemsHandler.getItems(tpl);
            if (dbItems.size() == 0) {
                dbItems = FXCollections.observableArrayList(
                        DefaultTemplate.getTpl()
                );
            } else {
                dbItems.add(itemsHandler.getItem(tpl));
            }
            items.addAll(dbItems);
        }
    }

}

