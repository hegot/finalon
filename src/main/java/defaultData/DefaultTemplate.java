package defaultData;

import database.template.DbItemHandler;
import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DefaultTemplate {
    private static int counter = 1;

    public static ObservableList<Item> getTpl() {
        int lastId = DbItemHandler.getLastId() + 1;
        ObservableList<Item> Items = FXCollections.observableArrayList();
        counter = lastId;
        Items.add(new Item(counter, "Default Template", "DefaultTemplate", true, false, 0, 0));
        counter++;
        int SOFP = counter;
        Items.add(new Item(SOFP, "Statement of Financial Position \n (Balance Sheet)", "StatementOfFinancialPosition", true, false, lastId, 0));
        counter++;
        int SOCI = counter;
        Items.add(new Item(SOCI, "Statement of Comprehensive Income \n (P&L Statement)", "StatementOfComprehensiveIncome", true, false, lastId, 0));
        counter++;
        int CFS = counter;
        Items.add(new Item(CFS, "Cash Flow Statement", "CashFlowStatement", true, false, lastId, 0));
        counter++;
        int OD = counter;
        Items.add(new Item(OD, "Other Data", "OtherData", true, false, lastId, 0));
        counter++;

        int AssetsGeneral = counter;
        Items.add(new Item(AssetsGeneral, "Assets", "AssetsGeneral", true, false, SOFP, 2));
        counter++;
        int NonCurrentAssets = counter;
        Items.add(new Item(NonCurrentAssets, "Non-current assets", "NonCurrentAssets", true, false, AssetsGeneral, 3));
        counter++;
        int GeneralCurrentAssets = counter;
        Items.add(new Item(GeneralCurrentAssets, "Current assets", "GeneralCurrentAssets", true, false, AssetsGeneral, 3));
        counter++;

        //Equity And Liabilities
        int EquityAndLiabilities = counter;
        Items.add(new Item(EquityAndLiabilities, "Equity And Liabilities", "EquityAndLiabilities", true, false, SOFP, 1));
        counter++;

        int EquityGeneral = counter;
        Items.add(new Item(EquityGeneral, "Equity", "EquityGeneral", true, false, EquityAndLiabilities, 2));
        counter++;

        int LiabilitiesGeneral = counter;
        Items.add(new Item(LiabilitiesGeneral, "Liabilities", "LiabilitiesGeneral", true, false, EquityAndLiabilities, 2));
        counter++;
        int NonCurrentLiabilities = counter;
        Items.add(new Item(NonCurrentLiabilities, "Non-current liabilities", "NonCurrentLiabilities", true, false, LiabilitiesGeneral, 3));
        counter++;
        int CurrentLiabilitiesGeneral = counter;
        Items.add(new Item(CurrentLiabilitiesGeneral, "Current liabilities", "CurrentLiabilitiesGeneral", true, false, LiabilitiesGeneral, 3));
        counter++;


        //Non-current assets
        int PropertyPlantAndEquipment = counter;
        Items.add(new Item(PropertyPlantAndEquipment, "Property, plant and equipment", "PropertyPlantAndEquipment", true, false, NonCurrentAssets, 4));
        counter++;
        int InvestmentProperty = counter;
        Items.add(new Item(InvestmentProperty, "Investment property", "InvestmentProperty", true, false, NonCurrentAssets, 4));
        counter++;
        int GoodwillGeneral = counter;
        Items.add(new Item(GoodwillGeneral, "Goodwill", "GoodwillGeneral", true, false, NonCurrentAssets, 4));
        counter++;
        int IntangibleAssetsOtherThanGoodwill = counter;
        Items.add(new Item(IntangibleAssetsOtherThanGoodwill, "Intangible assets other than goodwill", "IntangibleAssetsOtherThanGoodwill", true, false, NonCurrentAssets, 4));
        counter++;
        int InvestmentAccountedForUsingEquityMethod = counter;
        Items.add(new Item(InvestmentAccountedForUsingEquityMethod, "Investment accounted for using equity method", "InvestmentAccountedForUsingEquityMethod", true, false, NonCurrentAssets, 4));
        counter++;
        int InvestmentsInSubsidiariesJointVenturesAndAssociates = counter;
        Items.add(new Item(InvestmentsInSubsidiariesJointVenturesAndAssociates, "Investments in subsidiaries, joint ventures and associates", "InvestmentsInSubsidiariesJointVenturesAndAssociates", true, false, NonCurrentAssets, 4));
        counter++;
        int NoncurrentBiologicalAssets = counter;
        Items.add(new Item(NoncurrentBiologicalAssets, "Non-current biological assets", "NoncurrentBiologicalAssets", true, false, NonCurrentAssets, 4));
        counter++;
        int NoncurrentReceivables = counter;
        Items.add(new Item(NoncurrentReceivables, "Trade and other non-current receivables", "NoncurrentReceivables", true, false, NonCurrentAssets, 4));
        counter++;
        int NoncurrentInventories = counter;
        Items.add(new Item(NoncurrentInventories, "Non-current inventories", "NoncurrentInventories", true, false, NonCurrentAssets, 4));
        counter++;
        int DeferredTaxAssets = counter;
        Items.add(new Item(DeferredTaxAssets, "Deferred tax assets", "DeferredTaxAssets", true, false, NonCurrentAssets, 4));
        counter++;
        int CurrentTaxAssetsNoncurrent = counter;
        Items.add(new Item(CurrentTaxAssetsNoncurrent, "Current tax assets, non-current", "CurrentTaxAssetsNoncurrent", true, false, NonCurrentAssets, 4));
        counter++;
        int OtherNoncurrentFinancialAssets = counter;
        Items.add(new Item(OtherNoncurrentFinancialAssets, "Other non-current financial assets", "OtherNoncurrentFinancialAssets", true, false, NonCurrentAssets, 4));
        counter++;
        int OtherNoncurrentNonfinancialAssets = counter;
        Items.add(new Item(OtherNoncurrentNonfinancialAssets, "Other non-current non-financial assets", "OtherNoncurrentNonfinancialAssets", true, false, NonCurrentAssets, 4));
        counter++;
        int NonCurrentNopnCashAssetsPleggedAsCollateral = counter;
        Items.add(new Item(NonCurrentNopnCashAssetsPleggedAsCollateral, "Non-current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "NonCurrentNopnCashAssetsPleggedAsCollateral", true, false, NonCurrentAssets, 4));
        counter++;
        //Current Assets
        int CurrentInventories = counter;
        Items.add(new Item(CurrentInventories, "Current inventories", "CurrentInventories", true, false, GeneralCurrentAssets, 4));
        counter++;
        int TradeAndOtherCurrentReceivables = counter;
        Items.add(new Item(TradeAndOtherCurrentReceivables, "Trade and other current receivables", "TradeAndOtherCurrentReceivables", true, false, GeneralCurrentAssets, 4));
        counter++;
        int CurrentTaxAssetsCurrent = counter;
        Items.add(new Item(CurrentTaxAssetsCurrent, "Current tax assets, current", "CurrentTaxAssetsCurrent", true, false, GeneralCurrentAssets, 4));
        counter++;
        int CurrentBiologicalAssets = counter;
        Items.add(new Item(CurrentBiologicalAssets, "Current biological assets", "CurrentBiologicalAssets", true, false, GeneralCurrentAssets, 4));
        counter++;
        int OtherCurrentFinancialAssets = counter;
        Items.add(new Item(OtherCurrentFinancialAssets, "Other current financial assets", "OtherCurrentFinancialAssets", true, false, GeneralCurrentAssets, 4));
        counter++;
        int OtherCurrentNonfinancialAssets = counter;
        Items.add(new Item(OtherCurrentNonfinancialAssets, "Other current non-financial assets", "OtherCurrentNonfinancialAssets", true, false, GeneralCurrentAssets, 4));
        counter++;
        int CashAndCashEquivalents = counter;
        Items.add(new Item(CashAndCashEquivalents, "Cash and cash equivalents", "CashAndCashEquivalents", true, false, GeneralCurrentAssets, 4));
        counter++;
        int CurrentNonCashCollat = counter;
        Items.add(new Item(CurrentNonCashCollat, "Current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "CurrentNonCashCollat", true, false, GeneralCurrentAssets, 4));
        counter++;
        int NonAssetsDispGroup = counter;
        Items.add(new Item(NonAssetsDispGroup, "Non-current assets or disposal groups classified as held for sale or as held for distribution to owners", "NonAssetsDispGroup", true, false, GeneralCurrentAssets, 4));
        counter++;

        //Equity
        int IssuedCapital = counter;
        Items.add(new Item(IssuedCapital, "Issued (share) capital", "IssuedCapital", true, false, EquityGeneral, 4));
        counter++;
        int SharePremium = counter;
        Items.add(new Item(SharePremium, "Share premium", "SharePremium", true, false, EquityGeneral, 4));
        counter++;
        int TreasuryShares = counter;
        Items.add(new Item(TreasuryShares, "Treasury shares", "TreasuryShares", false, false, EquityGeneral, 4));
        counter++;
        int OtherEquityInterest = counter;
        Items.add(new Item(OtherEquityInterest, "Other equity interest", "OtherEquityInterest", true, false, EquityGeneral, 4));
        counter++;
        int OtherReserves = counter;
        Items.add(new Item(OtherReserves, "Other reserves", "OtherReserves", true, false, EquityGeneral, 4));
        counter++;
        int RetainedEarnings = counter;
        Items.add(new Item(RetainedEarnings, "Retained earnings", "RetainedEarnings", true, false, EquityGeneral, 4));
        counter++;
        int NoncontrollingInterests = counter;
        Items.add(new Item(NoncontrollingInterests, "Non-controlling interests", "NoncontrollingInterests", true, true, EquityGeneral, 4));
        counter++;


        //Liabilities

        //Non-current liabilities
        int NoncurrentProvisionsForEmployeeBenefits = counter;
        Items.add(new Item(NoncurrentProvisionsForEmployeeBenefits, "Non-current provisions for employee benefits", "NoncurrentProvisionsForEmployeeBenefits", true, false, NonCurrentLiabilities, 4));
        counter++;
        int OtherLongtermProvisions = counter;
        Items.add(new Item(OtherLongtermProvisions, "Other non-current provisions", "OtherLongtermProvisions", true, false, NonCurrentLiabilities, 4));
        counter++;
        int NoncurrentPayables = counter;
        Items.add(new Item(NoncurrentPayables, "Trade and other non-current payables", "NoncurrentPayables", true, false, NonCurrentLiabilities, 4));
        counter++;
        int DeferredTaxLiabilities = counter;
        Items.add(new Item(DeferredTaxLiabilities, "Deferred tax liabilities", "DeferredTaxLiabilities", true, false, NonCurrentLiabilities, 4));
        counter++;
        int CurrentTaxLiabilitiesNoncurrent = counter;
        Items.add(new Item(CurrentTaxLiabilitiesNoncurrent, "Current tax liabilities, non-current", "CurrentTaxLiabilitiesNoncurrent", true, false, NonCurrentLiabilities, 4));
        counter++;
        int OtherNoncurrentFinancialLiabilities = counter;
        Items.add(new Item(OtherNoncurrentFinancialLiabilities, "Other non-current financial liabilities", "OtherNoncurrentFinancialLiabilities", true, false, NonCurrentLiabilities, 4));
        counter++;
        int OtherNoncurrentNonfinancialLiabilities = counter;
        Items.add(new Item(OtherNoncurrentNonfinancialLiabilities, "Other non-current non-financial liabilities", "OtherNoncurrentNonfinancialLiabilities", true, false, NonCurrentLiabilities, 4));
        counter++;

        //Current liabilities
        int CurrentProvisionsForEmployeeBenefits = counter;
        Items.add(new Item(CurrentProvisionsForEmployeeBenefits, "Current provisions for employee benefits", "CurrentProvisionsForEmployeeBenefits", true, false, CurrentLiabilitiesGeneral, 4));
        counter++;
        int OtherShorttermProvisions = counter;
        Items.add(new Item(OtherShorttermProvisions, "Other current provisions", "OtherShorttermProvisions", true, false, CurrentLiabilitiesGeneral, 4));
        counter++;
        int TradeAndOtherCurrentPayables = counter;
        Items.add(new Item(TradeAndOtherCurrentPayables, "Trade and other current payables", "TradeAndOtherCurrentPayables", true, false, CurrentLiabilitiesGeneral, 4));
        counter++;
        int CurrentTaxLiabilitiesCurrent = counter;
        Items.add(new Item(CurrentTaxLiabilitiesCurrent, "Current tax liabilities, current", "CurrentTaxLiabilitiesCurrent", true, false, CurrentLiabilitiesGeneral, 4));
        counter++;
        int OtherCurrentFinancialLiabilities = counter;
        Items.add(new Item(OtherCurrentFinancialLiabilities, "Other current financial liabilities", "OtherCurrentFinancialLiabilities", true, false, CurrentLiabilitiesGeneral, 4));
        counter++;
        int OtherCurrentNonfinancialLiabilities = counter;
        Items.add(new Item(OtherCurrentNonfinancialLiabilities, "Other current non-financial liabilities", "OtherCurrentNonfinancialLiabilities", true, false, CurrentLiabilitiesGeneral, 4));
        counter++;
        int LiabilitiesIncludedInDisposalGroupsClassifiedAsHeldForSale = counter;
        Items.add(new Item(LiabilitiesIncludedInDisposalGroupsClassifiedAsHeldForSale, "Liabilities included in disposal groups classified as held for sale", "LiabilitiesIncludedInDisposalGroupsClassifiedAsHeldForSale", true, false, CurrentLiabilitiesGeneral, 4));
        counter++;


        //Statement of Comprehensive Income (P&L Statement)

        int ComprehensiveIncomeGeneral = counter;
        counter++;
        int ProfitLossGeneral = counter;
        counter++;
        int ProfitLossFromContinuingOperations = counter;
        counter++;
        int ProfitLossFromDiscontinuedOperations = counter;
        counter++;
        int ProfitLossBeforeTax = counter;
        counter++;
        int ProfitLossFromOperatingActivities = counter;
        counter++;
        int GrossProfit = counter;
        counter++;


        int RevenueGeneral = counter;
        Items.add(new Item(RevenueGeneral, "RevenueGeneral", "RevenueGeneral", true, false, GrossProfit, 4));
        counter++;
        int CostOfSales = counter;
        Items.add(new Item(CostOfSales, "Cost of sales", "CostOfSales", false, false, GrossProfit, 4));
        counter++;
        Items.add(new Item(GrossProfit, "Gross profit", "GrossProfit", true, true, ProfitLossFromOperatingActivities, 3));


        int OtherIncomeGeneral = counter;
        Items.add(new Item(OtherIncomeGeneral, "Other income", "OtherIncomeGeneral", true, false, ProfitLossFromOperatingActivities, 4));
        counter++;
        int DistributionCostsGeneral = counter;
        Items.add(new Item(DistributionCostsGeneral, "Distribution costs", "DistributionCostsGeneral", false, false, ProfitLossFromOperatingActivities, 4));
        counter++;
        int AdministrativeExpense = counter;
        Items.add(new Item(AdministrativeExpense, "Administrative expense", "AdministrativeExpense", false, false, ProfitLossFromOperatingActivities, 4));
        counter++;
        int OtherExpenseByFunction = counter;
        Items.add(new Item(OtherExpenseByFunction, "Other expense", "OtherExpenseByFunction", false, false, ProfitLossFromOperatingActivities, 4));
        counter++;
        int OtherGainsLosses = counter;
        Items.add(new Item(OtherGainsLosses, "Other gains (losses)", "OtherGainsLosses", true, false, ProfitLossFromOperatingActivities, 4));
        counter++;
        Items.add(new Item(ProfitLossFromOperatingActivities, "Profit (loss) from operating activities", "ProfitLossFromOperatingActivities", true, true, ProfitLossBeforeTax, 3));


        int DiffDivPayAndNoncash = counter;
        Items.add(new Item(DiffDivPayAndNoncash, "Difference between carrying amount of dividends payable and carrying amount of non-cash assets distributed", "DiffDivPayAndNoncash", true, false, ProfitLossBeforeTax, 4));
        counter++;
        int GainsLossesOnNetMonetaryPosition = counter;
        Items.add(new Item(GainsLossesOnNetMonetaryPosition, "Gains (losses) on net monetary position", "GainsLossesOnNetMonetaryPosition", true, false, ProfitLossBeforeTax, 4));
        counter++;
        int GainDerecogAmort = counter;
        Items.add(new Item(GainDerecogAmort, "Gain (loss) arising from derecognition of financial assets measured at amortised cost", "GainDerecogAmort", true, false, ProfitLossBeforeTax, 4));
        counter++;
        int FinanceIncome = counter;
        Items.add(new Item(FinanceIncome, "Finance income", "FinanceIncome", true, false, ProfitLossBeforeTax, 4));
        counter++;
        int FinanceCosts = counter;
        Items.add(new Item(FinanceCosts, "Finance costs", "FinanceCosts", false, false, ProfitLossBeforeTax, 4));
        counter++;
        int ImpairmentLoss = counter;
        Items.add(new Item(ImpairmentLoss, "Impairment loss (impairment gain and reversal of impairment loss) determined in accordance with IFRS 9", "ImpairmentLoss", false, false, ProfitLossBeforeTax, 4));
        counter++;
        int ShareAssociates = counter;
        Items.add(new Item(ShareAssociates, "Share of profit (loss) of associates and joint ventures accounted for using equity method", "ShareAssociates", true, false, ProfitLossBeforeTax, 4));
        counter++;
        int OtherAssociates = counter;
        Items.add(new Item(OtherAssociates, "Other income (expense) from subsidiaries, jointly controlled entities and associates", "OtherAssociates", true, false, ProfitLossBeforeTax, 4));
        counter++;
        int GainDiffFinAssets = counter;
        Items.add(new Item(GainDiffFinAssets, "Gains (losses) arising from difference between previous carrying amount and fair value of financial assets reclassified as measured at fair value", "GainDiffFinAssets", true, false, ProfitLossBeforeTax, 4));
        counter++;
        int CumulatReclassFinAss = counter;
        Items.add(new Item(CumulatReclassFinAss, "Cumulative gain (loss) previously recognised in other comprehensive income arising from reclassification of financial assets out of fair value through other comprehensive income into fair value through profit or loss measurement category", "CumulatReclassFinAss", true, false, ProfitLossBeforeTax, 4));
        counter++;
        int HedgeOfGroup = counter;
        Items.add(new Item(HedgeOfGroup, "Hedging gains (losses) for hedge of group of items with offsetting risk positions", "HedgeOfGroup", true, false, ProfitLossBeforeTax, 4));
        counter++;
        Items.add(new Item(ProfitLossBeforeTax, "Profit (loss) before tax", "ProfitLossBeforeTax", true, true, ProfitLossFromContinuingOperations, 3));


        Items.add(new Item(ProfitLossFromDiscontinuedOperations, "Profit (loss) from discontinued operations", "ProfitLossFromDiscontinuedOperations", true, false, ProfitLossGeneral, 3));


        int IncomeTaxExpenseContinuingOperations = counter;
        Items.add(new Item(IncomeTaxExpenseContinuingOperations, "Income tax expense (from continuing operations)", "IncomeTaxExpenseContinuingOperations", false, false, ProfitLossFromContinuingOperations, 4));
        counter++;
        Items.add(new Item(ProfitLossFromContinuingOperations, "Profit (loss) from continuing operations", "ProfitLossFromContinuingOperations", true, true, ProfitLossGeneral, 3));

        Items.add(new Item(ProfitLossGeneral, "Profit (loss)", "ProfitLossGeneral", true, true, ComprehensiveIncomeGeneral, 3));

        int OtherComprehensiveIncome = counter;
        Items.add(new Item(OtherComprehensiveIncome, "Other comprehensive income", "OtherComprehensiveIncome", true, false, ComprehensiveIncomeGeneral, 4));
        counter++;
        Items.add(new Item(ComprehensiveIncomeGeneral, "Comprehensive Income", "ComprehensiveIncomeGeneral", true, true, SOCI, 2));


        //Cash Flow Statement
        int OperatingActivitiesGeneral = counter;
        Items.add(new Item(OperatingActivitiesGeneral, "Operating activities", "OperatingActivitiesGeneral", true, false, CFS, 2));
        counter++;
        int InvestingActivitiesGeneral = counter;
        Items.add(new Item(InvestingActivitiesGeneral, "Investing activities", "InvestingActivitiesGeneral", true, false, CFS, 2));
        counter++;
        int FinancingActivitiesGeneral = counter;
        Items.add(new Item(FinancingActivitiesGeneral, "Financing activities", "FinancingActivitiesGeneral", true, false, CFS, 2));
        counter++;

        //Operating activities
        int CashReceiptsFromCustomers = counter;

        Items.add(new Item(CashReceiptsFromCustomers, "Cash receipts from customers", "CashReceiptsFromCustomers", true, false, OperatingActivitiesGeneral, 4));
        counter++;
        int CashPaidToSuppliersAndEmployees = counter;
        Items.add(new Item(CashPaidToSuppliersAndEmployees, "Cash paid to suppliers and employees", "CashPaidToSuppliersAndEmployees", false, false, OperatingActivitiesGeneral, 4));
        counter++;
        int CashGeneratedFromOperations = counter;
        Items.add(new Item(CashGeneratedFromOperations, "Cash generated from operations", "CashGeneratedFromOperations", true, false, OperatingActivitiesGeneral, 4));
        counter++;
        int InterestPaidGeneral = counter;
        Items.add(new Item(InterestPaidGeneral, "Interest paid", "InterestPaidGeneral", false, false, OperatingActivitiesGeneral, 4));
        counter++;
        int IncomeTaxesPaid = counter;
        Items.add(new Item(IncomeTaxesPaid, "Income taxes paid", "IncomeTaxesPaid", false, false, OperatingActivitiesGeneral, 4));
        counter++;
        int NetCashFromOperatingActivities = counter;
        Items.add(new Item(NetCashFromOperatingActivities, "Net cash from operating activities", "NetCashFromOperatingActivities", true, true, OperatingActivitiesGeneral, 4));
        counter++;

        //Investing activities
        int AcquisitionOfSubsidiaryNetOfCashAcquired = counter;
        Items.add(new Item(AcquisitionOfSubsidiaryNetOfCashAcquired, "Acquisition of subsidiary, net of cash acquired", "AcquisitionOfSubsidiaryNetOfCashAcquired", false, false, InvestingActivitiesGeneral, 4));
        counter++;
        int PurchaseOfPropertyPlantAndEquipment = counter;
        Items.add(new Item(PurchaseOfPropertyPlantAndEquipment, "Purchase of property, plant and equipment", "PurchaseOfPropertyPlantAndEquipment", false, false, InvestingActivitiesGeneral, 4));
        counter++;
        int ProceedsFromSaleOfEquipment = counter;
        Items.add(new Item(ProceedsFromSaleOfEquipment, "Proceeds from sale of equipment", "ProceedsFromSaleOfEquipment", true, false, InvestingActivitiesGeneral, 4));
        counter++;
        int InterestReceived = counter;
        Items.add(new Item(InterestReceived, "Interest received", "InterestReceived", true, false, InvestingActivitiesGeneral, 4));
        counter++;
        int DividendsReceived = counter;
        Items.add(new Item(DividendsReceived, "Dividends received", "DividendsReceived", true, false, InvestingActivitiesGeneral, 4));
        counter++;
        int NetCashUsedInInvestingActivities = counter;
        Items.add(new Item(NetCashUsedInInvestingActivities, "Net cash used in investing activities", "NetCashUsedInInvestingActivities", true, true, InvestingActivitiesGeneral, 4));
        counter++;

        //Financing activities
        int ProceedsFromIssueOfShareCapital = counter;
        Items.add(new Item(ProceedsFromIssueOfShareCapital, "Proceeds from issue of share capital", "ProceedsFromIssueOfShareCapital", true, false, FinancingActivitiesGeneral, 4));
        counter++;
        int ProceedsFromLongTermBorrowings = counter;
        Items.add(new Item(ProceedsFromLongTermBorrowings, "Proceeds from long-term borrowings", "ProceedsFromLongTermBorrowings", true, false, FinancingActivitiesGeneral, 4));
        counter++;
        int PaymentOfFinanceLeaseLiabilities = counter;
        Items.add(new Item(PaymentOfFinanceLeaseLiabilities, "Payment of finance lease liabilities", "PaymentOfFinanceLeaseLiabilities", false, false, FinancingActivitiesGeneral, 4));
        counter++;
        int DividendsPaid = counter;
        Items.add(new Item(DividendsPaid, "Dividends paid", "DividendsPaid", false, false, FinancingActivitiesGeneral, 4));
        counter++;
        int NetCashUsedInFinancingActivities = counter;
        Items.add(new Item(NetCashUsedInFinancingActivities, "Net cash used in financing activities", "NetCashUsedInFinancingActivities", true, true, FinancingActivitiesGeneral, 4));
        counter++;
        int EffectOfExchangeRateChanges = counter;
        Items.add(new Item(EffectOfExchangeRateChanges, "Effect of exchange rate changes", "EffectOfExchangeRateChanges", true, false, FinancingActivitiesGeneral, 4));
        counter++;
        int NetIncreaseInCashAndCashEquivalents = counter;
        Items.add(new Item(NetIncreaseInCashAndCashEquivalents, "Net increase in cash and cash equivalents", "NetIncreaseInCashAndCashEquivalents", true, true, FinancingActivitiesGeneral, 4));
        counter++;
        int CashAndCashEquivalentsAtBeginningOfPeriod = counter;
        Items.add(new Item(CashAndCashEquivalentsAtBeginningOfPeriod, "Cash and cash equivalents at beginning of period", "CashAndCashEquivalentsAtBeginningOfPeriod", true, false, FinancingActivitiesGeneral, 4));
        counter++;
        int CashAndCashEquivalentsAtEndOfPeriod = counter;
        Items.add(new Item(CashAndCashEquivalentsAtEndOfPeriod, "Cash and cash equivalents at end of period", "CashAndCashEquivalentsAtEndOfPeriod", true, false, FinancingActivitiesGeneral, 4));

        counter++;

        //Other Data
        int NumberOfEmployees = counter;
        Items.add(new Item(NumberOfEmployees, "Number of employees", "NumberOfEmployees", true, false, OD, 4));
        counter++;
        int AmortizationGeneral = counter;
        Items.add(new Item(AmortizationGeneral, "Depreciation and amortisation expense", "AmortizationGeneral", true, false, OD, 4));


        return Items;
    }

}