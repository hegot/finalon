package finalonWindows.reusableComponents.selectbox;

import database.formula.DbFormulaHandler;
import entities.Formula;
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
