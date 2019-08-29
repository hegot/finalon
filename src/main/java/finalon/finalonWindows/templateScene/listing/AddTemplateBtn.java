package finalon.finalonWindows.templateScene.listing;

import finalon.database.template.DbItemHandler;
import finalon.entities.Formula;
import finalon.entities.FormulaConverter;
import finalon.finalonWindows.SceneName;
import finalon.finalonWindows.SceneSwitcher;
import finalon.globalReusables.StandardAndIndustry;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class AddTemplateBtn extends HBox {

    public AddTemplateBtn(String text) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings/addTplBtn.fxml"));
        fxmlLoader.getNamespace().put("labelText", text);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @FXML
    protected void addTemplateAction() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setWidth(400);
        dialog.setTitle("Select Industry");
        DialogPane dialogPane = dialog.getDialogPane();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        ObservableList<Formula> choices = choices();
        if (choices.size() > 0) {
            ComboBox<Formula> industry = industry();
            industry.setItems(choices);
            industry.getSelectionModel().selectFirst();
            vBox.getChildren().addAll(description(), industry);
            dialogPane.getButtonTypes().addAll(ButtonType.CLOSE, ButtonType.OK);
            dialogPane.setContent(vBox);
            Optional<Pair<String, String>> result = dialog.showAndWait();
            if (result.isPresent()) {
                SceneSwitcher.goTo(SceneName.ADDTEMPLATE, industry.getValue().getId());
            }
        } else {
            vBox.getChildren().add(noIndustriesLeft());
            dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
            dialogPane.setContent(vBox);
            dialog.showAndWait();
        }
    }

    private ObservableList<Formula> choices() {
        ArrayList<Integer> industryIds = DbItemHandler.templateIndustries();
        ObservableList<Formula> industries = StandardAndIndustry.getChoices(1);
        ObservableList<Formula> elseIndustries = StandardAndIndustry.getChoices(2);
        industries.addAll(elseIndustries);
        for (Integer industryId : industryIds) {
            for (int i = 0; i < industries.size(); i++) {
                if (industries.get(i).getId() == industryId) {
                    industries.remove(i);
                }
            }
        }
        return industries;
    }

    private Label noIndustriesLeft() {
        Label desc = new Label("Please create new industry. Now all existing " +
                "\nindustries  have templates attached");
        return desc;
    }

    private Label description() {
        Label desc = new Label("Please select industry this new template" +
                "\n will be associated with");
        return desc;
    }

    public static ComboBox<Formula> industry() {
        ComboBox<Formula> industryBox = new ComboBox<Formula>();
        industryBox.setPrefWidth(200);
        industryBox.setConverter(
                new FormulaConverter()
        );
        return industryBox;
    }

}