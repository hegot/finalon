package finalonWindows.formulaScene.eventHandlers;

import entities.Formula;
import javafx.collections.ObservableList;

public class FormulaExtended extends Formula {
    private int id;
    private String name;
    private String shortName;
    private String value;
    private String description;
    private String category;
    private String unit;
    private int parent;
    private ObservableList<Formula> childs;

    public FormulaExtended(int id,
                           String name,
                           String shortName,
                           String value,
                           String description,
                           String category,
                           String unit,
                           int parent,
                           ObservableList<Formula> childs) {
        super(id, name, shortName, value, description, category, unit, parent);
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.value = value;
        this.description = description;
        this.category = category;
        this.unit = unit;
        this.parent = parent;
        this.childs = childs;
    }

    FormulaExtended(Formula f, ObservableList<Formula> childs) {
        super(f.getId(), f.getName(), f.getShortName(), f.getValue(), f.getDescription(), f.getCategory(), f.getUnit(), f.getParent());
        this.id = f.getId();
        this.name = f.getName();
        this.shortName = f.getShortName();
        this.value = f.getShortName();
        this.description = f.getDescription();
        this.category = f.getCategory();
        this.unit = f.getUnit();
        this.parent = f.getParent();
        this.childs = childs;
    }

    public ObservableList<Formula> getChilds() {
        return this.childs;
    }

    public void setChilds(ObservableList<Formula> childs) {
        this.childs = childs;
    }
}
