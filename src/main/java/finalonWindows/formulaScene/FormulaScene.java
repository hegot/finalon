package finalonWindows.formulaScene;

import entities.Formula;
import finalonWindows.SceneBase;
import finalonWindows.formulaScene.AddIndustryPopup.AddIndustryPopup;
import finalonWindows.reusableComponents.SettingsMenu;
import finalonWindows.reusableComponents.TextImageButton;
import globalReusables.StandardAndIndustry;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FormulaScene extends SceneBase {

    public static Formula industry;


    public VBox getScene() {
        VBox vbox = new VBox(20);
        vbox.getStyleClass().add("formula-edit");
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(0, 10, 0, 10));

        hBox.getChildren().addAll(
                StandardAndIndustry.get(),
                addIndustryBtn()
        );
        StandardAndIndustry.shouldUpdateSettings(false);
        vbox.getChildren().addAll(
                new SettingsMenu().getMenu(),
                hBox,
                FormulaEditable.getFormulaTable()
        );
        StandardAndIndustry.getIndustry().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null && !StandardAndIndustry.updateSettings) {
                    FormulaEditable.updateTable(arg2);
                }
            }
        });
        return vbox;
    }


    private TextImageButton addIndustryBtn() {
        TextImageButton btn = new TextImageButton("Industry", "image/add-plus-button.png", 16);
        btn.setOnAction((ActionEvent event) -> {
            new AddIndustryPopup().getdialog();
        });
        return btn;
    }
}
