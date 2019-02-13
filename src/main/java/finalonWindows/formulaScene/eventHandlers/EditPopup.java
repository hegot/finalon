package finalonWindows.formulaScene.eventHandlers;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.EditStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditPopup {

    private Formula formula;
    private TreeItem treeItem;
    private String[][] arr;
    private ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
    private ButtonType closeButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.NEXT_FORWARD);
    private ObservableList<Formula> childs;
    private EditStorage storage = EditStorage.getInstance();
    private Tab tab2;
    private ArrayList<Formula> formulasAdd;
    private ArrayList<Formula> formulasRemove;
    private ScrollPane scrollPane;
    private Dialog dialog;
    private String type;

    public EditPopup(TreeItem treeItem, String type) {
        this.type = type;
        this.treeItem = treeItem;
        this.formula = (Formula) treeItem.getValue();
        this.arr = getEditArr();
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        this.childs = dbFormula.getFormulas(formula.getId());
        tab2 = new Tab("Normative values");
        this.formulasAdd = new ArrayList<Formula>();
        this.formulasRemove = new ArrayList<Formula>();
    }

    public Dialog getdialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit Formula");
        dialog.setWidth(400);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, closeButtonType);
        TabPane tabpane = new TabPane();
        Tab tab = new Tab("Formula");
        tab.setContent(formualEdit(dialog));
        tab2.setContent(normativeValues());
        tabpane.getTabs().addAll(tab, tab2);
        dialog.getDialogPane().setContent(tabpane);
        this.dialog = dialog;
        return dialog;
    }


    private VBox normativeValues() {
        VBox vBoxOuter = new VBox(15);
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(10, 2, 10, 2));
        vBox.setPrefWidth(450.00);
        for (Formula item : childs) {
            HBox hbox = new HBox(10);
            VBox vBoxIn = new VBox(3);
            vBoxIn.setStyle("-fx-padding: 5; -fx-border-style: solid outside;" +
                    "-fx-border-width: 1; -fx-border-radius: 2;" +
                    "-fx-border-color: #DDDDDD; -fx-pref-width: 350px; -fx-background-color: #E6E6FA");

            hbox.getChildren().addAll(
                    value(item),
                    comparator(item),
                    name(item),
                    comparator2(item),
                    value2(item),
                    removeButton(item.getId())
            );

            vBoxIn.getChildren().addAll(hbox, conclusions(item));
            vBox.getChildren().add(vBoxIn);
        }

        scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(vBox);
        vBoxOuter.getChildren().addAll(scrollPane, addButton());

        return vBoxOuter;
    }

    private Button addButton() {
        Button btn = new Button("+ Add");
        btn.setStyle("-fx-background-color: #AAD3E6; -fx-border-color: #898989; -fx-border-radius: 2;");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Formula item = new Formula(-1, "", "", "", "", "", "", formula.getId());
                childs.add(item);
                formulasAdd.add(item);
                tab2.setContent(normativeValues());
                scrollPane.setVvalue(1.0);
            }
        });
        return btn;
    }

    private Button removeButton(int Id) {
        Button btn = new Button("—");
        btn.setStyle("-fx-background-color: #AAD3E6; -fx-border-color: #AAD3E6; -fx-border-radius: 5em;  -fx-background-radius: 5em; -fx-text-fill: #242424;-fx-font-size: 15px;  -fx-font-weight: bold; -fx-padding: 0 4px 3px 4px;");
        //  btn.updateImages(new Image("/image/minus.png"), new Image("/image/minus.png"), 50);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int rem = -1;
                for (int j = 0; j < childs.size(); j++) {
                    Formula item = childs.get(j);
                    if(item.getId() == Id){
                        formulasRemove.add(item);
                        rem = j;
                    }
                }
                childs.remove(rem);
                tab2.setContent(normativeValues());
            }
        });
        return btn;
    }


    private TextField conclusions(Formula item) {
        TextField conclusions = textfield(item.getDescription(), 400.00, "Customize conclusions for this indicator range");
        conclusions.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) conclusions.getText();
            item.setDescription(text);
            storage.addItem(item.getId(), item);
        });
        return conclusions;
    }

    private TextField value(Formula item) {
        TextField value = textfield(item.getValue(), 40.00, "");
        value.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) value.getText();
            item.setValue(text);
            storage.addItem(item.getId(), item);
        });
        return value;
    }

    private TextField value2(Formula item) {
        TextField value2 = textfield(item.getUnit(), 40.00, "");
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


    private TextField textfield(String value, Double width, String prompt) {
        TextField textfield = new TextField();
        textfield.setMaxWidth(width);
        textfield.setText(value);
        textfield.setPromptText(prompt);
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
            if (dialogButton == closeButtonType) {
                if(type.equals("add")){
                    TreeItem Parent = treeItem.getParent();
                    if(Parent != null){
                        Parent.getChildren().remove(treeItem);
                    }
                }
                dialog.setResult(Boolean.TRUE);
                dialog.close();
            }

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
                storage.addItemsAdded(formulasAdd);
                storage.addItemsDeleted(formulasRemove);
                dialog.setResult(Boolean.TRUE);
                dialog.close();
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
                " ",
                ">",
                ">=",
                "=",
                "<",
                "<="
        );
    }

    private ObservableList<String> nameOp() {
        return FXCollections.observableArrayList(
                " ",
                "excellent",
                "good",
                "satisfactory",
                "unsatisfactory",
                "bad"
        );
    }

}
