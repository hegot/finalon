package finalonWindows.reusableComponents.autocomplete;

import database.template.DbItemHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Suggestions {

    public TreeSet<String> entries;

    public SortedSet<String> subSet;

    private ContextMenu entriesPopup;

    private String text;

    private AutoCompleteTextArea anchor;

    private int indexStart;

    private int indexEnd;


    public Suggestions() {
        DbItemHandler dbItemHandler = new DbItemHandler();
        entries =  new TreeSet<String>();
        entries = dbItemHandler.getCodes();
        entriesPopup = new ContextMenu();
        this.text = "";
        setSubset("");
    }


    public void change(String text, String endString, AutoCompleteTextArea anchor, int indexStart, int indexEnd){
        this.text = text;
        this.anchor = anchor;
        this.indexStart = indexStart;
        this.indexEnd = indexEnd;

        if (text.length() == 0) {
            entriesPopup.hide();
        } else {
            LinkedList<String> searchResult = new LinkedList<>();
            setSubset(endString);
            if(subSet != null){
                searchResult.addAll(subSet);
            }else{
                String a ="asd";
            }

            if (entries.size() > 0){
                populatePopup(searchResult);
                if (!entriesPopup.isShowing())
                {
                    if(anchor != null){
                        entriesPopup.show(anchor, Side.BOTTOM, 0, 0);
                    }
                }
            } else {
                entriesPopup.hide();
            }
        }
    }

    private void setSubset(String endString){
        if(endString != null && endString.length() > 0){
            this.subSet = entries.subSet(endString, endString + Character.MAX_VALUE);
        }else{
            this.subSet = new TreeSet<String>();
        }
    }

    public void hide(){
        entriesPopup.hide();
    }


    private void substitute(String result){
        int len = text.length();
        /*System.out.println(indexStart);
        System.out.println(indexEnd);
        System.out.println(len);
        System.out.println(result);
        System.out.println(text);*/
        String ending = indexEnd <= len ?  text.substring(indexEnd) : "";
        String beginning = indexStart <= len ? text.substring(0, indexStart) : "";
        String replacement = beginning + result + ending;
        if(indexStart== 0 && indexEnd==0){
            anchor.setText(result);
        }else{
            anchor.setText(replacement);
        }
    }

    /**
     * Populate the entry set with the given search results.  Display is limited to 10 entries, for performance.
     * @param searchResult The set of matching strings.
     */
    private void populatePopup(List<String> searchResult) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
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
}
