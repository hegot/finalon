package finalon.defaultData.Formula;

import finalon.defaultData.Formula.AltmanZScore.*;
import finalon.defaultData.Formula.FinancialSustainability.*;
import finalon.defaultData.Formula.InvestmentAnalysis.DegreeOfFinancialLeverage;
import finalon.defaultData.Formula.InvestmentAnalysis.NetAssets;
import finalon.defaultData.Formula.Liquidity.*;
import finalon.defaultData.Formula.Other.LaborProductivity;
import finalon.defaultData.Formula.ProfitabilityAndPerformance.*;
import finalon.defaultData.Formula.Turnover.*;
import finalon.entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DefaultFormulas {

    private static int counter = 1;

    public static ObservableList<Formula> getFormulas() {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();

        //Standard Level
        int IFRSiD = counter;
        Formulas.add(new Formula(IFRSiD, "IFRS", "", "", "", "standard", "", 0));
        counter++;
        int USGAAPiD = counter;
        Formulas.add(new Formula(USGAAPiD, "US GAAP", "", "", "", "standard", "", 0));
        counter++;
        //IndustryLevel
        int IFRSGeneraliD = counter;
        Formulas.add(new Formula(IFRSGeneraliD, "IFRS General", "IFRS General", "", "", "industry", "", IFRSiD));
        counter++;
        int USGAAPGeneraliD = counter;
        Formulas.add(new Formula(USGAAPGeneraliD, "US GAAP General", "US GAAP General", "", "", "industry", "", USGAAPiD));
        counter++;
        //IFRS Formula Level
        ObservableList<Formula> IFRSFormulas = getIndustryChilds(IFRSGeneraliD);
        ObservableList<Formula> USGAAPFormulas = getIndustryChilds(USGAAPGeneraliD);
        Formulas.addAll(IFRSFormulas);
        Formulas.addAll(USGAAPFormulas);
        return Formulas;
    }

    public static ObservableList<Formula> getFormulasForIndustry(int standard, String industry, int counterStart) {
        counter = counterStart;
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int industryId = counter;
        Formulas.add(new Formula(industryId, industry, industry, "", "", "industry", "", standard));
        counter++;
        ObservableList<Formula> childFormulas = getIndustryChilds(industryId);
        Formulas.addAll(childFormulas);
        return Formulas;
    }

    private static ObservableList<Formula> getIndustryChilds(int parent) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        //IFRS CategoryLevel
        int FSiD = counter;
        Formulas.add(new Formula(FSiD, "Financial Sustainability and \n Long-Term Debt-Paying Ability", "FinancialSustainability", "0", "", "section", "", parent));
        counter++;
        int LiD = counter;
        Formulas.add(new Formula(LiD, "Liquidity of Short-Term Assets", "Liquidity", "1", "", "section", "", parent));
        counter++;
        int PaPiD = counter;
        Formulas.add(new Formula(PaPiD, "Profitability and Performance", "ProfitabilityAndPerformance", "2", "", "section", "", parent));
        counter++;
        int TiD = counter;
        Formulas.add(new Formula(TiD, "Activity Ratios \n(Turnover Ratios)", "Turnover", "3", "", "section", "", parent));
        counter++;
        int IAiD = counter;
        Formulas.add(new Formula(IAiD, "Investor Analysis", "InvestorAnalysis", "4", "", "section", "", parent));
        counter++;
        int AZSiD = counter;
        Formulas.add(new Formula(AZSiD, "Forecasting Financial Failure \n (Bankruptcy Test)", "ZscoreModel", "5", "", "section", "", parent));
        counter++;
        int OIiD = counter;
        Formulas.add(new Formula(OIiD, "Labor Productivity", "LaborProductivitySection", "6", "", "section", "", parent));
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