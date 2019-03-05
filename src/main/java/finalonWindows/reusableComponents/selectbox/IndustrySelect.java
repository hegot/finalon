package finalonWindows.reusableComponents.selectbox;

import entities.Formula;
import entities.FormulaConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

public class IndustrySelect extends Choices {
    public static ComboBox<Formula> get(int defaultStandard, ObservableMap<String, Object> settings) {
        ComboBox<Formula> industryBox = new ComboBox<Formula>();
        industryBox.setConverter(new FormulaConverter());
        ObservableList<Formula> industries = getChoices(defaultStandard);
        industryBox.setItems(industries);
        industryBox.getSelectionModel().selectFirst();
        if(settings.size() > 0){
            industryBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
                @Override
                public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                    if (arg2 != null) {
                        settings.replace("industry", arg2);
                    }
                }
            });
        }
        return industryBox;
    }

}
