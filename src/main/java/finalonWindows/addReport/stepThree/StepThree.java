package finalonWindows.addReport.stepThree;

import entities.Formula;
import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.addReport.stepTwo.Periods;
import interpreter.Interprter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.util.ArrayList;

public class StepThree extends SceneBase {
    private ObservableMap<String, String> settings;
    private ObservableList<Item> items;
    private ObservableList<Formula> formulas;

    public StepThree(ObservableMap<String, String> settings, ObservableList<Item> items) {
        this.settings = settings;
        this.items = items;

    }

    public VBox show() {
        Interprter interprter = new Interprter(settings, items);
        this.formulas = interprter.result();
        VBox vbox = new VBox(10);
        TableView<Formula> table = new TableView<Formula>();
        table.setEditable(true);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        table.setMinHeight(primaryScreenBounds.getHeight() - 150);
        table.getColumns().add(getNameCol());
        Periods periods = new Periods(settings);
        ArrayList<String> arr = periods.getPeriodArr();
        for (String col : arr) {
            table.getColumns().add(getPeriodCol(col));
        }
        table.setItems(formulas);
        vbox.getChildren().add(table);

        return vbox;
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
}
