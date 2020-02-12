package finalonWindows.formulaScene.editScreen.EditPopup;

import defaultData.EvaluationTypes;
import entities.Formula;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PeriodsComparison {
    private Formula parent;
    private Tab tab;
    private Formula decrease;
    private Formula increase;
    private Formula nochange;

    PeriodsComparison(Formula parent) {
        this.parent = parent;
        this.decrease = getItem(EvaluationTypes.PERIOD_COMPARISON_INCREASE);
        this.increase = getItem(EvaluationTypes.PERIOD_COMPARISON_DECREASE);
        this.nochange = getItem(EvaluationTypes.PERIOD_COMPARISON_NOCHANGE);
        this.tab = new Tab("Periods Comparison");
        tab.setContent(normativeValues());

    }


    Tab getPeriodsComparison() {
        return tab;
    }

    private VBox normativeValues() {
        VBox vBoxOuter = new VBox(15);
        vBoxOuter.getStyleClass().add("popup-tab-outer");
        VBox vBox = new VBox(15);
        vBox.getStyleClass().add("popup-tab-inner");
        HBox hbox = new HBox(10);
        hbox.getChildren().add(
                LabelWrap.wrap("Period comparison conclusions describe case " +
                        "when formula calculated value increased/decreased/did not changed during evaluation period. ")
        );

        HBox hbox1 = new HBox(10);
        hbox1.getChildren().addAll(new Label("Decrease"), input(EvaluationTypes.PERIOD_COMPARISON_DECREASE, decrease));
        HBox hbox2 = new HBox(10);
        hbox2.getChildren().addAll(new Label("Increase"), input(EvaluationTypes.PERIOD_COMPARISON_INCREASE, increase));
        HBox hbox3 = new HBox(10);
        hbox3.getChildren().addAll(new Label("Did not changed"), input(EvaluationTypes.PERIOD_COMPARISON_NOCHANGE, nochange));
        vBox.getChildren().addAll(hbox, hbox1, hbox2, hbox3);
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

    private TextField input(EvaluationTypes type, Formula item) {
        TextField conclusions = new TextField();
        conclusions.setPrefWidth(400.00);
        conclusions.setText(item.getDescription());
        String typeStr = getType(type);
        conclusions.setPromptText("Conclusions in case " + typeStr);
        conclusions.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) conclusions.getText();
            item.setDescription(text);
        });
        return conclusions;
    }

    private String getType(EvaluationTypes type) {
        if (type == EvaluationTypes.PERIOD_COMPARISON_DECREASE) return "index value decreased";
        if (type == EvaluationTypes.PERIOD_COMPARISON_INCREASE) return "index value increased";
        return "index value did not changed";
    }
}
