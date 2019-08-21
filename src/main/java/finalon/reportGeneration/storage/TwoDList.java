package finalon.reportGeneration.storage;

import java.util.ArrayList;

public class TwoDList extends ArrayList<ArrayList<String>> {

    public void addList(ArrayList<String> element) {
        this.add(element);
    }

    public ArrayList<String> getList(int index) {
        return this.get(index);
    }

    public void addToInnerArray(int index, String element) {
        while (index >= this.size()) {
            this.add(new ArrayList<String>());
        }
        this.get(index).add(element);
    }

    public void addToInnerArray(int index, int index2, String element) {
        while (index >= this.size()) {
            this.add(new ArrayList<String>());
        }

        ArrayList<String> inner = this.get(index);
        while (index2 >= inner.size()) {
            inner.add(null);
        }

        inner.set(index2, element);
    }
}