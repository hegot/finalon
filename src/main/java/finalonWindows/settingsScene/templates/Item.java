package finalonWindows.settingsScene.templates;
import java.util.UUID;

public class Item {
    private String id;
    private String name;
    private int code;
    private String shortName;
    private Boolean isPositive;
    private String parent;
    private Double defaultValue;




    public Item(
        String id,
        String name,
        int code,
        String shortName,
        Boolean isPositive,
        String parent,
        Double defaultValue
    ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.shortName = shortName;
        this.isPositive = isPositive;
        this.parent = parent;
        this.defaultValue = defaultValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Boolean getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(Boolean isPositive) {
        this.isPositive = isPositive;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Double getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
    }

}
