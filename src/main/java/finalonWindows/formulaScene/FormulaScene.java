package finalonWindows.formulaScene;

import entities.Formula;
import finalonWindows.SceneBase;
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
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FormulaScene extends SceneBase {

    private ObservableMap<String, String> settings;
    private ComboBox<Formula> standardBox;
    public static Formula industry;

    public FormulaScene() {
        this.settings = FXCollections.observableHashMap();
        this.standardBox = standardBox(settings);
        Storage.getInstance();
    }

    public VBox getScene() {
        VBox vbox = new VBox(20);
        vbox.getStyleClass().add("formula-edit");
        VBox industryVBox = new VBox(1);
        industryVBox.getStyleClass().add("industry-box");
        industryVBox.getChildren().add(
                getChoicebox(industryBox(), "Industry:", "Select the industry")
        );
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(0, 10, 0, 10));
        hBox.getChildren().addAll(
                getChoicebox(standardBox, "Statements GAAP:", "Select the accounting standard"),
                industryVBox,
                addIndustryBtn()
        );
        vbox.getChildren().addAll(
                new SettingsMenu().getMenu(),
                hBox,
                Storage.getTable()
        );
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

    private ComboBox<Formula> industryBox() {
        ComboBox<Formula> industryBox = Storage.getIndustryBox();
        industryBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null) {
                    Storage.getFormulaEditable().updateTable(arg2);
                }
            }
        });
        industryBox.getStyleClass().add("industry-select");
    return industryBox;
    }

    private ComboBox<Formula> standardBox(ObservableMap<String, String> settings) {
        ComboBox<Formula> standardBox = StandardSelect.get(settings);
        standardBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null) {
                    Storage.getIndustryBox().setItems(Choices.getChoices(arg2.getId()));
                    Storage.getIndustryBox().getSelectionModel().selectFirst();
                }
            }
        });
        standardBox.getStyleClass().add("industry-select");
        return standardBox;
    }

}
