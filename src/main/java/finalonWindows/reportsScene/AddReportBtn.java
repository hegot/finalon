package finalonWindows.reportsScene;

import database.template.DbItemHandler;
import entities.Formula;
import entities.FormulaConverter;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import globalReusables.StandardAndIndustry;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class AddReportBtn extends HBox {

    public AddReportBtn(String text) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings/addTplBtn.fxml"));
        fxmlLoader.getNamespace().put("labelText", text);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void addTemplateAction() {
        SceneSwitcher.goTo(SceneName.ADDREPORT);
    }



}