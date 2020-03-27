package defaultData.Formula;

import database.formula.DbFormulaHandler;
import defaultData.Formula.AltmanZScore.*;
import defaultData.Formula.FinancialSustainability.*;
import defaultData.Formula.InvestmentAnalysis.DegreeOfFinancialLeverage;
import defaultData.Formula.InvestmentAnalysis.NetAssets;
import defaultData.Formula.Liquidity.*;
import defaultData.Formula.Other.LaborProductivity;
import defaultData.Formula.ProfitabilityAndPerformance.*;
import defaultData.Formula.Turnover.*;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DefaultFormulas {

    private static int counter;

    public static ObservableList<Formula> getFormulas(Integer id) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int lastId = DbFormulaHandler.getLastId() + 1;
        int ID = lastId;
        int tplId = (id != null) ? id : 0;
        Formulas.add(new Formula(ID, "Default", "", "", "", "template", "", tplId));
        counter = lastId + 1;
        ObservableList<Formula> IFRSFormulas = getIndustryChilds(ID);
        Formulas.addAll(IFRSFormulas);
        return Formulas;
    }


    private static ObservableList<Formula> getIndustryChilds(int parent) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        //IFRS CategoryLevel
        int CSAL = counter;
        Formulas.add(new Formula(CSAL, "Common-Size Analysis of Assets, \nLiabilities and Shareholders' Equity", "AssetsEquityStructureTrend", "0", "", "section", "", parent));
        counter++;
        int FSiD = counter;
        Formulas.add(new Formula(FSiD, "Financial Sustainability and \nLong-Term Debt-Paying Ability", "FinancialSustainability", "1", "", "section", "", parent));
        counter++;
        int LiD = counter;
        Formulas.add(new Formula(LiD, "Liquidity of Short-Term Assets", "Liquidity", "2", "", "section", "", parent));
        counter++;
        int OFR = counter;
        Formulas.add(new Formula(OFR, "Overview of the financial Results", "OverviewFinancialResults", "3", "", "section", "", parent));
        counter++;
        int PaPiD = counter;
        Formulas.add(new Formula(PaPiD, "Profitability and Performance", "ProfitabilityAndPerformance", "4", "", "section", "", parent));
        counter++;
        int TiD = counter;
        Formulas.add(new Formula(TiD, "Activity Ratios \n(Turnover Ratios)", "Turnover", "5", "", "section", "", parent));
        counter++;
        int IAiD = counter;
        Formulas.add(new Formula(IAiD, "Investor Analysis", "InvestorAnalysis", "6", "", "section", "", parent));
        counter++;
        int AZSiD = counter;
        Formulas.add(new Formula(AZSiD, "Forecasting Financial Failure \n(Bankruptcy Test)", "ZscoreModel", "7", "", "section", "", parent));
        counter++;
        int OIiD = counter;
        Formulas.add(new Formula(OIiD, "Labor Productivity", "LaborProductivitySection", "8", "", "section", "", parent));
        counter++;
        int FRAT = counter;
        Formulas.add(new Formula(FRAT, "Financial Rating", "FinancialRating", "9", "", "section", "", parent));
        counter++;

        //Financial Sustainability
        Formulas.addAll(TimesInterestEarned.get(FSiD, counter));
        counter = counter + 6;
        Formulas.addAll(DebtRatio.get(FSiD, counter));
        counter = counter + 6;
        Formulas.addAll(LongTermDebtRatio.get(FSiD, counter));
        counter = counter + 6;
        Formulas.addAll(TheLongTermDebttoTotalCapitalizationRatio.get(FSiD, counter));
        counter = counter + 6;
        Formulas.addAll(DebtEquityRatio.get(FSiD, counter));
        counter = counter + 4;
        Formulas.addAll(DebttoTangibleNetWorthRatio.get(FSiD, counter));
        counter = counter + 8;
        Formulas.addAll(LongTermDebttoEquity.get(FSiD, counter));
        counter = counter + 4;
        //Liquidity
        Formulas.addAll(CurrentRatio.get(LiD, counter));
        counter = counter + 10;
        Formulas.addAll(QuickRatio.get(LiD, counter));
        counter = counter + 7;
        Formulas.addAll(CashRatio.get(LiD, counter));
        counter = counter + 5;
        Formulas.addAll(NetWorkingCapital.get(LiD, counter));
        counter = counter + 7;
        Formulas.addAll(SalestoNetWorkingCapital.get(LiD, counter));
        counter = counter + 6;

        //Profitability and Performance
        Formulas.addAll(NetProfitMargin.get(PaPiD, counter));
        counter = counter + 6;
        Formulas.addAll(OperatingIncomeMargin.get(PaPiD, counter));
        counter = counter + 3;
        Formulas.addAll(GrossProfitMargin.get(PaPiD, counter));
        counter = counter + 6;
        Formulas.addAll(ReturnOnAssets.get(PaPiD, counter));
        counter = counter + 5;
        Formulas.addAll(ReturnOnNonCurrentAssets.get(PaPiD, counter));
        counter = counter + 4;
        Formulas.addAll(ReturnonOperatingAssets.get(PaPiD, counter));
        counter = counter + 4;
        Formulas.addAll(ReturnonInvestment.get(PaPiD, counter));
        counter = counter + 7;
        Formulas.addAll(ReturnonEquityafterTax.get(PaPiD, counter));
        counter = counter + 8;


        //Turnover
        Formulas.addAll(TotalAssetTurnover.get(TiD, counter));
        counter = counter + 4;
        Formulas.addAll(SalestoFixedAssets.get(TiD, counter));
        counter = counter + 5;
        Formulas.addAll(CurrentAssetTurnover.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(WorkingCapitalTurnover.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(AccountsReceivableTurnover.get(TiD, counter));
        counter = counter + 4;
        Formulas.addAll(AverageCollectionPeriod.get(TiD, counter));
        counter = counter + 6;
        Formulas.addAll(AccountsPayableTurnover.get(TiD, counter));
        counter = counter + 4;
        Formulas.addAll(DaysPayableOutstanding.get(TiD, counter));
        counter = counter + 5;
        Formulas.addAll(InventoryTurnover.get(TiD, counter));
        counter = counter + 5;
        Formulas.addAll(InventoryTurnoverinDays.get(TiD, counter));
        counter = counter + 5;
        Formulas.addAll(CashTurnover.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(OperatingCycle.get(TiD, counter));
        counter = counter + 4;
        Formulas.addAll(CashConversionCycle.get(TiD, counter));
        counter = counter + 6;

        //Investment analysis
        Formulas.addAll(NetAssets.get(IAiD, counter));
        counter = counter + 7;
        Formulas.addAll(DegreeOfFinancialLeverage.get(IAiD, counter));
        counter = counter + 2;


        //Altman Z-score
        Formulas.addAll(X1.get(AZSiD, counter));
        counter = counter + 1;
        Formulas.addAll(X2.get(AZSiD, counter));
        counter = counter + 1;
        Formulas.addAll(X3.get(AZSiD, counter));
        counter = counter + 1;
        Formulas.addAll(X4.get(AZSiD, counter));
        counter = counter + 1;
        Formulas.addAll(X5.get(AZSiD, counter));
        counter = counter + 1;
        Formulas.addAll(Z.get(AZSiD, counter));
        counter = counter + 9;

        //Other
        Formulas.addAll(LaborProductivity.get(OIiD, counter));
        counter = counter + 4;

        return Formulas;
    }

    public void resetCounter() {
        counter = 1;
    }

}