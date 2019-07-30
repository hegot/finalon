package finalonWindows.formulaScene.IndustryOperations;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class DeleteIndustry {
    private Formula industry;
    private Dialog<Pair<String, String>> dialog;

    public DeleteIndustry(Formula industry, Dialog<Pair<String, String>> dialog) {
        this.industry = industry;
        this.dialog = dialog;
    }

    public VBox getContent() {
        VBox vBox = new VBox(20);
        HBox hBox = new HBox(60);
        hBox.setPrefHeight(40);
        hBox.getChildren().addAll(
                cancelDeleteIndustryBtn(),
                deleteIndustryBtn()
        );
        vBox.getChildren().addAll(
                new Label("Are you sure you want to " +
                        "\ndelete this industry? " +
                        "\nAction can not be undone."),
                hBox
        );
        return vBox;
    }

    private Button cancelDeleteIndustryBtn() {
        Button btn = new Button("Cancel");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            dialog.close();
        });
        return btn;
    }

    private Button deleteIndustryBtn() {
        Button btn = new Button("Delete");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            new DbFormulaHandler().deleteItem(industry.getId());
            SceneSwitcher.goTo(SceneName.FORMULA);
            dialog.close();
        });
        return btn;
    }
}
