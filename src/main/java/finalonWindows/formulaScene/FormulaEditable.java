package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.EditPopup.EditPopup;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Screen;

class FormulaEditable {

    private Formula rootIndustry;
    private TreeTableView<Formula> table = new TreeTableView<>();

    FormulaEditable(Formula rootIndustry) {
        this.rootIndustry = rootIndustry;
    }

    TreeTableView getFormulaTable() {
        table.setEditable(false);
        table.setMinWidth(900);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        table.setMinHeight(primaryScreenBounds.getHeight() - 150);
        table.getColumns().addAll(
                getCol("Indicator", "name", 320),
                getCol("Code", "shortName", 200),
                getCol("Value", "value", 280),
                buttonCol()
        );
        updateTable(rootIndustry);

        table.setRowFactory(tv -> {
            TreeTableRow<Formula> row = new TreeTableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    EditPopup popup = new EditPopup(row.getTreeItem(), "edit");
                    popup.getdialog();
                    table.refresh();
                }
            });
            return row;
        });
        return table;
    }

    void updateTable(Formula rootIndustry) {
        TreeBuilder treeBuilder = new TreeBuilder(rootIndustry);
        TreeItem rootNode = treeBuilder.getTree();
        table.setRoot(rootNode);
    }

    TreeTableColumn getCol(String title, String key, double width) {
        TreeTableColumn<Formula, String> col = new TreeTableColumn<Formula, String>(title);
        col.setMinWidth(width);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Formula, String>(key));
        col.setCellFactory(TextFieldTreeTableCell.<Formula>forTreeTableColumn());
        return col;
    }

    TreeTableColumn buttonCol() {
        TreeTableColumn<Formula, Void> col = new TreeTableColumn<>("");
        col.setMinWidth(80);
        EditHandler editHandler = new EditHandler();
        col.setCellFactory(editHandler.getBtnFactory());
        return col;
    }

    class TreeBuilder {
        private TreeItem<Formula> rootNode;
        private DbFormulaHandler dbFormula = new DbFormulaHandler();

        TreeBuilder(Formula rootIndustry) {
            TreeItem<Formula> rootNode = new TreeItem<Formula>(rootIndustry);
            rootNode.setExpanded(true);
            this.rootNode = rootNode;
            attachChilds(rootIndustry.getId(), rootNode);
        }

        private void attachChilds(int parentId, TreeItem<Formula> root) {

            ObservableList<Formula> childs = dbFormula.getFormulas(parentId);
            for (Formula child : childs) {
                TreeItem treeItem = new TreeItem<Formula>(child);
                treeItem.setExpanded(true);
                root.getChildren().add(treeItem);
                ObservableList<Formula> childs2 = dbFormula.getFormulas(child.getId());
                for (Formula child2 : childs2) {
                    TreeItem treeItem2 = new TreeItem<Formula>(child2);
                    treeItem2.setExpanded(true);
                    treeItem.getChildren().add(treeItem2);
                }
            }
        }

        TreeItem getTree() {
            return rootNode;
        }
    }
}

