package finalonWindows.formulaScene.eventHandlers;

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

public class PrefixSuffix {
    private ScrollPane scrollPane;
    private Formula parent;
    private Tab tab;
    private Formula prefix;
    private Formula suffix;

    PrefixSuffix(Formula parent) {
        this.parent = parent;
        this.prefix = getItem(EvaluationTypes.PREFIX);
        this.suffix = getItem(EvaluationTypes.SUFFIX);
        this.tab = new Tab("Prefix / Suffix");
        this.scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        tab.setContent(normativeValues());

    }

    public ObservableList<Formula> getVals(){
        ObservableList<Formula> vals = FXCollections.observableArrayList();
        if(prefix.getId() != -1 || prefix.getDescription().length() > 0){
            vals.add(prefix);
        }
        if(suffix.getId() != -1 || suffix.getDescription().length() > 0){
            vals.add(suffix);
        }
        return vals;
    }


    Tab getPrefixSuffix() {
        return tab;
    }

    private VBox normativeValues() {
        VBox vBoxOuter = new VBox(15);
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(10, 2, 10, 2));
        vBox.setPrefWidth(550.00);

        HBox hbox1 = new HBox(10);
        hbox1.getChildren().addAll( new Label("Prefix"),  input(EvaluationTypes.PREFIX, prefix));
        HBox hbox2 = new HBox(10);
        hbox2.getChildren().addAll( new Label("Suffix"),  input(EvaluationTypes.SUFFIX, suffix));
        vBox.getChildren().addAll(hbox1, hbox2);
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
        conclusions.setPrefWidth(480.00);
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
