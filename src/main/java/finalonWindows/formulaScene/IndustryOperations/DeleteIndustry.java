package finalonWindows.formulaScene.IndustryOperations;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.Storage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class DeleteIndustry implements CancelBtn {
    private Formula industry;
    private Dialog<Pair<String, String>> dialog;

    public DeleteIndustry(Formula industry, Dialog<Pair<String, String>> dialog) {
        this.industry = industry;
        this.dialog = dialog;
    }

    public VBox getContent() {
        VBox vBox = new VBox(20);
        HBox hBox = new HBox(90);
        hBox.setPrefHeight(40);
        hBox.getChildren().addAll(
                cancelBtn(dialog),
                deleteIndustryBtn()
        );
        vBox.getChildren().addAll(
                new Label("Are you sure you want to delete " +
                        "\nthis industry? Action can not be \nundone."),
                hBox
        );
        return vBox;
    }


    private Button deleteIndustryBtn() {
        Button btn = new Button("Delete");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            dialog.close();
            new DbFormulaHandler().deleteItem(industry.getId());
            Storage.refresh();
        });
        return btn;
    }
}
