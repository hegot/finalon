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
        // Assets
        //Non-current Assets
        Items.add(new Item(0, "Property, plant and equipment", "PropertyPlantAndEquipment", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Investment property", "Investmentproperty", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Goodwill", "Goodwill", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Intangible assets other than goodwill", "IntangibleAssetsOtherThanGoodwill", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Investment accounted for using equity method", "InvestmentAccountedForUsingEquityMethod", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Investments in subsidiaries, joint ventures and associates", "InvestmentsInSubsidiariesJointVenturesAndAssociates", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Non-current biological assets", "NoncurrentBiologicalAssets", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Trade and other non-current receivables", "NoncurrentReceivables", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Non-current inventories", "NoncurrentInventories", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Deferred tax assets", "DeferredTaxAssets", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Current tax assets, non-current", "CurrentTaxAssetsNoncurrent", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Other non-current financial assets", "OtherNoncurrentFinancialAssets", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Other non-current non-financial assets", "OtherNoncurrentNonfinancialAssets", "Assets", "Non-current Assets", true, 0, 0));
        Items.add(new Item(0, "Non-current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "InvestmentAccountedForUsingEquityMethod", "Assets", "Non-current Assets", true, 0, 0));


        //Current Assets
        Items.add(new Item(0, "Current inventories", "Inventories", "Assets", "Current Assets", true, 0, 0));
        Items.add(new Item(0, "Trade and other current receivables", "TradeAndOtherCurrentReceivables", "Assets", "Current Assets", true, 0, 0));
        Items.add(new Item(0, "Current tax assets, current", "CurrentTaxAssetsCurrent", "Assets", "Current Assets", true, 0, 0));
        Items.add(new Item(0, "Current biological assets", "CurrentBiologicalAssets", "Assets", "Current Assets", true, 0, 0));
        Items.add(new Item(0, "Other current financial assets", "OtherCurrentFinancialAssets", "Assets", "Current Assets", true, 0, 0));
        Items.add(new Item(0, "Other current non-financial assets", "OtherCurrentNonfinancialAssets", "Assets", "Current Assets", true, 0, 0));
        Items.add(new Item(0, "Cash and cash equivalents", "CashAndCashEquivalents", "Assets", "Current Assets", true, 0, 0));
        Items.add(new Item(0, "Current non-cash assets pledged as collateral for which transferee has right by contract or custom to sell or repledge collateral", "CurrentNoncashAssetsPledgedAsCollateralForWhichTransfereeHasRightByContractOrCustomToSellOrRepledgeCollateral", "Assets", "Current Assets", true, 0, 0));
        Items.add(new Item(0, "Non-current assets or disposal groups classified as held for sale or as held for distribution to owners", "NoncurrentAssetsOrDisposalGroupsClassifiedAsHeldForSaleOrAsHeldForDistributionToOwners", "Assets", "Current Assets", true, 0, 0));


        //EQUITY and LIABILITIES
        //Equity

        Items.add(new Item(0, "Issued (share) capital", "IssuedCapital", "Equity and Liabilities", "Equity", true, 0, 0));
        Items.add(new Item(0, "Share premium", "SharePremium", "Equity and Liabilities", "Equity", true, 0, 0));
        Items.add(new Item(0, "Treasury shares", "TreasuryShares", "Equity and Liabilities", "Equity", true, 0, 0));
        Items.add(new Item(0, "Other equity interest", "OtherEquityInterest", "Equity and Liabilities", "Equity", true, 0, 0));
        Items.add(new Item(0, "Other reserves", "OtherReserves", "Equity and Liabilities", "Equity", true, 0, 0));
        Items.add(new Item(0, "Retained earnings", "RetainedEarnings", "Equity and Liabilities", "Equity", true, 0, 0));
        Items.add(new Item(0, "Non-controlling interests", "NoncontrollingInterests", "Equity and Liabilities", "Equity", true, 0, 0));


        //Non-current liabilities

        Items.add(new Item(0, "Non-current provisions for employee benefits", "NoncurrentProvisionsForEmployeeBenefits", "Equity and Liabilities", "Non-current liabilities", true, 0, 0));
        Items.add(new Item(0, "Other non-current provisions", "OtherLongtermProvisions", "Equity and Liabilities", "Non-current liabilities", true, 0, 0));
        Items.add(new Item(0, "Trade and other non-current payables", "NoncurrentPayables", "Equity and Liabilities", "Non-current liabilities", true, 0, 0));
        Items.add(new Item(0, "Deferred tax liabilities", "DeferredTaxLiabilities", "Equity and Liabilities", "Non-current liabilities", true, 0, 0));
        Items.add(new Item(0, "Current tax liabilities, non-current", "CurrentTaxLiabilitiesNoncurrent", "Equity and Liabilities", "Non-current liabilities", true, 0, 0));
        Items.add(new Item(0, "Other non-current financial liabilities", "OtherNoncurrentFinancialLiabilities", "Equity and Liabilities", "Non-current liabilities", true, 0, 0));
        Items.add(new Item(0, "Other non-current non-financial liabilities", "OtherNoncurrentNonfinancialLiabilities", "Equity and Liabilities", "Non-current liabilities", true, 0, 0));


        //Current liabilities

        Items.add(new Item(0, "Current provisions for employee benefits", "CurrentProvisionsForEmployeeBenefits", "Equity and Liabilities", "Current liabilities", true, 0, 0));
        Items.add(new Item(0, "Other current provisions", "OtherShorttermProvisions", "Equity and Liabilities", "Current liabilities", true, 0, 0));
        Items.add(new Item(0, "Trade and other current payables", "TradeAndOtherCurrentPayables", "Equity and Liabilities", "Current liabilities", true, 0, 0));
        Items.add(new Item(0, "Current tax liabilities, current", "CurrentTaxLiabilitiesCurrent", "Equity and Liabilities", "Current liabilities", true, 0, 0));
        Items.add(new Item(0, "Other current financial liabilities", "OtherCurrentFinancialLiabilities", "Equity and Liabilities", "Current liabilities", true, 0, 0));
        Items.add(new Item(0, "Other current non-financial liabilities", "OtherCurrentNonfinancialLiabilities", "Equity and Liabilities", "Current liabilities", true, 0, 0));
        Items.add(new Item(0, "Liabilities included in disposal groups classified as held for sale", "LiabilitiesIncludedInDisposalGroupsClassifiedAsHeldForSale", "Equity and Liabilities", "Current liabilities", true, 0, 0));

        return Items;
    }

    public static ObservableList<Item> getPLStatementItems() {
        ObservableList<Item> Items = FXCollections.observableArrayList();

        //Statement of Comprehensive Income (P&L Statement)
        Items.add(new Item(0, "Revenue", "Revenue", "", "", true, 0, 1));
        Items.add(new Item(0, "Cost of sales", "CostOfSales", "", "", true, 0, 1));
        Items.add(new Item(0, "Gross profit", "GrossProfit", "", "", true, 0, 1));
        Items.add(new Item(0, "Other income", "OtherIncome", "", "", true, 0, 1));
        Items.add(new Item(0, "Distribution costs", "DistributionCosts", "", "", true, 0, 1));
        Items.add(new Item(0, "Administrative expense", "AdministrativeExpense", "", "", true, 0, 1));
        Items.add(new Item(0, "Other expense", "OtherExpenseByFunction", "", "", true, 0, 1));
        Items.add(new Item(0, "Other gains (losses)", "OtherGainsLosses", "", "", true, 0, 1));
        Items.add(new Item(0, "Profit (loss) from operating activities", "ProfitLossFromOperatingActivities", "", "", true, 0, 1));
        Items.add(new Item(0, "Difference between carrying amount of dividends payable and carrying amount of non-cash assets distributed", "DifferenceBetweenCarryingAmountOfDividendsPayableAndCarryingAmountOfNoncashAssetsDistributed", "", "", true, 0, 1));
        Items.add(new Item(0, "Gains (losses) on net monetary position", "GainsLossesOnNetMonetaryPosition", "", "", true, 0, 1));
        Items.add(new Item(0, "Gain (loss) arising from derecognition of financial assets measured at amortised cost", "GainLossArisingFromDerecognitionOfFinancialAssetsMeasuredAtAmortisedCost", "", "", true, 0, 1));
        Items.add(new Item(0, "Finance income", "FinanceIncome", "", "", true, 0, 1));
        Items.add(new Item(0, "Finance costs", "FinanceCosts", "", "", true, 0, 1));
        Items.add(new Item(0, "Impairment loss (impairment gain and reversal of impairment loss) determined in accordance with IFRS 9", "ImpairmentLossImpairmentGainAndReversalOfImpairmentLossDeterminedInAccordanceWithIFRS9", "", "", true, 0, 1));
        Items.add(new Item(0, "Share of profit (loss) of associates and joint ventures accounted for using equity method", "ShareOfProfitLossOfAssociatesAndJointVenturesAccountedForUsingEquityMethod", "", "", true, 0, 1));
        Items.add(new Item(0, "Other income (expense) from subsidiaries, jointly controlled entities and associates", "OtherIncomeExpenseFromSubsidiariesJointlyControlledEntitiesAndAssociates", "", "", true, 0, 1));
        Items.add(new Item(0, "Gains (losses) arising from difference between previous carrying amount and fair value of financial assets reclassified as measured at fair value", "GainsLossesArisingFromDifferenceBetweenPreviousCarryingAmountAndFairValueOfFinancialAssetsReclassifiedAsMeasuredAtFairValue", "", "", true, 0, 1));
        Items.add(new Item(0, "Cumulative gain (loss) previously recognised in other comprehensive income arising from reclassification of financial assets out of fair value through other comprehensive income into fair value through profit or loss measurement category", "CumulativeGainLossPreviouslyRecognisedInOtherComprehensiveIncomeArisingFromReclassificationOfFinancialAssetsOutOfFairValueThroughOtherComprehensiveIncomeIntoFairValueThroughProfitOrLossMeasurementCategory", "", "", true, 0, 1));
        Items.add(new Item(0, "Hedging gains (losses) for hedge of group of items with offsetting risk positions", "HedgingGainsLossesForHedgeOfGroupOfItemsWithOffsettingRiskPositions", "", "", true, 0, 1));
        Items.add(new Item(0, "Profit (loss) before tax", "ProfitLossBeforeTax", "", "", true, 0, 1));
        Items.add(new Item(0, "Income tax expense (from continuing operations)", "IncomeTaxExpenseContinuingOperations", "", "", true, 0, 1));
        Items.add(new Item(0, "Profit (loss) from continuing operations", "ProfitLossFromContinuingOperations", "", "", true, 0, 1));
        Items.add(new Item(0, "Profit (loss) from discontinued operations", "ProfitLossFromDiscontinuedOperations", "", "", true, 0, 1));
        Items.add(new Item(0, "Profit (loss)", "ProfitLoss", "", "", true, 0, 1));
        Items.add(new Item(0, "Other comprehensive income", "OtherComprehensiveIncome", "", "", true, 0, 1));
        Items.add(new Item(0, "COMPREHENSIVE INCOME", "ComprehensiveIncome", "", "", true, 0, 1));

        return Items;
    }
}