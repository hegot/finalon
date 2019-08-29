package finalon.finalonWindows.formulaScene;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Formula;
import finalon.finalonWindows.formulaScene.EditPopup.EditPopup;
import finalon.globalReusables.StandardAndIndustry;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Screen;

public class FormulaEditable {

    private static TreeTableView<Formula> table = new TreeTableView<>();


    public static TreeTableView getFormulaTable() {
        int rootId = StandardAndIndustry.getIndustryId();
        Formula rootIndustry = DbFormulaHandler.findById(rootId);
        table.setEditable(false);
        table.setMinWidth(880);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        table.setMinHeight(primaryScreenBounds.getHeight() - 220);
        table.getColumns().addAll(
                getCol("Indicator", "name", 300),
                getCol("Code", "shortName", 180),
                getValueCol(),
                buttonCol()
        );
        updateTable(rootIndustry);
        table.setRowFactory(tv -> {
            TreeTableRow<Formula> row = new TreeTableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    EditPopup popup = new EditPopup(row.getTreeItem());
                    popup.getdialog();
                }
            });
            return row;
        });
        return table;
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

