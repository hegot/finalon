package finalonWindows.reusableComponents;

import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;
import java.util.List;

public class NumField extends TextField {
    public NumField(String text, Boolean isPositive) {
        setText(text);
        getStyleClass().add("num-field");
        NumField field = this;
        ContextMenu err = new ContextMenu();
        err.setStyle("-fx-background-color: #FFFFFF;");
        MenuItem item = new MenuItem("Item already negative");
        item.getStyleClass().add("num-err");
        err.getItems().addAll(item);

        this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent t) {
                char ar[] = t.getCharacter().toCharArray();
                char ch = ar[t.getCharacter().toCharArray().length - 1];
                boolean consume = false;
                if (!(ch >= '0' && ch <= '9')) {
                    consume = true;
                }
                String s = Character.toString(ch);
                String numberFormat = SettingsStorage.get("numberFormat");
                List<String> allowed = new ArrayList<String>();
                if (numberFormat.equals("comma")) {
                    allowed.add(",");
                } else {
                    allowed.add(".");
                }
                if (isPositive) {
                    allowed.add("-");
                }
                for (String i : allowed) {
                    if (s.contains(i)) {
                        consume = false;
                    }
                }
                if (consume) {
                    if (!isPositive && (ch == '-')) {
                        err.show(field, Side.BOTTOM, 0, 0);
                    }
                    t.consume();
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
    }
}
