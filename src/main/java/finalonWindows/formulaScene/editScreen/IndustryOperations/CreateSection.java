package finalonWindows.formulaScene.editScreen.IndustryOperations;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.editScreen.FormulaEditable;
import globalReusables.CancelBtn;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CreateSection extends FormulaAddBase {
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
                CancelBtn.cancelBtn(dialog),
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
                reorderFormulas(text);
                dialog.close();
                FormulaEditable.refresh();
            } else {
                error.setText("Industry name should be at least 3 characters long!");
                error.setWrapText(true);
            }
        });
        return btn;
    }

    private void reorderFormulas(String text) {
        ObservableList<Formula> items = SortedSections.getSections(industry.getId());
        Formula newSection = new Formula(biggestId(), text, text.replaceAll("\\s+", "_"), "0", "", "section", "", industry.getId());
        List<Formula> itemsNew = new ArrayList<>();
        itemsNew.add(newSection);
        for (int i = 0; i < items.size(); i++) {
            Formula formula = items.get(i);
            String newIndex = Integer.toString(i + 1);
            formula.setValue(newIndex);
            itemsNew.add(formula);
        }
        try {
            for (Formula item : itemsNew) {
                try {
                    DbFormulaHandler.updateFormula(item);
                } catch (Exception e) {
                    System.out.println("Formula reordering save error");
                }
            }
            DbFormulaHandler.updateFormula(newSection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
