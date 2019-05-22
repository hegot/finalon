package finalonWindows.addReport.stepThree;

import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import interpreter.Interprter;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class StepThree extends SceneBase {
    private ObservableMap<String, String> settings;
    private ObservableList<Item> items;
    private String companyName;
    private Periods periods;

    public StepThree(ObservableMap<String, String> settings, ObservableList<Item> items) {
        this.settings = settings;
        this.items = items;
    }

    public VBox show() {
        this.companyName = settings.get("company") + "'s";
        this.periods = new Periods(settings);
        Label label = new Label("1. The Common-Size Analysis of the Assets, Liabilities and Shareholders' Equity ");
        label.getStyleClass().add("assets-label");
        label.setWrapText(true);
        Interprter interprter = new Interprter(settings, items);
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        String periods = settings.get("periods");
        if (periods != null) {
            int periodsAmount = Integer.parseInt(periods);
            if (periodsAmount > 1) {
                Tab tab1 = new Tab("Assets trend Analysis");
                tab1.setContent(interprter.assetTrend());

                Tab tab2 = new Tab("Liabilities trend Analysis");
                tab2.setContent(interprter.liabilitiesTrend());
                tabs.getTabs().addAll(tab1, tab2);
            }
        }

        Tab tab3 = new Tab("Assets Structure Analysis");
        tab3.setContent(interprter.assetStructure());

        Tab tab4 = new Tab("Liabilities Structure Analysis");
        tab3.setContent(interprter.liabilitiesStructure());

        Tab tab5 = new Tab("Formula Calculation");
        tab4.setContent(interprter.formulaList());

        tabs.getTabs().addAll(tab3, tab4, tab5);
        VBox vBox = new VBox(5);
        vBox.getStyleClass().add("generated-report");
        vBox.getChildren().addAll(
                getTitle(),
                getDescText(),
                label,
                tabs);
        return vBox;
    }

    private Label getDescText() {
        Label text = new Label("This report analyzes the balance sheets and income statements of " + companyName
                + ". Trends for the major balance sheet and income statement items and ratio analysis are used " +
                "to understand the financial position and financial effectiveness of the company. " +
                "The report studied the " + periods.getStart() + " - " + periods.getEnd() + " period.");
        text.getStyleClass().add("report-text");
        text.setWrapText(true);
        return text;
    }


    private Label getTitle() {
        Label title = new Label(
                "Analysis of " + companyName
                        + " financial statements for the period from "
                        + periods.getStart() + " to "
                        + periods.getEnd());
        title.getStyleClass().add("report-title");
        title.setWrapText(true);
        return title;
    }


}
