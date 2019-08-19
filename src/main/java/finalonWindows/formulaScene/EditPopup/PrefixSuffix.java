package finalonWindows.formulaScene.EditPopup;

import defaultData.EvaluationTypes;
import entities.Formula;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrefixSuffix implements LabelWrap {
    private Formula parent;
    private Tab tab;
    private Formula prefix;
    private Formula suffix;

    PrefixSuffix(Formula parent) {
        this.parent = parent;
        this.prefix = getItem(EvaluationTypes.PREFIX);
        this.suffix = getItem(EvaluationTypes.SUFFIX);
        this.tab = new Tab("Prefix / Suffix");
        tab.setContent(normativeValues());

    }


    Tab getPrefixSuffix() {
        return tab;
    }

    private VBox normativeValues() {
        VBox vBoxOuter = new VBox(15);
        vBoxOuter.getStyleClass().add("popup-tab-outer");
        VBox vBox = new VBox(15);
        vBox.getStyleClass().add("popup-tab-inner");
        HBox hbox = new HBox(10);
        hbox.getChildren().add(
                labelWrap("Prefix - will get displayed before formula evaluation conclusions " +
                        "while suffix value will be appended after evaluation conclusions. ")
        );
        HBox hbox1 = new HBox(10);
        hbox1.getChildren().addAll(new Label("Prefix"), input(prefix));
        HBox hbox2 = new HBox(10);
        hbox2.getChildren().addAll(new Label("Suffix"), input(suffix));
        vBox.getChildren().addAll(hbox, hbox1, hbox2);
        vBoxOuter.getChildren().addAll(vBox);
        return vBoxOuter;
    }

    private Formula getItem(EvaluationTypes type) {
        ObservableList<Formula> items = parent.getChilds();
        Formula formula = null;
        for (Formula item : items) {
            String name = item.getName();
            if (name.equals(type.toString())) {
                formula = item;
            }
        }
        if (formula == null) {
            formula = new Formula(-1,
                    type.toString(),
                    "",
                    "",
                    "",
                    "",
                    "",
                    parent.getId());
            parent.getChilds().add(formula);
        }
        return formula;
    }

    private TextField input(Formula item) {
        TextField conclusions = new TextField();
        conclusions.setPrefWidth(480.00);
        conclusions.setText(item.getDescription());
        conclusions.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) conclusions.getText();
            item.setDescription(text);
        });
        return conclusions;
    }

}
