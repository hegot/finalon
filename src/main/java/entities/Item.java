package entities;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Item {
    private int id;
    private String name;
    private String shortName;
    private Boolean isPositive;
    private Boolean finResult;
    private int parent;
    private int parentSheet;
    private ObservableMap<String, Integer> values;

    public Item(
            int id,
            String name,
            String shortName,
            Boolean isPositive,
            Boolean finResult,
            int parent,
            int parentSheet
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.isPositive = isPositive;
        this.finResult = finResult;
        this.parent = parent;
        this.parentSheet = parentSheet;
        this.values = FXCollections.observableHashMap();
    }

    public Item(
            int id,
            String name,
            String shortName,
            Boolean isPositive,
            Boolean finResult,
            int parent,
            int parentSheet,
            ObservableMap<String, Integer> values
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.isPositive = isPositive;
        this.finResult = finResult;
        this.parent = parent;
        this.parentSheet = parentSheet;
        this.values = values;
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


    public Boolean getFinResult() {
        return finResult;
    }

    public void setFinResult(Boolean finResult) {
        this.finResult = finResult;
    }

    public Boolean getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(Boolean isPositive) {
        this.isPositive = isPositive;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getParentSheet() {
        return parentSheet;
    }

    public void setParentSheet(int parentSheet) {
        this.parentSheet = parentSheet;
    }

    public ObservableMap<String, Integer> getValues() {
        return this.values;
    }

    public void setValues(ObservableMap<String, Integer> values) {
        this.values = values;
    }

    public BooleanProperty isPositive() {
        return new SimpleBooleanProperty(this, "isPositive", isPositive);
    }
}
