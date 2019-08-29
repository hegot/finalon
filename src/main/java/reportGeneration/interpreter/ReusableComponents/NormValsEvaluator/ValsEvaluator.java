package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import reportGeneration.interpreter.ReusableComponents.helpers.EvaluateOperator;

class ValsEvaluator {
    Boolean matches(String firstComparator, String secondComparator, Double value, Double valueToCompare) {
        Boolean result = false;
        if (value != null && valueToCompare != null) {
            if (firstComparator.length() > 0) {
                result = EvaluateOperator.get(firstComparator, value, valueToCompare);
                if (!result) return false;
            }
            if (secondComparator.length() > 0) {
                result = EvaluateOperator.get(secondComparator, value, valueToCompare);
            }
        }
        return result;
    }
}
