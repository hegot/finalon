package finalon.finalonWindows.formulaScene.IndustryOperations;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Formula;
import finalon.finalonWindows.formulaScene.Storage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SortableList implements CancelBtn {
    private ObservableList<Formula> sections;
    private ListView<Formula> list;
    private Dialog dialog;

    public SortableList(ObservableList<Formula> sections, Dialog dialog) {
        this.sections = sections;
        this.list = new ListView<>(sections);
        this.dialog = dialog;
    }

    public VBox getContent() {
        list.setCellFactory(param -> new FormualCell());
        list.setMinHeight(230);
        VBox layout = new VBox(5);
        HBox hBox = new HBox(100);
        hBox.setPrefHeight(40);
        hBox.getChildren().addAll(
                cancelBtn(dialog),
                saveBtn()
        );
        layout.getChildren().addAll(list, hBox);
        return layout;
    }

    private Integer getIndexByName(String name, ObservableList<Formula> items) {
        for (int i = 0; i < items.size(); i++) {
            Formula item = items.get(i);
            if (item.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    private List<Formula> reorderItems(
            ObservableList<Formula> items,
            int draggedIndex,
            int thisIndex
    ) {
        Formula dragged = items.get(draggedIndex);
        items.remove(draggedIndex);
        items.add(thisIndex, dragged);
        for (int i = 0; i < items.size(); i++) {
            Formula formula = items.get(i);
            formula.setValue(Integer.toString(i));
        }
        List<Formula> itemscopy = new ArrayList<>(items);
        return itemscopy;
    }

    private Button saveBtn() {
        Button btn = new Button("Save");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            List<Formula> items = list.getItems();
            for (Formula item : items) {
                try {
                    DbFormulaHandler.updateFormula(item);
                } catch (SQLException e) {
                    System.out.println("Formula reordering save error");
                }
            }
            dialog.close();
            Storage.refresh();
        });
        return btn;
    }

    private class FormualCell extends ListCell<Formula> {

        public FormualCell() {
            ListCell thisCell = this;

            setAlignment(Pos.BASELINE_LEFT);

            setOnDragDetected(event -> {
                if (getItem() == null) {
                    return;
                }
                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                Formula section = getItem();
                content.putString(section.getName());
                dragboard.setContent(content);
                event.consume();
            });


            setOnDragOver(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            });

            setOnDragEntered(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(0.3);
                }
            });

            setOnDragExited(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(1);
                }
            });

            setOnDragDropped(event -> {
                if (getItem() == null) {
                    return;
                }

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    ObservableList<Formula> items = getListView().getItems();
                    int draggedIdx = getIndexByName(db.getString(), items);
                    int thisIdx = items.indexOf(getItem());
                    List<Formula> itemscopy = reorderItems(items, draggedIdx, thisIdx);
                    getListView().getItems().setAll(itemscopy);
                    success = true;
                }
                event.setDropCompleted(success);

                event.consume();
            });

            setOnDragDone(DragEvent::consume);
        }

        @Override
        protected void updateItem(Formula item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                setText(item.getName());
            }
        }
    }

}