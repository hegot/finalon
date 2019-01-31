package defaultTemplate;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DefaultTemplate {


    public static ObservableList<Item> getTpl() {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        Items.add(new Item(1, "Default Template", "DefaultTemplate", true, 0, 0));
            Items.add(new Item(2, "Statement of Financial Position \n (Balance Sheet)", "StatementOfFinancialPosition", true, 1, 0));
            Items.add(new Item(3, "Statement of Comprehensive Income \n (P&L Statement)", "StatementOfComprehensiveIncome", true, 1, 0));
            Items.add(new Item(4, "Cash Flow Statement", "CashFlowStatement", true, 1, 0));
            Items.add(new Item(5, "Other Data", "OtherData", true, 1, 0));


        Items.add(new Item(8, "Assets", "Assets", true, 2, 0));
            Items.add(new Item(9, "Non-current assets", "NonCurrentAssets", true, 8, 0));
            Items.add(new Item(10, "Current assets", "CurrentAssets", true, 8, 0));

        //Equity And Liabilities
        Items.add(new Item(11, "Equity And Liabilities", "EquityAndLiabilities", true, 2, 0));

            Items.add(new Item(12, "Equity", "Equity", true, 11, 0));

            Items.add(new Item(13, "Liabilities", "Liabilities", true, 11, 0));
            Items.add(new Item(14, "Non-current liabilities", "NonCurrentLiabilities", true, 13, 0));
            Items.add(new Item(15, "Current liabilities", "CurrentLiabilities", true, 13, 0));






        //Non-current assets
        Items.add(new Item(16, "Property, plant and equipment", "PropertyPlantAndEquipment", true, 9, 0));
        Items.add(new Item(17, "Investment property", "Investmentproperty", true, 9, 0));
        Items.add(new Item(18, "Goodwill", "Goodwill", true, 9, 0));
        Items.add(new Item(19, "Intangible assets other than goodwill", "IntangibleAssetsOtherThanGoodwill", true, 9, 0));
        Items.add(new Item(20, "Investment accounted for using equity method", "InvestmentAccountedForUsingEquityMethod", true, 9, 0));
        Items.add(new Item(21, "Investments in subsidiaries, joint ventures and associates", "InvestmentsInSubsidiariesJointVenturesAndAssociates", true, 9, 0));
        Items.add(new Item(22, "Non-current biological assets", "NoncurrentBiologicalAssets", true, 9, 0));
        Items.add(new Item(23, "Trade and other non-current receivables", "NoncurrentReceivables", true, 9, 0));
        Items.add(new Item(24, "Non-current inventories", "NoncurrentInventories", true, 9, 0));
        Items.add(new Item(25, "Deferred tax assets", "DeferredTaxAssets", true, 9, 0));
        Items.add(new Item(26, "Current tax assets, non-current", "CurrentTaxAssetsNoncurrent", true, 9, 0));
        Items.add(new Item(27, "Other non-current financial assets", "OtherNoncurrentFinancialAssets", true, 9, 0));
        Items.add(new Item(28, "Other non-current non-financial assets", "OtherNoncurrentNonfinancialAssets", true, 9, 0));
        Items.add(new Item(29, "Non-current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "InvestmentAccountedForUsingEquityMethod", true, 9, 0));

        //Current Assets
        Items.add(new Item(30, "Current inventories", "Inventories", true, 10, 0));
        Items.add(new Item(40, "Trade and other current receivables", "TradeAndOtherCurrentReceivables", true, 10, 0));
        Items.add(new Item(41, "Current tax assets, current", "CurrentTaxAssetsCurrent", true, 10, 0));
        Items.add(new Item(42, "Current biological assets", "CurrentBiologicalAssets", true, 10, 0));
        Items.add(new Item(43, "Other current financial assets", "OtherCurrentFinancialAssets", true, 10, 0));
        Items.add(new Item(44, "Other current non-financial assets", "OtherCurrentNonfinancialAssets", true, 10, 0));
        Items.add(new Item(45, "Cash and cash equivalents", "CashAndCashEquivalents", true, 10, 0));
        Items.add(new Item(46, "Current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "CurrentNoncashAssetsPledgedAsCollateralForWhichTransfereeHasRightByContractOrCustomToSellOrRepledgeCollateral", true, 10, 0));
        Items.add(new Item(47, "Non-current assets or disposal groups classified as held for sale or as held for distribution to owners", "NoncurrentAssetsOrDisposalGroupsClassifiedAsHeldForSaleOrAsHeldForDistributionToOwners", true, 10, 0));


        //Equity
        Items.add(new Item(48, "Issued (share) capital", "IssuedCapital", true, 12, 0));
        Items.add(new Item(49, "Share premium", "SharePremium", true, 12, 0));
        Items.add(new Item(50, "Treasury shares", "TreasuryShares", true, 12, 0));
        Items.add(new Item(51, "Other equity interest", "OtherEquityInterest", true, 12, 0));
        Items.add(new Item(52, "Other reserves", "OtherReserves", true, 12, 0));
        Items.add(new Item(53, "Retained earnings", "RetainedEarnings", true, 12, 0));
        Items.add(new Item(54, "Non-controlling interests", "NoncontrollingInterests", true, 12, 0));


        //Liabilities

        //Non-current liabilities

        Items.add(new Item(55, "Non-current provisions for employee benefits", "NoncurrentProvisionsForEmployeeBenefits", true, 14, 0));
        Items.add(new Item(56, "Other non-current provisions", "OtherLongtermProvisions", true, 14, 0));
        Items.add(new Item(57, "Trade and other non-current payables", "NoncurrentPayables", true, 14, 0));
        Items.add(new Item(58, "Deferred tax liabilities", "DeferredTaxLiabilities", true, 14, 0));
        Items.add(new Item(59, "Current tax liabilities, non-current", "CurrentTaxLiabilitiesNoncurrent", true, 14, 0));
        Items.add(new Item(60, "Other non-current financial liabilities", "OtherNoncurrentFinancialLiabilities", true, 14, 0));
        Items.add(new Item(61, "Other non-current non-financial liabilities", "OtherNoncurrentNonfinancialLiabilities", true, 14, 0));


        //Current liabilities
        Items.add(new Item(62, "Current provisions for employee benefits", "CurrentProvisionsForEmployeeBenefits", true, 15, 0));
        Items.add(new Item(63, "Other current provisions", "OtherShorttermProvisions", true, 15, 0));
        Items.add(new Item(64, "Trade and other current payables", "TradeAndOtherCurrentPayables", true, 15, 0));
        Items.add(new Item(65, "Current tax liabilities, current", "CurrentTaxLiabilitiesCurrent", true, 15, 0));
        Items.add(new Item(66, "Other current financial liabilities", "OtherCurrentFinancialLiabilities", true, 15, 0));
        Items.add(new Item(67, "Other current non-financial liabilities", "OtherCurrentNonfinancialLiabilities", true, 15, 0));
        Items.add(new Item(68, "Liabilities included in disposal groups classified as held for sale", "LiabilitiesIncludedInDisposalGroupsClassifiedAsHeldForSale", true, 15, 0));


        //Statement of Comprehensive Income (P&L Statement)
        Items.add(new Item(70, "Revenue", "Revenue", true, 3, 1));
        Items.add(new Item(71, "Cost of sales", "CostOfSales", true, 3, 1));
        Items.add(new Item(72, "Gross profit", "GrossProfit", true, 3, 1));
        Items.add(new Item(73, "Other income", "OtherIncome", true, 3, 1));
        Items.add(new Item(74, "Distribution costs", "DistributionCosts", true, 3, 1));
        Items.add(new Item(75, "Administrative expense", "AdministrativeExpense", true, 3, 1));
        Items.add(new Item(76, "Other expense", "OtherExpenseByFunction", true, 3, 1));
        Items.add(new Item(77, "Other gains (losses)", "OtherGainsLosses", true, 3, 1));
        Items.add(new Item(78, "Profit (loss) from operating activities", "ProfitLossFromOperatingActivities", true, 3, 1));
        Items.add(new Item(79, "Difference between carrying amount of dividends payable and carrying amount of non-cash assets distributed", "DifferenceBetweenCarryingAmountOfDividendsPayableAndCarryingAmountOfNoncashAssetsDistributed", true, 3, 1));
        Items.add(new Item(80, "Gains (losses) on net monetary position", "GainsLossesOnNetMonetaryPosition", true, 3, 1));
        Items.add(new Item(81, "Gain (loss) arising from derecognition of financial assets measured at amortised cost", "GainLossArisingFromDerecognitionOfFinancialAssetsMeasuredAtAmortisedCost", true, 3, 1));
        Items.add(new Item(82, "Finance income", "FinanceIncome", true, 3, 1));
        Items.add(new Item(83, "Finance costs", "FinanceCosts", true, 3, 1));
        Items.add(new Item(84, "Impairment loss (impairment gain and reversal of impairment loss) determined in accordance with IFRS 9", "ImpairmentLossImpairmentGainAndReversalOfImpairmentLossDeterminedInAccordanceWithIFRS9", true, 3, 1));
        Items.add(new Item(85, "Share of profit (loss) of associates and joint ventures accounted for using equity method", "ShareOfProfitLossOfAssociatesAndJointVenturesAccountedForUsingEquityMethod", true, 3, 1));
        Items.add(new Item(86, "Other income (expense) from subsidiaries, jointly controlled entities and associates", "OtherIncomeExpenseFromSubsidiariesJointlyControlledEntitiesAndAssociates", true, 3, 1));
        Items.add(new Item(87, "Gains (losses) arising from difference between previous carrying amount and fair value of financial assets reclassified as measured at fair value", "GainsLossesArisingFromDifferenceBetweenPreviousCarryingAmountAndFairValueOfFinancialAssetsReclassifiedAsMeasuredAtFairValue", true, 3, 1));
        Items.add(new Item(88, "Cumulative gain (loss) previously recognised in other comprehensive income arising from reclassification of financial assets out of fair value through other comprehensive income into fair value through profit or loss measurement category", "CumulativeGainLossPreviouslyRecognisedInOtherComprehensiveIncomeArisingFromReclassificationOfFinancialAssetsOutOfFairValueThroughOtherComprehensiveIncomeIntoFairValueThroughProfitOrLossMeasurementCategory", true, 3, 1));
        Items.add(new Item(89, "Hedging gains (losses) for hedge of group of items with offsetting risk positions", "HedgingGainsLossesForHedgeOfGroupOfItemsWithOffsettingRiskPositions", true, 3, 1));
        Items.add(new Item(90, "Profit (loss) before tax", "ProfitLossBeforeTax", true, 3, 1));
        Items.add(new Item(91, "Income tax expense (from continuing operations)", "IncomeTaxExpenseContinuingOperations", true, 3, 1));
        Items.add(new Item(92, "Profit (loss) from continuing operations", "ProfitLossFromContinuingOperations", true, 3, 1));
        Items.add(new Item(93, "Profit (loss) from discontinued operations", "ProfitLossFromDiscontinuedOperations", true, 3, 1));
        Items.add(new Item(94, "Profit (loss)", "ProfitLoss", true, 3, 1));
        Items.add(new Item(95, "Other comprehensive income", "OtherComprehensiveIncome", true, 3, 1));
        Items.add(new Item(96, "COMPREHENSIVE INCOME", "ComprehensiveIncome", true, 3, 1));



        //Cash Flow Statement
        Items.add(new Item(97, "Operating activities", "Operating activities", true, 4, 0));
        Items.add(new Item(98, "Investing activities", "Investing activities", true, 4, 0));
        Items.add(new Item(99, "Financing activities", "Financing activities", true, 4, 0));


            //Operating activities
            Items.add(new Item(100, "Cash receipts from customers", "CashReceiptsFromCustomers", true, 97, 1));
            Items.add(new Item(101, "Cash paid to suppliers and employees", "CashPaidToSuppliersAndEmployees", true, 97, 1));
            Items.add(new Item(102, "Cash generated from operations", "CashGeneratedFromOperations", true, 97, 1));
            Items.add(new Item(102, "Interest paid", "InterestPaid", true, 97, 1));
            Items.add(new Item(104, "Income taxes paid", "IncomeTaxesPaid", true, 97, 1));
            Items.add(new Item(105, "Net cash from operating activities", "NetCashFromOperatingActivities", true, 97, 1));

            //Investing activities
            Items.add(new Item(106, "Acquisition of subsidiary, net of cash acquired", "AcquisitionOfSubsidiaryNetOfCashAcquired", true, 98, 1));
            Items.add(new Item(107, "Purchase of property, plant and equipment", "PurchaseOfPropertyPlantAndEquipment", true, 98, 1));
            Items.add(new Item(108, " Proceeds from sale of equipment", " ProceedsFromSaleOfEquipment", true, 98, 1));
            Items.add(new Item(109, "Interest received", "InterestReceived", true, 98, 1));
            Items.add(new Item(110, "Dividends received", "DividendsReceived", true, 98, 1));
            Items.add(new Item(111, "Net cash used in investing activities", "NetCashUsedInInvestingActivities", true, 98, 1));

            //Financing activities

            Items.add(new Item(112, "Proceeds from issue of share capital", "ProceedsFromIssueOfShareCapital", true, 99, 1));
            Items.add(new Item(113, "Proceeds from long-term borrowings", "ProceedsFromLongTermBorrowings", true, 99, 1));
            Items.add(new Item(114, "Payment of finance lease liabilities", "PaymentOfFinanceLeaseLiabilities", true, 99, 1));
            Items.add(new Item(115, "Dividends paid", "DividendsPaid", true, 99, 1));
            Items.add(new Item(116, "Net cash used in financing activities", "NetCashUsedInFinancingActivities", true, 99, 1));
            Items.add(new Item(117, "Effect of exchange rate changes", "EffectOfExchangeRateChanges", true, 99, 1));
            Items.add(new Item(118, "Net increase in cash and cash equivalents", "NetIncreaseInCashAndCashEquivalents", true, 99, 1));
            Items.add(new Item(119, "Cash and cash equivalents at beginning of period", "CashAndCashEquivalentsAtBeginningOfPeriod", true, 99, 1));
            Items.add(new Item(120, "Cash and cash equivalents at end of period", "CashAndCashEquivalentsAtEndOfPeriod", true, 99, 1));


        //Other Data
        Items.add(new Item(121, "Number of employees", "NumberOfEmployees", true, 5, 0));
        Items.add(new Item(122, "Amortization", "Amortization", true, 5, 0));



        return Items;
    }

}