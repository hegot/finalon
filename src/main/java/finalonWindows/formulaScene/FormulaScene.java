package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;
import entities.Formula;
import entities.FormulaConverter;
import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.settingsScene.SettingsMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormulaScene extends SceneBase {

    private final int defaultStandard = 1;
    private Stage window;
    private DbFormulaHandler dbFormula = new DbFormulaHandler();
    private Formula selectedIndustry;
    private ComboBox<Formula> industryBox = getIndustry();
    private FormulaEditable formulaEditable = new FormulaEditable(selectedIndustry);

    private ComboBox<Formula> standardBox = getStandard();

    public FormulaScene(Stage windowArg) {
        window = windowArg;
    }

    public Scene getScene() {
        VBox vbox = new VBox(20);
        HBox standardChoiceBox = getChoicebox(standardBox, "Statements GAAP:", "Select the accounting standard");
        HBox industryChoiceBox = getChoicebox(industryBox, "Industry:", "Select the industry");

        SettingsMenu settingsMenu = new SettingsMenu(window);
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(0, 10, 0, 10));
        hBox.getChildren().addAll(standardChoiceBox, industryChoiceBox, saveFormulasButton());

        vbox.getChildren().addAll(
                settingsMenu.getMenu(),
                hBox,
                formulaEditable.getFormulaTable()
        );

        Scene scene = new Scene(vbox, 900, 600);
        scene.getStylesheets().addAll("styles/templateStyle.css", "styles/formulaEdit.css");
        EditStorage editStorage = EditStorage.getInstance();
        return scene;
    }


    private ObservableList<Formula> getChoices(int parent) {
        return dbFormula.getFormulas(parent);
    }

    private ComboBox getStandard() {
        ComboBox<Formula> cb = new ComboBox<>(getChoices(0));
        cb.setConverter(new FormulaConverter());
        cb.getSelectionModel().selectFirst();
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null) {
                    industryBox.setItems(getChoices(arg2.getId()));
                    industryBox.getSelectionModel().selectFirst();
                }
            }
        });
        return cb;
    }


    private ComboBox<Formula> getIndustry() {
        ComboBox<Formula> industryBox = new ComboBox<Formula>();
        industryBox.setConverter(new FormulaConverter());
        ObservableList<Formula> industries = getChoices(defaultStandard);
        selectedIndustry = industries.get(0);
        industryBox.setItems(industries);
        industryBox.getSelectionModel().selectFirst();
        industryBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null) {
                    formulaEditable.updateTable(arg2);
                }
            }
        });
        return industryBox;
    }


    private HBox getChoicebox(ComboBox choiceBox, String title, String tooltip) {
        HBox hBox = new HBox(0);
        Label label = new Label(title);
        label.setPadding(new Insets(5, 15, 0, 15));
        choiceBox.setTooltip(new Tooltip(tooltip));
        hBox.getChildren().addAll(label, choiceBox);
        return hBox;
    }

    private Button saveFormulasButton() {
        Button btn = new Button("Save Changes");
        btn.getStyleClass().add("blue-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    EditStorage.saveItems();
                } catch (Exception exception) {
                    System.out.println("Error while saving formulas map");
                }
                window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.SETTINGSMAIN));
                window.setHeight(600);
            }
        });
        return btn;
    }

}
