package finalonWindows.formulaScene.editScreen;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.editScreen.EditPopup.EditPopup;
import globalReusables.StandardAndIndustry;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Screen;

public class FormulaEditable {

    private static int rootId;
    private static TreeTableView<Formula> table;


    public static TreeTableView getFormulaTable(int root) {
        rootId = root;
        table = createFormulaTable();
        return table;
    }

    private static TreeTableView<Formula> createFormulaTable() {
        TreeTableView<Formula> tbl = new TreeTableView<>();
        Formula rootIndustry = DbFormulaHandler.findById(rootId);
        tbl.setEditable(false);
        tbl.setMinWidth(880);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        tbl.setMinHeight(primaryScreenBounds.getHeight() - 220);
        tbl.getColumns().addAll(
                getCol("Indicator", "name", 300),
                getCol("Code", "shortName", 180),
                getValueCol(),
                buttonCol()
        );
        TreeItem rootNode = getRoot(rootIndustry);
        tbl.setRoot(rootNode);
        tbl.setRowFactory(tv -> {
            TreeTableRow<Formula> row = new TreeTableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    EditPopup popup = new EditPopup(row.getTreeItem());
                    popup.getdialog();
                }
            });
            return row;
        });
        return tbl;
    }

    public static void updateTable(Formula rootIndustry) {
        TreeItem rootNode = getRoot(rootIndustry);
        table.setRoot(rootNode);
    }


    static TreeTableColumn getValueCol() {
        TreeTableColumn<Formula, String> col = new TreeTableColumn<Formula, String>("Value");
        col.setMinWidth(300);
        col.setEditable(false);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Formula, String>("value"));
        col.setCellFactory(column -> {
            return new TreeTableCell<Formula, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        Formula formula = getTreeTableRow().getItem();
                        if (formula != null && formula.getCategory().equals("section")) {
                            setText(null);
                        } else {
                            setText(item);
                        }
                    }
                }
            };
        });
        col.setSortable(false);
        return col;
    }

    private static TreeTableColumn getCol(String title, String key, double width) {
        TreeTableColumn<Formula, String> col = new TreeTableColumn<Formula, String>(title);
        col.setMinWidth(width);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Formula, String>(key));
        col.setCellFactory(TextFieldTreeTableCell.<Formula>forTreeTableColumn());
        col.setSortable(false);
        return col;
    }

    private static TreeTableColumn buttonCol() {
        TreeTableColumn<Formula, Void> col = new TreeTableColumn<>("");
        col.setMinWidth(100);
        EditHandler editHandler = new EditHandler();
        col.setCellFactory(editHandler.getBtnFactory());
        col.setSortable(false);
        return col;
    }

    private static TreeItem getRoot(Formula rootIndustry) {
        TreeItem<Formula> rootNode = new TreeItem<Formula>(rootIndustry);
        if (rootIndustry != null) {
            rootNode.setExpanded(true);
            attachChilds(rootIndustry.getId(), rootNode);
        }
        return rootNode;
    }

    private static void attachChilds(int parentId, TreeItem<Formula> root) {
        ObservableList<Formula> childs = SortedSections.getSections(parentId);
        for (Formula child : childs) {
            TreeItem treeItem = new TreeItem<Formula>(child);
            treeItem.setExpanded(true);
            root.getChildren().add(treeItem);
            ObservableList<Formula> childs2 = DbFormulaHandler.getFormulas(child.getId());
            for (Formula child2 : childs2) {
                TreeItem treeItem2 = new TreeItem<Formula>(child2);
                treeItem2.setExpanded(true);
                treeItem.getChildren().add(treeItem2);
            }
        }
    }

    public static void refresh() {
        updateTable(StandardAndIndustry.getIndustry().getValue());
    }

    public static void refreshWithId(Integer id) {
        Formula formula = StandardAndIndustry.getIndustry().getValue();
        if (id != null) {
            formula = DbFormulaHandler.findById(id);
        }
        updateTable(formula);
        StandardAndIndustry.refreshIndustry();
        StandardAndIndustry.getIndustry().getSelectionModel().select(formula);
    }
}

