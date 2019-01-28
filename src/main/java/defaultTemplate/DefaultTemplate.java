package defaultTemplate;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DefaultTemplate {


    public static ObservableList<Item> getTpl() {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        Items.add(new Item(1, "Default Template", "DefaultTemplate", true, 0, 0));
        Items.add(new Item(2, "Statement of Financial Position (Balance Sheet)", "StatementOfFinancialPosition", true, 1, 0));
        Items.add(new Item(3, "Statement of Comprehensive Income (P&L Statement)", "StatementOfComprehensiveIncome", true, 1, 0));


        Items.add(new Item(4, "Assets", "Assets", true, 2, 0));
        Items.add(new Item(5, "Non-current assets", "NonCurrentAssets", true, 4, 0));
        Items.add(new Item(6, "Current assets", "CurrentAssets", true, 4, 0));
        //Equity And Liabilities
        Items.add(new Item(7, "Equity And Liabilities", "EquityAndLiabilities", true, 2, 0));
        Items.add(new Item(8, "Equity", "Equity", true, 7, 0));
        Items.add(new Item(9, "Liabilities", "Liabilities", true, 7, 0));
        Items.add(new Item(10, "Non-current liabilities", "NonCurrentLiabilities", true, 9, 0));
        Items.add(new Item(11, "Current liabilities", "CurrentLiabilities", true, 9, 0));

        //Non-current assets
        Items.add(new Item(12, "Property, plant and equipment", "PropertyPlantAndEquipment", true, 5, 0));
        Items.add(new Item(13, "Investment property", "Investmentproperty", true, 5, 0));
        Items.add(new Item(14, "Goodwill", "Goodwill", true, 5, 0));
        Items.add(new Item(15, "Intangible assets other than goodwill", "IntangibleAssetsOtherThanGoodwill", true, 5, 0));
        Items.add(new Item(16, "Investment accounted for using equity method", "InvestmentAccountedForUsingEquityMethod", true, 5, 0));
        Items.add(new Item(17, "Investments in subsidiaries, joint ventures and associates", "InvestmentsInSubsidiariesJointVenturesAndAssociates", true, 5, 0));
        Items.add(new Item(18, "Non-current biological assets", "NoncurrentBiologicalAssets", true, 5, 0));
        Items.add(new Item(19, "Trade and other non-current receivables", "NoncurrentReceivables", true, 5, 0));
        Items.add(new Item(20, "Non-current inventories", "NoncurrentInventories", true, 5, 0));
        Items.add(new Item(21, "Deferred tax assets", "DeferredTaxAssets", true, 5, 0));
        Items.add(new Item(22, "Current tax assets, non-current", "CurrentTaxAssetsNoncurrent", true, 5, 0));
        Items.add(new Item(23, "Other non-current financial assets", "OtherNoncurrentFinancialAssets", true, 5, 0));
        Items.add(new Item(24, "Other non-current non-financial assets", "OtherNoncurrentNonfinancialAssets", true, 5, 0));
        Items.add(new Item(25, "Non-current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "InvestmentAccountedForUsingEquityMethod", true, 5, 0));

        //Current Assets
        Items.add(new Item(26, "Current inventories", "Inventories", true, 6, 0));
        Items.add(new Item(27, "Trade and other current receivables", "TradeAndOtherCurrentReceivables", true, 6, 0));
        Items.add(new Item(28, "Current tax assets, current", "CurrentTaxAssetsCurrent", true, 6, 0));
        Items.add(new Item(29, "Current biological assets", "CurrentBiologicalAssets", true, 6, 0));
        Items.add(new Item(30, "Other current financial assets", "OtherCurrentFinancialAssets", true, 6, 0));
        Items.add(new Item(31, "Other current non-financial assets", "OtherCurrentNonfinancialAssets", true, 6, 0));
        Items.add(new Item(32, "Cash and cash equivalents", "CashAndCashEquivalents", true, 6, 0));
        Items.add(new Item(33, "Current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "CurrentNoncashAssetsPledgedAsCollateralForWhichTransfereeHasRightByContractOrCustomToSellOrRepledgeCollateral", true, 6, 0));
        Items.add(new Item(34, "Non-current assets or disposal groups classified as held for sale or as held for distribution to owners", "NoncurrentAssetsOrDisposalGroupsClassifiedAsHeldForSaleOrAsHeldForDistributionToOwners", true, 6, 0));



        //Equity
        Items.add(new Item(35, "Issued (share) capital", "IssuedCapital", true, 8, 0));
        Items.add(new Item(36, "Share premium", "SharePremium", true, 8, 0));
        Items.add(new Item(37, "Treasury shares", "TreasuryShares", true, 8, 0));
        Items.add(new Item(48, "Other equity interest", "OtherEquityInterest", true, 8, 0));
        Items.add(new Item(49, "Other reserves", "OtherReserves", true, 8, 0));
        Items.add(new Item(40, "Retained earnings", "RetainedEarnings", true, 8, 0));
        Items.add(new Item(41, "Non-controlling interests", "NoncontrollingInterests", true, 8, 0));


        //Liabilities

        //Non-current liabilities

        Items.add(new Item(42, "Non-current provisions for employee benefits", "NoncurrentProvisionsForEmployeeBenefits", true, 10, 0));
        Items.add(new Item(43, "Other non-current provisions", "OtherLongtermProvisions", true, 10, 0));
        Items.add(new Item(44, "Trade and other non-current payables", "NoncurrentPayables", true, 10, 0));
        Items.add(new Item(45, "Deferred tax liabilities", "DeferredTaxLiabilities", true, 10, 0));
        Items.add(new Item(46, "Current tax liabilities, non-current", "CurrentTaxLiabilitiesNoncurrent", true, 10, 0));
        Items.add(new Item(47, "Other non-current financial liabilities", "OtherNoncurrentFinancialLiabilities", true, 10, 0));
        Items.add(new Item(48, "Other non-current non-financial liabilities", "OtherNoncurrentNonfinancialLiabilities", true, 10, 0));


        //Current liabilities
        Items.add(new Item(49, "Current provisions for employee benefits", "CurrentProvisionsForEmployeeBenefits", true, 11, 0));
        Items.add(new Item(50, "Other current provisions", "OtherShorttermProvisions", true, 11, 0));
        Items.add(new Item(51, "Trade and other current payables", "TradeAndOtherCurrentPayables", true, 11, 0));
        Items.add(new Item(52, "Current tax liabilities, current", "CurrentTaxLiabilitiesCurrent", true, 11, 0));
        Items.add(new Item(53, "Other current financial liabilities", "OtherCurrentFinancialLiabilities", true, 11, 0));
        Items.add(new Item(54, "Other current non-financial liabilities", "OtherCurrentNonfinancialLiabilities", true, 11, 0));
        Items.add(new Item(55, "Liabilities included in disposal groups classified as held for sale", "LiabilitiesIncludedInDisposalGroupsClassifiedAsHeldForSale", true, 11, 0));


        //Statement of Comprehensive Income (P&L Statement)
        Items.add(new Item(56, "Revenue", "Revenue", true, 3, 1));
        Items.add(new Item(57, "Cost of sales", "CostOfSales", true, 3, 1));
        Items.add(new Item(58, "Gross profit", "GrossProfit", true, 3, 1));
        Items.add(new Item(59, "Other income", "OtherIncome", true, 3, 1));
        Items.add(new Item(60, "Distribution costs", "DistributionCosts", true, 3, 1));
        Items.add(new Item(61, "Administrative expense", "AdministrativeExpense", true, 3, 1));
        Items.add(new Item(62, "Other expense", "OtherExpenseByFunction", true, 3, 1));
        Items.add(new Item(63, "Other gains (losses)", "OtherGainsLosses", true, 3, 1));
        Items.add(new Item(64, "Profit (loss) from operating activities", "ProfitLossFromOperatingActivities", true, 3, 1));
        Items.add(new Item(65, "Difference between carrying amount of dividends payable and carrying amount of non-cash assets distributed", "DifferenceBetweenCarryingAmountOfDividendsPayableAndCarryingAmountOfNoncashAssetsDistributed", true, 3, 1));
        Items.add(new Item(66, "Gains (losses) on net monetary position", "GainsLossesOnNetMonetaryPosition", true, 3, 1));
        Items.add(new Item(67, "Gain (loss) arising from derecognition of financial assets measured at amortised cost", "GainLossArisingFromDerecognitionOfFinancialAssetsMeasuredAtAmortisedCost", true, 3, 1));
        Items.add(new Item(68, "Finance income", "FinanceIncome", true, 3, 1));
        Items.add(new Item(69, "Finance costs", "FinanceCosts", true, 3, 1));
        Items.add(new Item(70, "Impairment loss (impairment gain and reversal of impairment loss) determined in accordance with IFRS 9", "ImpairmentLossImpairmentGainAndReversalOfImpairmentLossDeterminedInAccordanceWithIFRS9", true, 3, 1));
        Items.add(new Item(71, "Share of profit (loss) of associates and joint ventures accounted for using equity method", "ShareOfProfitLossOfAssociatesAndJointVenturesAccountedForUsingEquityMethod", true, 3, 1));
        Items.add(new Item(72, "Other income (expense) from subsidiaries, jointly controlled entities and associates", "OtherIncomeExpenseFromSubsidiariesJointlyControlledEntitiesAndAssociates", true, 3, 1));
        Items.add(new Item(73, "Gains (losses) arising from difference between previous carrying amount and fair value of financial assets reclassified as measured at fair value", "GainsLossesArisingFromDifferenceBetweenPreviousCarryingAmountAndFairValueOfFinancialAssetsReclassifiedAsMeasuredAtFairValue", true, 3, 1));
        Items.add(new Item(74, "Cumulative gain (loss) previously recognised in other comprehensive income arising from reclassification of financial assets out of fair value through other comprehensive income into fair value through profit or loss measurement category", "CumulativeGainLossPreviouslyRecognisedInOtherComprehensiveIncomeArisingFromReclassificationOfFinancialAssetsOutOfFairValueThroughOtherComprehensiveIncomeIntoFairValueThroughProfitOrLossMeasurementCategory", true, 3, 1));
        Items.add(new Item(75, "Hedging gains (losses) for hedge of group of items with offsetting risk positions", "HedgingGainsLossesForHedgeOfGroupOfItemsWithOffsettingRiskPositions", true, 3, 1));
        Items.add(new Item(76, "Profit (loss) before tax", "ProfitLossBeforeTax", true, 3, 1));
        Items.add(new Item(77, "Income tax expense (from continuing operations)", "IncomeTaxExpenseContinuingOperations", true, 3, 1));
        Items.add(new Item(78, "Profit (loss) from continuing operations", "ProfitLossFromContinuingOperations", true, 3, 1));
        Items.add(new Item(79, "Profit (loss) from discontinued operations", "ProfitLossFromDiscontinuedOperations", true, 3, 1));
        Items.add(new Item(80, "Profit (loss)", "ProfitLoss", true, 3, 1));
        Items.add(new Item(81, "Other comprehensive income", "OtherComprehensiveIncome", true, 3, 1));
        Items.add(new Item(82, "COMPREHENSIVE INCOME", "ComprehensiveIncome", true, 3, 1));

        return Items;
    }

}