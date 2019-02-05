package defaultData;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DefaultFormulas {

    private static int counter = 1;

    public static ObservableList<Formula> getFormulas() {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();

        //Standard Level
        int IFRSiD = counter;
        Formulas.add(new Formula(IFRSiD, "IFRS", "", "", "", "", "", 0));
        counter++;

        int USGAAPiD = counter;
        Formulas.add(new Formula(USGAAPiD, "US GAAP", "", "", "", "", "", 0));
        counter++;

        //IndustryLevel
        int IFRSGeneraliD = counter;
        Formulas.add(new Formula(IFRSGeneraliD, "IFRS General", "IFRS General", "", "", "", "", IFRSiD));
        counter++;

        int USGAAPGeneraliD = counter;
        Formulas.add(new Formula(USGAAPGeneraliD, "US GAAP General", "US GAAP General", "", "", "", "", USGAAPiD));
        counter++;


        //IFRS Formula Level
        ObservableList<Formula> IFRSFormulas = getIndustryChilds(IFRSGeneraliD);
        ObservableList<Formula> USGAAPFormulas = getIndustryChilds(USGAAPGeneraliD);
        Formulas.addAll(IFRSFormulas);
        Formulas.addAll(USGAAPFormulas);

        return Formulas;
    }


    private static ObservableList<Formula> getIndustryChilds(int parent) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        //IFRS CategoryLevel
        int FSiD = counter;
        Formulas.add(new Formula(FSiD, "Financial Sustainability", "FS", "", "", "", "", parent));
        counter++;

        int LiD = counter;
        Formulas.add(new Formula(LiD, "Liquidity", "L", "", "", "", "", parent));
        counter++;

        int PaPiD = counter;
        Formulas.add(new Formula(PaPiD, "Profitability and Performance", "PaP", "", "", "", "", parent));
        counter++;

        int TiD = counter;
        Formulas.add(new Formula(TiD, "Turnover", "T", "", "", "", "", parent));
        counter++;


        int IAiD = counter;
        Formulas.add(new Formula(IAiD, "Investment analysis", "IA", "", "", "", "", parent));
        counter++;

        int AZSiD = counter;
        Formulas.add(new Formula(AZSiD, "Altman Z-score", "AZS", "", "", "", "", parent));
        counter++;

        int OIiD = counter;
        Formulas.add(new Formula(OIiD, "Other", "OI", "", "", "", "", parent));
        counter++;

        //Financial Sustainability
        int TimesInterestEarned = counter;
        Formulas.add(new Formula(TimesInterestEarned, "Times Interest Earned", "TimesInterestEarned", "(ProfitLossBeforeTax-FinanceCost-GainsLossesOnNetMonetaryPosition)/FinanceCost", "", "", "times", FSiD));
        counter++;
        int DebtRatio = counter;
        Formulas.add(new Formula(DebtRatio, "Debt Ratio", "DebtRatio", "(NonCurrentAssets+CurrentLiabilities)/Assets", "", "", "", FSiD));
        counter++;
        int LongTermDebtRatio = counter;
        Formulas.add(new Formula(LongTermDebtRatio, "Long-Term Debt Ratio", "LongTermDebtRatio", "NonCurrentAssets/Assets", "", "", "", FSiD));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatio = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatio, "The Long-Term Debt to Total Capitalization Ratio", "TheLongTermDebttoTotalCapitalizationRatio", "(NonCurrentAssets)/(NonCurrentAssets+IssuedCapital+SharePremium)", "", "", "", FSiD));
        counter++;
        int DebtEquityRatio = counter;
        Formulas.add(new Formula(DebtEquityRatio, "Debt/Equity Ratio", "DebtEquityRatio", "(NonCurrentAssets+CurrentLiabilities)/Equity", "", "", "", FSiD));
        counter++;
        int DebttoTangibleNetWorthRatio = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatio, "Debt to Tangible Net Worth Ratio", "DebttoTangibleNetWorthRatio", "(NonCurrentAssets+CurrentLiabilities)/(Equity-IntangibleAssetsOtherThanGoodwill-Goodwill)", "", "", "", FSiD));
        counter++;
        int LongTermDebttoEquity = counter;
        Formulas.add(new Formula(LongTermDebttoEquity, "Long-Term Debt to Equity", "LongTermDebttoEquity", "NonCurrentAssets/(IssuedCapital+SharePremium)", "", "", "", FSiD));
        counter++;


        //Liquidity
        int CurrentRatio = counter;
        Formulas.add(new Formula(CurrentRatio, "Current Ratio", "CurrentRatio", "CurrentAssets/CurrentLiabilities", "", "", "", LiD));
        counter++;
        int QuickRatio = counter;
        Formulas.add(new Formula(QuickRatio, "Quick Ratio (Acid Test Ratio)", "QuickRatio", "(CashAndCashEquivalents+TradeAndOtherCurrentReceivables)/CurrentLiabilities", "", "", "", LiD));
        counter++;
        int CashRatio = counter;
        Formulas.add(new Formula(CashRatio, "Cash Ratio", "CashRatio", "CashAndCashEquivalents/CurrentLiabilities", "", "", "", LiD));
        counter++;
        int NetWorkingCapital = counter;
        Formulas.add(new Formula(NetWorkingCapital, "Net Working Capital", "NetWorkingCapital", "CurrentAssets-CurrentLiabilities", "", "", "money (tousand dollar)", LiD));
        counter++;
        int SalestoNetWorkingCapital = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapital, "Sales to Net Working Capital", "SalestoNetWorkingCapital", "Revenue/((CurrentAssets[1]-CurrentLiabilities[1]+CurrentAssets[0]-CurrentLiabilities[0])/2)", "", "", "times", LiD));
        counter++;


        //Profitability and Performance
        int NetProfitMargin = counter;
        Formulas.add(new Formula(NetProfitMargin, "Net Profit Margin", "NetProfitMargin", "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/Revenue", "", "", "%", PaPiD));
        counter++;
        int OperatingIncomeMargin = counter;
        Formulas.add(new Formula(OperatingIncomeMargin, "Operating Income Margin", "OperatingIncomeMargin", "ProfitLossFromOperatingActivities/Revenue", "", "", "%", PaPiD));
        counter++;
        int GrossProfitMargin = counter;
        Formulas.add(new Formula(GrossProfitMargin, "Gross Profit Margin", "GrossProfitMargin", "GrossProfit/Revenue", "", "", "%", PaPiD));
        counter++;
        int ReturnoNonCurrentAssets = counter;
        Formulas.add(new Formula(ReturnoNonCurrentAssets, "Return on Assets", "ReturnoNonCurrentAssets", "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/(Assets[1]/2+Assets[0]/2)", "", "", "%", PaPiD));
        counter++;
        int ReturnonOperatingAssets = counter;
        Formulas.add(new Formula(ReturnonOperatingAssets, "Return on Operating Assets", "ReturnonOperatingAssets", "ProfitLossFromOperatingActivities/(Assets[0]/2+Assets[1]/2-IntangibleAssetsOtherThanGoodwill[0]/2-IntangibleAssetsOtherThanGoodwill[1]/2-Goodwill[0]/2-Goodwill[1]/2-OtherNoncurrentNonfinancialAssets[0]/2-OtherNoncurrentNonfinancialAssets[1]/2-DeferredTaxAssets[0]/2-DeferredTaxAssets[1]/2-OtherNoncurrentFinancialAssets[0]/2-OtherNoncurrentFinancialAssets[1]/2-CurrentTaxAssetsNoncurrent[0]/2-CurrentTaxAssetsNoncurrent[1]/2)", "", "", "%", PaPiD));
        counter++;
        int ReturnonInvestment = counter;
        Formulas.add(new Formula(ReturnonInvestment, "Return on Investment", "ReturnonInvestment", "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations+FinanceCost)/(Equity[0]/2+Equity[1]/2+NonCurrentAssets[0]/2+NonCurrentAssets[1]/2)", "", "", "%", PaPiD));
        counter++;
        int ReturnonEquityafterTax = counter;
        Formulas.add(new Formula(ReturnonEquityafterTax, "Return on Equity after Tax", "ReturnonEquityafterTax", "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/(Equity[0]/2+Equity[1]/2)", "", "", "%", PaPiD));
        counter++;
        int ReturnoNonAssets = counter;
        Formulas.add(new Formula(ReturnoNonAssets, "Return on Assets", "ReturnoNonAssets", "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/(Assets[0]/2+Assets[1]/2)", "", "", "%", PaPiD));
        counter++;


        //Turnover
        int TotalAssetTurnover = counter;
        Formulas.add(new Formula(TotalAssetTurnover, "Total Asset Turnover", "TotalAssetTurnover", "Revenue/(Assets[0]/2+Assets[1]/2)", "", "", "times", TiD));
        counter++;
        int SalestoFixedAssets = counter;
        Formulas.add(new Formula(SalestoFixedAssets, "Sales to Fixed Assets", "SalestoFixedAssets", "Revenue/(PropertyPlantAndEquipment[0]/2+PropertyPlantAndEquipment[1]/2)", "", "", "times", TiD));
        counter++;
        int CurrentAssetTurnover = counter;
        Formulas.add(new Formula(CurrentAssetTurnover, "Current Asset Turnover", "CurrentAssetTurnover", "Revenue/((CurrentAssets[0]+CurrentAssets[1])/2)", "", "", "times", TiD));
        counter++;
        int WorkingCapitalTurnover = counter;
        Formulas.add(new Formula(WorkingCapitalTurnover, "Working Capital Turnover", "WorkingCapitalTurnover", "Revenue/((CurrentAssets[1]-CurrentLiabilities[1]+CurrentAssets[0]-CurrentLiabilities[0])/2)", "", "", "times", TiD));
        counter++;
        int AccountsReceivableTurnover = counter;
        Formulas.add(new Formula(AccountsReceivableTurnover, "Accounts Receivable Turnover (Times)", "AccountsReceivableTurnover", "Revenue/((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)", "", "", "times", TiD));
        counter++;
        int AverageCollectionPeriod = counter;
        Formulas.add(new Formula(AverageCollectionPeriod, "Average Collection Period (Accounts Receivable Turnover in Days)", "AverageCollectionPeriod", "360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/Revenue", "", "", "days", TiD));
        counter++;
        int AccountsPayableTurnover = counter;
        Formulas.add(new Formula(AccountsPayableTurnover, "Accounts Payable Turnover", "AccountsPayableTurnover", "CostOfSales/(((TradeAndOtherCurrentPayables[0]+TradeAndOtherCurrentPayables[1])/2))", "", "", "times", TiD));
        counter++;
        int DaysPayableOutstanding = counter;
        Formulas.add(new Formula(DaysPayableOutstanding, "Days Payable Outstanding", "DaysPayableOutstanding", "(((TradeAndOtherCurrentPayables[0]+TradeAndOtherCurrentPayables[1])/2)*360)/CostOfSales", "", "", "times", TiD));
        counter++;
        int InventoryTurnover = counter;
        Formulas.add(new Formula(InventoryTurnover, "Inventory Turnover  (Days Inventory Outstanding)", "InventoryTurnover", "CostOfSales/((Inventories[1]+Inventories[0])/2)", "", "", "days", TiD));
        counter++;
        int InventoryTurnoverinDays = counter;
        Formulas.add(new Formula(InventoryTurnoverinDays, "Inventory Turnover in Days", "InventoryTurnoverinDays", "360*((Inventories[1]+Inventories[0])/2)/CostOfSales", "", "", "days", TiD));
        counter++;
        int CashTurnover = counter;
        Formulas.add(new Formula(CashTurnover, "Cash Turnover", "CashTurnover", "Revenue/((CashAndCashEquivalents[0]+CashAndCashEquivalents[1])/2)", "", "", "times", TiD));
        counter++;
        int OperatingCycle = counter;
        Formulas.add(new Formula(OperatingCycle, "Operating Cycle", "OperatingCycle", "360*((Inventories[1]+Inventories[0])/2)/CostOfSales+360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/Revenue", "", "", "days", TiD));
        counter++;
        int CashConversionCycle = counter;
        Formulas.add(new Formula(CashConversionCycle, "Cash Conversion Cycle", "CashConversionCycle", "360*((Inventories[1]+Inventories[0])/2)/CostOfSales+360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/Revenue-(((TradeAndOtherCurrentPayables[0]+TradeAndOtherCurrentPayables[1])/2)*360)/CostOfSales", "", "", "days", TiD));
        counter++;


        //Investment analysis
        int NetAssets = counter;
        Formulas.add(new Formula(NetAssets, "Net assets (Net worth)", "NetAssets", "Assets-NonCurrentAssets-CurrentAssets", "", "", "money", IAiD));
        counter++;


        //Altman Z-score
        int X1 = counter;
        Formulas.add(new Formula(X1, "Altman Z-score X1", "X1", "(CurrentAssets-CurrentLiabilities)/Assets", "", "", "", AZSiD));
        counter++;

        int X2 = counter;
        Formulas.add(new Formula(X2, "Altman Z-score X2", "X2", "RetainedEarnings/Assets", "", "", "", AZSiD));
        counter++;
        int X3 = counter;
        Formulas.add(new Formula(X3, "Altman Z-score	X3", "X3", "(ProfitLossBeforeTax+FinanceCost)/Assets", "", "", "", AZSiD));
        counter++;
        int X4 = counter;
        Formulas.add(new Formula(X4, "Altman Z-score X4", "X4", "Equity/(NonCurrentAssets+CurrentLiabilities)", "", "", "", AZSiD));
        counter++;
        int X5 = counter;
        Formulas.add(new Formula(X5, "Altman Z-score X5", "X5", "Revenue/Assets", "", "", "", AZSiD));
        counter++;
        int Z = counter;
        Formulas.add(new Formula(Z, "Altman Z-score Z", "Z", "0.717*(CurrentAssets-CurrentLiabilities)/Assets+0.847*RetainedEarnings/Assets+3.107*(ProfitLossBeforeTax+FinanceCost)/Assets+0.42*Equity/(NonCurrentAssets+CurrentLiabilities)+0.998*Revenue/Assets", "", "", "", AZSiD));
        counter++;

        //Other
        int Laborproductivity = counter;
        Formulas.add(new Formula(Laborproductivity, "Labor productivity", "Laborproductivity", "Revenue/NumberOfEmployees", "", "", "money per person", OIiD));
        counter++;
        return Formulas;
    }

}