package finalon.database.template;

import finalon.entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TemplateCreator extends TemplateBase {
    private ObservableList<Item> items;
    private String tplName;

    public TemplateCreator(
            String tplName,
            ObservableList<Item> items

    ) {
        super(items);
        this.items = items;
        this.tplName = tplName;
    }


    public void createTpl() {
        for (Item item : items) {
            if (item.getParent() == 0) {
                item.setName(tplName);
                int templateId = createItem(item);
                setParentTpl(templateId);
                updateChilds(item.getId(), templateId);
                break;
            }
        }
    }



    private void setParentTpl(int templateId) {
        for (Item item : items) {
            item.setParentSheet(templateId);
        }
    }


}

