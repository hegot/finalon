package finalon.reportGeneration.interpreter.ReusableComponents.helpers;

public class EvaluateOperator {
    public static Boolean get(String operator, Double a, Double b) {
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
