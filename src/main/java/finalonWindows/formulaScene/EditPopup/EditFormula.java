package finalonWindows.formulaScene.EditPopup;

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
    private EditRow[] arr;
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
            EditRow row = arr[j];
            grid.add(new Label(row.label), 0, j);
            TextField textfield = new TextField();
            textfield.setMinWidth(350);
            textfield.setText(row.value);
            grid.add(textfield, 1, j);
            row.textfield = textfield;
            arr[j] = row;
        }
        if (!formula.getCategory().equals("section")) {
            grid.add(new Label("Edit formula"), 0, 4);
            grid.add(formulaEditor(), 1, 4);
        }
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

    GridPane getGrid() {
        return grid;
    }


    EditRow[] getTextfields() {
        return arr;
    }

    private EditRow[] getEditArr() {

        if (formula.getCategory().equals("section")) {
            EditRow arr[] = new EditRow[1];
            arr[0] = new EditRow("name", "Section Name:", formula.getName());
            return arr;
        } else {
            EditRow arr[] = new EditRow[3];
            arr[0] = new EditRow("name", "Name:", formula.getName());
            arr[1] = new EditRow("shortName", "Code:", formula.getShortName());
            arr[2] = new EditRow("unit", "Unit:", formula.getUnit());
            return arr;
        }
    }

    AutoCompleteTextArea getTextArea() {
        return textArea;
    }
}
