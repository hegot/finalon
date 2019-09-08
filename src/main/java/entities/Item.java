package entities;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class Item implements Comparable<Item>, Cloneable {
    private int id;
    private String name;
    private String shortName;
    private Boolean isPositive;
    private Boolean finResult;
    private int parent;
    private int parentSheet;
    private ObservableMap<String, Double> values;
    private Integer level;
    private Integer weight;
    private Boolean updated;
    private Boolean shortNameUpdated;

    public Item(
            int id,
            String name,
            String shortName,
            Boolean isPositive,
            Boolean finResult,
            int parent,
            int parentSheet,
            Integer level,
            Integer weight
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.isPositive = isPositive;
        this.finResult = finResult;
        this.parent = parent;
        this.parentSheet = parentSheet;
        this.values = FXCollections.observableHashMap();
        this.level = level;
        this.weight = weight;
        this.updated = false;
        this.shortNameUpdated = false;
    }

    public Item(
            int id,
            String name,
            String shortName,
            Boolean isPositive,
            Boolean finResult,
            int parent,
            int parentSheet,
            Integer level
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.isPositive = isPositive;
        this.finResult = finResult;
        this.parent = parent;
        this.parentSheet = parentSheet;
        this.values = FXCollections.observableHashMap();
        this.level = level;
        this.weight = -1;
        this.updated = false;
        this.shortNameUpdated = false;
    }

    public Item(
            int id,
            String name,
            String shortName,
            Boolean isPositive,
            Boolean finResult,
            int parent,
            int parentSheet,
            ObservableMap<String, Double> values,
            Integer level,
            Integer weight
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.isPositive = isPositive;
        this.finResult = finResult;
        this.parent = parent;
        this.parentSheet = parentSheet;
        this.values = values;
        this.level = level;
        this.weight = weight;
        this.updated = false;
        this.shortNameUpdated = false;
    }

    public Item(
            int id,
            String name,
            String shortName,
            Boolean isPositive,
            Boolean finResult,
            int parent,
            int parentSheet,
            ObservableMap<String, Double> values,
            Integer level
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.isPositive = isPositive;
        this.finResult = finResult;
        this.parent = parent;
        this.parentSheet = parentSheet;
        this.values = values;
        this.level = level;
        this.weight = -1;
        this.updated = false;
        this.shortNameUpdated = false;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public Boolean getUpdated() {
        return updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    public Boolean getShortNameUpdated() {
        return shortNameUpdated;
    }

    public void setShortNameUpdated(Boolean shortNameUpdated) {
        this.shortNameUpdated = shortNameUpdated;
    }


    public Double getLastVal() {
        ArrayList<String> arr = Periods.getPeriodArr();
        String key = arr.get(arr.size() - 1);
        if (key != null) {
            return values.get(key);
        }
        return null;
    }

    public Double getFirstVal() {
        ArrayList<String> arr = Periods.getPeriodArr();
        if (arr.get(0) != null) {
            return values.get(arr.get(0));
        }
        return null;
    }

    public Double getVal(String period) {
        Double val = null;
        if (values.size() > 0 && period != null) {
            val = values.get(period);
        }
        return val;
    }

    public String getStrVal(String period) {
        String val = "";
        if (values.size() > 0) {
            Double value = values.get(period);
            if(value != null){
                val = Formatter.doubleCommaFormat(value);
            }
        }
        return val;
    }

    public void setVal(String period, Double val) {
        values.put(period, val);
    }

    public Double getChange() {
        Double last = getLastVal();
        Double first = getFirstVal();
        if (last != null && first != null && first != 0) {
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

    public void updateItem(String value, String param) {
        if (value.length() > 0) {
            values.put(param, Formatter.parseDouble(value));
        } else {
            values.remove(param);
        }
    }

    @Override
    public int compareTo(Item o) {
        return this.getWeight().compareTo(o.getWeight());
    }
}
