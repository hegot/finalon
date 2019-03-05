package finalonWindows.reusableComponents.selectbox;

import database.setting.DbSettingHandler;
import defaultData.DefaultCurrency;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

public class CurrencySelect {
    public static ComboBox<String> get(ObservableMap<String, Object> settings) {
        ComboBox<String> currencyBox = new ComboBox<String>();
        ObservableList<String> currencies = DefaultCurrency.getCurrencies();
        DbSettingHandler dbSettingHandler = new DbSettingHandler();
        String defaultVal = dbSettingHandler.getSetting("defaultCurrency");
        int index = 0;
        for (int j = 0; j < currencies.size(); j++) {
            if (defaultVal.equals(currencies.get(j))) {
                index = j;
            }
        }
        currencyBox.setItems(currencies);
        currencyBox.getSelectionModel().select(index);
        currencyBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2 != null) {
                    settings.replace("defaultCurrency", arg2);
                }
            }
        });
        return currencyBox;
    }
}
