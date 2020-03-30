package database.template;

import database.formula.DbFormulaHandler;
import defaultData.DefaultTemplate;
import defaultData.Formula.DefaultFormulas;
import entities.Formula;
import entities.Item;
import javafx.collections.ObservableList;

import java.util.Comparator;

public class TemplateCreator {
    private ObservableList<Item> items;

    public TemplateCreator(
    ) {
        this.items = DefaultTemplate.getTpl();
    }


    public void saveTpl(String title) {
        items.sort(Comparator.comparing(Item::getId));
        Item root = items.get(0);
        root.setName(title);
        for (Item item : items) {
            item.setWeight(item.getId());
            DbItemHandler.addItem(item);
        }
        ObservableList<Formula> formulas = DefaultFormulas.getFormulas(root.getId());
        formulas.sort(Comparator.comparing(Formula::getId));
        for (Formula formula : formulas) {
            try {
                DbFormulaHandler.addDefaultFormula(formula);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
