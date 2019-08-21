package finalon.database.formula;

import finalon.defaultData.Formula.DefaultFormulas;
import finalon.entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class FormulaCreator {
    private ObservableList<Formula> formulas;
    private int newId;

    public FormulaCreator() {
        DefaultFormulas formulasClass = new DefaultFormulas();
        formulasClass.resetCounter();
        this.formulas = FXCollections.observableArrayList(formulasClass.getFormulas());
    }

    public FormulaCreator(int standard, String Industry) {
        int lastId = DbFormulaHandler.getLastId() + 1;
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
            try{
                DbFormulaHandler.addDefaultFormula(formula);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }
}

