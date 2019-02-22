package finalonWindows.formulaScene.eventHandlers;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.EditStorage;
import finalonWindows.reusableComponents.ImageButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class EditHandler {


    public Callback<TreeTableColumn<Formula, Void>, TreeTableCell<Formula, Void>> getBtnFactory() {
        return new Callback<TreeTableColumn<Formula, Void>, TreeTableCell<Formula, Void>>() {
            @Override
            public TreeTableCell<Formula, Void> call(final TreeTableColumn<Formula, Void> param) {
                final TreeTableCell<Formula, Void> cell = new TreeTableCell<Formula, Void>() {
                    private EditStorage storage = EditStorage.getInstance();

                    private ImageButton editBtn() {
                        TreeTableView<Formula> table = getTreeTableView();

                        ImageButton btn = new ImageButton();
                        btn.setStyle(btnStyle());
                        btn.updateImages(new Image("image/pencil.png"), 16);
                        TreeItem treeItem = this.getTreeTableRow().getTreeItem();
                        if (treeItem != null) {
                            btn.setOnAction((ActionEvent event) -> {
                                EditPopup popup = new EditPopup(treeItem, "edit");
                                Dialog dialog = popup.getdialog();
                                dialog.showAndWait();
                                table.refresh();
                            });
                        }
                        return btn;
                    }

                    private ImageButton addBtn() {
                        TreeTableView<Formula> table = getTreeTableView();

                        ImageButton btn = new ImageButton();
                        btn.setStyle(btnStyle());
                        btn.updateImages(new Image("image/add-plus-button.png"), 16);
                        TreeItem root = this.getTreeTableRow().getTreeItem();
                        if (root != null) {
                            btn.setOnAction((ActionEvent event) -> {
                                Formula parentFormula = (Formula) root.getValue();
                                DbFormulaHandler dbFormula = new DbFormulaHandler();
                                int id = dbFormula.getLastId();
                                int biggestId = storage.getBiggestId();
                                if (biggestId >= id) {
                                    id = biggestId + 1;
                                }
                                Formula newFormula = new Formula(id, "", "", "", "", "TO_BE_ADDED", "", parentFormula.getId());
                                TreeItem treeItemNew = new TreeItem<Formula>(newFormula);
                                root.getChildren().add(treeItemNew);
                                EditPopup popup = new EditPopup(treeItemNew, "add");
                                Dialog dialog = popup.getdialog();
                                dialog.showAndWait();
                                table.refresh();
                            });
                        }
                        return btn;
                    }

                    private ImageButton removeBtn() {
                        ImageButton btn = new ImageButton();
                        btn.setStyle(btnStyle());
                        btn.updateImages(new Image("image/remove.png"), 16);
                        TreeItem root = this.getTreeTableRow().getTreeItem();
                        if (root != null) {
                            btn.setOnAction((ActionEvent event) -> {
                                Formula formula = (Formula) root.getValue();
                                TreeItem parentTreeItem = root.getParent();
                                if (parentTreeItem != null) {
                                    parentTreeItem.getChildren().remove(root);
                                }
                                formula.setCategory("TO_BE_DELETED");
                                FormulaExtended formulaExtended = new FormulaExtended(formula, FXCollections.observableArrayList());
                                storage.addItem(formula.getId(), formulaExtended);
                            });
                        }
                        return btn;
                    }

                    private HBox container() {
                        HBox hBox = new HBox(5);
                        TreeItem treeItem = this.getTreeTableRow().getTreeItem();
                        if (treeItem != null) {
                            Formula item = (Formula) treeItem.getValue();
                            hBox.getChildren().add(editBtn());
                            if (item.getCategory().equals("section")) {
                                hBox.getChildren().add(addBtn());
                            }
                            if (item.getCategory().equals("formula")) {
                                hBox.getChildren().add(removeBtn());
                            }
                        }
                        return hBox;
                    }


                    private String btnStyle() {
                        return "-fx-background-color: #FFFFFF;" +
                                "-fx-font-size: 12px;" +
                                "-fx-text-fill: #FFFFFF;" +
                                "-fx-padding: 5px;" +
                                "-fx-alignment:  baseline-left;" +
                                "-fx-background-radius: 5em;";
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(container());
                        }
                    }
                };
                return cell;
            }
        };
    }
}
