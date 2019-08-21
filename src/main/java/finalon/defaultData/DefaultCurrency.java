package finalon.defaultData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;

public class DefaultCurrency {
    public static ObservableList<String> getCurrencies() {
        ObservableList<String> Currencies = FXCollections.observableArrayList();
        String[] arr = {
                "USD", "RUR", "EUR",
                "GBP", "AED", "ARA",
                "ARS", "AUD", "BGL",
                "BHD", "BRL", "BWP",
                "BYR", "CAD", "CHF",
                "CLP", "CNY", "COP",
                "CSD", "CZK", "DKK",
                "DOP", "EEK", "EGP",
                "GHS", "GRD", "HKD",
                "HRD", "HRK", "HUF",
                "IDR", "IQD", "INR",
                "JPY", "KRW", "KSH",
                "KWD", "KZT", "LTL",
                "LVL", "MDL", "MUR",
                "MXN", "MYR", "MZN",
                "NGN", "NOK", "NPR",
                "NZD", "PHP", "PKR",
                "PLN", "QAR", "ROL",
                "SAR", "SCR", "SDG",
                "SEK", "SGD", "SZL",
                "THB", "TMM", "TRY",
                "TWD", "TZS", "VEF",
                "VND", "UAH", "UGX",
                "UZS", "ZAR", "ZMK"
        };
        Collections.addAll(Currencies, arr);
        return Currencies;
    }
}
