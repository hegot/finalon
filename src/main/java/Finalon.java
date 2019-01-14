import javafx.application.Application;
import javafx.stage.Stage;
import finalonWindows.SceneSwitcher;
import java.sql.SQLException;
import  database.AddDefaultTpl;
import finalonWindows.SceneSwitcher;
import finalonWindows.SceneName;

public class Finalon extends Application {

    private Stage window;




    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage)  throws ClassNotFoundException, SQLException {

        AddDefaultTpl addDefaultTpl = new AddDefaultTpl();
        addDefaultTpl.start();
        window = primaryStage;
        SceneSwitcher sceneSwitcher = new SceneSwitcher(window);
        window.setScene(SceneSwitcher.getScenes().get(SceneName.MAIN));
        window.show();
    }



}