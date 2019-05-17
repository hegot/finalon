package finalonWindows.formulaScene.eventHandlers;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.EditStorage;
import finalonWindows.reusableComponents.autocomplete.AutoCompleteTextArea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Pair;


public class EditPopup {

    private Formula formula;
    private TreeItem treeItem;
    private ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
    private ButtonType closeButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.NEXT_FORWARD);
    private String type;
    private EditFormula editFormula;
    private NormativeValues normativeValues;

    public EditPopup(TreeItem treeItem, String type) {
        this.type = type;
        this.treeItem = treeItem;
        this.formula = (Formula) treeItem.getValue();


        this.editFormula = new EditFormula(formula);
        Formula formulaExtended = EditStorage.find(formula.getId());
        if (formulaExtended == null) {
            formula.setChilds(getChilds());
        }
        this.normativeValues = new NormativeValues(formula);
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
        if (formula.getCategory().equals("industry")) {
            dialog.setTitle("Edit Industry");
            dialog.getDialogPane().setContent(editFormula.getGrid());
        } else {
            dialog.setTitle("Edit Formula");
            TabPane tabpane = new TabPane();
            tabpane.getTabs().addAll(editFormula.getTab(), normativeValues.getNormativeValues());
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
                EditStorage.addItem(formula.getId(), formula);
                dialog.close();
            } else {
                if (editFormula.getTextArea().getErrors().size() > 0) {
                    event.consume();
                } else {
                    updateFormula();
                    treeItem.setValue(formula);
                    Formula formulaWithNormative = normativeValues.getFormulaUpdated();
                    ObservableList<Formula> childs = formulaWithNormative.getChilds();
                    formula.setChilds(childs);
                    EditStorage.addItem(formula.getId(), formula);
                    dialog.close();
                }
            }
        };
        Button okButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        okButton.addEventFilter(ActionEvent.ACTION, saveFilter);

        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(closeButtonType);
        EventHandler<ActionEvent> closeFilter = event -> {
            if (type.equals("add")) {
                TreeItem Parent = treeItem.getParent();
                if (Parent != null) {
                    Parent.getChildren().remove(treeItem);
                }
            }
            dialog.close();
        };
        cancelButton.addEventFilter(ActionEvent.ACTION, closeFilter);
    }


    private void updateFormula() {
        Row[] arr = editFormula.getTextfields();

        for (Row row : arr) {
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
