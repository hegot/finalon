package entities;

import defaultData.EvaluationTypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class Formula {
    private int id;
    private String name;
    private String shortName;
    private String value;
    private String description;
    private String category;
    private String unit;
    private int parent;
    private ObservableList<Formula> childs;
    private ObservableMap<String, Double> periods;

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
        this.childs = FXCollections.observableArrayList();
        this.periods = FXCollections.observableHashMap();
    }

    public Formula(
            int id,
            String name,
            String shortName,
            String value,
            String description,
            String category,
            String unit,
            int parent,
            ObservableList<Formula> childs
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.value = value;
        this.description = description;
        this.category = category;
        this.unit = unit;
        this.parent = parent;
        this.childs = childs;
        this.periods = FXCollections.observableHashMap();
    }

    public Formula(
            int id,
            String name,
            String shortName,
            String value,
            String description,
            String category,
            String unit,
            int parent,
            ObservableMap<String, Double> periods
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.value = value;
        this.description = description;
        this.category = category;
        this.unit = unit;
        this.parent = parent;
        this.childs = null;
        this.periods = periods;
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


    public ObservableList<Formula> getChilds() {
        return this.childs;
    }

    public ObservableList<Formula> getChildsOfType(EvaluationTypes type) {
        ObservableList<Formula> returnChilds = FXCollections.observableArrayList();;
        for (Formula child : this.childs) {
            if(child.getName().equals(type.toString())){
                returnChilds.add(child);
            }
        }
        return returnChilds;
    }

    public void setChilds(ObservableList<Formula> childs) {
        this.childs = childs;
    }

    public ObservableMap<String, Double> getPeriods() {
        return this.periods;
    }

    public void setPeriods(ObservableMap<String, Double> periods) {
        this.periods = periods;
    }

    public Double getLastVal() {
        ArrayList<String> arr = Periods.getPeriodArr();
        String key = arr.get(arr.size() - 1);
        if (key != null) {
            return periods.get(key);
        }
        return null;
    }

    public Double getFirstVal() {
        ArrayList<String> arr = Periods.getPeriodArr();
        if (arr.get(0) != null) {
            Double val = periods.get(arr.get(0));
            if (val == null) {
                if (arr.size() > 1 && arr.get(1) != null) {
                    val = periods.get(arr.get(1));
                }
            }
            return val;
        }
        return null;
    }

    public Double getVal(String period) {
        Double val = null;
        if (periods.size() > 0 && period != null) {
            val = periods.get(period);
        }
        return val;
    }

    public String getFormulaStart() {
        ArrayList<String> arr = Periods.getPeriodArr();
        if (arr.get(0) != null) {
            Double val = periods.get(arr.get(0));
            if (val == null) {
                if (arr.size() > 1 && arr.get(1) != null) {
                    return arr.get(1);
                }
            }
        }
        return arr.get(0);
    }

}