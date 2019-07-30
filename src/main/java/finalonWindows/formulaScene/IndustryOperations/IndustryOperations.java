package finalonWindows.formulaScene.IndustryOperations;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.Optional;

public class IndustryOperations {
    private Formula industry;
    private VBox container;
    private Dialog<Pair<String, String>> dialog;

    public IndustryOperations(Formula industry) {
        this.industry = industry;
        this.dialog = new Dialog<>();
        this.container = getContent();
    }

    public void showDialog() {
        dialog.setWidth(250);
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add("styles/formulaEdit.css");
        dialogPane.getStyleClass().add("industryDialog");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        dialog.getDialogPane().setContent(container);
        dialog.showAndWait();
    }

    private VBox getContent() {
        VBox vBox = new VBox();
        vBox.setPrefWidth(250);
        vBox.setPrefHeight(100);
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(
                createSectionBtn(),
                removeIndustryBtn()
        );
        vBox.getChildren().add(
                hBox
        );
        return vBox;
    }

    private Button removeIndustryBtn() {
        Button btn = new Button("Delete industry");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            VBox newContent = new DeleteIndustry(industry, dialog).getContent();
            container.getChildren().setAll(newContent);

        });
        return btn;
    }


    private Button createSectionBtn() {
        Button btn = new Button("Add Section");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            VBox newContent = new CreateSection(industry, dialog).getContent();
            container.getChildren().setAll(newContent);
        });
        return btn;
    }
}
