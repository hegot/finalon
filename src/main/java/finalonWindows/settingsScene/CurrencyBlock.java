package finalonWindows.settingsScene;

import finalonWindows.reusableComponents.selectbox.CurrencySelect;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CurrencyBlock {
    private ObservableMap<String, String> settings;

    CurrencyBlock(ObservableMap<String, String> settings) {
        this.settings = settings;
    }

    public VBox get() {
        VBox vboxrow = new VBox(10);
        vboxrow.getStyleClass().add("vbox-row");
        vboxrow.setPrefWidth(400);
        Label label = new Label("Select default currency");
        label.getStyleClass().add("sub-label");
        vboxrow.getChildren().addAll(label, currencyBox());
        return vboxrow;
    }

    private ComboBox<String> currencyBox() {
        ComboBox<String> currencyBox = CurrencySelect.get(FXCollections.observableHashMap());
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
