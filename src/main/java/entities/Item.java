package entities;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class Item {
    private int id;
    private String name;
    private String shortName;
    private Boolean isPositive;
    private Boolean finResult;
    private int parent;
    private int parentSheet;
    private ObservableMap<String, Double> values;

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
            ObservableMap<String, Double> values
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

    public ObservableMap<String, Double> getValues() {
        return this.values;
    }

    public void setValues(ObservableMap<String, Double> values) {
        this.values = values;
    }

    public BooleanProperty isPositive() {
        return new SimpleBooleanProperty(this, "isPositive", isPositive);
    }


    public Double getLastVal() {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        String key = arr.get(arr.size() - 1);
        if (key != null) {
            return values.get(key);
        }
        return null;
    }

    public Double getFirstVal() {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        if (arr.get(0) != null) {
            return values.get(arr.get(0));
        }
        return null;
    }

    public Double getVal(String period) {
        Double val = null;
        if (values.size() > 0) {
            val = values.get(period);
        }
        return val;
    }

    public Double getChange() {
        Double last = getLastVal();
        Double first = getFirstVal();
        if (last != null && first != null) {
            return ((last - first) / first) * 100;
        }
        return null;
    }

    public String trend() {
        Double last = getLastVal();
        Double first = getFirstVal();
        if (last != null && first != null) {
            if ((last - first) > 0) {
                return "INCREASED";
            }
            if ((last - first) < 0) {
                return "DECREASED";
            }
            if ((last - first) == 0) {
                return "STABLE";
            }
        }
        return "STABLE";
    }
}
