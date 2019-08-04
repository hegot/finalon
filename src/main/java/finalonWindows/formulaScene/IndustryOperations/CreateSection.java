package finalonWindows.formulaScene.IndustryOperations;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.FormulaAddBase;
import finalonWindows.formulaScene.Storage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class CreateSection extends FormulaAddBase implements CancelBtn {
    private Formula industry;
    private TextField textField;
    private Label error;
    private Dialog<Pair<String, String>> dialog;

    public CreateSection(Formula industry, Dialog<Pair<String, String>> dialog) {
        this.industry = industry;
        this.textField = new TextField();
        this.error = new Label("Please add section name");
        this.dialog = dialog;
        error.setWrapText(true);
    }

    public VBox getContent() {
        VBox vBox = new VBox(10);
        HBox hBox = new HBox(60);
        hBox.setPrefHeight(40);
        hBox.getChildren().addAll(
                cancelBtn(dialog),
                createSectionBtn()
        );
        vBox.getChildren().addAll(
                error,
                textField,
                hBox
        );
        return vBox;
    }

    private Button createSectionBtn() {
        Button btn = new Button("Add Section");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            String text = textField.getText();
            if (text.length() > 3) {
                Formula newSection = new Formula(biggestId(), text, text.replaceAll("\\s+", "_"), "", "", "section", "", industry.getId());
                DbFormulaHandler dbFormula = new DbFormulaHandler();
                try {
                    dbFormula.updateFormula(newSection);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                dialog.close();
                Storage.refresh();
            } else {
                error.setText("Industry name should be at least 3 characters long!");
                error.setWrapText(true);
            }
        });
        return btn;
    }
}
