package entities;

public class Formula {
    private int id;
    private String name;
    private String shortName;
    private String value;
    private String description;
    private String category;
    private String unit;
    private int parent;

    public Formula(
            int id,
            String name,
            String shortName,
            String value,
            String description,
            String category,
            String unit,
            int parent
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.value = value;
        this.description = description;
        this.category = category;
        this.unit = unit;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String toString(Formula formula) {
        return formula == null ? null : formula.getName();
    }
}