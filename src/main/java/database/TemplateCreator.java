package database;
import entities.Item;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public class TemplateCreator {
    private ObservableList<Item> items;
    private String tplName;

    public TemplateCreator(
            String tplName,
            ObservableList<Item> items

    ) {
        this.items = items;
        this.tplName = tplName;
    }


    public void createTpl() {
        int templateId = 0;
        for (Item item : this.items) {
            if (item.getParent() == 0) {
                item.setName(this.tplName);
                templateId = createItem(item);
                updateChilds(item.getId(), templateId);
                setParentTpl(templateId);
            }
        }
        for (Item item : this.items) {
            if (item.getParent() != 0) {
                int newId = createItem(item);
                updateChilds(item.getId(), newId);
            }
        }

    }


    private void setParentTpl(int templateId){
        for (Item item : this.items) {
            item.setParentSheet(templateId);
        }
    }

    private int createItem(Item item) {
        try {
            DbItemHandler itemCreator = new DbItemHandler();
            return itemCreator.addItem(item);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        return 0;
    }


    private void updateChilds(int oldId, int newId){
        for (Item item : this.items) {
            if (oldId == item.getParent()) {
                item.setParent(newId);
            }
        }
    }
}

