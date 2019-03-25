package interpreter;

import database.formula.DbFormulaHandler;
import entities.Formula;
import entities.Item;
import finalonWindows.addReport.stepTwo.Periods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Interprter {
    private ObservableMap<String, String> settings;
    private ObservableList<Item> items;
    private ObservableList<Formula> formulas;
    private DbFormulaHandler dbFormula = new DbFormulaHandler();

    public Interprter(
            ObservableMap<String, String> settings,
            ObservableList<Item> items
    ) {

        this.settings = settings;
        this.items = items;
        int rootIndustry = Integer.parseInt(settings.get("industry"));
        ObservableList<Formula> myFormulas = FXCollections.observableArrayList();
        ObservableList<Formula> parents = dbFormula.getFormulas(rootIndustry);
        for (Formula child : parents) {
            myFormulas.add(child);
            ObservableList<Formula> childs2 = dbFormula.getFormulas(child.getId());
            for (Formula child2 : childs2) {
                myFormulas.add(child2);
            }
        }

        formulas = myFormulas;
    }


    private ArrayList<String> getPeriods() {
        Periods periods = new Periods(settings);
        return periods.getPeriodArr();
    }


    public VBox result() {
        Map<String, ObservableMap<String, Double>> values = obtainKeyValueArray();
        ArrayList<String> periods = getPeriods();
        VBox vBox = new VBox(20);

        HBox header = new HBox(20);
        header.getChildren().add(new Label("Formula Name"));
        for (String period : periods) {
            header.getChildren().add(new Label(period));
        }
        vBox.getChildren().add(header);

        for (Formula formula : formulas) {
            if (formula.getValue().length() > 0) {
                HBox hBox = new HBox(20);
                hBox.getChildren().add(new Label(formula.getShortName()));
                for (String period : periods) {
                    FormulaHahdler formulaHahdler = new FormulaHahdler(formula, values, period);
                    String res = formulaHahdler.getResult();
                    if(res != null && res.length() > 0){
                        hBox.getChildren().add(new Label(res));
                    }
                }
                vBox.getChildren().add(hBox);
            }

        }
        return vBox;
    }


    private Map<String, ObservableMap<String, Double>> obtainKeyValueArray() {
        Map<String, ObservableMap<String, Double>> values = new HashMap<>();
        for (Item item : items) {
            if (item.getValues().size() > 0) {
                values.put(item.getShortName(), item.getValues());
            }
        }
        return values;
    }
}


