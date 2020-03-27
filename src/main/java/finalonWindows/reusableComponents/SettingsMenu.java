package finalonWindows.reusableComponents;

import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import globalReusables.AddNewReport;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

public class SettingsMenu {

    public MenuBar getMenu() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("navigation");
        menu.setGraphic(new ImageView("image/menuBtn.jpg"));
        menu.getItems().addAll(home(), addCompany(), settings(), templates(), reports(), logs());
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private MenuItem addCompany() {
        MenuItem home = new MenuItem("Add company");
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddNewReport.run();
            }
        });
        return home;
    }

    private MenuItem home() {
        MenuItem home = new MenuItem("Home");
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneSwitcher.goTo(SceneName.MAIN);
            }
        });
        return home;
    }

    private MenuItem templates() {
        MenuItem templates = new MenuItem("Templates Customization");
        templates.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneSwitcher.goTo(SceneName.TEMPLATESLIST);
            }
        });
        return templates;
    }



    private MenuItem settings() {
        MenuItem settings = new MenuItem("General Settings");
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneSwitcher.goTo(SceneName.SETTINGSMAIN);
            }
        });
        return settings;
    }

    private MenuItem reports() {
        MenuItem settings = new MenuItem("Saved Reports");
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneSwitcher.goTo(SceneName.REPORTLIST);
            }
        });
        return settings;
    }

    private MenuItem logs() {
        MenuItem settings = new MenuItem("Logs");
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneSwitcher.goTo(SceneName.LOGS);
            }
        });
        return settings;
    }

}
