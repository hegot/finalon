package finalonWindows.formulaScene.listing;

import database.formula.FormulaCreator;
import entities.Formula;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import globalReusables.StandardAndIndustry;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

public class AddIndustryBtn extends HBox {

    private TextField industry;
    private ComboBox<Formula> standard;

    public AddIndustryBtn(String text) {
        this.industry = new TextField();
        this.standard = StandardAndIndustry.standard();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listing/addIndustryBtn.fxml"));
        fxmlLoader.getNamespace().put("labelText", text);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Dialog showDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add New Industry");
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.setDisable(true);
        industry.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(getContent());
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Pair<>(industry.getText(), Integer.toString(standard.getValue().getId()));
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(id -> {
            try {
                runTask();
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(700),
                        ae -> SceneSwitcher.goTo(SceneName.FORMULALIST)));
                timeline.play();

            } catch (Exception e) {
                System.out.println("Industry=" + industry.getText() + ", standard=" + standard.getValue().getName() + " not created");
                e.printStackTrace();
            }
        });
        return dialog;
    }

    private GridPane getContent() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Industry:"), 0, 0);
        grid.add(industry, 1, 0);
        grid.add(new Label("Statements GAAP:"), 0, 1);
        grid.add(standard, 1, 1);
        return grid;
    }

    private void runTask() {
        Task listLoader = new Task<Integer>() {
            {
                setOnSucceeded(workerStateEvent -> {
                    StandardAndIndustry.refreshIndustry();
                });
                setOnFailed(workerStateEvent -> getException().printStackTrace());
            }

            @Override
            protected Integer call() throws Exception {
                FormulaCreator formulaCreator = new FormulaCreator(
                        standard.getValue().getId(),
                        industry.getText()
                );
                formulaCreator.createFormulas();
                return formulaCreator.getNewId();
            }
        };
        Thread loadingThread = new Thread(listLoader, "list-loader2");
        loadingThread.setDaemon(true);
        loadingThread.start();
    }

    @FXML
    protected void addIndustryAction() {
        showDialog();
    }
}