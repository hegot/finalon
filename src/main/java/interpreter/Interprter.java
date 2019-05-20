package interpreter;

import entities.Item;
import interpreter.AssetsReport.AssetsReport;
import interpreter.FormulaList.FormulaList;
import interpreter.LiabilitiesReport.LiabilitiesReport;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;

public class Interprter {
    private ObservableMap<String, String> settings;
    private ObservableList<Item> items;

    public Interprter(
            ObservableMap<String, String> settings,
            ObservableList<Item> items
    ) {

        this.settings = settings;
        this.items = items;

    }


    public VBox assetReport() {
        return new AssetsReport(this.items, this.settings).get();
    }

    public VBox liabilitiesReport() {
        return new LiabilitiesReport(this.items, this.settings).get();
    }

    public VBox formulaList() {
        return new FormulaList(this.items, this.settings).get();
    }

}


