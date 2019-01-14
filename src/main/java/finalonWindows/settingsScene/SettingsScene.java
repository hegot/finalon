package finalonWindows.settingsScene;

import defaultTemplate.DefaultTemplate;
import entities.Item;
import entities.Sheet;
import entities.Template;
import finalonWindows.ImageButton;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import finalonWindows.SceneBase;

import java.util.ArrayList;
import database.DbTemplateHandler;
public class SettingsScene extends SceneBase {

    private Stage window;

    public SettingsScene(Stage windowArg) {
        window = windowArg;
    }


    public Scene getScene() {
        TabPane tabs = new TabPane();
        Tab templates = new Tab();
        templates.setText("Templates Customization");
        templates.setContent(getTemplates());
        Tab formulas = new Tab();
        formulas.setText("Formula Customization");
        formulas.setContent(getFormulas());
        tabs.getTabs().addAll(templates, formulas);
        Scene scene = new Scene(tabs,900,600);
        return scene;
    }


    private HBox getFormulas(){
        HBox  hbox = new HBox(20);
        Text templateName = new Text("Formula Customization");
        templateName.setFont(Font.font("Verdana", 13));
        templateName.setFill(Color.BROWN);
        hbox.getChildren().add(templateName);
        return hbox;
    }

    private VBox getTemplates(){
        DbTemplateHandler dbTempalte = new DbTemplateHandler();
        ArrayList<Template> templates = dbTempalte.getTemplates();
        VBox  vbox = new VBox(20);
        vbox.setPadding(new Insets(50, 50, 20, 50));
        if(templates.size() > 0){
            for (int j = 0; j < templates.size(); j++) {
                Template template = templates.get(j);
                Text templateName = new Text(template.name);
                templateName.setFont(Font.font("Verdana", 13));
                templateName.setFill(Color.BROWN);
                ImageButton button = editTemplateButton(template.id);
                vbox.getChildren().addAll(templateName, button);
            }
        }else{
            Text text = new Text("" +
                    "Templates customization allows you to customize your report input format. \n" +
                    "You can add additional variables and remove those you don`t need. \n" +
                    "Also you can rearange entries for more comfortable data entrance. \n" +
                    "You have no custom templates yet, you can add one here: \n");
            text.setFont(Font.font("Verdana", 15));
            text.setFill(Color.BROWN);

            HBox  hbox = new HBox(20);
            Text text2 = new Text("You have no custom templates yet, you can add one here: ");
            text2.setFont(Font.font("Verdana", 20));
            text2.setFill(Color.FUCHSIA);
            hbox.getChildren().addAll(text2,addTemplateButton());
            vbox.getChildren().addAll(text,hbox);
        }
        return vbox;
    }

    public ImageButton addTemplateButton() {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/plus.png"), new Image("/image/plus.png"), 23);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                window.setScene(SceneSwitcher.getScenes().get(SceneName.ADDTEMPLATE));

            }
        });
        return btn;
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
