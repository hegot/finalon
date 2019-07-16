package reportGeneration.stepThree;

import finalonWindows.SceneBase;
import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.Interprter;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;
import reportGeneration.wordExport.WordExport;

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

        Map<String, Tab> tabsArr = new HashMap<>();
        String t1 = "1. The Common-Size  Analysis \n of the Assets, Liabilities \n and Shareholders' Equity";
        ResultsStorage.addStr(2, "sectionTitle", t1);
        String t2 = "Assets trend Analysis";
        String t3 = "Liabilities trend Analysis";
        String t4 = "Assets Structure Analysis";
        String t5 = "Liabilities Structure Analysis";


        String t6 = "2. Financial Sustainability \n and Long-Term Debt-Paying Ability";
        ResultsStorage.addStr(40, "sectionTitle", t6);
        String t7 = "3. Liquidity of \n Short-Term Assets";
        ResultsStorage.addStr(50, "sectionTitle", t7);
        String t8 = "4. Overview of the \n Financial Results";
        ResultsStorage.addStr(60, "sectionTitle", t8);
        String t9 = "5. Profitability Ratios";
        ResultsStorage.addStr(70, "sectionTitle", t9);
        String t10 = "6. Activity Ratios (Turnover Ratios)";
        ResultsStorage.addStr(80, "sectionTitle", t10);
        String t11 = "7. Investor Analysis";
        ResultsStorage.addStr(90, "sectionTitle", t11);
        String t12 = "8. Forecasting Financial Failure  \n (Bankruptcy Test) \n (Financial Distress Prediction)";
        ResultsStorage.addStr(100, "sectionTitle", t12);
        String t13 = "9. Labor Productivity";
        ResultsStorage.addStr(110, "sectionTitle", t13);

        VBox vBox = new VBox(5);
        vBox.getStyleClass().add("generated-report");
        vBox.getChildren().addAll(
                getTitle(),
                getDescText(),
                exportBtn()
        );

        Tab tab1 = new Tab(t1);

        TabPane tabs2 = new TabPane();
        tabs2.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        if (periods.getPeriodArr().size() > 1) {
            Tab tab01 = new Tab(t2);
            tabsArr.put("assetTrend", tab01);

            Tab tab02 = new Tab(t3);
            tabsArr.put("liabilitiesTrend", tab02);

            tabs2.getTabs().addAll(tab01, tab02);
        }
        Tab tab03 = new Tab(t4);
        tabsArr.put("assetStructure", tab03);

        Tab tab04 = new Tab(t5);
        tabsArr.put("liabilitiesStructure", tab04);

        Tab tab05 = new Tab("Formula Calculation");
        tabsArr.put("formulaList", tab05);

        tabs2.getTabs().addAll(tab03, tab04, tab05);

        tab1.setContent(tabs2);

        Tab tab2 = new Tab(t6);
        tabsArr.put("financialSustainability", tab2);

        Tab tab3 = new Tab(t7);
        tabsArr.put("liquidity", tab3);

        Tab tab4 = new Tab(t8);
        tabsArr.put("financialResultsTrend", tab4);

        Tab tab5 = new Tab(t9);
        tabsArr.put("profitabilityRatios", tab5);

        Tab tab6 = new Tab(t10);
        tabsArr.put("turnoverRatios", tab6);

        Tab tab7 = new Tab(t11);
        tabsArr.put("investorAnalysis", tab7);

        Tab tab8 = new Tab(t12);
        tabsArr.put("zScoreModel", tab8);

        Tab tab9 = new Tab(t13);
        tabsArr.put("laborProductivity", tab9);

        populateInThread(tabsArr, 13);
        tabs.getTabs().addAll(tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9);

        vBox.getChildren().add(tabs);
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
        String text = "This report analyzes the balance sheets and income statements of " + companyName
                + ". Trends for the major balance sheet and income statement items and ratio analysis are used " +
                "to understand the financial position and financial effectiveness of the company. " +
                "The report studied the " + periods.getStart() + " - " + periods.getEnd() + " period.";
        Label label = new Label(text);
        ResultsStorage.addStr(1, "h3", text);
        label.getStyleClass().add("report-text");
        label.setWrapText(true);
        return label;
    }


    private Button exportBtn() {
        Button btn = new Button("Export Doc");
        btn.getStyleClass().add("blue-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    WordExport export = new WordExport();
                    export.exportDoc();
                } catch (Exception exception) {
                    System.out.println("Error while saving file: " + exception.getMessage());
                }
            }
        });
        return btn;
    }

    private Label getTitle() {
        String t1 = "Analysis of " + companyName
                + " financial statements for the period from "
                + periods.getStart() + " to "
                + periods.getEnd();
        ResultsStorage.addStr(0, "h1", t1);
        Label title = new Label(t1);
        title.getStyleClass().add("report-title");
        title.setWrapText(true);
        return title;
    }
}

