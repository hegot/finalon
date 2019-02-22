package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;
import entities.Formula;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
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
        Columns cols = new Columns();
        table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol(), cols.getValueCol(), cols.buttonCol());
        updateTable(rootIndustry);
        return table;
    }

    void updateTable(Formula rootIndustry) {
        TreeBuilder treeBuilder = new TreeBuilder(rootIndustry);
        TreeItem rootNode = treeBuilder.getTree();
        table.setRoot(rootNode);
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

