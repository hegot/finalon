package finalonWindows.reusableComponents.selectbox;

import entities.Formula;
import entities.FormulaConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

public class StandardSelect extends Choices {
    public static ComboBox get(ObservableMap<String, Object> settings) {
        ComboBox<Formula> cb = new ComboBox<>(getChoices(0));
        cb.setConverter(new FormulaConverter());
        cb.getSelectionModel().selectFirst();
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null) {
                    settings.replace("standard", arg2);
                }
            }
        });
        return cb;
    }
}