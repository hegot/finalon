package reportGeneration.stepThree;

import entities.Formula;
import finalonWindows.SceneBase;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.Interprter;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;
import reportGeneration.wordExport.WordExport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StepThree extends SceneBase {
    private String companyName;
    private Periods periods;
    private ObservableMap<String, String> settings;
    private Interprter interprter;
    private Map<String, Tab> tabsArr;

    public StepThree() {
        this.settings = SettingsStorage.getInstance().getSettings();
        this.companyName = settings.get("company") + "'s";
        this.interprter = new Interprter();
        this.periods = Periods.getInstance();
        this.tabsArr = new HashMap<>();
    }


    private ArrayList<Tab> loopSections() {
        ArrayList<Tab> tabs = new ArrayList<Tab>();
        ObservableList<Formula> sections = FormulaStorage.getInstance().getSections();
        int weight = 30;
        int number = 2;
        for (int i = 0; i < sections.size(); i++) {
            Formula formula = sections.get(i);
            String name = number + ". " + formula.getName();
            number ++;
            weight = weight + 10;
            Tab tab = new Tab(name);
            tabs.add(tab);
            tabsArr.put(formula.getShortName(), tab);
        }
        return tabs;
    }

    public VBox show() {
        TabPane tabs = new TabPane();

        Tab tab1 =  getStaticTab();

        String t09 = "4. Overview of the \n Financial Results";
        ResultsStorage.addStr(130, "sectionTitle", t09);

        String t14 = "10. Financial Rating";
        ResultsStorage.addStr(140, "sectionTitle", t14);

        Tab tab09 = new Tab(t09);
        tabsArr.put("financialResultsTrend", tab09);
        Tab tab10 = new Tab(t14);
        tabsArr.put("financialRating", tab10);
        tabs.getTabs().add(tab1);
        for(Tab formulaTab : loopSections()){
            tabs.getTabs().add(formulaTab);
        }
        tabs.getTabs().add(tab09);
        tabs.getTabs().add(tab10);
        populateInThread(tabsArr, 13);
        VBox vBox = initVbox();
        vBox.getChildren().add(tabs);
        return vBox;
    }

    private void populateInThread(Map<String, Tab> tabs, int n) {
        for (Map.Entry<String, Tab> entry : tabs.entrySet()) {
            Runnable updater = new Runnable() {
                @Override
                public void run() {
                    System.out.println(entry.getKey());
                    VBox vBox = interprter.getReport(entry.getKey());
                    if (vBox != null) {
                        entry.getValue().setContent(vBox);
                    }
                }
            };
            Platform.runLater(updater);
        }
    }


    private Tab getStaticTab(){
        String t1 = "1. The Common-Size  Analysis \n of the Assets, Liabilities \n and Shareholders' Equity";
        ResultsStorage.addStr(2, "sectionTitle", t1);
        String t2 = "Assets trend Analysis";
        String t3 = "Liabilities trend Analysis";
        String t4 = "Assets Structure Analysis";
        String t5 = "Liabilities Structure Analysis";
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

        return tab1;
    }

    private VBox initVbox(){
        VBox vBox = new VBox(5);
        vBox.getStyleClass().add("generated-report");
        vBox.getChildren().addAll(
                getTitle(),
                getDescText(),
                exportBtn()
        );
        return vBox;
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

