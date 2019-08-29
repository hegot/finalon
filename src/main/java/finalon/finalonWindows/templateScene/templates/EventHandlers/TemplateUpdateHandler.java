package finalon.finalonWindows.templateScene.templates.EventHandlers;

import finalon.database.template.DbItemHandler;
import finalon.entities.Item;
import finalon.finalonWindows.templateScene.templates.TemplateEditPage;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class TemplateUpdateHandler {
    private ObservableList<Item> items;
    private Item root;

    public TemplateUpdateHandler(
            String tplName
    ) {
        this.items = TemplateEditPage.getItems();
        this.root = getRoot();
        root.setName(tplName);
    }

    public Item getRoot() {
        for (Item item : items) {
            if (item.getParent() == 0) {
                return item;
            }
        }
        return null;
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
        ObservableList<Item> oldItems = DbItemHandler.getItems(root.getId());
        if (oldItems.size() > 0) {
            updateItem(root);
            for (Item item : oldItems) {
                if (!hasItem(item.getId())) {
                    deleteItem(item.getId());
                }
            }
            for (Item item : items) {
                if (item.getId() == -1) {
                    item.setParentSheet(root.getId());
                    int newId = DbItemHandler.addItem(item);
                    TemplateBase.updateChilds(item.getId(), newId);
                } else if (item.getUpdated()) {
                    TemplateBase.updateDependantFormulas(item);
                    updateItem(item);
                }
            }
        }
    }


}