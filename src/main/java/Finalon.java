import database.AddDefaultTables;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Finalon extends Application {

    private Stage window;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {

        AddDefaultTables addDefaultTpl = new AddDefaultTables();
        addDefaultTpl.start();
        window = primaryStage;
        SceneSwitcher sceneSwitcher = new SceneSwitcher(window);
        window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.MAIN));
        window.show();
    }


}