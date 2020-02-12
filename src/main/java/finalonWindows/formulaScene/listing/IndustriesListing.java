package finalonWindows.formulaScene.listing;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.SettingsMenu;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class IndustriesListing extends SceneBase {

    private static TilePane getPane() {
        TilePane tilePane = new TilePane();
        tilePane.setStyle("-fx-padding:10px");
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        return tilePane;
    }

    public VBox getScene() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("templates-listing");
        vbox.setPrefSize(900, 600);
        vbox.setPrefHeight(600);
        SettingsMenu settingsMenu = new SettingsMenu();
        vbox.getChildren().addAll(settingsMenu.getMenu(), getIndustries());
        return vbox;
    }

    private VBox getIndustries() {
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 20px 30px;");
        ObservableList<Formula> industriesIFRS = DbFormulaHandler.getFormulas(1);
        Label labelIFRS = new Label("IFRS Standard");
        labelIFRS.setStyle("-fx-font-weight:bold;");
        TilePane tilePaneIFRS = getPane();
        if (industriesIFRS.size() > 0) {
            for (Formula item : industriesIFRS) {
                tilePaneIFRS.getChildren().add(new IndustryRow(item));
            }
            vbox.getChildren().addAll(
                    labelIFRS,
                    tilePaneIFRS
            );
        }
        ObservableList<Formula> industriesUS = DbFormulaHandler.getFormulas(2);
        Label labelUS = new Label("US GAAP Standard");
        labelUS.setStyle("-fx-font-weight:bold;");
        TilePane tilePaneUS = getPane();
        if (industriesUS.size() > 0) {
            for (Formula item : industriesUS) {
                tilePaneUS.getChildren().add(new IndustryRow(item));
            }
            vbox.getChildren().addAll(
                    labelUS,
                    tilePaneUS
            );
        }
        vbox.getChildren().addAll(
                new IndustriesMessage(),
                new AddIndustryBtn("Add new industry: ")
        );
        return vbox;
    }
}

