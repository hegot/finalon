package finalon.finalonWindows.templateScene.templates.EventHandlers;

import finalon.database.formula.DbFormulaHandler;
import finalon.database.template.DbItemHandler;
import finalon.entities.Formula;
import finalon.entities.Item;
import finalon.finalonWindows.templateScene.templates.TemplateEditPage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TemplateBase {
    public static int createItem(Item item) {
        try {
            return DbItemHandler.addItem(item);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        return 0;
    }

    public static List<Item> getChilds(int parentId) {
        List<Item> childs = new ArrayList<Item>();
        for (Item item : TemplateEditPage.getItems()) {
            if (parentId == item.getParent()) {
                childs.add(item);
            }
        }
        return childs;
    }

    public static void updateChilds(int oldId, int newId) {
        List<Item> childs = getChilds(oldId);
        for (Item child : childs) {
            Item newItem = (Item) child.clone();
            newItem.setParent(newId);
            int newNewId = createItem(newItem);
            updateChilds(newItem.getId(), newNewId);
        }
    }

    public static Item getRoot() {
        for (Item item : TemplateEditPage.getItems()) {
            if (item.getParent() == 0) {
                return item;
            }
        }
        return null;
    }

    public static void updateDependantFormulas(Item item) {
        try {
            if (item.getShortNameUpdated()) {
                Integer rootIndustryId = getRoot().getParentSheet();
                Item starter = DbItemHandler.getItem(item.getId());
                if (starter != null && starter.getShortName().length() > 0) {
                    List<Formula> usages = DbFormulaHandler.findUsage(
                            starter.getShortName(),
                            rootIndustryId
                    );
                    if (usages != null && usages.size() > 0) {
                        for (Formula formula : usages) {
                            String value = formula.getValue();
                            value = value.replace(starter.getShortName(), item.getShortName());
                            formula.setValue(value);
                            DbFormulaHandler.updateFormula(formula);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
