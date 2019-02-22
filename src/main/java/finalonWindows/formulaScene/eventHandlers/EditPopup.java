package finalonWindows.formulaScene.eventHandlers;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.EditStorage;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Pair;

public class EditPopup {

    private Formula formula;
    private TreeItem treeItem;
    private ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
    private ButtonType closeButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.NEXT_FORWARD);
    private EditStorage storage = EditStorage.getInstance();
    private String type;
    private EditFormula editFormula;
    private NormativeValues normativeValues;

    public EditPopup(TreeItem treeItem, String type) {
        this.type = type;
        this.treeItem = treeItem;
        this.formula = (Formula) treeItem.getValue();


        this.editFormula = new EditFormula(formula);
        FormulaExtended formulaExtended = storage.find(formula.getId());
        if (formulaExtended == null) {
            formulaExtended = new FormulaExtended(formula, getChilds());
        }
        this.normativeValues = new NormativeValues(formulaExtended);
    }

    private ObservableList<Formula> getChilds() {
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        return dbFormula.getFormulas(formula.getId());
    }

    public Dialog getdialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit Formula");
        dialog.setWidth(400);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, closeButtonType, ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        TabPane tabpane = new TabPane();
        tabpane.getTabs().addAll(editFormula.getTab(), normativeValues.getNormativeValues());
        dialog.getDialogPane().setContent(tabpane);
        dialogSubmit(dialog);
        return dialog;
    }

    private void dialogSubmit(Dialog dialog) {
        dialog.setResultConverter(dialogButton -> {
            dialog.setResult(Boolean.TRUE);
            if (dialogButton == closeButtonType) {
                if (type.equals("add")) {
                    TreeItem Parent = treeItem.getParent();
                    if (Parent != null) {
                        Parent.getChildren().remove(treeItem);
                    }
                }
                dialog.close();
            }
            Row[] arr = editFormula.getTextfields();
            if (dialogButton == saveButtonType) {
                if(editFormula.getErrors().size() > 0){

                }else{
                    for (int j = 0; j < arr.length; j++) {
                        Row row = arr[j];
                        String key = row.key;
                        TextField textfieldget = row.textfield;
                        String value = textfieldget.getText();
                        switch (key) {
                            case "name":
                                formula.setName(value);
                                break;
                            case "shortName":
                                formula.setShortName(value);
                                break;
                            case "value":
                                formula.setValue(value);
                                break;
                            case "unit":
                                formula.setUnit(value);
                                break;
                            default:
                                System.out.println("no match");
                        }
                    }
                    formula.setCategory("formula");
                    treeItem.setValue(formula);
                    FormulaExtended formulaExtended = normativeValues.getFormulaUpdated();
                    ObservableList<Formula> childs = formulaExtended.getChilds();
                    FormulaExtended newFormulaExtended = new FormulaExtended(formula, childs);
                    storage.addItem(formula.getId(), newFormulaExtended);
                    dialog.close();
                }
            }

            return null;
        });
    }


}
