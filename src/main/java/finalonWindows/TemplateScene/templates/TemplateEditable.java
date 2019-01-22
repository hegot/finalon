package finalonWindows.TemplateScene.templates;

import entities.Item;
import entities.Sheet;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

import java.util.ArrayList;

public class TemplateEditable {


    private ArrayList<Sheet> sheets;

    public TemplateEditable(ArrayList<Sheet> sheets) {
        this.sheets = sheets;
    }

    public TabPane getTemplateEditable() {
        TabPane tabs = new TabPane();
        for (Sheet sheet : this.sheets) {
            Tab tab = new Tab();
            tab.setText(sheet.name);
            tab.setContent(getSingleTable(sheet));
            tabs.getTabs().add(tab);
        }
        return tabs;
    }


    private TreeTableView getSingleTable(Sheet sheet) {
        TreeTableView<Item> table = new TreeTableView<>();
        table.setEditable(true);
        table.setMinHeight(780);
        Columns cols = new Columns();
        table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol(), cols.isPositiveCol(), cols.buttonCol());
        TreeBuilder treeBuilder = new TreeBuilder(sheet);
        TreeItem rootNode = treeBuilder.getTree();
        table.setRoot(rootNode);
        return table;
    }


    public ArrayList<Sheet> getSheets() {
        return this.sheets;
    }


}

