package finalonWindows.formulaScene.AddIndustryPopup;

import database.formula.FormulaCreator;
import entities.Formula;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.reusableComponents.selectbox.StandardSelect;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.Optional;

public class AddIndustryPopup {

    private TextField industry;
    private ComboBox<Formula> standard;
    private ObservableMap<String, String> settings;

    public AddIndustryPopup(ObservableMap<String, String> settings) {
        this.settings = settings;
        this.industry = new TextField();
        this.standard = StandardSelect.get(settings);
    }

    public Dialog getdialog() {
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
        result.ifPresent(usernamePassword -> {
            FormulaCreator formulaCreator = new FormulaCreator(
                    standard.getValue().getId(),
                    industry.getText()
            );
            try {
                formulaCreator.createFormulas();
                SceneSwitcher.goTo(SceneName.FORMULA);
            } catch (SQLException e) {
                System.out.println("Username=" + industry.getText() + ", standard=" + standard.getValue().getName() + " not created");
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

}
