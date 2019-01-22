package database;

import defaultTemplate.DefaultTemplate;
import entities.Item;
import entities.Sheet;
import entities.Template;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class TemplateCreator {
    private String tplName;
    private ArrayList<Sheet> sheets;

    public TemplateCreator(
            String tplName,
            ArrayList<Sheet> sheets
    ) {
        this.tplName = tplName;
        this.sheets = DefaultTemplate.getSheets();
    }


    public void createTpl() {
        try {
            DbTemplateHandler templateCreator = new DbTemplateHandler();
            int parentTplId = templateCreator.addTemplate(new Template(0, tplName));
            createSheets(parentTplId);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }


    private void createSheets(int parentTplId) {
        if (parentTplId > 0) {
            for (int i = 0; i < sheets.size(); i++) {
                Sheet sheet = sheets.get(i);
                int sheetId = sheetCreator(sheet, parentTplId);
                for (int j = 0; j < sheet.items.size(); j++) {
                    Item item = sheet.items.get(j);
                    item.setParentSheet(sheetId);
                    int newId = createItem(item);
                    updateChilds(item.getId(), newId, i);
                }
            }
        }
    }

    private void updateChilds(int oldId, int newId, int i){
        Sheet sheet = sheets.get(i);
        for (int j = 0; j < sheet.items.size(); j++) {
            Item item = sheet.items.get(j);
            if(oldId == item.getParent()){
                item.setParent(newId);
            }
        }
    }

    private int sheetCreator(Sheet sheet, int parentTplId) {
        try {
            sheet.parentTpl = parentTplId;
            DbSheetHandler sheetCreator = new DbSheetHandler();
            return sheetCreator.addSheet(sheet);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        return 0;
    }

    private int createItem(Item item) {
        try {
            DbItemHandler itemCreator = new DbItemHandler();
            return itemCreator.addItem(item);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        return 0;
    }
}
