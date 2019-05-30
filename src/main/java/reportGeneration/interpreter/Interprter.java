package reportGeneration.interpreter;

import javafx.scene.layout.VBox;
import reportGeneration.interpreter.AssetsReport.AssetsReport;
import reportGeneration.interpreter.FinancialSustainability.FinancialSustainabilityReport;
import reportGeneration.interpreter.FormulaCalculation.FormulaCalculation;
import reportGeneration.interpreter.FormulaList.FormulaList;
import reportGeneration.interpreter.LiabilitiesReport.LiabilitiesReport;
import reportGeneration.interpreter.Liquidity.LiquidityReport;

public class Interprter {
    public VBox getReport(String type) {
        FormulaCalculation calc = new FormulaCalculation();
        calc.setFormulaValues();
        VBox vbox = new VBox();
        switch (type) {
            case "assetTrend":
                vbox = new AssetsReport().getTrend();
                break;
            case "assetStructure":
                vbox = new AssetsReport().getStructure();
                break;
            case "liabilitiesTrend":
                vbox = new LiabilitiesReport().getTrend();
                break;
            case "liabilitiesStructure":
                vbox = new LiabilitiesReport().getStructure();
                break;
            case "formulaList":
                vbox = new FormulaList().get();
                break;
            case "financialSustainability":
                vbox = new FinancialSustainabilityReport().get();
                break;
            case "liquidity":
                vbox = new LiquidityReport().get();
                break;
        }
        return vbox;
    }


}


