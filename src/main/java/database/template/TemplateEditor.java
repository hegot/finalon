package database.template;

import entities.Item;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class TemplateEditor extends TemplateBase {
    private ObservableList<Item> items;
    private int rootId;

    public TemplateEditor(
            String tplName,
            ObservableList<Item> items
    ) {
        super(tplName, items);
        this.items = items;
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
            DbItemHandler.updateItem(item);
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


    public void deleteItem(int Id) {
        DbItemHandler.deleteItem(Id);
        for (Item item : items) {
            if (item.getParent() == Id) {
                deleteItem(item.getId());
            }
        }
    }


    public void updateTpl() {
        ObservableList<Item> oldItems = DbItemHandler.getItems(rootId);
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
