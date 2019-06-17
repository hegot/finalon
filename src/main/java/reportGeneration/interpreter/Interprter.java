package reportGeneration.interpreter;

import javafx.scene.layout.VBox;
import reportGeneration.interpreter.AssetsReport.AssetsReport;
import reportGeneration.interpreter.FinancialResults.FinancialResultsReport;
import reportGeneration.interpreter.FinancialSustainability.FinancialSustainabilityReport;
import reportGeneration.interpreter.FormulaCalculation.FormulaCalculation;
import reportGeneration.interpreter.FormulaList.FormulaList;
import reportGeneration.interpreter.InvestorAnalysis.InvestorAnalysis;
import reportGeneration.interpreter.LaborProductivity.LaborProductivity;
import reportGeneration.interpreter.LiabilitiesReport.LiabilitiesReport;
import reportGeneration.interpreter.Liquidity.LiquidityReport;
import reportGeneration.interpreter.ProfitabilityRatios.ProfitabilityRatios;
import reportGeneration.interpreter.TurnoverRatios.TurnoverRatios;
import reportGeneration.interpreter.ZScoreModel.ZScoreModel;

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
            case "financialResultsTrend":
                vbox = new FinancialResultsReport().getTrend();
                break;
            case "profitabilityRatios":
                vbox = new ProfitabilityRatios().get();
                break;
            case "turnoverRatios":
                vbox = new TurnoverRatios().get();
                break;
            case "investorAnalysis":
                vbox = new InvestorAnalysis().get();
                break;
            case "zScoreModel":
                vbox = new ZScoreModel().get();
                break;
            case "laborProductivity":
                vbox = new LaborProductivity().get();
                break;
        }
        return vbox;
    }


}


