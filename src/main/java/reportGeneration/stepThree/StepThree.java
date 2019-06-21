package reportGeneration.stepThree;

import finalonWindows.SceneBase;
import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.Interprter;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.HashMap;
import java.util.Map;

public class StepThree extends SceneBase {
    private String companyName;
    private Periods periods;
    private ObservableMap<String, String> settings;
    private Interprter interprter;

    public StepThree() {
        this.settings = SettingsStorage.getInstance().getSettings();
        this.companyName = settings.get("company") + "'s";
        this.interprter = new Interprter();
        this.periods = Periods.getInstance();
    }


    public VBox show() {
        TabPane tabs = new TabPane();
        Tab tab1 = new Tab("1. The Common-Size  Analysis \n of the Assets, Liabilities \n and Shareholders' Equity");

        TabPane tabs2 = new TabPane();
        tabs2.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        if (periods.getPeriodArr().size() > 1) {
            Tab tab01 = new Tab("Assets trend Analysis");
            tab01.setContent(interprter.getReport("assetTrend"));

            Tab tab02 = new Tab("Liabilities trend Analysis");
            tab02.setContent(interprter.getReport("liabilitiesTrend"));

            tabs2.getTabs().addAll(tab01, tab02);
        }
        Tab tab03 = new Tab("Assets Structure Analysis");
        tab03.setContent(interprter.getReport("assetStructure"));


        Tab tab04 = new Tab("Liabilities Structure Analysis");
        tab04.setContent(interprter.getReport("liabilitiesStructure"));

        Tab tab05 = new Tab("Formula Calculation");
        tab05.setContent(interprter.getReport("formulaList"));
        tabs2.getTabs().addAll(tab03, tab04, tab05);
        tab1.setContent(tabs2);

        Map<String, Tab> tabsArr = new HashMap<>();
        Tab tab2 = new Tab("2. Financial Sustainability \n and Long-Term Debt-Paying Ability");
        tabsArr.put("financialSustainability", tab2);

        Tab tab3 = new Tab("3. Liquidity of \n Short-Term Assets");
        tabsArr.put("liquidity", tab3);

        Tab tab4 = new Tab("4. Overview of the \n Financial Results");
        tabsArr.put("financialResultsTrend", tab4);

        Tab tab5 = new Tab("5. Profitability Ratios");
        tabsArr.put("profitabilityRatios", tab5);

        Tab tab6 = new Tab("6. Activity Ratios (Turnover Ratios)");
        tabsArr.put("turnoverRatios", tab6);

        Tab tab7 = new Tab("7. Investor Analysis");
        tabsArr.put("investorAnalysis", tab7);

        Tab tab8 = new Tab("8. Forecasting Financial Failure  \n (Bankruptcy Test) \n (Financial Distress Prediction)");
        tabsArr.put("zScoreModel", tab8);

        Tab tab9 = new Tab("9. Labor Productivity");
        tabsArr.put("laborProductivity", tab9);


        populateInThread(tabsArr, 13);
        tabs.getTabs().addAll(tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9);

        VBox vBox = new VBox(5);
        vBox.getStyleClass().add("generated-report");
        vBox.getChildren().addAll(
                getTitle(),
                getDescText(),
                tabs
        );
        return vBox;
    }

    private void populateInThread(Map<String, Tab> tabs, int n) {
        for (Map.Entry<String, Tab> entry : tabs.entrySet()) {
            Runnable updater = new Runnable() {

                @Override
                public void run() {
                    VBox vBox = interprter.getReport(entry.getKey());

                    if (vBox != null) {
                        entry.getValue().setContent(vBox);
                    }
                }
            };
            Platform.runLater(updater);
        }

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

