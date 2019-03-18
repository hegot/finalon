package finalonWindows.reusableComponents;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class NumField extends TextField {
    public NumField() {
        this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent t) {
                char ar[] = t.getCharacter().toCharArray();
                char ch = ar[t.getCharacter().toCharArray().length - 1];
                boolean consume = false;
                if (!(ch >= '0' && ch <= '9')) {
                    consume = true;
                }
                String s = Character.toString(ch);
                String[] match = {"-", ".", ","};
                for(String i : match){
                    if(s.contains(i)){
                        consume = false;
                    }
                }
                if(consume){
                    t.consume();
                }
            }
        });
    }
}
