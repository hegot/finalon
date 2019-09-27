package finalonWindows.templateScene.templates.EventHandlers;

import database.formula.DbFormulaHandler;
import database.template.DbItemHandler;
import entities.Formula;
import entities.Item;
import finalonWindows.templateScene.templates.TemplateEditPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateBase {

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
            int newNewId = DbItemHandler.addItem(newItem);
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
                    Map<Integer, Formula> usages = DbFormulaHandler.findUsage(
                            starter.getShortName(),
                            rootIndustryId
                    );
                    if (usages != null && usages.size() > 0) {
                        for (Map.Entry<Integer, Formula> entry : usages.entrySet()) {
                            Formula formula  = entry.getValue();
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
