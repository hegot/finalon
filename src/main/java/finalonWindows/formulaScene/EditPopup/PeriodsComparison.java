package finalonWindows.formulaScene.EditPopup;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PeriodsComparison {
    private ScrollPane scrollPane;
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
        this.scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        tab.setContent(normativeValues());

    }

    public ObservableList<Formula> getVals(){
        ObservableList<Formula> vals = FXCollections.observableArrayList();
        if(decrease.getId() != -1 || decrease.getDescription().length() > 0){
            vals.add(decrease);
        }
        if(increase.getId() != -1 || increase.getDescription().length() > 0){
            vals.add(increase);
        }
        if(nochange.getId() != -1 || nochange.getDescription().length() > 0){
            vals.add(nochange);
        }
        return vals;
    }


    Tab getPeriodsComparison() {
        return tab;
    }

    private VBox normativeValues() {
        VBox vBoxOuter = new VBox(15);
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(10, 2, 10, 2));
        vBox.setPrefWidth(550.00);

        HBox hbox1 = new HBox(10);
        hbox1.getChildren().addAll( new Label("Decrease"),  input(EvaluationTypes.PERIOD_COMPARISON_DECREASE, decrease));
        HBox hbox2 = new HBox(10);
        hbox2.getChildren().addAll( new Label("Increase"),  input(EvaluationTypes.PERIOD_COMPARISON_INCREASE, increase));
        HBox hbox3 = new HBox(10);
        hbox3.getChildren().addAll( new Label("Did not changed"),  input(EvaluationTypes.PERIOD_COMPARISON_NOCHANGE, nochange));
        vBox.getChildren().addAll(hbox1, hbox2, hbox3);
        scrollPane.setContent(vBox);
        vBoxOuter.getChildren().addAll(scrollPane);
        return vBoxOuter;
    }

    private Formula getItem(EvaluationTypes type){
        ObservableList<Formula> items = parent.getChilds();
        for(Formula item : items){
            String name = item.getName();
            if (name.equals(type.toString())){
                return item;
            }
        }
        return new Formula(-1,
                type.toString(),
                "",
                "",
                "",
                "",
                "",
                parent.getId());
    }

    private TextField input(EvaluationTypes type, Formula item) {
        TextField conclusions = new TextField();
        conclusions.setPrefWidth(400.00);
        conclusions.setText(item.getDescription());
        String typeStr = getType(type);
        conclusions.setPromptText("Customize conclusions for " + typeStr);
        conclusions.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) conclusions.getText();
            item.setDescription(text);
        });
        return conclusions;
    }

    private String getType(EvaluationTypes type){
        if (type == EvaluationTypes.PREFIX) return "prefix";
        return "suffix";
    }
}
