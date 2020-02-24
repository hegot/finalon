package finalonWindows.reusableComponents;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import reportGeneration.storage.SettingsStorage;

public class NumField extends TextField {


    public NumField(String text, Boolean isPositive) {
        setText(text);
        getStyleClass().add("num-field");
        NumField field = this;
        ContextMenu err = new ContextMenu();
        err.setStyle("-fx-background-color: #FFFFFF;");
        MenuItem item = new MenuItem("The item is negative already");
        item.getStyleClass().add("num-err");
        err.getItems().addAll(item);

        this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent t) {
                char ar[] = t.getCharacter().toCharArray();
                char ch = ar[t.getCharacter().toCharArray().length - 1];
                if (!isPositive && (ch == '-')) {
                    err.show(field, Side.BOTTOM, 0, 0);
                }

            }
        });
        this.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        err.hide();
                    }
                });
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!isPositive) {
                    newValue = newValue.replace("-", "");
                }
                String numberFormat = SettingsStorage.get("numberFormat");
                if (numberFormat.equals("comma")) {
                    newValue = newValue.replaceAll("[^0-9,-]", "");
                } else {
                    newValue = newValue.replaceAll("[^0-9.-]", "");
                }

                setText(newValue);
            }
        });
    }
}
