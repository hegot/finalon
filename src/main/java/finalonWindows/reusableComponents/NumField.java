package finalonWindows.reusableComponents;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import reportGeneration.storage.SettingsStorage;

public class NumField extends TextField {
    public NumField(String text) {
        setText(text);
        getStyleClass().add("num-field");
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
                String[] match;
                if (numberFormat.equals("comma")) {
                    match = new String[]{"-", ","};
                } else {
                    match = new String[]{"-", "."};
                }
                for (String i : match) {
                    if (s.contains(i)) {
                        consume = false;
                    }
                }
                if (consume) {
                    t.consume();
                }
            }
        });
    }
}
