package reportGeneration.interpreter;

import javafx.scene.layout.VBox;
import reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.AssetsLiabilitiesEquityAnalysis;
import reportGeneration.interpreter.FinancialRating.FinancialRating;
import reportGeneration.interpreter.FinancialResults.FinancialResultsReport;
import reportGeneration.interpreter.FinancialSustainability.FinancialSustainabilityReport;
import reportGeneration.interpreter.FormulaCalculation.FormulaCalculation;
import reportGeneration.interpreter.GeneralAnalysis.GeneralAnalysis;
import reportGeneration.interpreter.InvestorAnalysis.InvestorAnalysis;
import reportGeneration.interpreter.LaborProductivity.LaborProductivity;
import reportGeneration.interpreter.Liquidity.LiquidityReport;
import reportGeneration.interpreter.ProfitabilityRatios.ProfitabilityRatios;
import reportGeneration.interpreter.TurnoverRatios.TurnoverRatios;
import reportGeneration.interpreter.ZScoreModel.ZScoreModel;

public class Interprter {
    public VBox getReport(String type, int counter) {
        FormulaCalculation calc = new FormulaCalculation();
        calc.setFormulaValues();
        VBox vbox = new VBox();
        switch (type) {
            case "AssetsEquityStructureTrend":
                vbox = new AssetsLiabilitiesEquityAnalysis(counter).get();
                break;
            case "FinancialSustainability":
                vbox = new FinancialSustainabilityReport(counter).get();
                break;
            case "Liquidity":
                vbox = new LiquidityReport(counter).get();
                break;
            case "OverviewFinancialResults":
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
            case "FinancialRating":
                vbox = new FinancialRating(counter).get();
                break;
            default:
                vbox = new GeneralAnalysis(type, counter).get();
                break;
        }
        return vbox;
    }


}


