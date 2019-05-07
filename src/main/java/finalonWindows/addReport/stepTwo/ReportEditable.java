package finalonWindows.addReport.stepTwo;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.ItemsTable;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.stage.Screen;

import java.util.ArrayList;


public class ReportEditable extends ItemsTable {

    private ObservableList<Item> items;
    private int rootId;
    private ArrayList<TreeItem> roots;
    private ObservableMap<String, String> settings;

    ReportEditable(ObservableList<Item> items, ObservableMap<String, String> settings) {
        super(items);
        this.items = items;
        this.roots = new ArrayList<>();
        this.settings = settings;
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
        TreeTableView<Item> table =  getTable(Id);
        table.getStyleClass().add("report-input");
        table.setEditable(true);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        table.setMinHeight(primaryScreenBounds.getHeight() - 150);
        table.setPrefWidth(880);
        TextEditHandler texthandler = new TextEditHandler(items);
        Columns cols = new Columns(texthandler, settings);
        table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol());
        Periods periods = new Periods(settings);
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

