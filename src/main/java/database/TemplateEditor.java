package database;

import entities.Item;
import entities.Sheet;
import entities.Template;

import java.sql.SQLException;
import java.util.ArrayList;

public class TemplateEditor {
    private Template template;
    private ArrayList<Sheet> sheets;

    public TemplateEditor(
            Template template,
            ArrayList<Sheet> sheets
    ) {
        this.template = template;
        this.sheets = sheets;
    }


    public void updateTpl() {
        try {
            DbTemplateHandler templateCreator = new DbTemplateHandler();
            templateCreator.updateTemplate(this.template);
            updateSheets();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }


    private void updateSheets() {
        ArrayList<Sheet> sheets = this.sheets;
        for (int i = 0; i < sheets.size(); i++) {
            Sheet sheet = sheets.get(i);
            for (int j = 0; j < sheet.items.size(); j++) {
                Item item = sheet.items.get(j);
                updateItem(item);
            }
        }
    }


    private void updateItem(Item item) {
        try {
            DbItemHandler itemUpdater = new DbItemHandler();
            itemUpdater.updateItem(item);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }
}
