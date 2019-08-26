package finalon.reportGeneration.interpreter.ReusableComponents.interfaces;

public interface EquityShareAnalyze extends GetVal, JsCalcHelper {
    default String equityShareAnalyse(Double share, String period) {
        StringBuilder str = new StringBuilder();
        if (share >= 60) {
            str.append("High share of the equity (" + format(share) + ") in " + formatDate(period) +
                    " decreases the risk that the firm will not " +
                    "be able to pay interest and repay the " +
                    "principal on the amount borrowed.");
        } else if (share >= 40) {
            str.append("Average share of the equity (" + format(share) + ") in " + formatDate(period) +
                    " means the financial risk level was acceptable.");
        } else {
            str.append("Low share of the equity (" + format(share) + ") in " + formatDate(period) +
                    " increases the risk that the firm will not " +
                    "be able to pay interest and repay the " +
                    "principal on the amount borrowed.");
        }
        return str.toString();
    }
}
