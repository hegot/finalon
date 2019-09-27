package reportGeneration.stepThree;

import finalonWindows.SceneBase;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class StepThree extends SceneBase {
    private String companyName;

    public StepThree() {
        this.companyName = SettingsStorage.get("company") + "'s";
    }


    public VBox show() {
        VBox vBox = new VBox(5);
        vBox.getStyleClass().add("generated-report");
        vBox.getChildren().addAll(
                getTitle(),
                getDescText(),
                new ResultTabs().getTabs()
        );
        return vBox;
    }

    private Label getDescText() {
        String text = "This report analyzes the balance sheets and income statements of ”" + companyName
                + "”. Trends for the major balance sheet and income statement items and ratio analysis are used " +
                "to understand the financial position and financial effectiveness of the company. " +
                "The report studied the " + Periods.getStart() + " - " + Periods.getEnd() + " period.\n";
        Label label = new Label(text);
        ResultsStorage.addStr(1, "h3", text);
        label.getStyleClass().add("report-text");
        label.setWrapText(true);
        return label;
    }


    private Label getTitle() {
        String t1 = "Analysis of " + companyName
                + " financial statements for the period from "
                + Periods.getStart() + " to "
                + Periods.getEnd();
        ResultsStorage.addStr(0, "h1", t1);
        Label title = new Label(t1);
        title.getStyleClass().add("report-title");
        title.setWrapText(true);
        return title;
    }


}

