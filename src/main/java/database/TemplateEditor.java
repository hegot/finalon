package database;

import entities.Item;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class TemplateEditor extends TemplateBase {
    private ObservableList<Item> items;
    private int rootId;
    private DbItemHandler itemsHandler;

    public TemplateEditor(
            String tplName,
            ObservableList<Item> items
    ) {
        super(tplName, items);
        this.items = items;
        this.itemsHandler = new DbItemHandler();
        setRoot();
    }


    public void setRoot() {
        for (Item item : items) {
            if (item.getParent() == 0) {
                this.rootId = item.getId();
            }
        }
    }


    private void updateItem(Item item) {
        try {
            DbItemHandler itemUpdater = new DbItemHandler();
            itemUpdater.updateItem(item);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }

    private Boolean hasItem(int Id) {
        Boolean contains = false;
        for (Item item : items) {
            if (item.getId() == Id) {
                contains = true;
            }
        }
        return contains;
    }


    private void deleteItem(int Id) {
        itemsHandler.deleteItem(Id);
        for (Item item : items) {
            if (item.getParent() == Id) {
                deleteItem(item.getId());
            }
        }
    }


    public void updateTpl() {
        ObservableList<Item> oldItems = itemsHandler.getItems(rootId);
        for (Item item : oldItems) {
            if (!hasItem(item.getId())) {
                deleteItem(item.getId());
            }
        }
        for (Item item : items) {
            if (item.getId() == -1) {
                item.setParentSheet(rootId);
                int newId = createItem(item);
                updateChilds(item.getId(), newId);
            } else {
                updateItem(item);
            }

        }
    }

}
