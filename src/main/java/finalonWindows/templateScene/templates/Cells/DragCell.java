package finalonWindows.templateScene.templates.Cells;

import entities.Item;
import finalonWindows.reusableComponents.ImageButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class DragCell {

    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    public static Callback<TableColumn<Item, Void>, TableCell<Item, Void>> getDragFactory() {
        return new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private ImageButton dragBtn() {
                        ImageButton btn = new ImageButton("image/move.png", 16);
                        btn.getStyleClass().add("img-btn");
                        btn.setOnAction((ActionEvent event) -> {

                        });
                        btn.setOnDragDetected(event -> {
                            TableRow selectedRow = getTableRow();
                            if (!selectedRow.isEmpty()) {
                                Integer index = selectedRow.getIndex();
                                Dragboard db = selectedRow.startDragAndDrop(TransferMode.MOVE);
                                db.setDragView(selectedRow.snapshot(null, null));
                                ClipboardContent cc = new ClipboardContent();
                                cc.put(SERIALIZED_MIME_TYPE, index);
                                db.setContent(cc);
                                event.consume();
                            }
                        });

                        btn.setOnDragOver(event -> {
                            Dragboard db = event.getDragboard();
                            if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                                TableRow row = getTableRow();
                                if (row.getIndex() != ((Integer) db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                                    event.consume();
                                }
                            }
                        });

                        btn.setOnDragDropped(event -> {
                            Dragboard db = event.getDragboard();
                            if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                                TableView table = this.getTableView();
                                TableRow row = getTableRow();
                                int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                                List<Item> items = new ArrayList<>(table.getItems());
                                Item draggedItem = items.get(draggedIndex);

                                int dropIndex;
                                if (row.isEmpty()) {
                                    dropIndex = table.getItems().size();
                                } else {
                                    dropIndex = row.getIndex();
                                }
                                int parentWeight = 999;
                                for (Item item : items) {
                                    if (draggedItem.getParent() == item.getId()) {
                                        parentWeight = item.getWeight();
                                    }
                                }
                                if (dropIndex < parentWeight) {
                                    items.remove(draggedIndex);
                                    items.add(dropIndex, draggedItem);
                                    event.setDropCompleted(true);
                                    for (int i = 0; i < items.size(); i++) {
                                        Item sortItem = items.get(i);
                                        sortItem.setUpdated(true);
                                        sortItem.setWeight(i);
                                    }
                                    table.getItems().setAll(items);
                                    table.getSelectionModel().select(dropIndex);
                                }
                                event.consume();
                            }
                        });
                        return btn;
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(10);
                            TableRow row = this.getTableRow();
                            if (row != null) {
                                Item rowItem = (Item) row.getItem();
                                if (rowItem != null) {
                                    Integer level = rowItem.getLevel();
                                    if (level.equals(4)) {
                                        hBox.getChildren().add(dragBtn());
                                    }
                                }
                            }
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };
    }
}
