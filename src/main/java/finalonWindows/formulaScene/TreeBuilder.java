package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;
import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

class TreeBuilder {
    private TreeItem<Formula> rootNode;
    private ObservableList<Formula> items;
    private DbFormulaHandler dbFormula = new DbFormulaHandler();

    TreeBuilder(
            Formula rootIndustry
    ) {
        TreeItem<Formula> rootNode = new TreeItem<Formula>(
                rootIndustry
        );
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
