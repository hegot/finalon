package finalonWindows.formulaScene.eventHandlers;

import entities.Formula;
import finalonWindows.ImageButton;
import finalonWindows.NumField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class NormativeValues {
    private ScrollPane scrollPane;
    private FormulaExtended parent;
    private Tab tab;

    public NormativeValues(FormulaExtended parent) {
        this.parent = parent;
        tab = new Tab("Normative values");
        this.scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        tab.setContent(normativeValues());
    }


    public Tab getNormativeValues() {
        return tab;
    }

    public FormulaExtended getFormulaUpdated() {
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
                Formula item = new Formula(-1, "", "", "", "", "", "", parent.getId());
                parent.getChilds().add(item);
                tab.setContent(normativeValues());
                scrollPane.setVvalue(1.0);
            }
        });
        return btn;
    }

    private Button removeButton(int Id) {
        ImageButton btn = new ImageButton();
        btn.setStyle(btnStyle());
        btn.updateImages(new Image("image/removeBlack.png"), 16);
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

    private String btnStyle() {
        return "-fx-background-color: #A2CBDF;" +
                "-fx-font-size: 0px;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-padding: 4px;" +
                "-fx-alignment:  baseline-left;" +
                "-fx-background-radius: 5em;";
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


