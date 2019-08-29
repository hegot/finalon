package finalonWindows.templateScene.templates.EventHandlers;

import database.template.DbItemHandler;
import entities.Item;
import finalonWindows.templateScene.templates.TemplateEditPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TemplateSaveHandler {
    private ObservableList<Item> items;
    private Item root;

    public TemplateSaveHandler(
            String tplName
    ) {
        this.items = FXCollections.observableArrayList(
                TemplateEditPage.getItems()
        );
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

    public void saveTpl() {
        for (Item item : items) {
            if (item.getParent() == 0) {
                int templateId = DbItemHandler.addItem(item);
                setParentTpl(templateId);
                TemplateBase.updateChilds(item.getId(), templateId);
                break;
            }
        }
    }

    private void setParentTpl(int templateId) {
        for (Item item : items) {
            if(item.getUpdated()){
                TemplateBase.updateDependantFormulas(item);
            }
            item.setParentSheet(templateId);
        }
    }
}
