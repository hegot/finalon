import database.AddDefaultTables;
import database.setting.DbSettingHandler;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import globalReusables.CallTypes;
import globalReusables.RandomString;
import globalReusables.Setting;
import globalReusables.StatTrigger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;

import java.sql.SQLException;
import java.util.Properties;

public class Finalon extends Application {

    private Stage window;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        Properties prop = new Properties();
        prop.setProperty("log4j.rootLogger", "WARN");
        PropertyConfigurator.configure(prop);
        AddDefaultTables addDefaultTpl = new AddDefaultTables();
        addDefaultTpl.start();
        window = primaryStage;
        VBox mainVBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setMinWidth(900);
        scrollPane.setPrefHeight(height());
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        Scene scene = new Scene(scrollPane, 900, height());
        scene.getStylesheets().addAll(
                "styles/addReport.css",
                "styles/formulaEdit.css",
                "styles/generalSettings.css",
                "styles/generatedReport.css",
                "styles/mainStyle.css",
                "styles/templatesListing.css",
                "styles/templateStyle.css"
        );
        primaryStage.setScene(scene);
        SceneSwitcher sceneSwitcher = new SceneSwitcher(mainVBox);
        setAppId();
        StatTrigger.call(CallTypes.program_started_times);
        sceneSwitcher.goTo(SceneName.MAIN);
        window.show();
    }


    private void setAppId(){
        DbSettingHandler dbSettingHandler = new DbSettingHandler();
        ObservableMap<String, String> settings = FXCollections.observableHashMap();
        String id = dbSettingHandler.getSetting(Setting.appId);
        if(id.length() == 0){
            dbSettingHandler.updateSetting(Setting.appId, RandomString.get());
        }
    }

    protected double height() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        return primaryScreenBounds.getHeight() - 100;
    }

}