package finalonWindows.reusableComponents.autocomplete;

public class StrValidator {

    public static final String OPERATORS = ".*+-/:";
    public static final String DIGITS = "0-9";
    public static final String LETTERS = "a-zA-Z";
    private Character ch;
    private String text;
    private String beforeStr;

    public Boolean validate(Character ch, String text) {
        this.ch = ch;
        this.text = text;
        this.beforeStr = Character.toString(ch);

        Boolean valid = digitValidate();
        if (!valid) {
            valid = letterValidate();
            if (!valid) {
                valid = afterOperator();
                if (!valid) {
                    valid = afterSquareBracketOpen();
                    if (!valid) {
                        valid = afterSquareBracketClose();
                        if (!valid) {
                            valid = afterRoundBracketOpen();
                            if (!valid) {
                                valid = afterRoundBracketClose();
                                if (!valid) {
                                    valid = afterDigit();
                                }
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }

    private Boolean digitValidate() {
        if (Character.isDigit(ch)) {
            return !text.matches("[^" + DIGITS + OPERATORS + "\\]]");
        }
        return false;
    }

    private Boolean letterValidate() {
        if (Character.isLetter(ch)) {
            if (!text.matches("[^" + LETTERS + OPERATORS + ")\\[]")) {
                if (text.length() > 0) {
                    return true;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean afterDigit() {
        if (!beforeStr.matches("[^" + DIGITS + "]")) {
            return !text.matches("[^" + DIGITS + OPERATORS + ")]");
        }
        return false;
    }

    private Boolean afterOperator() {
        if (!beforeStr.matches("[^" + OPERATORS + "]")) {
            return !text.matches("[^" + LETTERS + DIGITS + "(]");
        }
        return false;
    }

    private Boolean afterSquareBracketOpen() {
        if (beforeStr.matches("[\\[]")) {
            return !text.matches("[^" + DIGITS + "]");
        }
        return false;
    }

    private Boolean afterRoundBracketOpen() {
        if (beforeStr.matches("[(]")) {
            return !text.matches("[^" + LETTERS + DIGITS + "(]");
        }
        return false;
    }

    private Boolean afterRoundBracketClose() {
        if (beforeStr.matches("[)]")) {
            return !text.matches("[^" + OPERATORS + ")]");
        }
        return false;
    }


    private Boolean afterSquareBracketClose() {
        if (beforeStr.matches("[\\]]")) {
            return !text.matches("[^" + OPERATORS + ")]");
        }
        return false;
    }


    Boolean validStart(String text) {
        return !text.matches("[^" + LETTERS + OPERATORS + "()]");
    }

    Boolean validSelection(String text) {
        return !text.matches("[^" + LETTERS + OPERATORS + "()\\[\\]]");
    }

}
