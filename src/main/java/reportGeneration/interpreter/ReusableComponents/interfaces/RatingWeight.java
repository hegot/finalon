package reportGeneration.interpreter.ReusableComponents.interfaces;

public interface RatingWeight {
    default Double getWeight(String code) {
        Double weight = 0.15;
        switch (code) {
            case "NetProfitMargin":
                weight = 0.15;
                break;
            case "ReturnOnAssets":
                weight = 0.15;
                break;
            case "DebtEquityRatio":
                weight = 0.15;
                break;
            case "CurrentRatio":
                weight = 0.1;
                break;
            case "NetSalesChange":
                weight = 0.1;
                break;
            case "OperatingIncomeMargin":
                weight = 0.1;
                break;
            case "EquityChange":
                weight = 0.1;
                break;
            case "QuickRatio":
                weight = 0.05;
                break;
            case "DebtRatio":
                weight = 0.05;
                break;
            case "TimesInterestEarned":
                weight = 0.05;
                break;
        }
        return weight;
    }
}
