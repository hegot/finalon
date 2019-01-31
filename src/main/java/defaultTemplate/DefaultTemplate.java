package defaultTemplate;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DefaultTemplate {


    public static ObservableList<Item> getTpl() {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        Items.add(new Item(1, "Default Template", "DefaultTemplate", true, false, 0, 0));
        Items.add(new Item(2, "Statement of Financial Position \n (Balance Sheet)", "StatementOfFinancialPosition", true, false, 1, 0));
        Items.add(new Item(3, "Statement of Comprehensive Income \n (P&L Statement)", "StatementOfComprehensiveIncome", true, false, 1, 0));
        Items.add(new Item(4, "Cash Flow Statement", "CashFlowStatement", true, false, 1, 0));
        Items.add(new Item(5, "Other Data", "OtherData", true, false, 1, 0));


        Items.add(new Item(8, "Assets", "Assets", true, false, 2, 0));
        Items.add(new Item(9, "Non-current assets", "NonCurrentAssets", true, false, 8, 0));
        Items.add(new Item(10, "Current assets", "CurrentAssets", true, false, 8, 0));

        //Equity And Liabilities
        Items.add(new Item(11, "Equity And Liabilities", "EquityAndLiabilities", true, false, 2, 0));

        Items.add(new Item(12, "Equity", "Equity", true, false, 11, 0));

        Items.add(new Item(13, "Liabilities", "Liabilities", true, false, 11, 0));
        Items.add(new Item(14, "Non-current liabilities", "NonCurrentLiabilities", true, false, 13, 0));
        Items.add(new Item(15, "Current liabilities", "CurrentLiabilities", true, false, 13, 0));


        //Non-current assets
        Items.add(new Item(16, "Property, plant and equipment", "PropertyPlantAndEquipment", true, false, 9, 0));
        Items.add(new Item(17, "Investment property", "Investmentproperty", true, false, 9, 0));
        Items.add(new Item(18, "Goodwill", "Goodwill", true, false, 9, 0));
        Items.add(new Item(19, "Intangible assets other than goodwill", "IntangibleAssetsOtherThanGoodwill", true, false, 9, 0));
        Items.add(new Item(20, "Investment accounted for using equity method", "InvestmentAccountedForUsingEquityMethod", true, false, 9, 0));
        Items.add(new Item(21, "Investments in subsidiaries, joint ventures and associates", "InvestmentsInSubsidiariesJointVenturesAndAssociates", true, false, 9, 0));
        Items.add(new Item(22, "Non-current biological assets", "NoncurrentBiologicalAssets", true, false, 9, 0));
        Items.add(new Item(23, "Trade and other non-current receivables", "NoncurrentReceivables", true, false, 9, 0));
        Items.add(new Item(24, "Non-current inventories", "NoncurrentInventories", true, false, 9, 0));
        Items.add(new Item(25, "Deferred tax assets", "DeferredTaxAssets", true, false, 9, 0));
        Items.add(new Item(26, "Current tax assets, non-current", "CurrentTaxAssetsNoncurrent", true, false, 9, 0));
        Items.add(new Item(27, "Other non-current financial assets", "OtherNoncurrentFinancialAssets", true, false, 9, 0));
        Items.add(new Item(28, "Other non-current non-financial assets", "OtherNoncurrentNonfinancialAssets", true, false, 9, 0));
        Items.add(new Item(29, "Non-current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "InvestmentAccountedForUsingEquityMethod", true, false, 9, 0));

        //Current Assets
        Items.add(new Item(30, "Current inventories", "Inventories", true, false, 10, 0));
        Items.add(new Item(40, "Trade and other current receivables", "TradeAndOtherCurrentReceivables", true, false, 10, 0));
        Items.add(new Item(41, "Current tax assets, current", "CurrentTaxAssetsCurrent", true, false, 10, 0));
        Items.add(new Item(42, "Current biological assets", "CurrentBiologicalAssets", true, false, 10, 0));
        Items.add(new Item(43, "Other current financial assets", "OtherCurrentFinancialAssets", true, false, 10, 0));
        Items.add(new Item(44, "Other current non-financial assets", "OtherCurrentNonfinancialAssets", true, false, 10, 0));
        Items.add(new Item(45, "Cash and cash equivalents", "CashAndCashEquivalents", true, false, 10, 0));
        Items.add(new Item(46, "Current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "CurrentNoncashAssetsPledgedAsCollateralForWhichTransfereeHasRightByContractOrCustomToSellOrRepledgeCollateral", true, false, 10, 0));
        Items.add(new Item(47, "Non-current assets or disposal groups classified as held for sale or as held for distribution to owners", "NoncurrentAssetsOrDisposalGroupsClassifiedAsHeldForSaleOrAsHeldForDistributionToOwners", true, false, 10, 0));


        //Equity
        Items.add(new Item(48, "Issued (share) capital", "IssuedCapital", true, false, 12, 0));
        Items.add(new Item(49, "Share premium", "SharePremium", true, false, 12, 0));
        Items.add(new Item(50, "Treasury shares", "TreasuryShares", false, false, 12, 0));
        Items.add(new Item(51, "Other equity interest", "OtherEquityInterest", true, false, 12, 0));
        Items.add(new Item(52, "Other reserves", "OtherReserves", true, false, 12, 0));
        Items.add(new Item(53, "Retained earnings", "RetainedEarnings", true, false, 12, 0));
        Items.add(new Item(54, "Non-controlling interests", "NoncontrollingInterests", true, true, 12, 0));


        //Liabilities

        //Non-current liabilities

        Items.add(new Item(55, "Non-current provisions for employee benefits", "NoncurrentProvisionsForEmployeeBenefits", true, false, 14, 0));
        Items.add(new Item(56, "Other non-current provisions", "OtherLongtermProvisions", true, false, 14, 0));
        Items.add(new Item(57, "Trade and other non-current payables", "NoncurrentPayables", true, false, 14, 0));
        Items.add(new Item(58, "Deferred tax liabilities", "DeferredTaxLiabilities", true, false, 14, 0));
        Items.add(new Item(59, "Current tax liabilities, non-current", "CurrentTaxLiabilitiesNoncurrent", true, false, 14, 0));
        Items.add(new Item(60, "Other non-current financial liabilities", "OtherNoncurrentFinancialLiabilities", true, false, 14, 0));
        Items.add(new Item(61, "Other non-current non-financial liabilities", "OtherNoncurrentNonfinancialLiabilities", true, false, 14, 0));


        //Current liabilities
        Items.add(new Item(62, "Current provisions for employee benefits", "CurrentProvisionsForEmployeeBenefits", true, false, 15, 0));
        Items.add(new Item(63, "Other current provisions", "OtherShorttermProvisions", true, false, 15, 0));
        Items.add(new Item(64, "Trade and other current payables", "TradeAndOtherCurrentPayables", true, false, 15, 0));
        Items.add(new Item(65, "Current tax liabilities, current", "CurrentTaxLiabilitiesCurrent", true, false, 15, 0));
        Items.add(new Item(66, "Other current financial liabilities", "OtherCurrentFinancialLiabilities", true, false, 15, 0));
        Items.add(new Item(67, "Other current non-financial liabilities", "OtherCurrentNonfinancialLiabilities", true, false, 15, 0));
        Items.add(new Item(68, "Liabilities included in disposal groups classified as held for sale", "LiabilitiesIncludedInDisposalGroupsClassifiedAsHeldForSale", true, false, 15, 0));


        //Statement of Comprehensive Income (P&L Statement)
        Items.add(new Item(70, "Revenue", "Revenue", true, false, 3, 1));
        Items.add(new Item(71, "Cost of sales", "CostOfSales", false, false, 3, 1));
        Items.add(new Item(72, "Gross profit", "GrossProfit", true, true, 3, 1));
        Items.add(new Item(73, "Other income", "OtherIncome", true, false, 3, 1));
        Items.add(new Item(74, "Distribution costs", "DistributionCosts", false, false, 3, 1));
        Items.add(new Item(75, "Administrative expense", "AdministrativeExpense", false, false, 3, 1));
        Items.add(new Item(76, "Other expense", "OtherExpenseByFunction", false, false, 3, 1));
        Items.add(new Item(77, "Other gains (losses)", "OtherGainsLosses", true, false, 3, 1));
        Items.add(new Item(78, "Profit (loss) from operating activities", "ProfitLossFromOperatingActivities", true, true, 3, 1));
        Items.add(new Item(79, "Difference between carrying amount of dividends payable and carrying amount of non-cash assets distributed", "DifferenceBetweenCarryingAmountOfDividendsPayableAndCarryingAmountOfNoncashAssetsDistributed", true, false, 3, 1));
        Items.add(new Item(80, "Gains (losses) on net monetary position", "GainsLossesOnNetMonetaryPosition", true, false, 3, 1));
        Items.add(new Item(81, "Gain (loss) arising from derecognition of financial assets measured at amortised cost", "GainLossArisingFromDerecognitionOfFinancialAssetsMeasuredAtAmortisedCost", true, false, 3, 1));
        Items.add(new Item(82, "Finance income", "FinanceIncome", true, false, 3, 1));
        Items.add(new Item(83, "Finance costs", "FinanceCosts", false, false, 3, 1));
        Items.add(new Item(84, "Impairment loss (impairment gain and reversal of impairment loss) determined in accordance with IFRS 9", "ImpairmentLossImpairmentGainAndReversalOfImpairmentLossDeterminedInAccordanceWithIFRS9", false, false, 3, 1));
        Items.add(new Item(85, "Share of profit (loss) of associates and joint ventures accounted for using equity method", "ShareOfProfitLossOfAssociatesAndJointVenturesAccountedForUsingEquityMethod", true, false, 3, 1));
        Items.add(new Item(86, "Other income (expense) from subsidiaries, jointly controlled entities and associates", "OtherIncomeExpenseFromSubsidiariesJointlyControlledEntitiesAndAssociates", true, false, 3, 1));
        Items.add(new Item(87, "Gains (losses) arising from difference between previous carrying amount and fair value of financial assets reclassified as measured at fair value", "GainsLossesArisingFromDifferenceBetweenPreviousCarryingAmountAndFairValueOfFinancialAssetsReclassifiedAsMeasuredAtFairValue", true, false, 3, 1));
        Items.add(new Item(88, "Cumulative gain (loss) previously recognised in other comprehensive income arising from reclassification of financial assets out of fair value through other comprehensive income into fair value through profit or loss measurement category", "CumulativeGainLossPreviouslyRecognisedInOtherComprehensiveIncomeArisingFromReclassificationOfFinancialAssetsOutOfFairValueThroughOtherComprehensiveIncomeIntoFairValueThroughProfitOrLossMeasurementCategory", true, false, 3, 1));
        Items.add(new Item(89, "Hedging gains (losses) for hedge of group of items with offsetting risk positions", "HedgingGainsLossesForHedgeOfGroupOfItemsWithOffsettingRiskPositions", true, false, 3, 1));
        Items.add(new Item(90, "Profit (loss) before tax", "ProfitLossBeforeTax", true, true, 3, 1));
        Items.add(new Item(91, "Income tax expense (from continuing operations)", "IncomeTaxExpenseContinuingOperations", false, false, 3, 1));
        Items.add(new Item(92, "Profit (loss) from continuing operations", "ProfitLossFromContinuingOperations", true, true, 3, 1));
        Items.add(new Item(93, "Profit (loss) from discontinued operations", "ProfitLossFromDiscontinuedOperations", true, false, 3, 1));
        Items.add(new Item(94, "Profit (loss)", "ProfitLoss", true, true, 3, 1));
        Items.add(new Item(95, "Other comprehensi ж      ve income", "OtherComprehensiveIncome", true, false, 3, 1));
        Items.add(new Item(96, "Comprehensive Income", "ComprehensiveIncome", true, true, 3, 1));


        //Cash Flow Statement
        Items.add(new Item(97, "Operating activities", "Operating activities", true, false, 4, 0));
        Items.add(new Item(98, "Investing activities", "Investing activities", true, false, 4, 0));
        Items.add(new Item(99, "Financing activities", "Financing activities", true, false, 4, 0));


        //Operating activities
        Items.add(new Item(100, "Cash receipts from customers", "CashReceiptsFromCustomers", true, false, 97, 1));
        Items.add(new Item(101, "Cash paid to suppliers and employees", "CashPaidToSuppliersAndEmployees", false, false, 97, 1));
        Items.add(new Item(102, "Cash generated from operations", "CashGeneratedFromOperations", true, false, 97, 1));
        Items.add(new Item(102, "Interest paid", "InterestPaid", false, false, 97, 1));
        Items.add(new Item(104, "Income taxes paid", "IncomeTaxesPaid", false, false, 97, 1));
        Items.add(new Item(105, "Net cash from operating activities", "NetCashFromOperatingActivities", true, true, 97, 1));

        //Investing activities
        Items.add(new Item(106, "Acquisition of subsidiary, net of cash acquired", "AcquisitionOfSubsidiaryNetOfCashAcquired", false, false, 98, 1));
        Items.add(new Item(107, "Purchase of property, plant and equipment", "PurchaseOfPropertyPlantAndEquipment", false, false, 98, 1));
        Items.add(new Item(108, " Proceeds from sale of equipment", " ProceedsFromSaleOfEquipment", true, false, 98, 1));
        Items.add(new Item(109, "Interest received", "InterestReceived", true, false, 98, 1));
        Items.add(new Item(110, "Dividends received", "DividendsReceived", true, false, 98, 1));
        Items.add(new Item(111, "Net cash used in investing activities", "NetCashUsedInInvestingActivities", true, true, 98, 1));

        //Financing activities

        Items.add(new Item(112, "Proceeds from issue of share capital", "ProceedsFromIssueOfShareCapital", true, false, 99, 1));
        Items.add(new Item(113, "Proceeds from long-term borrowings", "ProceedsFromLongTermBorrowings", true, false, 99, 1));
        Items.add(new Item(114, "Payment of finance lease liabilities", "PaymentOfFinanceLeaseLiabilities", false, false, 99, 1));
        Items.add(new Item(115, "Dividends paid", "DividendsPaid", false, false, 99, 1));
        Items.add(new Item(116, "Net cash used in financing activities", "NetCashUsedInFinancingActivities", true, true, 99, 1));
        Items.add(new Item(117, "Effect of exchange rate changes", "EffectOfExchangeRateChanges", true, false, 99, 1));
        Items.add(new Item(118, "Net increase in cash and cash equivalents", "NetIncreaseInCashAndCashEquivalents", true, true, 99, 1));
        Items.add(new Item(119, "Cash and cash equivalents at beginning of period", "CashAndCashEquivalentsAtBeginningOfPeriod", true, false, 99, 1));
        Items.add(new Item(120, "Cash and cash equivalents at end of period", "CashAndCashEquivalentsAtEndOfPeriod", true, false, 99, 1));


        //Other Data
        Items.add(new Item(121, "Number of employees", "NumberOfEmployees", true, false, 5, 0));
        Items.add(new Item(122, "Depreciation and amortisation expense", "Amortization", true, false, 5, 0));


        return Items;
    }

}