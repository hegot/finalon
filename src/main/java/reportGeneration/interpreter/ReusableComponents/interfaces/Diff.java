package reportGeneration.interpreter.ReusableComponents.interfaces;

import javafx.beans.property.SimpleStringProperty;

public interface Diff extends CommaFormat {
    default SimpleStringProperty diff(Double startVAl, Double endVal) {
        if (startVAl != null && endVal != null) {
            String absolute = commaFormat(endVal - startVAl);
            return new SimpleStringProperty(absolute);
        }
        return null;
    }
}
