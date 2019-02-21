package finalonWindows.reusableComponents.autocomplete;

import java.util.regex.Pattern;

public class ParserBase {
    public String[] getChunks(String input) {
        String charToDel = "()-+/*:[].";
        String pat = "[" + Pattern.quote(charToDel) + "]";
        String str = input.replaceAll(pat, " ");
        str = str.replaceAll("^\\d+|\\d+$", "");
        str = str.replaceAll("\\d", "");
        str = str.replaceAll("\\s{2,}", " ").trim();
        String[] words = (str.split(" "));
        return words;
    }
}
