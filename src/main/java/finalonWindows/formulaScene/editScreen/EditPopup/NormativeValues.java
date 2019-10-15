package finalonWindows.formulaScene.editScreen.EditPopup;

import defaultData.EvaluationTypes;
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

public class NormativeValues {
    private Formula formula;
    private ScrollPane scrollPane;
    private Tab tab;
    private EvaluationTypes type;


    public NormativeValues(
            Formula formula,
            EvaluationTypes type,
            String tabName
    ) {
        this.formula = formula;
        this.type = type;
        this.tab = new Tab(tabName);
        this.scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        tab.setContent(normativeValues());
    }


    public Tab getTab() {
        return tab;
    }


    private VBox normativeValues() {
        VBox vBoxOuter = new VBox(15);
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(10));
        vBox.setPrefWidth(650.00);

        for (Formula item : formula.getChildsOfType(type)) {
            if (filter(item)) {
                HBox hbox = new HBox(10);
                hbox.getStyleClass().add("normative-row");
                VBox vBoxIn = new VBox(3);
                vBoxIn.getStyleClass().add("normative-container");
                vBoxIn.getChildren().addAll(choices(item), conclusions(item));
                hbox.getChildren().addAll(vBoxIn, removeButton(item.getId()));
                vBox.getChildren().add(hbox);
            }
        }
        scrollPane.setContent(vBox);
        vBoxOuter.getChildren().addAll(scrollPane, addButton());
        return vBoxOuter;
    }

    private HBox choices(Formula item) {
        HBox hbox = new HBox(10);
        hbox.setPrefWidth(650);
        hbox.getChildren().addAll(
                new Label("Value Is"),
                comparator(item),
                value(item),
                new Label("And"),
                comparator2(item),
                value2(item)
        );
        return hbox;
    }

    private Boolean filter(Formula item) {
        if (item.getCategory().equals("TO_BE_DELETED")) return false;
        if (item.getName().equals(type.toString())) return true;
        return false;
    }


    private Button addButton() {
        Button btn = new Button("+ Add");
        btn.getStyleClass().add("normative-add-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Formula item = new Formula(-1, type.toString(), "", "", "", "", "", formula.getId());
                formula.getChilds().add(item);
                tab.setContent(normativeValues());
                scrollPane.setVvalue(1.0);
            }
        });
        return btn;
    }

    private Button removeButton(int Id) {
        ImageButton btn = new ImageButton("image/trash.png", 16);
        btn.getStyleClass().add("normative-remove-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ObservableList<Formula> childs = formula.getChildsOfType(type);
                for (int j = 0; j < childs.size(); j++) {
                    Formula item = childs.get(j);
                    if (item.getId() == Id) {
                        item.setCategory("TO_BE_DELETED");
                    }
                }
                tab.setContent(normativeValues());
            }
        });
        return btn;
    }


    private TextField conclusions(Formula item) {
        TextField conclusions = new TextField();
        conclusions.setMaxWidth(550.00);
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

    private ComboBox comparator2(Formula item) {
        ComboBox comparator2 = choicebox(compOp(), item.getCategory());
        comparator2.setOnAction((e) -> {
            String text = (String) comparator2.getSelectionModel().getSelectedItem();
            item.setCategory(text);
        });
        return comparator2;
    }


    private TextField textfield(String value, Double width, String prompt) {
        TextField textfield = new NumField(value);
        textfield.setMaxWidth(width);
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
}


