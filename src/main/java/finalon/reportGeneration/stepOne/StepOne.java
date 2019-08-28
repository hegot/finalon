package finalon.reportGeneration.stepOne;

import finalon.defaultData.DefaultCurrency;
import finalon.globalReusables.StandardAndIndustry;
import finalon.reportGeneration.storage.SettingsStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private Label errors = new Label();

    public VBox show() {
        errors.getStyleClass().add("settings-error");
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("inner-container");
        Label mainLabel = new Label("Generate Report");
        mainLabel.getStyleClass().add("main-label");
        HBox err = new HBox(20);
        err.getStyleClass().add("hbox-row");
        err.getChildren().add(errors);
        vbox.getChildren().addAll(
                mainLabel,
                ReportName.get(),
                titledHbox("Template", TemplateSelect.get()),
                currencyRow(),
                standardIndustry(),
                periodsRow(),
                new DateSelect().get(),
                err,
                nextButton()
        );
        return vbox;
    }

    private HBox periodsRow() {
        HBox hBox = new HBox(0);
        ObservableList<String> steps = FXCollections.observableArrayList();
        steps.addAll("year", "half year", "quater", "month");
        ObservableList<String> periods = FXCollections.observableArrayList();
        for (int i = 1; i < 15; i++) {
            periods.add(Integer.toString(i));
        }
        hBox.getChildren().addAll(
                titledHbox(
                        "Step of Analysis",
                        SettingsSelect.get(steps, "reportStep", "year")
                ),
                titledHbox(
                        "How many periods \nyou want to analyse: ",
                        SettingsSelect.get(periods, "periods", "1")
                )
        );
        return hBox;
    }

    private HBox currencyRow() {
        HBox hBox = new HBox(20);
        Label label = new Label("Currency");
        hBox.getStyleClass().add("hbox-row");
        label.getStyleClass().add("sub-label");
        ObservableList<String> amountItems = FXCollections.observableArrayList();
        amountItems.addAll("thousand", "million");
        hBox.getChildren().addAll(
                label,
                SettingsSelect.get(amountItems, "amount", "thousand"),
                SettingsSelect.get(DefaultCurrency.getCurrencies(), "defaultCurrency", "USD")
        );
        return hBox;
    }

    private HBox standardIndustry() {
        HBox hBox = StandardAndIndustry.get();
        StandardAndIndustry.shouldUpdateSettings(true);
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
                String company = SettingsStorage.get("company");
                if (company.length() == 0) {
                    errors.setText("Please fill in company name");
                } else {
                    errors.setText("");
                    SettingsStorage.put("step", "two");
                }
            }
        });
        hBox.getChildren().add(btn);
        hBox.setHgrow(btn, Priority.ALWAYS);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        return hBox;
    }
}
