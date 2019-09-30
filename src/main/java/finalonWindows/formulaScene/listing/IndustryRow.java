package finalonWindows.formulaScene.listing;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.formulaScene.editScreen.IndustryEdit;
import globalReusables.StandardAndIndustry;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class IndustryRow extends VBox {
    @FXML
    private Formula formula;

    public IndustryRow(Formula formula) {
        this.formula = formula;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listing/templateRow.fxml"));
        if(coreIndustries()){
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listing/emptyRow.fxml"));
        }
        fxmlLoader.getNamespace().put("labelText", formula.getName());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void editRowAction() {
        SceneSwitcher.getWindow().getChildren().setAll(IndustryEdit.getScene(formula.getId()));
    }

    @FXML
    protected void deleteRowAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Industry");
        alert.setHeaderText("Are you Sure you want to delete industry? This Action can not be undone.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            DbFormulaHandler.deleteItem(formula.getId());
            StandardAndIndustry.refreshIndustry();
            SceneSwitcher.goTo(SceneName.FORMULALIST);
        }
    }

    private Boolean coreIndustries() {
        return formula.getShortName().equals("IFRS General") || formula.getShortName().equals("US GAAP General");
    }
}