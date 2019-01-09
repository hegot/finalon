package finalonWindows.settingsScene;

import defaultTemplate.DefaultTemplate;
import entities.Item;
import entities.Sheet;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import finalonWindows.SceneBase;
import finalonWindows.settingsScene.templates.TemplateEditable;

import java.util.ArrayList;

public class SettingsScene extends SceneBase {

    private Stage window;

    public SettingsScene(Stage windowArg) {
        window = windowArg;
    }

    private HBox getHeader() {
        HBox header = new HBox(10);
        header.setMaxHeight(180);
        header.setPrefHeight(180);
        header.setFillHeight(false);
        header.setBackground(background("#000000"));
        return header;
    }

    public Scene getSettingsScene() {
       /* BorderPane root = new BorderPane();
        //DbItemHandler dbItemHandler = new DbItemHandler();
        //ObservableList Items = dbItemHandler.getAllItems();
        //return Items;
        ObservableList<Item> allItems = DefaultTemplate.getAllItems();
        ArrayList<Sheet> sheets = DefaultTemplate.getSheets();
        TemplateEditable templateEditable = new TemplateEditable(allItems, sheets);
        root.setTop(templateEditable.getTemplateEditable());
        return new Scene(root, 1000, 600);*/
        ObservableList<Item> allItems = DefaultTemplate.getAllItems();
        ArrayList<Sheet> sheets = DefaultTemplate.getSheets();
        TemplateEditable templateEditable = new TemplateEditable(allItems, sheets);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(900, 550);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(templateEditable.getTemplateEditable());
        Scene scene = new Scene(scrollPane, 1000, 900);
        scene.getStylesheets().add("/styles/style.css");
        return scene;
    }
}
