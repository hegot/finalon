package finalonWindows.templateScene.templates.Cells;

import database.formula.DbFormulaHandler;
import entities.Formula;
import entities.Item;
import finalonWindows.reusableComponents.autocomplete.AutoCompleteTextArea;
import finalonWindows.templateScene.templates.TemplateEditPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class FormulaUpdater {
    private Map<Integer, AutoCompleteTextArea> formulaMap = new HashMap<Integer, AutoCompleteTextArea>();
    private Map<Integer, Formula> usages;
    private TableView table;
    private Item selectedItem;
    private Label errors = new Label();
    private Dialog<Pair<String, String>> dialog = new Dialog<>();

    public FormulaUpdater(Map<Integer, Formula> usages, TableView table, Item selectedItem){
        this.table = table;
        this.selectedItem = selectedItem;
        this.usages = usages;
    }

    private ScrollPane formulasEditBox(){

        VBox vBox = new VBox(5);
        for (Map.Entry<Integer, Formula> entry : usages.entrySet()) {
            Formula formula  = entry.getValue();
            Label label = new Label("\nUpdate following formula: «" + formula.getName() + "»");
            AutoCompleteTextArea textArea = new AutoCompleteTextArea(formula.getValue());
            textArea.setWrapText(true);
            textArea.setPrefSize(400, 80);
            formulaMap.put(formula.getId(), textArea);
            vBox.getChildren().addAll(label, textArea);
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-padding: 4px;");
        scrollPane.setContent(vBox);
        scrollPane.setFitToWidth(true);
        if(formulaMap.size() > 1){
            scrollPane.setPrefHeight(200);
        }
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        return scrollPane;
    }

    public void showDialog() {
        errors.setWrapText(true);
        errors.setStyle("-fx-text-fill: red;");
        dialog.setWidth(400);
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add("styles/formulaEdit.css");
        dialogPane.getStyleClass().add("myDialog");
        ButtonType closeButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.NEXT_FORWARD);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE, closeButtonType);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.setVisible(false);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(closeButtonType);
        EventHandler<ActionEvent> closeFilter = event -> {
            dialog.close();
        };

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER_LEFT);
        Label label =  new Label("Index that you want to delete is used in correlated formulas, " +
                "\nplease edit formula calculation and remove index" +
                "\nthere to be able to delete it.");
        vBox.getChildren().addAll(label, formulasEditBox(), errors, saveBtn(), deleteBtn());
        dialog.getDialogPane().setContent(vBox);
        dialog.showAndWait();
    }

    private Button deleteBtn(){
        Button btn = new Button("Remove all - index and correlated formulas");
        btn.getStyleClass().add("updater-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    for (Map.Entry<Integer, AutoCompleteTextArea> entry : formulaMap.entrySet()) {
                        DbFormulaHandler.deleteItem(entry.getKey());
                    }
                    table.getItems().remove(selectedItem);
                    TemplateEditPage.getItems().remove(selectedItem);

                } catch (Exception exception) {
                    System.out.println("Error while deleting index and formulas");
                }
            }
        });
        return btn;
    }


    private Button saveBtn(){
        Button btn = new Button("Save updated formulas and remove index");
        btn.getStyleClass().add("updater-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    Boolean contains = false;
                    for (Map.Entry<Integer, AutoCompleteTextArea> entry : formulaMap.entrySet()) {
                       Formula formula = usages.get(entry.getKey());
                        AutoCompleteTextArea editor = entry.getValue();
                        String output = editor.getText();
                        contains = output.contains(selectedItem.getShortName());
                        if(contains){
                            errors.setText("Index was not fully removed from formulas, please try one more time.");
                            break;
                        }else{
                            formula.setValue(output);
                            DbFormulaHandler.updateFormula(formula);
                        }
                    }
                    if(!contains){
                        table.getItems().remove(selectedItem);
                        TemplateEditPage.getItems().remove(selectedItem);
                        dialog.close();
                    }
                } catch (Exception exception) {
                    System.out.println("Error while saving formulas and removing index");
                }
            }
        });
        return btn;
    }

}
