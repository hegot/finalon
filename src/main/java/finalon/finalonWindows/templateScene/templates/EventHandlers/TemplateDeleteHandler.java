package finalon.finalonWindows.templateScene.templates.EventHandlers;

import finalon.database.template.DbItemHandler;
import finalon.entities.Item;
import javafx.collections.ObservableList;

public class TemplateDeleteHandler {
    public static void deleteItems(int Id) {
        ObservableList<Item> items = DbItemHandler.getItems(Id);
        DbItemHandler.deleteItem(Id);
        if (items.size() > 0) {
            for (Item item : items) {
                DbItemHandler.deleteItem(item.getId());
            }
        }
    }
}
