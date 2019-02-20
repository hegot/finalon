package finalonWindows.reusableComponents.autocomplete;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * This class is a TextField which implements an "autocomplete" functionality, based on a supplied list of entries.
 *
 * @author Caleb Brinkman
 */
public class AutoCompleteTextArea extends TextArea {
    /**
     * The existing autocomplete entries.
     */

    private Suggestions suggestions;
    private StrParser parser;
    /**
     * Construct a new AutoCompleteTextField.
     */
    public AutoCompleteTextArea(String value) {
        super();
        this.setText(value);
        parser = new StrParser();
        suggestions = new Suggestions();
        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                suggestions.hide();
            }
        });

    }

    @Override
    public void replaceText(int start, int end, String text) {
        String cur = getText();
        int len = text.length();
        if (len > 0) {
            parser.change(cur, text, start, end);
            AutoCompleteTextArea anchor = AutoCompleteTextArea.this;
            //System.out.println(parser.endString());
            suggestions.change(cur, parser.endString(), anchor, parser.start(), parser.end());

            if (start > 0 && cur.length() >= start) {
                Character before = cur.charAt(start - 1);
                String beforeStr = Character.toString(before);
                if (Character.isDigit(before)) {
                    if (!text.matches("[^0-9.*+-/:\\]]")) {
                        super.replaceText(start, end, text);
                    }
                }

                if (Character.isLetter(before)) {
                    if (!text.matches("[^a-zA-Z.*+()-/:\\[]")) {
                        if (text.length() > 0) {
                            if (text.equals(")") || text.equals("[")) {
                                super.replaceText(start, end, text);
                            } else if (!text.equals("(")) {
                                if (suggestions.subSet.size() > 0) {
                                    super.replaceText(start, end, text);
                                }
                            }
                        } else {
                            super.replaceText(start, end, text);
                        }
                    }
                    if (!text.matches("[^0-9]")) {
                        text = text.replace(":", "/");
                        super.replaceText(start, end, text);
                    }
                }

                if (!beforeStr.matches("[^.*+()-/:]")) {
                    if (!text.matches("[^a-zA-Z0-9()\\[]")) {
                        super.replaceText(start, end, text);
                    }
                }

                if (beforeStr.matches("[\\[]")) {
                    if (!text.matches("[^0-9]")) {
                        super.replaceText(start, end, text);
                    }
                }

                if (beforeStr.matches("[\\]]")) {
                    if (!text.matches("[^.*+()-/:]")) {
                        super.replaceText(start, end, text);
                    }
                }
            } else if (start == 0) {
                if (!text.matches("[^a-zA-Z0-9.*+()-/:\\[\\]]")) {
                    text = text.replace(":", "/");
                    super.replaceText(start, end, text);
                }
            }
        } else {
            super.replaceText(start, end, text);
        }

    }


    @Override
    public void replaceSelection(String text) {
        if (!text.matches("[^a-zA-Z0-9.*+()-/:\\[\\]]")) {
            text = text.replace(":", "/");
            super.replaceSelection(text);
        }
    }
}