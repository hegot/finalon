package database.template;

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
        for (Item item : items) {
            if (item.getParent() == 0) {
                item.setName(tplName);
                int templateId = createItem(item);
                updateChilds(item.getId(), templateId);
                setParentTpl(templateId);
                break;
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

