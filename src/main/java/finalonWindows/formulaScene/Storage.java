package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.reusableComponents.selectbox.IndustrySelect;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeTableView;

public class Storage {

    private static FormulaEditable formulaEditable;
    private static ComboBox<Formula> industryBox;
    private boolean initalized = false;

    private Storage() {
        if (!initalized) {
            industryBox = IndustrySelect.get("2", FXCollections.observableHashMap());
            formulaEditable = new FormulaEditable();
            initalized = true;
        }
    }

    public static Storage getInstance() {
        return SingletonHolder.INSTANCE;
    }

    static FormulaEditable getFormulaEditable() {
        return formulaEditable;
    }

    public static TreeTableView getTable() {
        return formulaEditable.getFormulaTable(industryBox.getValue());
    }

    public static void refresh() {
        formulaEditable.updateTable(industryBox.getValue());
    }

    public static void refreshWithId(Integer id) {
        Formula formula = industryBox.getValue();
        if (id != null) {
            formula = DbFormulaHandler.findById(id);
        }
        formulaEditable.updateTable(formula);
        ComboBox<Formula> newSelect = IndustrySelect.get("2", FXCollections.observableHashMap());
        industryBox.setItems(newSelect.getItems());
        industryBox.getSelectionModel().select(formula);
    }

    public static ComboBox<Formula> getIndustryBox() {
        return industryBox;
    }

    private static class SingletonHolder {
        static final Storage INSTANCE = new Storage();
    }
}