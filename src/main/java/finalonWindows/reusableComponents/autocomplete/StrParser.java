package finalonWindows.reusableComponents.autocomplete;

import java.util.ArrayList;

class StrParser extends ParserBase {
    private int indexStart;
    private int indexEnd;
    private String endString;


    void change(String input, String text, int start, int end) {
        if (start > 0 && end > 0) {
            input = addChar(input, text, start);
            String[] words = getChunks(input, false);
            for (String s : words) {
                int len = input.length();
                if (len > 0 && len > s.length()) {
                    for (Integer indexStart : getIndexes(input, s)) {
                        setEndString(s, indexStart, start, end);
                    }
                } else {
                    setEndString(s, 0, start, end);
                }

            }
        } else {
            setEndString(text, 0, start, end);
        }

    }

    private ArrayList<Integer> getIndexes(String phrase, String word) {
        ArrayList<Integer> locations = new ArrayList<>();
        int pos = 0;
        while ((pos = phrase.indexOf(word, pos)) != -1) {
            locations.add(pos);
            pos++;
        }
        return locations;
    }


    private void setEndString(String s, int indexStart, int start, int end) {
        int indexEnd = indexStart + s.length();
        if (start >= indexStart && end <= indexEnd) {
            this.endString = s;
            this.indexStart = indexStart;
            if (end == indexEnd) {
                this.indexEnd = indexEnd;
            } else {
                this.indexEnd = indexEnd - 1;
            }
        }
    }


    private String addChar(String str, String letter, int position) {
        if (letter.length() > 0) {
            char ch = letter.charAt(0);
            return str.substring(0, position) + ch + str.substring(position);
        } else {
            return str;
        }
    }

    int start() {
        return this.indexStart;
    }

    int end() {
        return this.indexEnd;
    }

    String endString() {
        return this.endString;
    }
}
