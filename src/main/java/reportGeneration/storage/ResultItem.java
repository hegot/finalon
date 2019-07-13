package reportGeneration.storage;

public class ResultItem<T> {
    private T item;
    private String type;

    public ResultItem(T item, String type) {
        this.item = item;
        this.type = type;
    }

    public T get() {
        return this.item;
    }

    public void add(T item) {
        this.item = item;
    }

    public String getType() {
        return this.type;
    }
}