package finalonWindows.templateScene.listing.partials;

import database.template.TemplateCreator;
import entities.Formula;
import entities.FormulaConverter;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Optional;

public class AddTemplateBtn extends HBox {

    private int tplId;
    private TextField template;

    public AddTemplateBtn(String text) {
        this.template = new TextField();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listing/addTplBtn.fxml"));
        fxmlLoader.getNamespace().put("labelText", text);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static ComboBox<Formula> industry() {
        ComboBox<Formula> industryBox = new ComboBox<Formula>();
        industryBox.setPrefWidth(200);
        industryBox.setConverter(
                new FormulaConverter()
        );
        return industryBox;
    }

    @FXML
    protected void addTemplateAction() {
        showDialog();
    }


    private void runTask(String title) {
        Task listLoader = new Task<Integer>() {
            {
                setOnSucceeded(workerStateEvent -> {
                    SceneSwitcher.goTo(SceneName.TEMPLATESLIST);
                });
                setOnFailed(workerStateEvent -> getException().printStackTrace());
            }

            @Override
            protected Integer call() throws Exception {
                TemplateCreator saver = new TemplateCreator();
                saver.saveTpl(title);
                return null;
            }
        };
        Thread loadingThread = new Thread(listLoader, "list-loader2");
        loadingThread.setDaemon(true);
        loadingThread.start();
    }


    public Dialog showDialog() {
        Dialog dialog = new Dialog();
        dialog.setTitle("Add New Template");
        ButtonType saveButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.setDisable(true);
        template.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().length() < 2);
        });
        template.setStyle("-fx-pref-width: 190px;");
        dialog.getDialogPane().setPrefSize(180, 100);
        dialog.getDialogPane().setContent(getContent());
        Optional<ButtonType> result = dialog.showAndWait();
        result.ifPresent(id -> {
            try {
                ButtonType button = result.get();
                if(button == ButtonType.CANCEL){
                    dialog.close();
                }else{
                    runTask(template.getText());
                    SceneSwitcher.runPreloader(12000, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return dialog;
    }

    private GridPane getContent() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(new Label("Template Name:"), 0, 0);
        grid.add(template, 0, 1);
        return grid;
    }

}