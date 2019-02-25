package finalonWindows.formulaScene.eventHandlers;

import entities.Formula;
import finalonWindows.reusableComponents.ImageButton;
import finalonWindows.reusableComponents.NumField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


class NormativeValues {
    private ScrollPane scrollPane;
    private FormulaExtended parent;
    private Tab tab;

    NormativeValues(FormulaExtended parent) {
        this.parent = parent;
        tab = new Tab("Normative values");
        this.scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        tab.setContent(normativeValues());
    }


    Tab getNormativeValues() {
        return tab;
    }

    FormulaExtended getFormulaUpdated() {
        return parent;
    }

    private VBox normativeValues() {
        VBox vBoxOuter = new VBox(15);
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(10, 2, 10, 2));
        vBox.setPrefWidth(450.00);
        for (Formula item : parent.getChilds()) {
            HBox hbox = new HBox(10);
            VBox vBoxIn = new VBox(3);
            vBoxIn.getStyleClass().add("normative-container");
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
        scrollPane.setContent(vBox);
        vBoxOuter.getChildren().addAll(scrollPane, addButton());
        return vBoxOuter;
    }

    private Button addButton() {
        Button btn = new Button("+ Add");
        btn.getStyleClass().add("normative-add-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Formula item = new Formula(-1, "", "", "", "", "", "", parent.getId());
                parent.getChilds().add(item);
                tab.setContent(normativeValues());
                scrollPane.setVvalue(1.0);
            }
        });
        return btn;
    }

    private Button removeButton(int Id) {
        ImageButton btn = new ImageButton("image/removeBlack.png", 16);
        btn.getStyleClass().add("normative-remove-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int rem = -1;
                ObservableList<Formula> items = parent.getChilds();
                for (int j = 0; j < items.size(); j++) {
                    Formula item = items.get(j);
                    if (item.getId() == Id) {
                        rem = j;
                    }
                }
                items.remove(rem);
                tab.setContent(normativeValues());
            }
        });
        return btn;
    }


    private TextField conclusions(Formula item) {

        TextField conclusions = new TextField();
        conclusions.setMaxWidth(400.00);
        conclusions.setText(item.getDescription());
        conclusions.setPromptText("Customize conclusions for this indicator range");
        conclusions.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) conclusions.getText();
            item.setDescription(text);
        });
        return conclusions;
    }

    private TextField value(Formula item) {
        TextField value = textfield(item.getValue(), 40.00, "");
        value.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = value.getText();
            item.setValue(text);
        });
        return value;
    }

    private TextField value2(Formula item) {
        TextField value2 = textfield(item.getUnit(), 40.00, "");
        value2.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = value2.getText();
            item.setUnit(text);
        });
        return value2;
    }

    private ComboBox comparator(Formula item) {
        ComboBox comparator = choicebox(compOp(), item.getShortName());
        comparator.setOnAction((e) -> {
            String text = (String) comparator.getSelectionModel().getSelectedItem();
            item.setShortName(text);
        });
        return comparator;
    }

    private ComboBox name(Formula item) {
        ComboBox name = choicebox(nameOp(), item.getName());
        name.setOnAction((e) -> {
            String text = (String) name.getSelectionModel().getSelectedItem();
            item.setName(text);
        });
        return name;
    }

    private ComboBox comparator2(Formula item) {
        ComboBox comparator2 = choicebox(compOp(), item.getCategory());
        comparator2.setOnAction((e) -> {
            String text = (String) comparator2.getSelectionModel().getSelectedItem();
            item.setCategory(text);
        });
        return comparator2;
    }


    private TextField textfield(String value, Double width, String prompt) {
        TextField textfield = new NumField();
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


