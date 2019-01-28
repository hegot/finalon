package database;

import entities.Item;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class TemplateEditor {
    private ObservableList<Item> items;
    private String tplName;
    private int rootId;
    public TemplateEditor(
            String tplName,
            ObservableList<Item> items

    ) {
        this.items = items;
        this.tplName = tplName;
        setRoot();
    }


    public void setRoot() {
        for (Item item : this.items) {
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

    private Boolean hasItem(int Id){
        Boolean contains = false;
        for (Item item : this.items) {
            if(item.getId() == Id){
                contains = true;
            }
        }
        return contains;
    }


    public void updateTpl(){
        DbItemHandler itemsHandler = new DbItemHandler();
        ObservableList<Item> oldItems = itemsHandler.getItems(rootId);
        for (Item item : oldItems) {
            if(!hasItem(item.getId())){
                itemsHandler.deleteItem(item.getId());
            }
        }
        for (Item item : this.items) {
            updateItem(item);
        }
    }

}
