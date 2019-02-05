package finalonWindows.formulaScene.eventHandlers;

import entities.Formula;
import finalonWindows.ImageButton;
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



                    private ImageButton editBtn() {
                        TreeTableView<Formula> table = getTreeTableView();

                        ImageButton btn = new ImageButton();
                        btn.setStyle(btnStyle());
                        btn.updateImages(new Image("image/pencil.png"), new Image("image/pencil.png"), 16);
                        TreeItem treeItem = this.getTreeTableRow().getTreeItem();
                        if (treeItem != null) {
                            btn.setOnAction((ActionEvent event) -> {
                                EditPopup popup = new EditPopup(treeItem);
                                Dialog dialog = popup.getdialog();
                                dialog.showAndWait();
                                table.refresh();
                            });
                        }
                        return btn;
                    }

                    private HBox container() {
                        HBox hBox = new HBox(10);
                        hBox.getChildren().addAll(editBtn());
                        return hBox;
                    }


                    private String btnStyle() {
                        return  "-fx-background-color: #FFFFFF;" +
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
