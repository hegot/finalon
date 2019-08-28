package finalon.globalReusables;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Formula;
import finalon.entities.FormulaConverter;
import finalon.reportGeneration.storage.SettingsStorage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class StandardAndIndustry {

    private static ObservableMap<String, String> settings = SettingsStorage.getSettings();
    private static int standardId = Integer.parseInt(settings.get("standard"));
    public static boolean updateSettings = false;
    private static int industryId = getInitIndustry();
    private static ComboBox<Formula> standard = standard();
    private static ComboBox<Formula> industry = industry();


    private static int getInitIndustry(){
        ObservableList<Formula> industries = getChoices(standardId);
        if(industries.size() > 0){
            return industries.get(0).getId();
        }
        return 3;
    }

    public static void refreshIndustry(){
        ObservableList<Formula> industries = getChoices(standardId);
        industry.setItems(industries);
        industry.getSelectionModel().selectFirst();
    }

    public static void shouldUpdateSettings(boolean should){
        updateSettings = should;
    }

    public static ObservableList<Formula> getChoices(int parent) {
        ObservableList<Formula> choices = DbFormulaHandler.getFormulas(parent);
        if (choices.size() > 0) {
            return choices;
        } else {
            return FXCollections.observableArrayList();
        }
    }

    public static ComboBox<Formula> getStandard(){
        return standard;
    }

    public static ComboBox<Formula> getIndustry(){
        return industry;
    }

    public static ComboBox<Formula> standard() {
        ObservableList<Formula> standards = getChoices(0);
        ComboBox<Formula> cb = new ComboBox<>(standards);
        cb.setConverter(
                new FormulaConverter()
        );
        cb.getSelectionModel().selectFirst();
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
            @Override
            public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                if (arg2 != null) {
                    standardId = arg2.getId();
                    if(updateSettings){
                        settings.replace(
                                "standard",
                                Integer.toString(standardId)
                        );
                    }
                    industry.setItems(getChoices(standardId));
                    industry.getSelectionModel().selectFirst();
                }
            }
        });
        return cb;
    }


    public static ComboBox<Formula> industry() {
        ComboBox<Formula> industryBox = new ComboBox<Formula>();
        industryBox.setConverter(
                new FormulaConverter()
        );
        ObservableList<Formula> industries = getChoices(standardId);
        industryBox.setItems(industries);
        String industry = settings.get("industry");
        if (industries.size() > 0 && industry.length() == 0){
            settings.put(
                "industry",
                Integer.toString(industryId)
            );
        }else{
            industryBox.getSelectionModel().select(industryId);
        }
        if (industries.size() > 0) {
            industryBox.getSelectionModel().selectFirst();
            industryBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formula>() {
                @Override
                public void changed(ObservableValue<? extends Formula> arg0, Formula arg1, Formula arg2) {
                    if (arg2 != null) {
                        if(updateSettings) {
                            industryId = arg2.getId();
                            settings.replace(
                                    "industry", Integer.toString(industryId)
                            );
                        }
                    }

                }
            });
        }
        return industryBox;
    }


    public static HBox get(){
        HBox standardB = Choicebox.get(standard, "Statements GAAP:", "Select the accounting standard");
        HBox industryB = Choicebox.get(industry, "Industry:", "Select the industry");
        HBox container = new HBox(0);
        container.getChildren().addAll(standardB, industryB);
        return container;
    }

    public static int getIndustryId(){
        return industryId;
    }

}
