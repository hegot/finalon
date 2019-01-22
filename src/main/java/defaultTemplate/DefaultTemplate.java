package defaultTemplate;

import entities.Item;
import entities.Sheet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DefaultTemplate {
    public static ArrayList getSheets() {
        ArrayList<Sheet> Sheets = new ArrayList<Sheet>();
        Sheets.add(new Sheet(0, "Statement of Financial Position (Balance Sheet)", 0, getBalanceSheetItems()));
        Sheets.add(new Sheet(1, "Statement of Comprehensive Income (P&L Statement)", 0, getPLStatementItems()));
        return Sheets;
    }



    public static ObservableList<Item> getBalanceSheetItems() {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        Items.add(new Item(1, "Assets", "Assets",  true, 0, 0));
        Items.add(new Item(2, "Non-current assets", "NonCurrentAssets",  true, 1, 0));
        Items.add(new Item(3, "Current assets", "CurrentAssets", true, 1, 0));

        //Non-current assets
        Items.add(new Item(9, "Property, plant and equipment", "PropertyPlantAndEquipment",  true, 2, 0));
        Items.add(new Item(10, "Investment property", "Investmentproperty", true, 2, 0));
        Items.add(new Item(11, "Goodwill", "Goodwill",  true, 2, 0));
        Items.add(new Item(12, "Intangible assets other than goodwill", "IntangibleAssetsOtherThanGoodwill",  true, 2, 0));
        Items.add(new Item(13, "Investment accounted for using equity method", "InvestmentAccountedForUsingEquityMethod",  true, 2, 0));
        Items.add(new Item(14, "Investments in subsidiaries, joint ventures and associates", "InvestmentsInSubsidiariesJointVenturesAndAssociates",  true, 0, 0));
        Items.add(new Item(15, "Non-current biological assets", "NoncurrentBiologicalAssets", true, 2, 0));
        Items.add(new Item(16, "Trade and other non-current receivables", "NoncurrentReceivables",  true, 2, 0));
        Items.add(new Item(17, "Non-current inventories", "NoncurrentInventories", true, 2, 0));
        Items.add(new Item(18, "Deferred tax assets", "DeferredTaxAssets",  true, 2, 0));
        Items.add(new Item(19, "Current tax assets, non-current", "CurrentTaxAssetsNoncurrent",  true, 2, 0));
        Items.add(new Item(20, "Other non-current financial assets", "OtherNoncurrentFinancialAssets",  true, 2, 0));
        Items.add(new Item(21, "Other non-current non-financial assets", "OtherNoncurrentNonfinancialAssets",  true, 2, 0));
        Items.add(new Item(22, "Non-current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "InvestmentAccountedForUsingEquityMethod",  true, 2, 0));

        //Current Assets
        Items.add(new Item(23, "Current inventories", "Inventories", true, 3, 0));
        Items.add(new Item(24, "Trade and other current receivables", "TradeAndOtherCurrentReceivables",  true, 3, 0));
        Items.add(new Item(25, "Current tax assets, current", "CurrentTaxAssetsCurrent",  true, 3, 0));
        Items.add(new Item(26, "Current biological assets", "CurrentBiologicalAssets",  true, 3, 0));
        Items.add(new Item(27, "Other current financial assets", "OtherCurrentFinancialAssets",  true, 3, 0));
        Items.add(new Item(28, "Other current non-financial assets", "OtherCurrentNonfinancialAssets",  true, 3, 0));
        Items.add(new Item(29, "Cash and cash equivalents", "CashAndCashEquivalents",  true, 3, 0));
        Items.add(new Item(30, "Current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "CurrentNoncashAssetsPledgedAsCollateralForWhichTransfereeHasRightByContractOrCustomToSellOrRepledgeCollateral",  true, 3, 0));
        Items.add(new Item(31, "Non-current assets or disposal groups classified as held for sale or as held for distribution to owners", "NoncurrentAssetsOrDisposalGroupsClassifiedAsHeldForSaleOrAsHeldForDistributionToOwners", true, 3, 0));

        //Equity And Liabilities
        Items.add(new Item(4, "Equity And Liabilities", "EquityAndLiabilities", true, 0, 0));
        Items.add(new Item(5, "Equity", "Equity", true, 4, 0));
        Items.add(new Item(6, "Liabilities", "Liabilities", true, 4, 0));
        Items.add(new Item(7, "Non-current liabilities", "NonCurrentLiabilities", true, 6, 0));
        Items.add(new Item(8, "Current liabilities", "CurrentLiabilities", true, 6, 0));

        //Equity
        Items.add(new Item(37, "Issued (share) capital", "IssuedCapital",  true, 5, 0));
        Items.add(new Item(38, "Share premium", "SharePremium",  true, 5, 0));
        Items.add(new Item(39, "Treasury shares", "TreasuryShares",  true, 5, 0));
        Items.add(new Item(40, "Other equity interest", "OtherEquityInterest",  true, 5, 0));
        Items.add(new Item(41, "Other reserves", "OtherReserves",  true, 5, 0));
        Items.add(new Item(42, "Retained earnings", "RetainedEarnings",  true, 5, 0));
        Items.add(new Item(43, "Non-controlling interests", "NoncontrollingInterests",  true, 5, 0));


        //Liabilities

        //Non-current liabilities

        Items.add(new Item(44, "Non-current provisions for employee benefits", "NoncurrentProvisionsForEmployeeBenefits",  true, 7, 0));
        Items.add(new Item(45, "Other non-current provisions", "OtherLongtermProvisions", true, 7, 0));
        Items.add(new Item(46, "Trade and other non-current payables", "NoncurrentPayables",  true, 7, 0));
        Items.add(new Item(47, "Deferred tax liabilities", "DeferredTaxLiabilities",  true, 7, 0));
        Items.add(new Item(48, "Current tax liabilities, non-current", "CurrentTaxLiabilitiesNoncurrent",  true, 7, 0));
        Items.add(new Item(49, "Other non-current financial liabilities", "OtherNoncurrentFinancialLiabilities",  true, 7, 0));
        Items.add(new Item(50, "Other non-current non-financial liabilities", "OtherNoncurrentNonfinancialLiabilities",  true, 7, 0));


        //Current liabilities
        Items.add(new Item(51, "Current provisions for employee benefits", "CurrentProvisionsForEmployeeBenefits", true, 8, 0));
        Items.add(new Item(52, "Other current provisions", "OtherShorttermProvisions", true, 8, 0));
        Items.add(new Item(53, "Trade and other current payables", "TradeAndOtherCurrentPayables", true, 8, 0));
        Items.add(new Item(54, "Current tax liabilities, current", "CurrentTaxLiabilitiesCurrent", true, 8, 0));
        Items.add(new Item(55, "Other current financial liabilities", "OtherCurrentFinancialLiabilities",  true, 8, 0));
        Items.add(new Item(56, "Other current non-financial liabilities", "OtherCurrentNonfinancialLiabilities",  true, 8, 0));
        Items.add(new Item(57, "Liabilities included in disposal groups classified as held for sale", "LiabilitiesIncludedInDisposalGroupsClassifiedAsHeldForSale", true, 8, 0));

        return Items;
    }








    public static ObservableList<Item> getPLStatementItems() {
        ObservableList<Item> Items = FXCollections.observableArrayList();

        //Statement of Comprehensive Income (P&L Statement)
        Items.add(new Item(58, "Revenue", "Revenue", true, 0, 1));
        Items.add(new Item(59, "Cost of sales", "CostOfSales",  true, 0, 1));
        Items.add(new Item(60, "Gross profit", "GrossProfit",  true, 0, 1));
        Items.add(new Item(61, "Other income", "OtherIncome",  true, 0, 1));
        Items.add(new Item(62, "Distribution costs", "DistributionCosts",  true, 0, 1));
        Items.add(new Item(63, "Administrative expense", "AdministrativeExpense",  true, 0, 1));
        Items.add(new Item(64, "Other expense", "OtherExpenseByFunction",  true, 0, 1));
        Items.add(new Item(65, "Other gains (losses)", "OtherGainsLosses",  true, 0, 1));
        Items.add(new Item(66, "Profit (loss) from operating activities", "ProfitLossFromOperatingActivities",  true, 0, 1));
        Items.add(new Item(67, "Difference between carrying amount of dividends payable and carrying amount of non-cash assets distributed", "DifferenceBetweenCarryingAmountOfDividendsPayableAndCarryingAmountOfNoncashAssetsDistributed",  true, 0, 1));
        Items.add(new Item(68, "Gains (losses) on net monetary position", "GainsLossesOnNetMonetaryPosition", true, 0, 1));
        Items.add(new Item(69, "Gain (loss) arising from derecognition of financial assets measured at amortised cost", "GainLossArisingFromDerecognitionOfFinancialAssetsMeasuredAtAmortisedCost",  true, 0, 1));
        Items.add(new Item(70, "Finance income", "FinanceIncome",  true, 0, 1));
        Items.add(new Item(71, "Finance costs", "FinanceCosts",  true, 0, 1));
        Items.add(new Item(72, "Impairment loss (impairment gain and reversal of impairment loss) determined in accordance with IFRS 9", "ImpairmentLossImpairmentGainAndReversalOfImpairmentLossDeterminedInAccordanceWithIFRS9",  true, 0, 1));
        Items.add(new Item(73, "Share of profit (loss) of associates and joint ventures accounted for using equity method", "ShareOfProfitLossOfAssociatesAndJointVenturesAccountedForUsingEquityMethod",  true, 0, 1));
        Items.add(new Item(74, "Other income (expense) from subsidiaries, jointly controlled entities and associates", "OtherIncomeExpenseFromSubsidiariesJointlyControlledEntitiesAndAssociates",  true, 0, 1));
        Items.add(new Item(75, "Gains (losses) arising from difference between previous carrying amount and fair value of financial assets reclassified as measured at fair value", "GainsLossesArisingFromDifferenceBetweenPreviousCarryingAmountAndFairValueOfFinancialAssetsReclassifiedAsMeasuredAtFairValue",  true, 0, 1));
        Items.add(new Item(76, "Cumulative gain (loss) previously recognised in other comprehensive income arising from reclassification of financial assets out of fair value through other comprehensive income into fair value through profit or loss measurement category", "CumulativeGainLossPreviouslyRecognisedInOtherComprehensiveIncomeArisingFromReclassificationOfFinancialAssetsOutOfFairValueThroughOtherComprehensiveIncomeIntoFairValueThroughProfitOrLossMeasurementCategory",  true, 0, 1));
        Items.add(new Item(77, "Hedging gains (losses) for hedge of group of items with offsetting risk positions", "HedgingGainsLossesForHedgeOfGroupOfItemsWithOffsettingRiskPositions",  true, 0, 1));
        Items.add(new Item(78, "Profit (loss) before tax", "ProfitLossBeforeTax",  true, 0, 1));
        Items.add(new Item(79, "Income tax expense (from continuing operations)", "IncomeTaxExpenseContinuingOperations",  true, 0, 1));
        Items.add(new Item(80, "Profit (loss) from continuing operations", "ProfitLossFromContinuingOperations",  true, 0, 1));
        Items.add(new Item(81, "Profit (loss) from discontinued operations", "ProfitLossFromDiscontinuedOperations",  true, 0, 1));
        Items.add(new Item(82, "Profit (loss)", "ProfitLoss",  true, 0, 1));
        Items.add(new Item(83, "Other comprehensive income", "OtherComprehensiveIncome",  true, 0, 1));
        Items.add(new Item(84, "COMPREHENSIVE INCOME", "ComprehensiveIncome", true, 0, 1));

        return Items;
    }
}