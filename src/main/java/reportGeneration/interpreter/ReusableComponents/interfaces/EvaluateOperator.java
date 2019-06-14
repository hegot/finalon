package reportGeneration.interpreter.ReusableComponents.interfaces;

public interface EvaluateOperator {
    default Boolean evaluateOperator(String operator, Double a, Double b) {
        Boolean result = false;
        switch (operator) {
            case (">"):
                result = a > b;
                break;
            case ("<"):
                result = a < b;
                break;
            case ("="):
                result = a.equals(b);
                break;
            case (">="):
                result = a >= b;
                break;
            case ("<="):
                result = a <= b;
                break;
        }
        return result;
    }
}
