package finalon.finalonWindows.reusableComponents.selectbox;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Choices {
    public static ObservableList<Formula> getChoices(int parent) {
        ObservableList<Formula> choices = DbFormulaHandler.getFormulas(parent);
        if (choices.size() > 0) {
            return choices;
        } else {
            return FXCollections.observableArrayList();
        }
    }
}