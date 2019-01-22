package finalonWindows.TemplateScene.templates;

import entities.Item;
import entities.Sheet;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

class TreeBuilder {
    private TreeItem<Item> rootNode;
    private ObservableList<Item> items;

    TreeBuilder(
            Sheet sheet
    ) {
        TreeItem<Item> rootNode = new TreeItem<>(
                new Item(0, sheet.name, sheet.name, true, 0, 0)
        );
        rootNode.setExpanded(true);
        this.rootNode = rootNode;
        this.items = sheet.items;
        loopItems(0, rootNode);
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
