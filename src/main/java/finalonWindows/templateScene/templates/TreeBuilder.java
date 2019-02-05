package finalonWindows.templateScene.templates;

import entities.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

class TreeBuilder {
    private TreeItem<Item> rootNode;
    private ObservableList<Item> items;

    TreeBuilder(
            int Id,
            ObservableList<Item> items
    ) {
        this.items = items;

        Item rootItem = new Item(0, "", "", true, false, 0, 0);
        for (int i = 0; i < this.items.size(); i++) {
            Item item = this.items.get(i);
            if (item.getId() == Id) {
                rootItem = item;
            }
        }
        TreeItem<Item> rootNode = new TreeItem<>(
                rootItem
        );
        rootNode.setExpanded(true);
        this.rootNode = rootNode;
        loopItems(rootItem.getId(), rootNode);
    }


    private void loopItems(int parentId, TreeItem<Item> root) {
        for (Item item : items) {
            int par = item.getParent();
            if (par == parentId) {
                TreeItem treeItem = new TreeItem<Item>(item);
                treeItem.setExpanded(true);
                root.getChildren().add(treeItem);
                loopItems(item.getId(), treeItem);
            }
        }
    }

    TreeItem getTree() {
        return rootNode;
    }
}
