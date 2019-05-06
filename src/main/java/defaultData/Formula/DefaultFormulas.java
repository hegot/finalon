package defaultData.Formula;

import defaultData.Formula.FinancialSustainability.*;
import defaultData.Formula.Liquidity.*;
import defaultData.Formula.ProfitabilityAndPerformance.*;
import defaultData.Formula.Turnover.*;
import entities.Formula;
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
        counter = counter + 4;
        Formulas.addAll(DebtRatio.get(FSiD, counter));
        counter = counter + 4;
        Formulas.addAll(LongTermDebtRatio.get(FSiD, counter));
        counter = counter + 4;
        Formulas.addAll(TheLongTermDebttoTotalCapitalizationRatio.get(FSiD, counter));
        counter = counter + 4;
        Formulas.addAll(DebtEquityRatio.get(FSiD, counter));
        counter = counter + 1;
        Formulas.addAll(DebttoTangibleNetWorthRatio.get(FSiD, counter));
        counter = counter + 4;
        Formulas.addAll(LongTermDebttoEquity.get(FSiD, counter));
        counter = counter + 4;

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




















        int CashConversionCycle = counter;
        Formulas.add(new Formula(CashConversionCycle, "Cash Conversion Cycle", "CashConversionCycle", "360*((Inventories[1]+Inventories[0])/2)/CostOfSales+360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/RevenueGeneral-(((TradeAndOtherCurrentPayables[0]+TradeAndOtherCurrentPayables[1])/2)*360)/CostOfSales", "", "formula", "days", TiD));
        counter++;
        int CashConversionCyclegood = counter;
        Formulas.add(new Formula(CashConversionCyclegood, "good", "<", "60", "", "", "", CashConversionCycle));
        counter++;
        int CashConversionCyclesatisfactory = counter;
        Formulas.add(new Formula(CashConversionCyclesatisfactory, "unsatisfactory", ">=", "60", "", "", "", CashConversionCycle));
        counter++;




        //Investment analysis
        int NetAssets = counter;
        Formulas.add(new Formula(NetAssets, "Net assets (Net worth)", "NetAssets", "AssetsGeneral-NonCurrentAssets-GeneralCurrentAssets", "", "formula", "money", IAiD));
        counter++;


        //Altman Z-score
        int X1 = counter;
        Formulas.add(new Formula(X1, "Altman Z-score X1", "X1", "(GeneralCurrentAssets-CurrentLiabilities)/AssetsGeneral", "", "formula", "", AZSiD));
        counter++;

        int X2 = counter;
        Formulas.add(new Formula(X2, "Altman Z-score X2", "X2", "RetainedEarnings/AssetsGeneral", "", "formula", "", AZSiD));
        counter++;
        int X3 = counter;
        Formulas.add(new Formula(X3, "Altman Z-score X3", "X3", "(ProfitLossBeforeTax+FinanceCosts)/AssetsGeneral", "", "formula", "", AZSiD));
        counter++;
        int X4 = counter;
        Formulas.add(new Formula(X4, "Altman Z-score X4", "X4", "EquityGeneral/(NonCurrentAssets+CurrentLiabilities)", "", "formula", "", AZSiD));
        counter++;
        int X5 = counter;
        Formulas.add(new Formula(X5, "Altman Z-score X5", "X5", "RevenueGeneral/AssetsGeneral", "", "formula", "", AZSiD));
        counter++;
        int Z = counter;
        Formulas.add(new Formula(Z, "Altman Z-score Z", "Z", "0.717*(GeneralCurrentAssets-CurrentLiabilities)/AssetsGeneral+0.847*RetainedEarnings/AssetsGeneral+3.107*(ProfitLossBeforeTax+FinanceCosts)/AssetsGeneral+0.42*EquityGeneral/(NonCurrentAssets+CurrentLiabilities)+0.998*RevenueGeneral/AssetsGeneral", "", "formula", "", AZSiD));
        counter++;

        //Other
        int Laborproductivity = counter;
        Formulas.add(new Formula(Laborproductivity, "Labor productivity", "Laborproductivity", "RevenueGeneral/NumberOfEmployees", "", "formula", "money per person", OIiD));
        counter++;
        return Formulas;
    }

}