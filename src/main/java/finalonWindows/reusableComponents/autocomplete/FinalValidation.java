package finalonWindows.reusableComponents.autocomplete;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;

class FinalValidation extends ParserBase {

    private String input;
    private TreeSet<String> entries;
    private TreeSet<String> errors;

    FinalValidation(String input, TreeSet<String> entries) {
        this.input = input;
        this.entries = entries;
        this.errors = new TreeSet<String>();
        validateWords();
        validateBrackets();
        validateSequence();
        validateEnding();
    }

    private static boolean isBalanced(String s, Character ch1, Character ch2) {
        Map<Character, Character> openClosePair = new HashMap<Character, Character>();
        openClosePair.put(ch1, ch2);

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {

            if (openClosePair.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));

            } else if (openClosePair.containsValue(s.charAt(i))) {
                if (stack.isEmpty())
                    return false;
                if (openClosePair.get(stack.pop()) != s.charAt(i))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    private void validateWords() {
        String[] words = getChunks(input);
        for (String s : words) {
            if (!entries.contains(s)) {
                errors.add("Index with name '" + s + "' do not exist - please correct or add it to your template");
            }
        }
    }

    private void validateBrackets() {
        if (!isBalanced(input, '(', ')')) {
            errors.add("Some Curl Brackets '( )' are not closed inside your formula");
        }
        if (!isBalanced(input, '[', ']')) {
            errors.add("Some Square Brackets '[ ]' are not closed inside your formula");
        }
    }

    private void validateSequence() {
        for (int i = 0; i < input.length() - 1; i++) {
            String s1 = Character.toString(input.charAt(i));
            String s2 = Character.toString(input.charAt(i + 1));
            String message = "Sequence with two operators '" + s1 + s2 + "' is not permitted";
            if (isOperator(s1) && isOperator(s2)) {
                errors.add(message);
            }
            if (isOperator(s1) && s2.equals("]")) {
                errors.add(message);
            }
        }
    }

    private void validateEnding() {
        try {
            if (input.length() > 0) {
                String last = Character.toString(input.charAt(input.length() - 1));
                if (last.matches("[" + StrValidator.OPERATORS + "]")) {
                    errors.add("Formula can not end with operator: '" + last + "'");
                }
            }
        } catch (Exception e) {

        }

    }


    private Boolean isOperator(String str) {
        return !str.matches("[^" + StrValidator.OPERATORS + "]");
    }

    TreeSet<String> getErrors() {
        return errors;
    }
}
