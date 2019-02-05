package finalonWindows.formulaScene.eventHandlers;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class EditPopup {

    private Formula formula;
    private TreeItem treeItem;

    public EditPopup(TreeItem treeItem) {
        this.treeItem = treeItem;
        this.formula = (Formula) treeItem.getValue();
    }

    public Dialog getdialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit Formula");
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));


        String[][] arr = getEditArr();
        Map<String, TextField> textfields = new HashMap<>();
        for (int j = 0; j < arr.length; j++) {
            grid.add(new Label(arr[j][1]), 0, j);
            TextField textfield = new TextField();
            textfield.setText(arr[j][2]);
            textfields.put(arr[j][0], textfield);
            grid.add(textfield, 1, j);
        }


        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                for (int j = 0; j < arr.length; j++) {
                    TextField textfieldget = textfields.get(arr[j][0]);
                    String value = textfieldget.getText();
                    switch (arr[j][0]) {
                        case "name":
                            formula.setName(value);
                            break;
                        case "shortName":
                            formula.setShortName(value);
                            break;
                        case "value":
                            formula.setValue(value);
                            break;
                        case "unit":
                            formula.setUnit(value);
                            break;
                        default:
                            System.out.println("no match");
                    }
                }
                treeItem.setValue(formula);
            }
            return null;
        });


        return dialog;
    }


    private String[][] getEditArr() {
        String[][] arr = new String[][]{
                {"name", "Name:", formula.getName(),},
                {"shortName", "Code:", formula.getShortName(),},
                {"value", "Formula:", formula.getValue(),},
                {"unit", "Unit:", formula.getUnit(),},
        };
        return arr;
    }

}
