package finalon.finalonWindows.formulaScene.IndustryOperations;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Formula;
import finalon.finalonWindows.formulaScene.FormulaEditable;
import finalon.globalReusables.CancelBtn;
import finalon.globalReusables.StandardAndIndustry;
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
        HBox hBox = new HBox(90);
        hBox.setPrefHeight(40);
        hBox.getChildren().addAll(
                CancelBtn.cancelBtn(dialog),
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
            DbFormulaHandler.deleteItem(industry.getId());
            StandardAndIndustry.refreshIndustry();
            FormulaEditable.refresh();
        });
        return btn;
    }
}
