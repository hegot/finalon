package database.formula;

import defaultData.DefaultFormulas;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class FormulaCreator {
    private ObservableList<Formula> formulas;

    public FormulaCreator() {
        ObservableList<Formula> formulas = FXCollections.observableArrayList(DefaultFormulas.getFormulas());
        this.formulas = formulas;
    }

    public void createFormulas() throws SQLException {
        DbFormulaHandler formulaCreator = new DbFormulaHandler();
        for (Formula formula : formulas) {
            formulaCreator.addDefaultFormula(formula);
        }

    }
}

