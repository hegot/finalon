package finalon.reportGeneration.interpreter.ReusableComponents.interfaces;

public class Round {
    public static String format(Double input) {
        return (input != null) ? String.format("%.2f", input) : null;
    }
}
