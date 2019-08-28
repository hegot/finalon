package finalon.reportGeneration.stepThree;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.Interprter;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.Periods;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.wordExport.WordExport;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultTabs {

    private Interprter interprter = new Interprter();
    private Map<String, Tab> tabsArr = new HashMap<>();
    private ImageView throbber = getThrobber();


    private ImageView getThrobber() {
        Image img = new Image("image/loading.gif");
        ImageView iv = new ImageView(img);
        iv.setFitHeight(200);
        iv.setFitWidth(200);
        return iv;
    }

    private ArrayList<Tab> loopSections() {
        ArrayList<Tab> tabs = new ArrayList<Tab>();
        ObservableList<Formula> sections = FormulaStorage.getSections();
        int number = 2;
        for (int i = 0; i < sections.size(); i++) {
            Formula formula = sections.get(i);
            String name = number + ". " + formula.getName();
            number++;
            Tab tab = new Tab(name);
            tabs.add(tab);
            tabsArr.put(formula.getShortName(), tab);
        }
        return tabs;
    }

    private void populateInThread(Map<String, Tab> tabs) {
        int counter = 30;
        for (Map.Entry<String, Tab> entry : tabs.entrySet()) {
            counter = counter + 10;
            final int num = counter;
            System.out.println(entry.getKey());
            VBox vBox = interprter.getReport(entry.getKey(), num);
            if (vBox != null) {
                entry.getValue().setContent(vBox);
            }
        }
    }

    public VBox getTabs() {
        VBox vBox = new VBox();
        Button export = exportBtn();
        vBox.getChildren().addAll(export, throbber);
        TabPane tabs = new TabPane();
        tabs.setStyle("-fx-padding: 10px 0 0 0;");
        Task listLoader = new Task<ArrayList<Tab>>() {
            {
                setOnSucceeded(workerStateEvent -> {
                    tabs.getTabs().addAll(getValue());
                    vBox.getChildren().remove(throbber);
                    export.setVisible(true);

                });
                setOnFailed(workerStateEvent -> getException().printStackTrace());
            }

            @Override
            protected ArrayList<Tab> call() throws Exception {
                ArrayList<Tab> tabs = new ArrayList<>();
                String t1 = "1. The Common-Size  Analysis \n of the Assets, Liabilities \n and Shareholders' Equity";
                ResultsStorage.addStr(2, "sectionTitle", t1);
                String t2 = "Assets trend Analysis";
                String t3 = "Liabilities trend Analysis";
                String t4 = "Assets Structure Analysis";
                String t5 = "Liabilities Structure Analysis";
                Tab tab1 = new Tab(t1);
                TabPane tabs2 = new TabPane();
                tabs2.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
                if (Periods.getPeriodArr().size() > 1) {
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

                String t09 = "4. Overview of the \n Financial Results";
                ResultsStorage.addStr(130, "sectionTitle", t09);
                String t14 = "10. Financial Rating";
                ResultsStorage.addStr(140, "sectionTitle", t14);
                Tab tab09 = new Tab(t09);
                tabsArr.put("financialResultsTrend", tab09);
                Tab tab10 = new Tab(t14);
                tabsArr.put("financialRating", tab10);
                tabs.add(tab1);
                for (Tab formulaTab : loopSections()) {
                    tabs.add(formulaTab);
                }
                tabs.add(tab09);
                tabs.add(tab10);
                populateInThread(tabsArr);
                return tabs;
            }
        };

        Thread loadingThread = new Thread(listLoader, "list-loader");
        loadingThread.setDaemon(true);
        loadingThread.start();
        vBox.getChildren().add(tabs);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
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
        btn.setVisible(false);
        return btn;
    }

}
