package finalonWindows.templateScene.FormulaEditPage.IndustryOperations;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class IndustryOperations {
    private Formula industry;
    private VBox container;
    private Dialog<Pair<String, String>> dialog;

    public IndustryOperations(Formula industry) {
        this.industry = industry;
        this.dialog = new Dialog<>();
        this.container = getContent();
    }

    public void showDialog() {
        dialog.setWidth(250);
        dialog.setHeight(200);
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add("styles/formulaEdit.css");
        dialogPane.getStyleClass().add("industryDialog");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        dialog.getDialogPane().setContent(container);
        dialog.showAndWait();
    }

    private VBox getContent() {
        VBox vBox = new VBox(15);
        vBox.setPrefWidth(250);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(
                createSectionBtn(),
                reorderSectionBtn()
        );
        return vBox;
    }

    private Button createSectionBtn() {
        Button btn = new Button("Add Section");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            VBox newContent = new CreateSection(industry, dialog).getContent();
            container.getChildren().setAll(newContent);
        });
        return btn;
    }

    private Button reorderSectionBtn() {
        Button btn = new Button("Reorder Sections");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            dialog.setHeight(310);
            ObservableList<Formula> childs = SortedSections.getSections(industry.getId());
            VBox newContent = new SortableList(childs, dialog).getContent();
            container.getChildren().setAll(newContent);
        });
        return btn;
    }
}
