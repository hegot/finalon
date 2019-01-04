import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import finalonWindows.SceneSwitcher;

public class Finalon extends Application {

    private Stage window;




    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        SceneSwitcher sceneSwitcher = new SceneSwitcher(window);
        sceneSwitcher.setMainScene();
        window.show();
    }



}