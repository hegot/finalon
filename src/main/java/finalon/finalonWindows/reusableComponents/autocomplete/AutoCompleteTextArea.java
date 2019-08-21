package finalon.finalonWindows.reusableComponents.autocomplete;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

import java.util.TreeSet;


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
    private StrValidator strValidator;
    private TreeSet<String> errors;

    /**
     * Construct a new AutoCompleteTextField.
     */
    public AutoCompleteTextArea(String value) {
        super();
        this.setText(value);
        parser = new StrParser();
        suggestions = new Suggestions();
        strValidator = new StrValidator();

        this.errors = new TreeSet<String>();

        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                suggestions.hide();
                FinalValidation finalValidation = new FinalValidation(getText(), suggestions.entries);
                errors = finalValidation.getErrors();
            }
        });

    }


    @Override
    public void replaceText(int start, int end, String text) {
        String cur = getText();
        text = text.replace(":", "/");
        int len = text.length();
        if (len > 0) {
            parser.change(cur, text, start, end);
            AutoCompleteTextArea anchor = AutoCompleteTextArea.this;
            suggestions.change(cur, parser.endString(), anchor, parser.start(), parser.end());

            if (start > 0 && cur.length() >= start) {
                Character before = cur.charAt(start - 1);
                Boolean valid = strValidator.validate(before, text);
                if (valid) {
                    super.replaceText(start, end, text);
                }
            } else if (start == 0) {
                if (strValidator.validStart(text)) {
                    super.replaceText(start, end, text);
                }
            }
        } else {
            super.replaceText(start, end, text);
        }

    }


    @Override
    public void replaceSelection(String text) {
        if (strValidator.validSelection(text)) {
            text = text.replace(":", "/");
            super.replaceSelection(text);
        }
    }


    public TreeSet<String> getErrors() {
        return errors;
    }
}