package reportGeneration.interpreter.FormulaList;

import database.formula.DbFormulaHandler;
import entities.Formula;
import entities.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import reportGeneration.ItemsStorage;
import reportGeneration.Periods;
import reportGeneration.SettingsStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FormulaList {
    private ObservableList<Formula> formulas;
    private ArrayList<String> periods;
    private DbFormulaHandler dbFormula = new DbFormulaHandler();

    public FormulaList() {
        this.periods = Periods.getInstance().getPeriodArr();
        this.formulas = getFormulas();
    }

    private ObservableList<Formula> getFormulas() {
        int rootIndustry = Integer.parseInt(SettingsStorage.getSettings().get("industry"));
        ObservableList<Formula> myFormulas = FXCollections.observableArrayList();
        ObservableList<Formula> parents = dbFormula.getFormulas(rootIndustry);
        for (Formula child : parents) {
            myFormulas.add(child);
            ObservableList<Formula> childs2 = dbFormula.getFormulas(child.getId());
            for (Formula child2 : childs2) {
                myFormulas.add(child2);
            }
        }
        return myFormulas;
    }

    TableColumn getNameCol() {
        TableColumn<Formula, String> col = new TableColumn<Formula, String>("Formula");
        col.setMinWidth(350);
        col.setCellValueFactory(new PropertyValueFactory<Formula, String>("name"));
        return col;
    }

    TableColumn getPeriodCol(String colname) {
        TableColumn<Formula, String> col = new TableColumn<Formula, String>(colname);
        col.setMinWidth(100);
        col.setCellValueFactory(cellData -> {
            Formula formula = (Formula) cellData.getValue();
            if (formula != null && formula.getPeriods().size() > 0) {
                String period = formula.getPeriods().get(colname);
                if (period != null) {
                    return new SimpleStringProperty(period);
                }

            }
            return null;
        });
        return col;
    }


    public VBox get() {
        Map<String, ObservableMap<String, Double>> values = obtainKeyValueArray();
        for (Formula formula : formulas) {
            if (formula.getValue().length() > 0) {
                ObservableMap<String, String> formulaPeriods = FXCollections.observableHashMap();
                for (String period : periods) {
                    FormulaHandler formulaHahdler = new FormulaHandler(formula, values, period);
                    String res = formulaHahdler.getResult();
                    if (res != null && res.length() > 0) {
                        formulaPeriods.put(period, res);
                    }
                }
                formula.setPeriods(formulaPeriods);
            }
        }
        TableView<Formula> table = new TableView<Formula>();
        table.setEditable(true);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        table.setMinHeight(primaryScreenBounds.getHeight() - 150);
        table.getColumns().add(getNameCol());
        for (String col : periods) {
            table.getColumns().add(getPeriodCol(col));
        }
        table.setItems(formulas);
        VBox vbox = new VBox();
        vbox.getChildren().add(table);
        return vbox;
    }

    private Map<String, ObservableMap<String, Double>> obtainKeyValueArray() {
        Map<String, ObservableMap<String, Double>> values = new HashMap<>();
        for (Item item : ItemsStorage.getItems()) {
            if (item.getValues().size() > 0) {
                values.put(item.getShortName(), item.getValues());
            }
        }
        return values;
    }
}
