package database;

import entities.Item;
import javafx.collections.ObservableList;

public class TemplateCreator extends TemplateBase {
    private ObservableList<Item> items;
    private String tplName;

    public TemplateCreator(
            String tplName,
            ObservableList<Item> items

    ) {
        super(tplName, items);
        this.items = items;
        this.tplName = tplName;
    }


    public void createTpl() {
        int templateId = 0;
        for (Item item : items) {
            if (item.getParent() == 0) {
                item.setName(tplName);
                templateId = createItem(item);
                updateChilds(item.getId(), templateId);
                setParentTpl(templateId);
            }
        }
        for (Item item : items) {
            if (item.getParent() != 0) {
                int newId = createItem(item);
                updateChilds(item.getId(), newId);
            }
        }

    }


    private void setParentTpl(int templateId) {
        for (Item item : items) {
            item.setParentSheet(templateId);
        }
    }


}

