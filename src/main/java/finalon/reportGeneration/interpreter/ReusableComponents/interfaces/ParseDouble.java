package finalon.reportGeneration.interpreter.ReusableComponents.interfaces;

public interface ParseDouble {
    default Double parseDouble(String str) {
        if (str == null) return null;
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    default String toString(Double dob) {
        if (dob != null) {
            return Round.format(dob);
        }
        return null;
    }

}
