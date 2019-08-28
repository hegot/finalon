package finalon.reportGeneration.interpreter.ReusableComponents.interfaces;

import javafx.beans.property.SimpleStringProperty;

public class Diff {
    public static SimpleStringProperty diff(Double startVAl, Double endVal) {
        if (startVAl != null && endVal != null) {
            String absolute = CommaFormat.format(endVal - startVAl);
            return new SimpleStringProperty(absolute);
        }
        return null;
    }
}
