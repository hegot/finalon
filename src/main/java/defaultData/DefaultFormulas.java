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
        int TimesInterestEarned = counter;
        Formulas.add(new Formula(TimesInterestEarned, "Times Interest Earned", "TimesInterestEarned", "(ProfitLossBeforeTax-FinanceCost-GainsLossesOnNetMonetaryPosition)/FinanceCost", "", "", "times", FSiD));
        counter++;
            int TimesInterestEarnedexcellent = counter;
            Formulas.add(new Formula(TimesInterestEarnedexcellent, "excellent", ">=", "2", "", "", "", TimesInterestEarned));
            counter++;
            int TimesInterestEarnedsatisfactory = counter;
            Formulas.add(new Formula(TimesInterestEarnedsatisfactory, "satisfactory", "<=", "1", "", "<", "2", TimesInterestEarned));
            counter++;
            int TimesInterestEarnedbad = counter;
            Formulas.add(new Formula(TimesInterestEarnedbad, "bad", "<", "1", "", "", "", TimesInterestEarned));
            counter++;

        int DebtRatio = counter;
        Formulas.add(new Formula(DebtRatio, "Debt Ratio", "DebtRatio", "(NonCurrentAssets+CurrentLiabilities)/Assets", "", "", "", FSiD));
        counter++;
            int DebtRatioexcellent = counter;
            Formulas.add(new Formula(DebtRatioexcellent, "excellent", "<=", "0.4", "", "", "", DebtRatio));
            counter++;
            int DebtRatiogood = counter;
            Formulas.add(new Formula(DebtRatiogood, "good", "<", "0.4", "", "<=", "0.6", DebtRatio));
            counter++;
            int DebtRatiobad = counter;
            Formulas.add(new Formula(DebtRatiobad, "bad", ">", "6", "", "", "", DebtRatio));
            counter++;

        int LongTermDebtRatio = counter;
        Formulas.add(new Formula(LongTermDebtRatio, "Long-Term Debt Ratio", "LongTermDebtRatio", "NonCurrentAssets/Assets", "", "", "", FSiD));
        counter++;
            int LongTermDebtRatioexcellent = counter;
            Formulas.add(new Formula(LongTermDebtRatioexcellent, "excellent", ">=", "0.2", "", "", "", LongTermDebtRatio));
            counter++;
            int LongTermDebtRatiosatisfactory = counter;
            Formulas.add(new Formula(LongTermDebtRatiosatisfactory, "satisfactory", "<=", "0.1", "", "<", "0.2", LongTermDebtRatio));
            counter++;
            int LongTermDebtRatiobad = counter;
            Formulas.add(new Formula(LongTermDebtRatiobad, "bad", "<", "0.1", "", "", "", LongTermDebtRatio));
            counter++;

        int TheLongTermDebttoTotalCapitalizationRatio = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatio, "The Long-Term Debt to Total Capitalization Ratio", "TheLongTermDebttoTotalCapitalizationRatio", "(NonCurrentAssets)/(NonCurrentAssets+IssuedCapital+SharePremium)", "", "", "", FSiD));
        counter++;
            int TheLongTermDebttoTotalCapitalizationRatioexcellent = counter;
            Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatioexcellent, "excellent", ">=", "0.6", "", "", "", TheLongTermDebttoTotalCapitalizationRatio));
            counter++;
            int TheLongTermDebttoTotalCapitalizationRatiosatisfactory = counter;
            Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatiosatisfactory, "satisfactory", "<=", "0.4", "", "<", "0.6", TheLongTermDebttoTotalCapitalizationRatio));
            counter++;
            int TheLongTermDebttoTotalCapitalizationRatiobad = counter;
            Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatiobad, "bad", "<", "0.4", "", "", "", TheLongTermDebttoTotalCapitalizationRatio));
            counter++;

        int DebtEquityRatio = counter;
        Formulas.add(new Formula(DebtEquityRatio, "Debt/Equity Ratio", "DebtEquityRatio", "(NonCurrentAssets+CurrentLiabilities)/Equity", "", "", "", FSiD));
        counter++;
        int DebttoTangibleNetWorthRatio = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatio, "Debt to Tangible Net Worth Ratio", "DebttoTangibleNetWorthRatio", "(NonCurrentAssets+CurrentLiabilities)/(Equity-IntangibleAssetsOtherThanGoodwill-Goodwill)", "", "", "", FSiD));
        counter++;
            int DebttoTangibleNetWorthRatioexcellent = counter;
            Formulas.add(new Formula(DebttoTangibleNetWorthRatioexcellent, "excellent", ">=", "0.6", "", "", "", DebttoTangibleNetWorthRatio));
            counter++;
            int DebttoTangibleNetWorthRatiosatisfactory = counter;
            Formulas.add(new Formula(DebttoTangibleNetWorthRatiosatisfactory, "satisfactory", "<=", "0.4", "", "<", "0.6", DebttoTangibleNetWorthRatio));
            counter++;
            int DebttoTangibleNetWorthRatiobad = counter;
            Formulas.add(new Formula(DebttoTangibleNetWorthRatiobad, "bad", "<", "0.4", "", "", "", DebttoTangibleNetWorthRatio));
            counter++;

        int LongTermDebttoEquity = counter;
        Formulas.add(new Formula(LongTermDebttoEquity, "Long-Term Debt to Equity", "LongTermDebttoEquity", "NonCurrentAssets/(IssuedCapital+SharePremium)", "", "", "", FSiD));
        counter++;
            int LongTermDebttoEquityexcellent = counter;
            Formulas.add(new Formula(LongTermDebttoEquityexcellent, "excellent", ">=", "1", "", "", "", LongTermDebttoEquity));
            counter++;
            int LongTermDebttoEquitysatisfactory = counter;
            Formulas.add(new Formula(LongTermDebttoEquitysatisfactory, "satisfactory", "<=", "0.5", "", "<", "1", LongTermDebttoEquity));
            counter++;
            int LongTermDebttoEquitybad = counter;
            Formulas.add(new Formula(LongTermDebttoEquitybad, "bad", "<", "0.5", "", "", "", LongTermDebttoEquity));
            counter++;

        //Liquidity
        int CurrentRatio = counter;
        Formulas.add(new Formula(CurrentRatio, "Current Ratio", "CurrentRatio", "CurrentAssets/CurrentLiabilities", "", "", "", LiD));
        counter++;
            int CurrentRatiosatisfactory = counter;
            Formulas.add(new Formula(CurrentRatiosatisfactory, "satisfactory", ">", "3", "", "", "", CurrentRatio));
            counter++;
            int CurrentRatioexcellent = counter;
            Formulas.add(new Formula(CurrentRatioexcellent, "excellent", "<", "2", "", "<=", "3", CurrentRatio));
            counter++;
            int CurrentRatiogood = counter;
            Formulas.add(new Formula(CurrentRatiogood, "good", "<=", "1.5", "", "<=", "2", CurrentRatio));
            counter++;
            int CurrentRatiobad = counter;
            Formulas.add(new Formula(CurrentRatiobad, "bad", "<", "1,5", "", "", "", CurrentRatio));
            counter++;

        int QuickRatio = counter;
        Formulas.add(new Formula(QuickRatio, "Quick Ratio (Acid Test Ratio)", "QuickRatio", "(CashAndCashEquivalents+TradeAndOtherCurrentReceivables)/CurrentLiabilities", "", "", "", LiD));
        counter++;
        int CashRatio = counter;
        Formulas.add(new Formula(CashRatio, "Cash Ratio", "CashRatio", "CashAndCashEquivalents/CurrentLiabilities", "", "", "", LiD));
        counter++;
            int CashRatioexcellent = counter;
            Formulas.add(new Formula(CashRatioexcellent, "excellent", ">=", "0.2", "", "", "", CashRatio));
            counter++;
            int CashRatiogood = counter;
            Formulas.add(new Formula(CashRatiogood, "good", "<=", "0.1", "", "<", "0.2", CashRatio));
            counter++;
            int CashRatiobad = counter;
            Formulas.add(new Formula(CashRatiobad, "bad", "<", "0.1", "", "", "", CashRatio));
            counter++;

        int NetWorkingCapital = counter;
        Formulas.add(new Formula(NetWorkingCapital, "Net Working Capital", "NetWorkingCapital", "CurrentAssets-CurrentLiabilities", "", "", "money (tousand dollar)", LiD));
        counter++;
        int SalestoNetWorkingCapital = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapital, "Sales to Net Working Capital", "SalestoNetWorkingCapital", "Revenue/((CurrentAssets[1]-CurrentLiabilities[1]+CurrentAssets[0]-CurrentLiabilities[0])/2)", "", "", "", LiD));
        counter++;
            int SalestoNetWorkingCapitalexcellent = counter;
            Formulas.add(new Formula(SalestoNetWorkingCapitalexcellent, "excellent", "<", "3", "", "<", "3", SalestoNetWorkingCapital));
            counter++;
            int SalestoNetWorkingCapitalsatisfactory = counter;
            Formulas.add(new Formula(SalestoNetWorkingCapitalsatisfactory, "satisfactory", "<", "3", "", "", "", SalestoNetWorkingCapital));
            counter++;
            int SalestoNetWorkingCapitalunsatisfactory = counter;
            Formulas.add(new Formula(SalestoNetWorkingCapitalunsatisfactory, "unsatisfactory", "<=", "0", "", "", "", SalestoNetWorkingCapital));
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
            int GrossProfitMargingood = counter;
            Formulas.add(new Formula(GrossProfitMargingood, "good", ">", "0", "", "", "", GrossProfitMargin));
            counter++;
            int GrossProfitMarginunsatisfactory = counter;
            Formulas.add(new Formula(GrossProfitMarginunsatisfactory, "unsatisfactory", "<=", "0", "", "", "", GrossProfitMargin));
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
            int ReturnonInvestmentexcellent = counter;
            Formulas.add(new Formula(ReturnonInvestmentexcellent, "excellent", ">", "4", "", "", "", ReturnonInvestment));
            counter++;
            int ReturnonInvestmentgood = counter;
            Formulas.add(new Formula(ReturnonInvestmentgood, "good", ">", "3,5", "", "", "", ReturnonInvestment));
            counter++;
            int ReturnonInvestmentsatisfactory = counter;
            Formulas.add(new Formula(ReturnonInvestmentsatisfactory, "satisfactory", ">", "0", "", "", "%", ReturnonInvestment));
            counter++;
            int ReturnonInvestmentunsatisfactory = counter;
            Formulas.add(new Formula(ReturnonInvestmentunsatisfactory, "unsatisfactory", "<=", "0", "", "", "%", ReturnonInvestment));
            counter++;

        int ReturnonEquityafterTax = counter;
        Formulas.add(new Formula(ReturnonEquityafterTax, "Return on Equity after Tax", "ReturnonEquityafterTax", "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/(Equity[0]/2+Equity[1]/2)", "", "", "%", PaPiD));
        counter++;
            int ReturnonEquityafterTaxexcellent = counter;
            Formulas.add(new Formula(ReturnonEquityafterTaxexcellent, "excellent", ">", "7", "", "", "", ReturnonEquityafterTax));
            counter++;
            int ReturnonEquityafterTaxgood = counter;
            Formulas.add(new Formula(ReturnonEquityafterTaxgood, "good", ">", "4", "", "", "", ReturnonEquityafterTax));
            counter++;
            int ReturnonEquityafterTaxsatisfactory = counter;
            Formulas.add(new Formula(ReturnonEquityafterTaxsatisfactory, "satisfactory", ">", "0", "", "", "", ReturnonEquityafterTax));
            counter++;
            int ReturnonEquityafterTaxunsatisfactory = counter;
            Formulas.add(new Formula(ReturnonEquityafterTaxunsatisfactory, "unsatisfactory", "<=", "0", "", "", "%", ReturnonEquityafterTax));
            counter++;

        int ReturnoNonAssets = counter;
        Formulas.add(new Formula(ReturnoNonAssets, "Return on Assets", "ReturnoNonAssets", "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/(Assets[0]/2+Assets[1]/2)", "", "", "%", PaPiD));
        counter++;
            int ReturnoNonAssetsexcellent = counter;
            Formulas.add(new Formula(ReturnoNonAssetsexcellent, "excellent", ">", "5", "", "", "", ReturnoNonAssets));
            counter++;
            int ReturnoNonAssetsgood = counter;
            Formulas.add(new Formula(ReturnoNonAssetsgood, "good", ">", "3", "", "", "", ReturnoNonAssets));
            counter++;
            int ReturnoNonAssetssatisfactory = counter;
            Formulas.add(new Formula(ReturnoNonAssetssatisfactory, "satisfactory", ">", "0", "", "", "", ReturnoNonAssets));
            counter++;
            int ReturnoNonAssetsunsatisfactory = counter;
            Formulas.add(new Formula(ReturnoNonAssetsunsatisfactory, "unsatisfactory", "<=", "0", "", "", "", ReturnoNonAssets));
            counter++;

        //Turnover
        int TotalAssetTurnover = counter;
        Formulas.add(new Formula(TotalAssetTurnover, "Total Asset Turnover", "TotalAssetTurnover", "Revenue/(Assets[0]/2+Assets[1]/2)", "", "", "times", TiD));
        counter++;
            int TotalAssetTurnovergood = counter;
            Formulas.add(new Formula(TotalAssetTurnovergood, "good", ">", "6", "", "", "", TotalAssetTurnover));
            counter++;
            int TotalAssetTurnoverunsatisfactory = counter;
            Formulas.add(new Formula(TotalAssetTurnoverunsatisfactory, "unsatisfactory", "<=", "6", "", "", "", TotalAssetTurnover));
            counter++;

        int SalestoFixedAssets = counter;
        Formulas.add(new Formula(SalestoFixedAssets, "Sales to Fixed Assets", "SalestoFixedAssets", "Revenue/(PropertyPlantAndEquipment[0]/2+PropertyPlantAndEquipment[1]/2)", "", "", "times", TiD));
        counter++;
            int SalestoFixedAssetsgood = counter;
            Formulas.add(new Formula(SalestoFixedAssetsgood, "good", ">", "8", "", "", "", SalestoFixedAssets));
            counter++;
            int SalestoFixedAssetsunsatisfactory = counter;
            Formulas.add(new Formula(SalestoFixedAssetsunsatisfactory, "unsatisfactory", "<=", "8", "", "", "", SalestoFixedAssets));
            counter++;

        int CurrentAssetTurnover = counter;
        Formulas.add(new Formula(CurrentAssetTurnover, "Current Asset Turnover", "CurrentAssetTurnover", "Revenue/((CurrentAssets[0]+CurrentAssets[1])/2)", "", "", "times", TiD));
        counter++;
            int CurrentAssetTurnovergood = counter;
            Formulas.add(new Formula(CurrentAssetTurnovergood, "good", ">", "10", "", "", "", CurrentAssetTurnover));
            counter++;
            int CurrentAssetTurnoverunsatisfactory = counter;
            Formulas.add(new Formula(CurrentAssetTurnoverunsatisfactory, "unsatisfactory", "<=", "10", "", "", "times", CurrentAssetTurnover));
            counter++;


        int WorkingCapitalTurnover = counter;
        Formulas.add(new Formula(WorkingCapitalTurnover, "Working Capital Turnover", "WorkingCapitalTurnover", "Revenue/((CurrentAssets[1]-CurrentLiabilities[1]+CurrentAssets[0]-CurrentLiabilities[0])/2)", "", "", "times", TiD));
        counter++;
            int WorkingCapitalTurnovergood = counter;
            Formulas.add(new Formula(WorkingCapitalTurnovergood, "good", ">", "5", "", "", "", WorkingCapitalTurnover));
            counter++;
            int WorkingCapitalTurnoversatisfactory = counter;
            Formulas.add(new Formula(WorkingCapitalTurnoversatisfactory, "unsatisfactory", "<=", "5", "", "", "", WorkingCapitalTurnover));
            counter++;


        int AccountsReceivableTurnover = counter;
        Formulas.add(new Formula(AccountsReceivableTurnover, "Accounts Receivable Turnover (Times)", "AccountsReceivableTurnover", "Revenue/((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)", "", "", "times", TiD));
        counter++;
            int AccountsReceivableTurnovergood = counter;
            Formulas.add(new Formula(AccountsReceivableTurnovergood, "good", ">", "6", "", "", "", AccountsReceivableTurnover));
            counter++;
            int AccountsReceivableTurnoversatisfactory = counter;
            Formulas.add(new Formula(AccountsReceivableTurnoversatisfactory, "unsatisfactory", "<=", "6", "", "", "", AccountsReceivableTurnover));
            counter++;

        int AverageCollectionPeriod = counter;
        Formulas.add(new Formula(AverageCollectionPeriod, "Average Collection Period (Accounts Receivable Turnover in Days)", "AverageCollectionPeriod", "360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/Revenue", "", "", "days", TiD));
        counter++;
            int AverageCollectionPeriodgood = counter;
            Formulas.add(new Formula(AverageCollectionPeriodgood, "good", "<", "60", "", "", "", AverageCollectionPeriod));
            counter++;
            int AverageCollectionPeriodsatisfactory = counter;
            Formulas.add(new Formula(AverageCollectionPeriodsatisfactory, "unsatisfactory", ">=", "60", "", "", "", AverageCollectionPeriod));
            counter++;

        int AccountsPayableTurnover = counter;
        Formulas.add(new Formula(AccountsPayableTurnover, "Accounts Payable Turnover", "AccountsPayableTurnover", "CostOfSales/(((TradeAndOtherCurrentPayables[0]+TradeAndOtherCurrentPayables[1])/2))", "", "", "times", TiD));
        counter++;
            int AccountsPayableTurnovergood = counter;
            Formulas.add(new Formula(AccountsPayableTurnovergood, "good", ">", "6", "", "", "", AccountsPayableTurnover));
            counter++;
            int AccountsPayableTurnoversatisfactory = counter;
            Formulas.add(new Formula(AccountsPayableTurnoversatisfactory, "unsatisfactory", "<=", "6", "", "", "", AccountsPayableTurnover));
            counter++;

        int DaysPayableOutstanding = counter;
        Formulas.add(new Formula(DaysPayableOutstanding, "Days Payable Outstanding", "DaysPayableOutstanding", "(((TradeAndOtherCurrentPayables[0]+TradeAndOtherCurrentPayables[1])/2)*360)/CostOfSales", "", "", "times", TiD));
        counter++;
            int DaysPayableOutstandinggood = counter;
            Formulas.add(new Formula(DaysPayableOutstandinggood, "good", "<", "60", "", "", "", DaysPayableOutstanding));
            counter++;
            int DaysPayableOutstandingsatisfactory = counter;
            Formulas.add(new Formula(DaysPayableOutstandingsatisfactory, "unsatisfactory", ">=", "60", "", "", "times", DaysPayableOutstanding));
            counter++;

        int InventoryTurnover = counter;
        Formulas.add(new Formula(InventoryTurnover, "Inventory Turnover  (Days Inventory Outstanding)", "InventoryTurnover", "CostOfSales/((Inventories[1]+Inventories[0])/2)", "", "", "days", TiD));
        counter++;
            int InventoryTurnovergood = counter;
            Formulas.add(new Formula(InventoryTurnovergood, "good", ">", "6", "", "", "", InventoryTurnover));
            counter++;
            int InventoryTurnoversatisfactory = counter;
            Formulas.add(new Formula(InventoryTurnoversatisfactory, "unsatisfactory", "<=", "6", "", "", "", InventoryTurnover));
            counter++;

        int InventoryTurnoverinDays = counter;
        Formulas.add(new Formula(InventoryTurnoverinDays, "Inventory Turnover in Days", "InventoryTurnoverinDays", "360*((Inventories[1]+Inventories[0])/2)/CostOfSales", "", "", "days", TiD));
        counter++;
            int InventoryTurnoverinDaysgood = counter;
            Formulas.add(new Formula(InventoryTurnoverinDaysgood, "good", "<", "60", "", "", "", InventoryTurnoverinDays));
            counter++;
            int InventoryTurnoverinDayssatisfactory = counter;
            Formulas.add(new Formula(InventoryTurnoverinDayssatisfactory, "unsatisfactory", ">=", "60", "", "", "", InventoryTurnoverinDays));
            counter++;

        int CashTurnover = counter;
        Formulas.add(new Formula(CashTurnover, "Cash Turnover", "CashTurnover", "Revenue/((CashAndCashEquivalents[0]+CashAndCashEquivalents[1])/2)", "", "", "times", TiD));
        counter++;
            int CashTurnovergood = counter;
            Formulas.add(new Formula(CashTurnovergood, "good", ">", "12", "", "", "", CashTurnover));
            counter++;
            int CashTurnoversatisfactory = counter;
            Formulas.add(new Formula(CashTurnoversatisfactory, "unsatisfactory", "<=", "12", "", "", "", CashTurnover));
            counter++;


        int OperatingCycle = counter;
        Formulas.add(new Formula(OperatingCycle, "Operating Cycle", "OperatingCycle", "360*((Inventories[1]+Inventories[0])/2)/CostOfSales+360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/Revenue", "", "", "days", TiD));
        counter++;
            int OperatingCyclegood = counter;
            Formulas.add(new Formula(OperatingCyclegood, "good", "<", "90", "", "", "", OperatingCycle));
            counter++;
            int OperatingCyclesatisfactory = counter;
            Formulas.add(new Formula(OperatingCyclesatisfactory, "unsatisfactory", ">=", "90", "", "", "", OperatingCycle));
            counter++;

        int CashConversionCycle = counter;
        Formulas.add(new Formula(CashConversionCycle, "Cash Conversion Cycle", "CashConversionCycle", "360*((Inventories[1]+Inventories[0])/2)/CostOfSales+360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/Revenue-(((TradeAndOtherCurrentPayables[0]+TradeAndOtherCurrentPayables[1])/2)*360)/CostOfSales", "", "", "days", TiD));
        counter++;
            int CashConversionCyclegood = counter;
            Formulas.add(new Formula(CashConversionCyclegood, "good", "<", "60", "", "", "", CashConversionCycle));
            counter++;
            int CashConversionCyclesatisfactory = counter;
            Formulas.add(new Formula(CashConversionCyclesatisfactory, "unsatisfactory", ">=", "60", "", "", "", CashConversionCycle));
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