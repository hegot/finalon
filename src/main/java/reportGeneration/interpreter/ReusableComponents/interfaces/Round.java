package reportGeneration.interpreter.ReusableComponents.interfaces;

public interface Round {
    default String round(Double input) {
        return (input != null) ? String.format("%.2f", input) : null;
    }
}
