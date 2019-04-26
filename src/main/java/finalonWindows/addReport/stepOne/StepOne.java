package finalonWindows.addReport.stepOne;

import entities.Formula;
import finalonWindows.reusableComponents.selectbox.Choices;
import finalonWindows.reusableComponents.selectbox.CurrencySelect;
import finalonWindows.reusableComponents.selectbox.IndustrySelect;
import finalonWindows.reusableComponents.selectbox.StandardSelect;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class StepOne {
    private final String defaultStandard = "1";
    private ObservableMap<String, String> settings;
    private Label errors = new Label();
    private VBox parent;

    public StepOne(ObservableMap<String, String> settings, VBox parent) {
        errors.getStyleClass().add("settings-error");
        this.settings = settings;
        this.parent = parent;
        settings.put("standard", defaultStandard);
        settings.put("company", " ");
    }


    public VBox show() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("inner-container");
        Label mainLabel = new Label("Generate Report");
        mainLabel.getStyleClass().add("main-label");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(
                titledHbox("Step of Analysis", ReportStep.get(settings)),
                titledHbox("How many periods \nyou want to analyse: ", Periods.get(settings))
        );

        HBox err = new HBox(20);
        err.getStyleClass().add("hbox-row");
        err.getChildren().add(errors);

        DateSelect dateSelect = new DateSelect(settings);
        vbox.getChildren().addAll(
                mainLabel,
                ReportName.get(settings),
                titledHbox("Template", TemplateSelect.get(settings)),
                currencyRow(),
                standardIndustry(),
                hBox,
                dateSelect.get(),
                err,
                nextButton()
        );
        return vbox;
    }


    private HBox currencyRow() {
        HBox hBox = new HBox(20);
        Label label = new Label("Currency");
        hBox.getStyleClass().add("hbox-row");
        label.getStyleClass().add("sub-label");
        hBox.getChildren().addAll(label, AmountSelect.get(settings), CurrencySelect.get(settings));
        return hBox;
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
                String company = settings.get("company").toString();
                if (company.length() == 0) {
                    errors.setText("Please fill in company name");
                } else {
                    errors.setText("");
                    settings.put("step", "two");
                }
            }
        });
        hBox.getChildren().add(btn);
        hBox.setHgrow(btn, Priority.ALWAYS);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        return hBox;
    }
}
