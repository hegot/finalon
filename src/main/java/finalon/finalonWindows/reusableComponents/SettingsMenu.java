package finalon.finalonWindows.reusableComponents;

import finalon.finalonWindows.SceneName;
import finalon.finalonWindows.SceneSwitcher;
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
        menu.getItems().addAll(home(), addCompany(), settings(), templates(), formulas());
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private MenuItem addCompany() {
        MenuItem home = new MenuItem("Add company");
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneSwitcher.goTo(SceneName.ADDREPORT);
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

    private MenuItem formulas() {
        MenuItem formulas = new MenuItem("Formula Customization");
        formulas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneSwitcher.goTo(SceneName.FORMULA);
            }
        });
        return formulas;
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

}