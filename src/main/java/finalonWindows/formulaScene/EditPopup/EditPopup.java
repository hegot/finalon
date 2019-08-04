package finalonWindows.formulaScene.EditPopup;

import database.formula.DbFormulaHandler;
import defaultData.EvaluationTypes;
import entities.Formula;
import finalonWindows.formulaScene.Storage;
import finalonWindows.reusableComponents.autocomplete.AutoCompleteTextArea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.sql.SQLException;


public class EditPopup {

    private Formula formula;
    private TreeItem treeItem;
    private ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
    private ButtonType closeButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.NEXT_FORWARD);
    private String type;
    private EditFormula editFormula;
    private NormativeValues normativeValuesEndPeriod;
    private NormativeValues normativeValuesPreEndPeriod;
    private PrefixSuffix prefixSuffix;
    private PeriodsComparison periodsComparison;

    public EditPopup(TreeItem treeItem, String type) {
        this.type = type;
        this.treeItem = treeItem;
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
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        return dbFormula.getFormulas(formula.getId());
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
        if (formula.getCategory().equals("section")) {
            dialog.setTitle("Edit Section");
            dialog.getDialogPane().setContent(editFormula.getGrid());
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
            dialog.getDialogPane().setContent(tabpane);
        }

        dialogSubmit(dialog);
        dialog.showAndWait();
        return dialog;
    }

    private void dialogSubmit(Dialog dialog) {
        EventHandler<ActionEvent> saveFilter = event -> {
            if (formula.getCategory().equals("industry")) {
                updateFormula();
                treeItem.setValue(formula);
                addFormula(formula);
                dialog.close();
                Storage.refresh();
            } else {
                if (editFormula.getTextArea().getErrors().size() > 0) {
                    event.consume();
                } else {
                    dialog.close();
                    updateFormula();
                    addFormula(formula);
                }
            }
        };
        Button okButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        okButton.addEventFilter(ActionEvent.ACTION, saveFilter);

        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(closeButtonType);
        EventHandler<ActionEvent> closeFilter = event -> {
            dialog.close();
        };
        cancelButton.addEventFilter(ActionEvent.ACTION, closeFilter);
    }

    private void addFormula(Formula formula) {
        try {
            DbFormulaHandler dbFormula = new DbFormulaHandler();
            if (type.equals("add")) {
                dbFormula.addFormula(formula);
            } else {
                dbFormula.updateFormula(formula);
            }
            ObservableList<Formula> childs = formula.getChilds();
            for (Formula child : childs) {
                if (child.getId() == -1) {
                    String name = child.getName();
                    if (
                            name.equals(EvaluationTypes.PREFIX.toString()) ||
                                    name.equals(EvaluationTypes.SUFFIX.toString()) ||
                                    name.equals(EvaluationTypes.PERIOD_COMPARISON_NOCHANGE.toString()) ||
                                    name.equals(EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString()) ||
                                    name.equals(EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString())
                    ) {
                        if (child.getDescription().length() > 2) {
                            dbFormula.addFormula(child);
                        }
                    } else {
                        dbFormula.addFormula(child);
                    }
                } else if (child.getCategory().equals("TO_BE_DELETED")) {
                    dbFormula.deleteItem(child.getId());
                } else {
                    dbFormula.updateFormula(child);
                }
            }
            Storage.refresh();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private void updateFormula() {
        EditRow[] arr = editFormula.getTextfields();

        for (EditRow row : arr) {
            String key = row.key;
            TextField textfieldget = row.textfield;
            String value = textfieldget.getText();
            switch (key) {
                case "name":
                    formula.setName(value);
                    System.out.println(value);
                    break;
                case "shortName":
                    formula.setShortName(value);
                    break;
                case "unit":
                    formula.setUnit(value);
                    break;
                default:
                    System.out.println("no match");
            }
        }
        if (!formula.getCategory().equals("industry")) {
            formula.setCategory("formula");
            AutoCompleteTextArea textArea = editFormula.getTextArea();
            String value = textArea.getText();
            formula.setValue(value);
        }
    }


}
