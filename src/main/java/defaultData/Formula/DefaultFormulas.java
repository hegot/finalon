package defaultData.Formula;

import defaultData.Formula.AltmanZScore.*;
import defaultData.Formula.FinancialSustainability.*;
import defaultData.Formula.InvestmentAnalysis.NetAssets;
import defaultData.Formula.Liquidity.*;
import defaultData.Formula.Other.LaborProductivity;
import defaultData.Formula.ProfitabilityAndPerformance.*;
import defaultData.Formula.Turnover.*;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DefaultFormulas {

    private static int counter = 1;

    public void resetCounter(){
        counter = 1;
    }

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
        counter = counterStart + 1;
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
        Formulas.add(new Formula(FSiD, "Financial Sustainability", "FS", "", "", "section", "", parent));
        counter++;
        int LiD = counter;
        Formulas.add(new Formula(LiD, "Liquidity", "L", "", "", "section", "", parent));
        counter++;
        int PaPiD = counter;
        Formulas.add(new Formula(PaPiD, "Profitability and Performance", "PaP", "", "", "section", "", parent));
        counter++;
        int TiD = counter;
        Formulas.add(new Formula(TiD, "Turnover", "T", "", "", "section", "", parent));
        counter++;
        int IAiD = counter;
        Formulas.add(new Formula(IAiD, "Investment analysis", "IA", "", "", "section", "", parent));
        counter++;
        int AZSiD = counter;
        Formulas.add(new Formula(AZSiD, "Altman Z-score", "AZS", "", "", "section", "", parent));
        counter++;
        int OIiD = counter;
        Formulas.add(new Formula(OIiD, "Other", "OI", "", "", "section", "", parent));
        counter++;
        //Financial Sustainability
        Formulas.addAll(TimesInterestEarned.get(FSiD, counter));
        counter = counter + 6;
        Formulas.addAll(DebtRatio.get(FSiD, counter));
        counter = counter + 4;
        Formulas.addAll(LongTermDebtRatio.get(FSiD, counter));
        counter = counter + 6;
        Formulas.addAll(TheLongTermDebttoTotalCapitalizationRatio.get(FSiD, counter));
        counter = counter + 6;
        Formulas.addAll(DebtEquityRatio.get(FSiD, counter));
        counter = counter + 4;
        Formulas.addAll(DebttoTangibleNetWorthRatio.get(FSiD, counter));
        counter = counter + 7;
        Formulas.addAll(LongTermDebttoEquity.get(FSiD, counter));
        counter = counter + 5;
        //Liquidity
        Formulas.addAll(CurrentRatio.get(LiD, counter));
        counter = counter + 5;
        Formulas.addAll(QuickRatio.get(LiD, counter));
        counter = counter + 3;
        Formulas.addAll(CashRatio.get(LiD, counter));
        counter = counter + 4;
        Formulas.addAll(NetWorkingCapital.get(LiD, counter));
        counter = counter + 4;
        Formulas.addAll(SalestoNetWorkingCapital.get(LiD, counter));
        counter = counter + 4;
        //Profitability and Performance
        Formulas.addAll(NetProfitMargin.get(PaPiD, counter));
        counter = counter + 1;
        Formulas.addAll(OperatingIncomeMargin.get(PaPiD, counter));
        counter = counter + 1;
        Formulas.addAll(GrossProfitMargin.get(PaPiD, counter));
        counter = counter + 3;
        Formulas.addAll(ReturnOnCurrentAssets.get(PaPiD, counter));
        counter = counter + 1;
        Formulas.addAll(ReturnonOperatingAssets.get(PaPiD, counter));
        counter = counter + 1;
        Formulas.addAll(ReturnonInvestment.get(PaPiD, counter));
        counter = counter + 5;
        Formulas.addAll(ReturnonEquityafterTax.get(PaPiD, counter));
        counter = counter + 5;
        Formulas.addAll(ReturnOnAssets.get(PaPiD, counter));
        counter = counter + 5;
        //Turnover
        Formulas.addAll(TotalAssetTurnover.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(SalestoFixedAssets.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(CurrentAssetTurnover.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(WorkingCapitalTurnover.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(AccountsReceivableTurnover.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(AverageCollectionPeriod.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(AccountsPayableTurnover.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(DaysPayableOutstanding.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(InventoryTurnover.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(InventoryTurnoverinDays.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(CashTurnover.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(OperatingCycle.get(TiD, counter));
        counter = counter + 3;
        Formulas.addAll(CashConversionCycle.get(TiD, counter));
        counter = counter + 3;
        //Investment analysis
        Formulas.addAll(NetAssets.get(IAiD, counter));
        counter = counter + 1;
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
        counter = counter + 1;
        //Other
        Formulas.addAll(LaborProductivity.get(OIiD, counter));
        counter = counter + 1;

        return Formulas;
    }

}