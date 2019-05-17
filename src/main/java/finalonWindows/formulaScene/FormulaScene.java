package finalonWindows.formulaScene;

import entities.Formula;
import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.formulaScene.AddIndustryPopup.AddIndustryPopup;
import finalonWindows.reusableComponents.SettingsMenu;
import finalonWindows.reusableComponents.TextImageButton;
import finalonWindows.reusableComponents.selectbox.Choices;
import finalonWindows.reusableComponents.selectbox.IndustrySelect;
import finalonWindows.reusableComponents.selectbox.StandardSelect;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FormulaScene extends SceneBase {

    private ObservableMap<String, String> settings;
    private ComboBox<Formula> standardBox;
    private ComboBox<Formula> industryBox;
    private FormulaEditable formulaEditable;

    public FormulaScene() {
        String defaultStandard = "2";
        this.settings = FXCollections.observableHashMap();
        this.standardBox = standardBox(settings);
        this.industryBox = industryBox(defaultStandard, settings);
        Formula selectedIndustry = industryBox.getValue();
        this.formulaEditable = new FormulaEditable(selectedIndustry);
    }

    public VBox getScene() {
        VBox vbox = new VBox(20);
        vbox.getStyleClass().add("formula-edit");
        VBox industryVBox = new VBox(1);
        industryVBox.getStyleClass().add("industry-box");
        industryVBox.getChildren().addAll(
                getChoicebox(industryBox, "Industry:", "Select the industry"),
                addIndustryBtn()
        );
        HBox hBox = new HBox(40);
        hBox.setPadding(new Insets(0, 10, 0, 10));
        hBox.getChildren().addAll(
                getChoicebox(standardBox, "Statements GAAP:", "Select the accounting standard"),
                industryVBox,
                saveFormulasButton()
        );
        vbox.getChildren().addAll(
                new SettingsMenu().getMenu(),
                hBox,
                formulaEditable.getFormulaTable()
        );
        EditStorage.getInstance();
        return vbox;
    }


    private TextImageButton addIndustryBtn() {
        TextImageButton btn = new TextImageButton("Industry", "image/add-plus-button.png", 16);
        btn.setOnAction((ActionEvent event) -> {
            new AddIndustryPopup(settings).getdialog();
        });
        return btn;
    }

    private HBox getChoicebox(ComboBox choiceBox, String title, String tooltip) {
        HBox hBox = new HBox(0);
        Label label = new Label(title);
        label.setPadding(new Insets(5, 15, 0, 15));
        choiceBox.setTooltip(new Tooltip(tooltip));
        hBox.getChildren().addAll(label, choiceBox);
        return hBox;
    }

    private ComboBox<Formula> industryBox(String defaultStandard, ObservableMap<String, String> settings) {
        ComboBox<Formula> industryBox = IndustrySelect.get(defaultStandard, settings);
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

    private ComboBox<Formula> standardBox(ObservableMap<String, String> settings) {
        ComboBox<Formula> standardBox = StandardSelect.get(settings);
        standardBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null) {
                    industryBox.setItems(Choices.getChoices(arg2.getId()));
                    industryBox.getSelectionModel().selectFirst();
                }
            }
        });
        return standardBox;
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
                SceneSwitcher.goTo(SceneName.SETTINGSMAIN);
            }
        });
        return btn;
    }

}
