package finalonWindows.reusableComponents.ItemsTable;

import entities.Item;
import finalonWindows.templateScene.templates.TreeBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

import java.util.ArrayList;

public class ItemsTable {

    private ObservableList<Item> items;
    private int rootId;
    private ArrayList<TreeItem> roots;
    private ObservableMap<String, String> settings;

    ItemsTable(ObservableList<Item> items, ObservableMap<String, String> settings) {
        this.items = items;
        this.roots = new ArrayList<>();
        this.settings = settings;
        setRoot();
    }

    protected TreeTableView<Item> getTable(int Id) {
        TreeTableView<Item> table = new TreeTableView<>();
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


    public ObservableList<Item> getItems() {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (TreeItem rootNode : this.roots) {
            populateList(rootNode, Items);
        }
        return Items;
    }

}
