package reportGeneration.stepTwo;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.ItemsTable;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeTableView;
import javafx.stage.Screen;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;


public class ReportEditable extends ItemsTable {

    private ObservableList<Item> items;
    private int rootId;

    ReportEditable() {
        super(ItemsStorage.getItems());
        this.items = ItemsStorage.getItems();
        this.rootId = getRoot();
    }


    TabPane getTemplateEditable() {
        TabPane tabs = new TabPane();
        tabs.getStyleClass().add("report-tabs");
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        ObservableList<Item> Sheets = getChildren(rootId);
        for (Item Sheet : Sheets) {
            Tab tab = new Tab();
            Item sheet = Sheet;
            tab.setText(sheet.getName());
            TreeTableView<Item> table = getSingleTable(sheet.getId());
            tab.setContent(table);
            tabs.getTabs().add(tab);
        }
        return tabs;
    }

    private TreeTableView<Item> getSingleTable(int Id) {
        TreeTableView<Item> table = getTable(Id);
        table.getStyleClass().add("report-input");
        table.setEditable(true);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        table.setMinHeight(primaryScreenBounds.getHeight() - 150);
        table.setPrefWidth(880);
        TextEditHandler texthandler = new TextEditHandler();
        Columns cols = new Columns(texthandler);
        table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol());
        Periods periods = new Periods();
        ArrayList<String> arr = periods.getPeriodArr();
        for (String col : arr) {
            table.getColumns().add(cols.getPeriodCol(col));
        }
        return table;
    }


    private int getRoot() {
        for (Item item : this.items) {
            if (item.getParent() == 0) {
                return item.getId();
            }
        }
        return 0;
    }

}

