package reportGeneration.interpreter.ReusableComponents.interfaces;

public interface ParseDouble {
    default Double parseDouble(String str) {
        if (str == null) return null;
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
