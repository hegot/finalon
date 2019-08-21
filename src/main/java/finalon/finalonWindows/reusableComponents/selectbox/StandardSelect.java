package finalon.finalonWindows.reusableComponents.selectbox;

import finalon.entities.Formula;
import finalon.entities.FormulaConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

public class StandardSelect extends Choices {
    public static ComboBox get(ObservableMap<String, String> settings) {
        ObservableList<Formula> standards = getChoices(0);
        int defaultStandard = standards.get(0).getId();
        ComboBox<Formula> cb = new ComboBox<>(standards);
        cb.setConverter(
                new FormulaConverter()
        );
        cb.getSelectionModel().select(defaultStandard);
        settings.put(
                "standard",
                Integer.toString(defaultStandard)
        );
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null) {
                    settings.replace(
                            "standard",
                            Integer.toString(arg2.getId())
                    );
                }
            }
        });
        return cb;
    }
}