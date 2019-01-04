package finalonWindows;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import finalonWindows.mainScene.MainScene;
import finalonWindows.settingsScene.SettingsScene;


public class SceneSwitcher {

    private Stage window;
    private Scene mainScene;
    private Scene settingsScene;


    public SceneSwitcher(Stage windowArg) {
        window = windowArg;

        MainScene mainSceneclass = new MainScene(window);
        mainScene = mainSceneclass.getMainScene(
                addCompanyButton(),
                addSettingsButton(),
                addHelpButton(),
                addExitButton()
        );

        //Settings scene init
        SettingsScene settingsSceneclass = new SettingsScene(window);
        settingsScene = settingsSceneclass.getSettingsScene();
    }


    public void setMainScene() {
        window.setScene(mainScene);
        window.setTitle("Financial Statement Analysis Application");
    }


    public void setSettingsScene() {
        window.setScene(settingsScene);
        window.setTitle("Financial Statement Analysis Application Settings");
    }


    public ImageButton addCompanyButton() {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/add.png"), new Image("/image/add1.png"));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Accepted");
            }
        });
        return btn;
    }

    public ImageButton addSettingsButton() {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/settings.png"), new Image("/image/settings1.png"));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setSettingsScene();
            }
        });
        return btn;
    }

    public ImageButton addHelpButton() {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/info.png"), new Image("/image/info1.png"));
        return btn;
    }

    public ImageButton addExitButton() {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/exit.png"), new Image("/image/exit1.png"));
        return btn;
    }
}
