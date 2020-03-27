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


    public void saveTpl() {
        items.sort(Comparator.comparing(Item::getId));
        for (Item item : items) {
            DbItemHandler.addItem(item);
        }
        ObservableList<Formula> formulas = DefaultFormulas.getFormulas(items.get(0).getId());
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
