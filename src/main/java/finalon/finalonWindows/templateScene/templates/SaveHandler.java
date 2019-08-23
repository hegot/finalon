package finalon.finalonWindows.templateScene.templates;

import finalon.database.template.DbItemHandler;
import finalon.entities.Item;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaveHandler {
    private ObservableList<Item> items;
    private Item root;


    public SaveHandler(
            String tplName
    ) {
        this.items = EditTemplate.getItems();
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
        if(oldItems.size() > 0){
            for (Item item : oldItems) {
                if (!hasItem(item.getId())) {
                    deleteItem(item.getId());
                }
            }
            for (Item item : items) {
                if (item.getId() == -1) {
                    item.setParentSheet(root.getId());
                    int newId = createItem(item);
                    updateChilds(item.getId(), newId);
                } else {
                    updateItem(item);
                }
            }
        }else{
            for (Item item : items) {
                if (item.getParent() == 0) {
                    int templateId = createItem(item);
                    setParentTpl(templateId);
                    updateChilds(item.getId(), templateId);
                    break;
                }
            }
        }
    }

    private void setParentTpl(int templateId) {
        for (Item item : items) {
            item.setParentSheet(templateId);
        }
    }


    int createItem(Item item) {
        try {
            return DbItemHandler.addItem(item);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        return 0;
    }

    List<Item> getChilds(int parentId){
        List<Item> childs = new ArrayList<Item>();
        for (Item item : items) {
            if (parentId == item.getParent()) {
                childs.add(item);
            }
        }
        return childs;
    }

    void updateChilds(int oldId, int newId) {
        List<Item> childs = getChilds(oldId);
        for (Item child : childs) {
            Item newItem = (Item) child.clone();
            newItem.setParent(newId);
            int newNewId = createItem(newItem);
            updateChilds(newItem.getId(), newNewId);
        }
    }

}
