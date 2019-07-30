package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.formulaScene.EditPopup.EditPopup;
import finalonWindows.formulaScene.IndustryOperations.IndustryOperations;
import finalonWindows.reusableComponents.ImageButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.Optional;

public class EditHandler extends FormulaAddBase{

    public Callback<TreeTableColumn<Formula, Void>, TreeTableCell<Formula, Void>> getBtnFactory() {
        return new Callback<TreeTableColumn<Formula, Void>, TreeTableCell<Formula, Void>>() {
            @Override
            public TreeTableCell<Formula, Void> call(final TreeTableColumn<Formula, Void> param) {
                final TreeTableCell<Formula, Void> cell = new TreeTableCell<Formula, Void>() {


                    private TreeItem treeItem;
                    private TreeTableView<Formula> table;
                    private Formula parentFormula;


                    private ImageButton editBtn() {
                        ImageButton btn = new ImageButton("image/pencil.png", 16);
                        btn.getStyleClass().add("formula-btn");
                        btn.setOnAction((ActionEvent event) -> {
                            EditPopup popup = new EditPopup(treeItem, "edit");
                            popup.getdialog();
                            table.refresh();
                        });
                        return btn;
                    }

                    private ImageButton addBtn() {
                        ImageButton btn = new ImageButton("image/add-plus-button.png", 16);
                        btn.getStyleClass().add("formula-btn");
                        btn.setOnAction((ActionEvent event) -> {
                            Formula newFormula = new Formula(biggestId(), "", "", "", "", "TO_BE_ADDED", "", parentFormula.getId());
                            TreeItem treeItemNew = new TreeItem<Formula>(newFormula);
                            treeItem.getChildren().add(treeItemNew);
                            EditPopup popup = new EditPopup(treeItemNew, "add");
                            popup.getdialog();
                            table.refresh();
                        });
                        return btn;
                    }


                    private ImageButton removeBtn() {
                        ImageButton btn = new ImageButton("image/remove.png", 16);
                        btn.getStyleClass().add("formula-btn");
                        btn.setOnAction((ActionEvent event) -> {
                            TreeItem parentTreeItem = treeItem.getParent();
                            if (parentTreeItem != null) {
                                parentTreeItem.getChildren().remove(treeItem);
                            }
                            parentFormula.setCategory("TO_BE_DELETED");
                            EditStorage.addItem(parentFormula.getId(), parentFormula);
                        });
                        return btn;
                    }

                    private Button removeIndustryBtn() {

                        Button btn = new Button("Industry\noperations");
                        btn.getStyleClass().add("industry-btn");
                        btn.setOnAction((ActionEvent event) -> {
                            IndustryOperations operations = new IndustryOperations(parentFormula);
                            operations.showDialog();
                        });
                        return btn;
                    }

                    private HBox container() {
                        HBox hBox = new HBox(5);

                        if (parentFormula.getCategory().equals("section")) {
                            hBox.getChildren().add(editBtn());
                            hBox.getChildren().add(addBtn());
                        }
                        if (parentFormula.getCategory().equals("formula")) {
                            hBox.getChildren().add(editBtn());
                            hBox.getChildren().add(removeBtn());
                        }
                        if (parentFormula.getCategory().equals("industry")) {
                            hBox.getChildren().add(removeIndustryBtn());
                        }
                        return hBox;
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        this.treeItem = getTreeTableRow().getTreeItem();
                        if (treeItem != null) {
                            this.table = getTreeTableView();
                            this.parentFormula = (Formula) treeItem.getValue();
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(container());
                            }
                        }
                    }
                };
                return cell;
            }
        };
    }
}
