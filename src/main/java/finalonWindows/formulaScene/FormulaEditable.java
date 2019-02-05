package finalonWindows.formulaScene;


import entities.Formula;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.stage.Screen;


public class FormulaEditable {

    private Formula rootIndustry;
    private TreeItem root;
    private TreeTableView<Formula> table = new TreeTableView<>();

    public FormulaEditable(Formula rootIndustry) {
        this.rootIndustry = rootIndustry;
    }

    public TreeTableView getFormulaTable() {
        table.setEditable(true);
        table.setMinWidth(900);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        table.setMinHeight(primaryScreenBounds.getHeight() - 150);
        Columns cols = new Columns();
        table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol(), cols.getValueCol(), cols.getUnitCol());
        updateTable(rootIndustry);
        return table;
    }


    public void updateTable(Formula rootIndustry){
        TreeBuilder treeBuilder = new TreeBuilder(rootIndustry);
        TreeItem rootNode = treeBuilder.getTree();
        table.setRoot(rootNode);
    }

}

