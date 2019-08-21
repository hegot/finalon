package finalon.database.template;

import finalon.entities.Item;
import javafx.collections.ObservableList;

import java.sql.SQLException;

class TemplateBase {
    private ObservableList<Item> items;
    private String tplName;

    TemplateBase(
            String tplName,
            ObservableList<Item> items

    ) {
        this.items = items;
        this.tplName = tplName;
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

    void updateChilds(int oldId, int newId) {
        for (Item item : items) {
            if (oldId == item.getParent()) {
                item.setParent(newId);
            }
        }
    }
}
