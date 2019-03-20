package finalonWindows.addReport.stepTwo;

import database.template.DbItemHandler;
import defaultData.DefaultTemplate;
import entities.Item;
import finalonWindows.SceneBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class StepTwo extends SceneBase {
    private ObservableMap<String, String> settings;
    private ReportEditable report;
    private ObservableList<Item> items;
    private VBox parent;

    public StepTwo(ObservableMap<String, String> settings, ObservableList<Item> items, VBox parent) {
        this.settings = settings;
        this.items = items;
        this.parent = parent;
    }

    public TabPane show() {
        setItems();
        this.report = new ReportEditable(items, settings);
        return report.getTemplateEditable();
    }

    private void setItems() {
        if (this.items.size() == 0) {
            int tpl = Integer.parseInt(settings.get("template"));
            DbItemHandler itemsHandler = new DbItemHandler();
            ObservableList<Item> dbItems = itemsHandler.getItems(tpl);
            if (dbItems.size() == 0) {
                dbItems = FXCollections.observableArrayList(DefaultTemplate.getTpl());
            } else {
                dbItems.add(itemsHandler.getItem(tpl));
            }
            this.items.addAll(dbItems);
        }
    }

}

