package finalonWindows.formulaScene.eventHandlers;

import entities.Formula;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

public class EditFormula {

    private Formula formula;
    private Row[] arr;
    private GridPane grid;

    public EditFormula(Formula formula) {
        this.formula = formula;
        this.arr = getEditArr();
        createGrid();
        populateGrid();
    }

    private void createGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));
        this.grid = grid;
    }


    private void populateGrid() {
        for (int j = 0; j < arr.length; j++) {
            Row row = arr[j];
            grid.add(new Label(row.label), 0, j);
            TextField textfield = new TextField();
            textfield.setMinWidth(350);
            textfield.setText(row.value);
            grid.add(textfield, 1, j);
            row.textfield = textfield;
            arr[j] = row;
        }
    }

    public Tab getTab() {
        Tab tab = new Tab("Formula");
        tab.setContent(grid);
        return tab;
    }

    public Row[] getTextfields() {
        return arr;
    }

    private Row[] getEditArr() {
        Row arr[] = new Row[4];
        arr[0] = new Row("name", "Name:", formula.getName(), null);
        arr[1] = new Row("shortName", "Code:", formula.getShortName(), null);
        arr[2] = new Row("value", "Formula:", formula.getValue(), null);
        arr[3] = new Row("unit", "Unit:", formula.getUnit(), null);
        return arr;
    }


}

class Row {
    String key;
    String label;
    String value;
    TextField textfield;

    Row(String key, String label, String value, TextField textfield) {
        this.key = key;
        this.label = label;
        this.value = value;
        this.textfield = textfield;
    }
}