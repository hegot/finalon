package finalon.finalonWindows.formulaScene.EditPopup;

import finalon.entities.Formula;
import finalon.finalonWindows.reusableComponents.autocomplete.AutoCompleteTextArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.TreeSet;


class EditFormula {

    private Formula formula;
    private EditRow[] arr;
    private GridPane grid;
    private AutoCompleteTextArea textArea;

    EditFormula(Formula formula) {
        this.formula = formula;
        this.grid = createGrid();
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));
        grid.add(createRow("Name:", "name", formula.getName()), 0, 0);
        grid.add(createRow("Code:", "shortName", formula.getShortName()), 0, 1);
        if (!formula.getCategory().equals("section")) {
            grid.add(createRow("Unit:", "unit", formula.getUnit()), 0, 2);
            grid.add(new Label("Edit formula"), 0, 3);
            grid.add(formulaEditor(), 0, 4);
        }
        return grid;
    }

    private HBox createRow(String title, String key, String value) {
        HBox hBox = new HBox(10);
        Label label = new Label(title);
        label.setMinWidth(150);
        TextField textfield = new TextField();
        textfield.setMinWidth(350);
        textfield.setText(value);
        textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            switch (key) {
                case "name":
                    formula.setName(newValue);
                case "unit":
                    formula.setUnit(newValue);
                case "shortName":
                    formula.setShortName(newValue);
            }
        });
        hBox.getChildren().addAll(label, textfield);
        return hBox;
    }


    private VBox formulaEditor() {
        VBox vBox = new VBox(10);
        VBox errorsBox = new VBox(1);
        this.textArea = new AutoCompleteTextArea(formula.getValue());
        textArea.setPrefSize(650, 80);
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
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            formula.setValue(newValue);
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


    AutoCompleteTextArea getTextArea() {
        return textArea;
    }

    public Formula getFormula() {
        return formula;
    }
}
