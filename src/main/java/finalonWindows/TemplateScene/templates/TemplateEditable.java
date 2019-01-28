package finalonWindows.TemplateScene.templates;

import entities.Item;
import finalonWindows.TemplateScene.templates.eventHandlers.RemoveHandler;
import finalonWindows.TemplateScene.templates.eventHandlers.TextEditHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

import java.util.ArrayList;


public class TemplateEditable {

    private ObservableList<Item> items;
    private int rootId;
    private ArrayList<TreeItem> roots;

    public TemplateEditable(ObservableList<Item> items) {
        this.items = items;
        this.roots = new ArrayList<>();
        setRoot();
    }

    public TabPane getTemplateEditable() {
        TabPane tabs = new TabPane();
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
        TreeTableView<Item> table = new TreeTableView<>();
        table.setEditable(true);
        table.setMinHeight(780);
        TextEditHandler texthandler = new TextEditHandler();
        RemoveHandler removeHandler = new RemoveHandler();
        Columns cols = new Columns(texthandler, removeHandler);
        table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol(), cols.isPositiveCol(), cols.buttonCol());
        TreeBuilder treeBuilder = new TreeBuilder(Id, this.items);
        TreeItem rootNode = treeBuilder.getTree();
        table.setRoot(rootNode);
        roots.add(rootNode);
        return table;
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
        for (Item item : this.items) {
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

