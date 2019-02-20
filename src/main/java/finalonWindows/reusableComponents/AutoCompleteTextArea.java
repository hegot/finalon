package finalonWindows.reusableComponents;

import database.template.DbItemHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.*;
import java.util.regex.Pattern;

import java.lang.String.*;
import java.lang.Character.*;

import static jdk.nashorn.internal.objects.NativeString.indexOf;

/**
 * This class is a TextField which implements an "autocomplete" functionality, based on a supplied list of entries.
 * @author Caleb Brinkman
 */
public class AutoCompleteTextArea extends TextArea
{
    /** The existing autocomplete entries. */
    private TreeSet<String> entries;
    /** The popup used to select an entry. */
    private ContextMenu entriesPopup;

    private String[] chunks;

    private String endString;

    private SortedSet<String> subSet;

    private int indexStart;

    private int indexEnd;

    /** Construct a new AutoCompleteTextField. */
    public AutoCompleteTextArea(String value) {
        super();
        this.setText(value);
        DbItemHandler dbItemHandler = new DbItemHandler();
        entries =  new TreeSet<String>();
        entries = dbItemHandler.getCodes();
        entriesPopup = new ContextMenu();
        populateChunks(value, 0,0);
        this.endString = "";
        setSubset();
        this.indexStart = 0;
        this.indexEnd = 0;
        textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                handleSuggestions();
            }
        });

        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                entriesPopup.hide();
                /*if(isValidExpression(getText())){
                    System.out.println("ok");
                }else{
                    System.out.println("no ok");
                }*/
            }
        });

    }

    private void setSubset(){
       this.subSet = entries.subSet(endString, endString + Character.MAX_VALUE);
    }

    private void handleSuggestions(){
        if (getText().length() == 0) {
            entriesPopup.hide();
        } else {
            LinkedList<String> searchResult = new LinkedList<>();
            searchResult.addAll(subSet);

            if (entries.size() > 0){
                populatePopup(searchResult);
                if (!entriesPopup.isShowing())
                {
                    Node anchor = AutoCompleteTextArea.this;
                    if(anchor != null){
                        entriesPopup.show(anchor, Side.BOTTOM, 0, 0);
                    }
                }
            } else {
                entriesPopup.hide();
            }
        }
    }

    private void populateChunks(String input, int start, int end){
        this.endString = "";
        String charToDel = "()-+/*:[].";
        String pat = "[" + Pattern.quote(charToDel) + "]";
        String str = input.replaceAll(pat," ");
        str = str.replaceAll("^\\d+|\\d+$", "");
        str = str.replaceAll("\\s{2,}", " ").trim();
        String[] words = (str.split(" "));

        Set<String> unique = new HashSet<>();
        Set<String> duplicate = new HashSet<>();

        for (String val : words)
            (unique.contains(val) ? duplicate : unique).add(val);

        for (String s : unique) {
            int indexStart = input.indexOf(s);
            setEndString(s, indexStart, start, end);
        }

        for (String s : duplicate) {
            for(Integer indexStart : getIndexes(input, s)){
                setEndString(s, indexStart, start, end);
            }
        }

        this.chunks = words;
    }


    private void setEndString(String s, int indexStart, int start, int end){
        int indexEnd = indexStart + s.length();
        if(start >= indexStart && end <= indexEnd){
            this.endString = s;
            this.indexStart = indexStart;
            if(end == indexEnd){
                this.indexEnd = indexEnd-1;
            }else{
                this.indexEnd = indexEnd;
            }
        }
    }


    private ArrayList<Integer> getIndexes(String phrase, String word){
        ArrayList<Integer> locations = new ArrayList<>();
        int loc = phrase.indexOf(word);
        while (loc != -1) {
            locations.add(loc);
            loc = phrase.indexOf(word, loc + 1);
        }
        return locations;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        String cur =  getText();
        int len = cur.length();
        if(len > 0) {
            String modified = addChar(cur, text, start);
            populateChunks(modified, start, end);
            setSubset();
            if(start > 0 && len >= start){
                Character before = cur.charAt(start - 1);
                String beforeStr = Character.toString(before);
                if (Character.isDigit(before)) {
                    if (!text.matches("[^0-9.*+()-/:]")) {
                        super.replaceText(start, end, text);
                    }
                }

                if (Character.isLetter(before)) {
                    if (!text.matches("[^a-zA-Z.*+()-/:]")) {
                        if (text.length() > 0) {
                            if(text.equals(")")){
                                super.replaceText(start, end, text);
                            }else if(!text.equals("(")){
                                if (subSet.size() > 0) {
                                    super.replaceText(start, end, text);
                                }
                            }
                        } else {
                            super.replaceText(start, end, text);
                        }
                    }
                }

                if (!beforeStr.matches("[^.*+()-/:]")) {
                    if (!text.matches("[^a-zA-Z0-9]")) {
                        super.replaceText(start, end, text);
                    }
                }
            }else if(start == 0){
                if (!text.matches("[^a-zA-Z0-9.*+()-/:\\[\\]]")) {
                    text = text.replace(":", "/");
                    super.replaceText(start, end, text);
                }
            }
        }else{
            super.replaceText(start, end, text);
        }


        /*else if (!text.matches("[^a-zA-Z0-9.*+()-/:\\[\\]]")) {

            text = text.replace(":", "/");

            super.replaceText(start, end, text);

        }*/
    }


    private void substitute(String result){
        String cur =  getText();
        int len = cur.length();
        System.out.println(indexStart);
        System.out.println(indexEnd);
        System.out.println(len);
        System.out.println(result);
        String ending = indexEnd < len ?  cur.substring(indexEnd) : "";
        String beginning = indexStart < len ? cur.substring(0, indexStart) : "";
        String replacement = beginning + result + ending;
        if(indexStart== 0 && indexEnd==0){
            setText(result);
        }else{
            setText(replacement);
        }
    }


    @Override
    public void replaceSelection(String text) {
        if (!text.matches("[^a-zA-Z0-9.*+()-/:\\[\\]]")) {
            text = text.replace(":", "/");
            super.replaceSelection(text);
        }
    }

    /**
     * Get the existing set of autocomplete entries.
     * @return The existing autocomplete entries.
     */
    public SortedSet<String> getEntries() { return entries; }

    /**
     * Populate the entry set with the given search results.  Display is limited to 10 entries, for performance.
     * @param searchResult The set of matching strings.
     */
    private void populatePopup(List<String> searchResult) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        // If you'd like more entries, modify this line.
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i < count; i++)
        {
            final String result = searchResult.get(i);
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent) {
                    substitute(result);
                    entriesPopup.hide();
                }
            });
            menuItems.add(item);
        }
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);
    }



    private String addChar(String str, String letter, int position) {
        if(letter.length() > 0){
            char ch = letter.charAt(0);
            return str.substring(0, position) + ch + str.substring(position);
        }else{
            return str;
        }
    }







    public static boolean isAnOperator(char c) {
        switch (c) {
            case '*':
            case '/':
            case '+':
            case '-':
            case '%':
                return true;
            default:
                return false;
        }
    }
    public static boolean isANumber(char c){
        return ((int)c) >= 48 && ((int)c) <= 57;
    }

    private static boolean isValidExpression(String expression){
        //TEST 1: False if expression starts or ends with an operator
        if (isAnOperator(expression.charAt(0)) || isAnOperator(expression.charAt(expression.length()-1)))
            return false;

        //TEST 2: False if test has mismatching number of opening and closing parantheses
        int openParenthCount = 0;
        int closedParenthCount = 0;
        int consecutiveOpCount = 0;

        for (int i = 0; i < expression.length(); i++){
            if (expression.charAt(i) == '('){
                openParenthCount++;
                consecutiveOpCount = 0;
                //SUBTEST: False if expression ends with '('
                if (i == expression.length()-1) return false;
            }
            if (expression.charAt(i) == ')'){
                closedParenthCount++;
                consecutiveOpCount = 0;
                //SUBTEST: False if expression starts with ')'
                if (i == 1) return false;

            }
            if (isAnOperator(expression.charAt(i))){
                consecutiveOpCount++;
                //TEST 3: False if operator is preceded by opening paranthesis or followed by closing paranthesis
                if (expression.charAt(i-1) == '(' || expression.charAt(i+1) == ')')
                    return false;
            }
            //TEST 4: False if 2 operators found next to each other
            if (isAnOperator(expression.charAt(i)) || consecutiveOpCount > 0)
                return false;
        }
        if (openParenthCount != closedParenthCount)
            return false;

        //LAST TEST 5: All preceding checks ensure that
        // by this point, all exisiting parantheses open and close properly
        return true;
    }
}