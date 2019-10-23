package reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis;

import globalReusables.VBoxTryCatchWrap;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.AssetsReport.AssetsReport;
import reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.LiabilitiesReport.LiabilitiesReport;
import reportGeneration.interpreter.FormulaList.FormulaList;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;

public class AssetsLiabilitiesEquityAnalysis {
    private int weight;

    public AssetsLiabilitiesEquityAnalysis(int weight) {
        this.weight = weight;
    }

    public VBox get() {
        VBox vBox = new VBox(10);
        String t = "1. The Common-Size Analysis \n of the Assets, Liabilities \n and Shareholders' Equity";
        ResultsStorage.addStr(weight++, "sectionTitle", t);
        String t1 = "Assets trend Analysis";
        String t2 = "Liabilities trend Analysis";
        String t3 = "Assets Structure Analysis";
        String t4 = "Liabilities Structure Analysis";
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        if (Periods.getPeriodArr().size() > 1) {
            Tab tab01 = new Tab(t1);
            weight += 20;
            tab01.setContent(new VBoxTryCatchWrap(() -> new AssetsReport().getTrend(weight)).get());
            Tab tab02 = new Tab(t2);
            weight += 20;
            tab02.setContent(new VBoxTryCatchWrap(() -> new LiabilitiesReport().getTrend(weight)).get());
            tabs.getTabs().addAll(tab01, tab02);
        }

        Tab tab03 = new Tab(t3);
        weight += 20;
        tab03.setContent(new VBoxTryCatchWrap(() -> new AssetsReport().getStructure(weight)).get());
        Tab tab04 = new Tab(t4);
        weight += 20;
        tab04.setContent(new VBoxTryCatchWrap(() -> new LiabilitiesReport().getStructure(weight)).get());
        Tab tab05 = new Tab("Formula Calculation");
        tab05.setContent(new VBoxTryCatchWrap(() -> new FormulaList().get()).get());
        tabs.getTabs().addAll(tab03, tab04, tab05);
        vBox.getChildren().add(tabs);
        return vBox;
    }

}
