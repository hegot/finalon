package database.template;

import database.formula.DbFormulaHandler;
import entities.Formula;
import entities.Item;
import javafx.collections.ObservableList;

public class TemplateDeleter {
    public static void deleteItems(int Id) {
        loopItems(Id);
        Formula template = DbFormulaHandler.findTemplate(Id);
        if (template != null) {
            loopFormulas(template.getId());
        }

    }

    private static void loopItems(int id) {
        ObservableList<Item> items = DbItemHandler.getItems(id);
        if (items.size() > 0) {
            for (Item item : items) {
                loopItems(item.getId());
            }
        }
        DbItemHandler.deleteItem(id);
    }

    private static void loopFormulas(int id) {
        ObservableList<Formula> childs = DbFormulaHandler.getFormulas(id);
        for (Formula child : childs) {
            loopFormulas(child.getId());
        }
        DbFormulaHandler.deleteItem(id);
    }
}
