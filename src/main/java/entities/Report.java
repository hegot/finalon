package entities;

import database.formula.DbFormulaHandler;
import javafx.collections.ObservableMap;
import reportGeneration.reportJson.ReportJson;

public class Report {
    private int id;
    private String name;
    private String settings;
    private String items;
    private String updated;

    public Report(
            int id,
            String name,
            String settings,
            String items,
            String updated
    ) {
        this.id = id;
        this.name = name;
        this.settings = settings;
        this.items = items;
        this.updated = updated;
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

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getIndustryName() {
        ObservableMap<String, String> settings = ReportJson.jsonToSettings(getSettings());
        String industryId = settings.get("industry");
        if (industryId != null && industryId.length() > 0) {
            Integer id = Integer.parseInt(industryId);
            if (id != null) {
                Formula industry = DbFormulaHandler.findById(id);
                if (industry != null) {
                    return industry.getName();
                }
            }
        }
        return "";
    }
}
