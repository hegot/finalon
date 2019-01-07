package entities;

public class Sheet {

    public int id;
    public String name;
    public int parentTpl;

    public Sheet(
            int id,
            String name,
            int parentTpl
    ) {
        this.id = id;
        this.name = name;
        this.parentTpl = parentTpl;
    }

}
