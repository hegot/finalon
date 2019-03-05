package finalonWindows.addReport;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.reusableComponents.SettingsMenu;
import finalonWindows.reusableComponents.calendar.FXCalendar;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

public class AddReportScene {
    private Stage window;
    private DbFormulaHandler dbFormula = new DbFormulaHandler();
    private final int defaultStandard = 1;
    private ObservableMap<String, Object> settings;
    private FXCalendar calendarStart = new FXCalendar();
    private FXCalendar calendarEnd = new FXCalendar();

    public AddReportScene(Stage windowArg) {
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
        VBox vboxInner = new VBox(10);


        SettingsMenu settingsMenu = new SettingsMenu(window);

        vbox.getChildren().addAll(settingsMenu.getMenu(), vboxInner());
        Scene scene = new Scene(vbox, 900, 600);
        scene.getStylesheets().addAll("styles/addReport.css", "styles/calendar_styles.css");
        return scene;
    }

    // Factory to create Cell of DatePicker
    private Callback<DatePicker, DateCell> getDayCellFactory() {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                    }
                };
            }
        };
        return dayCellFactory;
    }


    private VBox vboxInner() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("inner-container");
        Label mainLabel = new Label("Generate Report");
        mainLabel.getStyleClass().add("main-label");

        ComboBox<Formula> standard = StandardSelect.get(settings);
        ComboBox<Formula> industry = IndustrySelect.get(defaultStandard, settings);
        settings.put("industry", industry.getValue());
        standard.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null) {
                    industry.setItems(Choices.getChoices(arg2.getId()));
                    industry.getSelectionModel().selectFirst();
                    settings.replace("standard", arg2);
                }
            }
        });
        ComboBox<String> currency = CurrencySelect.get(settings);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(titledHbox("Finance analysis standard", standard), titledHbox("Industry", industry));

        HBox hBox2 = new HBox(20);
        hBox2.getChildren().addAll(titledHbox("Step of Analysis", ReportStep.get(settings)), titledHbox("First date of financial year", FinancialYear.get(settings)));

        vbox.getChildren().addAll(
            mainLabel,
            ReportName.get(settings),
            titledHbox("Template", TemplateSelect.get(settings)),
            titledHbox("Currency", currency),
            hBox,
            hBox2,
            getPeriod(),
                nextButton()
        );
        return vbox;
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
        hBox.getChildren().addAll(from, calendarStart, to, calendarEnd);
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

                } catch (Exception exception) {
                    System.out.println("Error while saving formulas map");
                }
                window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.SETTINGSMAIN));
                window.setHeight(600);
            }
        });
        hBox.getChildren().add(btn);
        hBox.setHgrow(btn, Priority.ALWAYS);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        return hBox;
    }

}
