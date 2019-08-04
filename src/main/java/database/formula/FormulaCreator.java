package database.formula;

import defaultData.Formula.DefaultFormulas;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class FormulaCreator {
    private ObservableList<Formula> formulas;
    private DbFormulaHandler formulaDbHandler = new DbFormulaHandler();
    private int newId;

    public FormulaCreator() {
        DefaultFormulas formulasClass = new DefaultFormulas();
        formulasClass.resetCounter();
        this.formulas = FXCollections.observableArrayList(formulasClass.getFormulas());
    }

    public FormulaCreator(int standard, String Industry) {
        int lastId = formulaDbHandler.getLastId() + 1;
        this.formulas = FXCollections.observableArrayList(
                DefaultFormulas.getFormulasForIndustry(standard, Industry, lastId)
        );
        this.newId = lastId;
    }

    public int getNewId() {
        return newId;
    }

    public void createFormulas() throws SQLException {
        for (Formula formula : formulas) {
            formulaDbHandler.addDefaultFormula(formula);
        }
    }
}

