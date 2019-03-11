package finalonWindows.addReport.report;

import finalonWindows.templateScene.templates.TreeBuilder;
import entities.Item;
import finalonWindows.templateScene.templates.eventHandlers.RemoveHandler;
import finalonWindows.templateScene.templates.eventHandlers.TextEditHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.stage.Screen;

import java.util.ArrayList;


public class ReportEditable {

    private ObservableList<Item> items;
    private int rootId;
    private ArrayList<TreeItem> roots;
    private ObservableMap<String, String> settings;

    public ReportEditable(ObservableList<Item> items, ObservableMap<String, String> settings) {
        this.items = items;
        this.roots = new ArrayList<>();
        this.settings = settings;
        setRoot();
    }



    public TabPane getTemplateEditable() {
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        ObservableList<Item> Sheets = getChildren(rootId);
        for (Item Sheet : Sheets) {
            Tab tab = new Tab();
            Item sheet = Sheet;
            tab.setText(sheet.getName());
            TreeTableView<Item> table = getSingleTable(sheet.getId(), sheet.getName());
            tab.setContent(table);
            tabs.getTabs().add(tab);
        }
        return tabs;
    }

    private TreeTableView<Item> getSingleTable(int Id, String SheetName) {
        TreeTableView<Item> table = new TreeTableView<>();
        table.setEditable(true);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        table.setMinHeight(primaryScreenBounds.getHeight() - 150);
        TextEditHandler texthandler = new TextEditHandler();
        RemoveHandler removeHandler = new RemoveHandler();
        Columns cols = new Columns(texthandler, removeHandler);
        if (SheetName.equals("Statement of Financial Position \n (Balance Sheet)") || SheetName.equals("Other Data")) {
            table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol(), cols.isPositiveCol(), cols.buttonCol());
        } else {
            table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol(), cols.isPositiveCol(), cols.finResultCol(), cols.buttonCol());
        }
        TreeBuilder treeBuilder = new TreeBuilder(Id, this.items);
        TreeItem rootNode = treeBuilder.getTree();
        table.setRoot(rootNode);
        roots.add(rootNode);
        getPeriods();
        return table;
    }

    private String  getPeriods(){

        Period period = new Period(settings);
        ArrayList<String> periods = period.getPeriods();

        return "asd";
    }


    private void populateList(TreeItem<Item> item, ObservableList<Item> items) {

        if (item.getChildren().size() > 0) {
            Item node = (Item) item.getValue();
            items.add(node);
            for (TreeItem<Item> subItem : item.getChildren()) {
                populateList(subItem, items);
            }
        } else {
            Item node = (Item) item.getValue();
            items.add(node);
        }
    }


    public void setRoot() {
        for (Item item : this.items) {
            if (item.getParent() == 0) {
                this.rootId = item.getId();
            }
        }
    }


    private ObservableList<Item> getChildren(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : items) {
            if (item.getParent() == id) {
                Items.add(item);
            }
        }
        return Items;
    }

    public ObservableList<Item> getItems() {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (TreeItem rootNode : this.roots) {
            populateList(rootNode, Items);
        }
        return Items;
    }

}

