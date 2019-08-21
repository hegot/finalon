package finalon.reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.EvaluateOperator;

class ValsEvaluator implements EvaluateOperator {
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
}
