package finalonWindows.settingsScene;

import database.DbTemplateHandler;
import entities.Template;
import finalonWindows.ImageButton;
import finalonWindows.SceneBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SettingsScene extends SceneBase {

    private Stage window;

    public SettingsScene(Stage windowArg) {
        window = windowArg;
    }


    public Scene getScene() {
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab templates = new Tab();
        templates.setText("Templates Customization");
        templates.setContent(getTemplates());
        Tab formulas = new Tab();
        formulas.setText("Formula Customization");
        formulas.setContent(getFormulas());
        tabs.getTabs().addAll(templates, formulas);
        Scene scene = new Scene(tabs, 900, 600);
        scene.getStylesheets().add("styles/settingsStyle.css");
        return scene;
    }


    private HBox getFormulas() {
        HBox hbox = new HBox(20);
        Text templateName = new Text("Formula Customization");
        templateName.setFont(Font.font("Verdana", 13));
        templateName.setFill(Color.BROWN);
        hbox.getChildren().add(templateName);
        return hbox;
    }

    private VBox getTemplates() {
        DbTemplateHandler dbTempalte = new DbTemplateHandler();
        ArrayList<Template> templates = dbTempalte.getTemplates();
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 20px 30px;");
        TilePane tilePane = new TilePane();
        tilePane.setStyle("-fx-padding:10px");
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        if (templates.size() > 0) {
            for (int j = 0; j < templates.size(); j++) {
                tilePane.getChildren().add(new TemplateRow(window, templates.get(j)));
            }
            vbox.getChildren().addAll(tilePane, new AddTemplateBtn(window, "Add new template: "));
        } else {
            vbox.getChildren().addAll(new SettingsMessage(), new AddTemplateBtn(window, "You have no custom templates yet, you can add one here: "));
        }
        return vbox;
    }


    public ImageButton editTemplateButton(int id) {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/template.png"), new Image("/image/template.png"), 50);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println(id);

            }
        });
        return btn;
    }
}
