package finalonWindows.formulaScene;

import entities.Formula;
import finalonWindows.reusableComponents.selectbox.IndustrySelect;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeTableView;

public class Storage {

    public static FormulaEditable formulaEditable;
    public static ComboBox<Formula> industryBox;
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

    private static class SingletonHolder {
        static final Storage INSTANCE = new Storage();
    }

    public static  FormulaEditable getFormulaEditable(){
        return formulaEditable;
    }


    public static TreeTableView getTable(){
        return formulaEditable.getFormulaTable(industryBox.getValue());
    }

    public static void refresh(){
        formulaEditable.updateTable(industryBox.getValue());
    }

    public static ComboBox<Formula> getIndustryBox(){
        return industryBox;
    }
}