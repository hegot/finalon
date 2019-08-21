package finalon.reportGeneration.storage;

public class TitledItem<T> {
    private T item;
    private String title;

    public TitledItem(String title, T item) {
        this.item = item;
        this.title = title;
    }

    public T get() {
        return this.item;
    }

    public void add(T item) {
        this.item = item;
    }

    public String getTitle() {
        return title;
    }
}
