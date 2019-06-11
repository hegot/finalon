package finalonWindows.templateScene.templates;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

import java.util.ArrayList;


public class TemplatePreview {

    private ObservableList<Item> items;
    private int rootId;
    private ArrayList<TreeItem> roots;

    public TemplatePreview(ObservableList<Item> items) {
        this.items = items;
        this.roots = new ArrayList<>();
    }

    public HBox getTemplatePreview() {
        ObservableList<Item> Sheets = getChildren(rootId);
        HBox hbox = new HBox(20);
        for (Item Sheet : Sheets) {
            Item sheet = Sheet;
            TreeTableView<Item> table = getSingleTable(sheet.getId());
            hbox.getChildren().add(table);
        }
        return hbox;
    }

    private TreeTableView<Item> getSingleTable(int Id) {
        TreeTableView<Item> table = new TreeTableView<>();
        table.setEditable(false);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        double height = primaryScreenBounds.getHeight() - 10.00;
        table.setMinHeight(height);
        TreeTableColumn<Item, String> col1 = new TreeTableColumn<Item, String>("Indicator");
        col1.setMinWidth(500);
        col1.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("name"));
        col1.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        TreeTableColumn<Item, String> col2 = new TreeTableColumn<Item, String>("Indicator Code");
        col2.setMinWidth(400);
        col2.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("shortName"));
        col2.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());

        table.getColumns().addAll(col1, col2);
        return table;
    }


    private ObservableList<Item> getChildren(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : this.items) {
            if (item.getParent() == id) {
                Items.add(item);
            }
        }
        return Items;
    }


}

