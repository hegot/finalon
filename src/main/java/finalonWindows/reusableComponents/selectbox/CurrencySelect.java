package finalonWindows.reusableComponents.selectbox;

import database.setting.DbSettingHandler;
import defaultData.DefaultCurrency;
import globalReusables.Setting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

public class CurrencySelect {
    public static ComboBox<String> get(ObservableMap<String, String> settings) {
        ComboBox<String> currencyBox = new ComboBox<String>();
        ObservableList<String> currencies = DefaultCurrency.getCurrencies();
        String defaultVal = DbSettingHandler.getSetting(Setting.defaultCurrency);
        int index = 0;
        for (int j = 0; j < currencies.size(); j++) {
            if (defaultVal.equals(currencies.get(j))) {
                index = j;
            }
        }
        currencyBox.setItems(currencies);
        settings.put("currency", currencies.get(0));
        currencyBox.getSelectionModel().select(index);
        currencyBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2 != null) {
                    System.out.println(arg2);
                    settings.replace("defaultCurrency", arg2);
                }
            }
        });
        return currencyBox;
    }
}
