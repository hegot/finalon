package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

class ValsEvaluator {
    Boolean matches(String firstComparator, String secondComparator, Double value, Double valueToCompare) {
        Boolean result = false;
        if (value != null && valueToCompare != null) {
            if (firstComparator.length() > 0) {
                result = evaluateOperator(firstComparator, value, valueToCompare);
                if (!result) return false;
            }
            if (secondComparator.length() > 0) {
                result = evaluateOperator(secondComparator, value, valueToCompare);
            }
        }
        return result;
    }


    private Boolean evaluateOperator(String operator, Double a, Double b) {
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
