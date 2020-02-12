package entities;

public class NumberFormat {

    private String name;
    private String shortName;
    private String validationRule;
    private String example;

    public NumberFormat(
            String name,
            String shortName,
            String validationRule,
            String example
    ) {
        this.name = name;
        this.shortName = shortName;
        this.validationRule = validationRule;
        this.example = example;
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


    public String getValidationRule() {
        return validationRule;
    }

    public void setValidationRule(String validationRule) {
        this.validationRule = validationRule;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String shortName) {
        this.example = example;
    }

}
