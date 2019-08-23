package finalon.database.formula;

import finalon.entities.Formula;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class FormulaEditor extends FormulaBase {
    private ObservableList<Formula> formulas;
    private int rootId;

    public FormulaEditor(
            ObservableList<Formula> formulas
    ) {
        this.formulas = formulas;
        setRoot();
    }


    public void setRoot() {
        for (Formula formula : formulas) {
            if (formula.getParent() == 0) {
                this.rootId = formula.getId();
            }
        }
    }


    private void updateItem(Formula formula) {
        try {
            DbFormulaHandler.updateFormula(formula);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private Boolean hasItem(int Id) {
        Boolean contains = false;
        for (Formula formula : formulas) {
            if (formula.getId() == Id) {
                contains = true;
            }
        }
        return contains;
    }


    public void deleteItem(int Id) {
        DbFormulaHandler.deleteItem(Id);
        for (Formula formula : formulas) {
            if (formula.getParent() == Id) {
                deleteItem(formula.getId());
            }
        }
    }


    public void updateTpl() {
        ObservableList<Formula> oldItems = DbFormulaHandler.getFormulas(rootId);
        for (Formula formula : oldItems) {
            if (!hasItem(formula.getId())) {
                deleteItem(formula.getId());
            }
        }
        for (Formula formula : formulas) {
            if (formula.getId() == -1) {
                int newId = createFormula(formula);
                //updateChilds(formula.getId(), newId);
            } else {
                updateItem(formula);
            }

        }
    }

}