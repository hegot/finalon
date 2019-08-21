package finalon.reportGeneration.interpreter;

import javafx.scene.layout.VBox;
import finalon.reportGeneration.interpreter.AssetsReport.AssetsReport;
import finalon.reportGeneration.interpreter.FinancialRating.FinancialRating;
import finalon.reportGeneration.interpreter.FinancialResults.FinancialResultsReport;
import finalon.reportGeneration.interpreter.FinancialSustainability.FinancialSustainabilityReport;
import finalon.reportGeneration.interpreter.FormulaCalculation.FormulaCalculation;
import finalon.reportGeneration.interpreter.FormulaList.FormulaList;
import finalon.reportGeneration.interpreter.GeneralAnalysis.GeneralAnalysis;
import finalon.reportGeneration.interpreter.InvestorAnalysis.InvestorAnalysis;
import finalon.reportGeneration.interpreter.LaborProductivity.LaborProductivity;
import finalon.reportGeneration.interpreter.LiabilitiesReport.LiabilitiesReport;
import finalon.reportGeneration.interpreter.Liquidity.LiquidityReport;
import finalon.reportGeneration.interpreter.ProfitabilityRatios.ProfitabilityRatios;
import finalon.reportGeneration.interpreter.TurnoverRatios.TurnoverRatios;
import finalon.reportGeneration.interpreter.ZScoreModel.ZScoreModel;

public class Interprter {
    public VBox getReport(String type, int counter) {
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
            case "FinancialSustainability":
                vbox = new FinancialSustainabilityReport(counter).get();
                break;
            case "Liquidity":
                vbox = new LiquidityReport(counter).get();
                break;
            case "financialResultsTrend":
                vbox = new FinancialResultsReport(counter).getTrend();
                break;
            case "ProfitabilityAndPerformance":
                vbox = new ProfitabilityRatios(counter).get();
                break;
            case "Turnover":
                vbox = new TurnoverRatios(counter).get();
                break;
            case "InvestorAnalysis":
                vbox = new InvestorAnalysis(counter).get();
                break;
            case "ZscoreModel":
                vbox = new ZScoreModel(counter).get();
                break;
            case "LaborProductivitySection":
                vbox = new LaborProductivity(counter).get();
                break;
            case "financialRating":
                vbox = new FinancialRating(counter).get();
                break;
            default:
                vbox = new GeneralAnalysis(type, counter).get();
                break;
        }
        return vbox;
    }


}


