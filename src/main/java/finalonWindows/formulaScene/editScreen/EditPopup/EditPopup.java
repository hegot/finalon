package finalonWindows.formulaScene.editScreen.EditPopup;

import database.formula.DbFormulaHandler;
import defaultData.EvaluationTypes;
import entities.Formula;
import finalonWindows.formulaScene.editScreen.FormulaEditable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;


public class EditPopup {

    private Formula formula;
    private ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
    private ButtonType closeButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.NEXT_FORWARD);
    private EditFormula editFormula;
    private NormativeValues normativeValuesEndPeriod;
    private NormativeValues normativeValuesPreEndPeriod;
    private PrefixSuffix prefixSuffix;
    private PeriodsComparison periodsComparison;
    private ArrayList<String> errors = new ArrayList<>();

    public EditPopup(TreeItem treeItem) {
        errors.clear();
        this.formula = (Formula) treeItem.getValue();
        this.editFormula = new EditFormula(formula);
        formula.setChilds(getChilds());
        this.normativeValuesPreEndPeriod = new NormativeValues(
                formula,
                EvaluationTypes.EVALUATE_PRE_END,
                "Pre-End Period Evaluation"
        );
        this.normativeValuesEndPeriod = new NormativeValues(
                formula,
                EvaluationTypes.EVALUATE_END,
                "End Period Evaluation"
        );
        this.prefixSuffix = new PrefixSuffix(formula);
        this.periodsComparison = new PeriodsComparison(formula);
    }

    private ObservableList<Formula> getChilds() {
        return DbFormulaHandler.getFormulas(formula.getId());
    }

    public Dialog getdialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setWidth(400);
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add("styles/formulaEdit.css");
        dialogPane.getStyleClass().add("myDialog");
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, closeButtonType, ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        setDialogContent(dialog);
        EventHandler<ActionEvent> saveFilter = this::saveAction;
        Button okButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        okButton.addEventFilter(ActionEvent.ACTION, saveFilter);
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(closeButtonType);
        EventHandler<ActionEvent> closeFilter = event -> {
            dialog.close();
        };
        cancelButton.addEventFilter(ActionEvent.ACTION, closeFilter);
        dialog.showAndWait();

        return dialog;
    }


    private void setDialogContent(Dialog dialog) {
        VBox vBox = new VBox();
        if (formula.getCategory().equals("section")) {
            dialog.setTitle("Edit Section");
            vBox.getChildren().addAll(editFormula.getGrid());
            vBox.setPadding(new Insets(10, 10, 10, 10));
            dialog.getDialogPane().setContent(vBox);
        } else {
            dialog.setTitle("Edit Formula");
            TabPane tabpane = new TabPane();
            tabpane.getTabs().addAll(
                    editFormula.getTab(),
                    normativeValuesEndPeriod.getTab(),
                    normativeValuesPreEndPeriod.getTab(),
                    prefixSuffix.getPrefixSuffix(),
                    periodsComparison.getPeriodsComparison()
            );
            vBox.getChildren().addAll(tabpane);
            dialog.getDialogPane().setContent(vBox);
        }
    }


    private void saveAction(ActionEvent event) {
        errors.clear();
        EditFormula.errorsBox.getChildren().removeAll();
        validateFormula();
        TreeSet<String> errsTextarea = editFormula.getTextAreaErrors();
        if (errors.size() == 0 && errsTextarea.size() == 0) {
            formula.setCategory("formula");
            addFormula(formula);
            FormulaEditable.refresh();
        } else {
            EditFormula.errorsBox.getChildren().removeAll();
            for (String error : errors) {
                Label label = new Label(error);
                label.getStyleClass().add("formula-error");
                EditFormula.errorsBox.getChildren().setAll(label);
            }
            event.consume();
        }
    }

    private void addFormula(Formula formula) {
        try {
            EditFormula.errorsBox.getChildren().removeAll();
            DbFormulaHandler.updateFormula(formula);
            ObservableList<Formula> childs = formula.getChilds();
            for (Formula child : childs) {
                if (child.getId() == -1) {
                    if (isChild(child.getName())) {
                        if (child.getDescription().length() > 2) {
                            DbFormulaHandler.addFormula(child);
                        }
                    } else {
                        DbFormulaHandler.addFormula(child);
                    }
                } else if (child.getCategory().equals("TO_BE_DELETED")) {
                    DbFormulaHandler.deleteItem(child.getId());
                } else {
                    DbFormulaHandler.updateFormula(child);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isChild(String name) {
        if (
            name != null &&
            name.equals(EvaluationTypes.PREFIX.toString()) ||
            name.equals(EvaluationTypes.SUFFIX.toString()) ||
            name.equals(EvaluationTypes.PERIOD_COMPARISON_NOCHANGE.toString()) ||
            name.equals(EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString()) ||
            name.equals(EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString())
        ) {
            return true;
        }
        return false;
    }


    private void validateFormula() {
        Formula newFormula = editFormula.getFormula();
        ArrayList<String> codes = FormulaEditable.getCodes();
        if(formula.getCategory().equals("TO_BE_ADDED")){
            if (codes.contains(newFormula.getShortName())) {
                errors.add("Formula code you have added is already in use. \nPlease set up unique code.");
            }
        }
        int occurrences = Collections.frequency(codes, newFormula.getShortName());
        if (occurrences > 0) {
            errors.add("Formula code you have added is already in use. \nPlease set up unique code.");
        }
        if (newFormula.getName().length() == 0) {
            errors.add("Name field can not be empty! \n");
        }
        if (!formula.getCategory().equals("industry")) {
            if (newFormula.getShortName().length() == 0) {
                errors.add("Code field can not be empty! \n");
            }
            if (newFormula.getValue().length() == 0) {
                errors.add("Please add formula in text-field");
            }
        }
    }

}
