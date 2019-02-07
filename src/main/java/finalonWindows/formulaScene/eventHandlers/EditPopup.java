package finalonWindows.formulaScene.eventHandlers;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.EditStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class EditPopup {

    private Formula formula;
    private TreeItem treeItem;
    private String[][] arr;
    private ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
    private ObservableList<Formula> childs;
    private EditStorage storage = EditStorage.getInstance();

    public EditPopup(TreeItem treeItem) {
        this.treeItem = treeItem;
        this.formula = (Formula) treeItem.getValue();
        this.arr = getEditArr();
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        childs = dbFormula.getFormulas(formula.getId());
    }

    public Dialog getdialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit Formula");
        dialog.setWidth(400);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        TabPane tabpane = new TabPane();
        Tab tab = new Tab("Formula");
        tab.setContent(formualEdit(dialog));
        Tab tab2 = new Tab("Normative values");
        tab2.setContent(normativeValues(dialog));
        tabpane.getTabs().addAll(tab, tab2);
        dialog.getDialogPane().setContent(tabpane);
        return dialog;
    }


    private VBox normativeValues(Dialog dialog) {
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(10, 2, 10, 2));
        vBox.setPrefWidth(400.00);
        for (Formula item : childs) {
            HBox hbox = new HBox(10);
            VBox vBoxIn = new VBox(3);
            vBoxIn.setStyle(" -fx-padding: 5; -fx-border-style: solid outside;" +
                    "-fx-border-width: 1; -fx-border-radius: 2;" +
                    "-fx-border-color: #DDDDDD; -fx-pref-width: 300px; -fx-background-color: #E6E6FA");
            String rightComar = item.getCategory();
            if (rightComar != null && !rightComar.isEmpty()) {
                hbox.getChildren().addAll(
                        value(item),
                        comparator(item),
                        name(item),
                        comparator2(item),
                        value2(item)
                );
            } else {
                hbox.getChildren().addAll(
                        name(item),
                        comparator(item),
                        value(item)
                );
            }
            vBoxIn.getChildren().addAll(hbox, conclusions(item));
            vBox.getChildren().add(vBoxIn);
        }
        return vBox;
    }


    private TextField conclusions(Formula item) {
        TextField conclusions = textfield(item.getDescription(), 400.00);
        conclusions.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) conclusions.getText();
            item.setDescription(text);
            storage.addItem(item.getId(), item);
        });
        return conclusions;
    }

    private TextField value(Formula item) {
        TextField value = textfield(item.getValue(), 40.00);
        value.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) value.getText();
            item.setValue(text);
            storage.addItem(item.getId(), item);
        });
        return value;
    }

    private TextField value2(Formula item) {
        TextField value2 = textfield(item.getUnit(), 40.00);
        value2.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) value2.getText();
            item.setUnit(text);
            storage.addItem(item.getId(), item);
        });
        return value2;
    }

    private ComboBox comparator(Formula item) {
        ComboBox comparator = choicebox(compOp(), item.getShortName());
        comparator.setOnAction((e) -> {
            String text = (String) comparator.getSelectionModel().getSelectedItem();
            item.setShortName(text);
            storage.addItem(item.getId(), item);
        });
        return comparator;
    }

    private ComboBox name(Formula item) {
        ComboBox name = choicebox(nameOp(), item.getName());
        name.setOnAction((e) -> {
            String text = (String) name.getSelectionModel().getSelectedItem();
            item.setName(text);
            storage.addItem(item.getId(), item);
        });
        return name;
    }

    private ComboBox comparator2(Formula item) {
        ComboBox comparator2 = choicebox(compOp(), item.getCategory());
        comparator2.setOnAction((e) -> {
            String text = (String) comparator2.getSelectionModel().getSelectedItem();
            item.setCategory(text);
            storage.addItem(item.getId(), item);
        });
        return comparator2;
    }


    private TextField textfield(String value, Double width) {
        TextField textfield = new TextField();
        textfield.setMaxWidth(width);
        textfield.setText(value);
        textfield.setPromptText("Customize conclusions for this indicator range");
        return textfield;
    }


    private ComboBox choicebox(ObservableList<String> options, String value) {
        ComboBox<String> cb = new ComboBox<>(options);
        cb.getSelectionModel().select(value);
        return cb;
    }


    private GridPane formualEdit(Dialog dialog) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        Map<String, TextField> textfields = new HashMap<>();
        for (int j = 0; j < arr.length; j++) {
            grid.add(new Label(arr[j][1]), 0, j);
            TextField textfield = new TextField();
            textfield.setMinWidth(350);
            textfield.setText(arr[j][2]);
            textfields.put(arr[j][0], textfield);
            grid.add(textfield, 1, j);
        }
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
                storage.addItem(formula.getId(), formula);
            }
            return null;
        });
        return grid;
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

    private ObservableList<String> compOp() {
        return FXCollections.observableArrayList(
                ">",
                ">=",
                "=",
                "<",
                "<="
        );
    }

    private ObservableList<String> nameOp() {
        return FXCollections.observableArrayList(
                "excellent",
                "good",
                "satisfactory",
                "unsatisfactory",
                "bad"
        );
    }

}
