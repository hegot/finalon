package finalon.database.template;
import finalon.entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.SerializationUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class TemplateBase {
    private ObservableList<Item> items;
    public TemplateBase(ObservableList<Item> items) {
        this.items = items;
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
