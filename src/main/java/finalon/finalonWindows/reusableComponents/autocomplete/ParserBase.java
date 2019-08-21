package finalon.finalonWindows.reusableComponents.autocomplete;

import java.util.regex.Pattern;

public class ParserBase {
    public String[] getChunks(String input, boolean leavePeriods) {
        String charToDel = leavePeriods ? "()-+/*:." : "()-+/*:[].";
        String pat = "[" + Pattern.quote(charToDel) + "]";
        String str = input.replaceAll(pat, " ");
        str = str.replaceAll("^\\d+|\\d+$", "");
        if (!leavePeriods) {
            str = str.replaceAll("\\d", "");
        }
        str = str.replaceAll("\\s{2,}", " ").trim();
        String[] words = (str.split(" "));
        return words;
    }

}
