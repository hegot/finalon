package database.formula;

import defaultData.Formula.DefaultFormulas;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class FormulaCreator {
    private ObservableList<Formula> formulas;
    private DbFormulaHandler formulaDbHandler = new DbFormulaHandler();

    public FormulaCreator() {
        this.formulas = FXCollections.observableArrayList(DefaultFormulas.getFormulas());
    }

    public FormulaCreator(int standard, String Industry) {
        this.formulas = FXCollections.observableArrayList(
                DefaultFormulas.getFormulasForIndustry(standard, Industry, formulaDbHandler.getLastId())
        );
    }

    public void createFormulas() throws SQLException {
        for (Formula formula : formulas) {
            formulaDbHandler.addDefaultFormula(formula);
        }
    }
}

