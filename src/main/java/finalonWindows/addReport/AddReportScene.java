package finalonWindows.addReport;

import entities.Formula;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.addReport.report.SecondStep;
import finalonWindows.reusableComponents.SettingsMenu;
import finalonWindows.reusableComponents.selectbox.Choices;
import finalonWindows.reusableComponents.selectbox.CurrencySelect;
import finalonWindows.reusableComponents.selectbox.IndustrySelect;
import finalonWindows.reusableComponents.selectbox.StandardSelect;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddReportScene {
    private final String defaultStandard = "1";
    private Stage window;
    private ObservableMap<String, String> settings;
    private Label errors = new Label();

    public AddReportScene(Stage windowArg) {
        errors.getStyleClass().add("settings-error");
        window = windowArg;
        this.settings = FXCollections.observableHashMap();
        settings.put("standard", defaultStandard);
        settings.put("company", "");

        settings.put("step", "month");
        settings.put("finYear", "1st of January");
    }

    public Scene getScene() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("container");
        SettingsMenu settingsMenu = new SettingsMenu(window);
        vbox.getChildren().addAll(settingsMenu.getMenu(), vboxInner());
        Scene scene = new Scene(vbox, 900, 600);
        scene.getStylesheets().addAll("styles/addReport.css", "styles/calendar_styles.css");
        return scene;
    }


    private VBox vboxInner() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("inner-container");
        Label mainLabel = new Label("Generate Report");
        mainLabel.getStyleClass().add("main-label");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(
                titledHbox("Step of Analysis", ReportStep.get(settings)),
                titledHbox("First date of financial year", FinancialYear.get(settings))
        );
        HBox err = new HBox(20);
        err.getStyleClass().add("hbox-row");
        err.getChildren().add(errors);
        vbox.getChildren().addAll(
                mainLabel,
                ReportName.get(settings),
                titledHbox("Template", TemplateSelect.get(settings)),
                titledHbox("Currency", CurrencySelect.get(settings)),
                standardIndustry(),
                hBox,
                getPeriod(),
                err,
                nextButton()
        );
        return vbox;
    }


    private HBox standardIndustry() {
        HBox hBox = new HBox(20);
        ComboBox<Formula> standard = StandardSelect.get(settings);
        ComboBox<Formula> industry = IndustrySelect.get(defaultStandard, settings);
        standard.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null) {
                    industry.setItems(Choices.getChoices(arg2.getId()));
                    industry.getSelectionModel().selectFirst();
                    settings.replace("standard", Integer.toString(arg2.getId()));
                }
            }
        });
        hBox.getChildren().addAll(titledHbox("Finance analysis standard", standard), titledHbox("Industry", industry));
        return hBox;
    }


    private VBox getPeriod() {
        VBox vbox = new VBox(10);
        vbox.getStyleClass().add("hbox-row");
        Label label = new Label("Analysing period:");
        label.getStyleClass().add("sub-label");
        HBox hBox = new HBox(50);
        Label from = new Label("From:");
        from.getStyleClass().add("date-label");
        Label to = new Label("To:");
        to.getStyleClass().add("date-label");
        Callendar start = new Callendar("start", settings);
        Callendar end = new Callendar("end", settings);
        hBox.getChildren().addAll(from, start.get(), to, end.get());
        vbox.getChildren().addAll(label, hBox);
        return vbox;
    }

    private HBox titledHbox(String title, ComboBox select) {
        Label label = new Label(title);
        HBox hBox = new HBox(20);
        hBox.getStyleClass().add("hbox-row");
        label.getStyleClass().add("sub-label");
        hBox.getChildren().addAll(label, select);
        return hBox;
    }


    private HBox nextButton() {
        HBox hBox = new HBox(20);
        hBox.getStyleClass().add("hbox-row-btn");
        Button btn = new Button(" Next");
        Image img = new Image("image/right-arrow.png");
        ImageView iv = new ImageView(img);
        btn.setGraphic(iv);
        btn.getStyleClass().add("blue-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    String company = settings.get("company").toString();
                    if (company.length() == 0) {
                        errors.setText("Please fill in company name");
                    } else {
                        errors.setText("");
                        SecondStep secondStep = new SecondStep(window, settings);
                        window.setScene(secondStep.getScene());
                        window.setHeight(900);
                    }
                } catch (Exception exception) {
                    System.out.println("Error while saving report settings");
                }
            }
        });
        hBox.getChildren().add(btn);
        hBox.setHgrow(btn, Priority.ALWAYS);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        return hBox;
    }

}
