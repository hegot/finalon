package entities;

public class Item {
    public int id;
    public String name;
    public String shortName;
    public String mainCategory;
    public String subCategory;
    public Boolean isPositive;
    public int parent;
    public int parentSheet;

    public Item(
            int id,
            String name,
            String shortName,
            String mainCategory,
            String subCategory,
            Boolean isPositive,
            int parent,
            int parentSheet
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.isPositive = isPositive;
        this.parent = parent;
        this.parentSheet = parentSheet;
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

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
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

}
