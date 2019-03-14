package finalonWindows.formulaScene.eventHandlers;

import entities.Formula;
import finalonWindows.reusableComponents.autocomplete.AutoCompleteTextArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.TreeSet;


class EditFormula {

    private Formula formula;
    private Row[] arr;
    private GridPane grid;
    private AutoCompleteTextArea textArea;

    EditFormula(Formula formula) {
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
        grid.add(new Label("Edit formula"), 0, 4);
        grid.add(formulaEditor(), 1, 4);
    }

    private VBox formulaEditor() {
        VBox vBox = new VBox(10);
        VBox errorsBox = new VBox(1);
        this.textArea = new AutoCompleteTextArea(formula.getValue());
        textArea.setPrefHeight(80);
        textArea.setWrapText(true);
        textArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                TreeSet<String> errors = ((AutoCompleteTextArea) textArea).getErrors();
                if (errors.size() > 0) {
                    errorsBox.getChildren().clear();
                    for (String error : errors) {
                        Label err = new Label(error);
                        err.getStyleClass().add("error");
                        errorsBox.getChildren().add(err);
                    }
                } else {
                    errorsBox.getChildren().clear();
                }

            }
        });
        vBox.getChildren().addAll(textArea, errorsBox);
        return vBox;
    }


    Tab getTab() {
        Tab tab = new Tab("Formula");
        tab.setContent(grid);
        return tab;
    }

    Row[] getTextfields() {
        return arr;
    }

    private Row[] getEditArr() {
        Row arr[] = new Row[3];
        arr[0] = new Row("name", "Name:", formula.getName(), null);
        arr[1] = new Row("shortName", "Code:", formula.getShortName(), null);
        arr[2] = new Row("unit", "Unit:", formula.getUnit(), null);
        return arr;
    }

    AutoCompleteTextArea getTextArea() {
        return textArea;
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