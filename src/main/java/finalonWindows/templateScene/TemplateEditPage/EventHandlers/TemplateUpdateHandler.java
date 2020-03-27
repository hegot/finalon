package finalonWindows.templateScene.TemplateEditPage.EventHandlers;

import database.formula.DbFormulaHandler;
import database.template.DbItemHandler;
import entities.Formula;
import entities.Item;
import finalonWindows.templateScene.TemplateEditPage.TemplateEditTable;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class TemplateUpdateHandler {
    private ObservableList<Item> items;
    private Item root;

    public TemplateUpdateHandler(
            String tplName
    ) {
        this.items = TemplateEditTable.getItems();
        this.root = getRoot();
        root.setName(tplName);
    }

    public Item getRoot() {
        for (Item item : items) {
            if (item.getParent() == 0) {
                return item;
            }
        }
        return null;
    }

    private void updateItem(Item item) {
        try {
            DbItemHandler.updateItem(item);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }

    private Boolean hasItem(int Id) {
        Boolean contains = false;
        for (Item item : items) {
            if (item.getId() == Id) {
                contains = true;
            }
        }
        return contains;
    }

    public void deleteItem(int Id) {
        DbItemHandler.deleteItem(Id);
        for (Item item : items) {
            if (item.getParent() == Id) {
                deleteItem(item.getId());
            }
        }
    }

    private static void addAllChilds(int Id,  ArrayList<Item> oldItems) {
        ObservableList<Item> items = DbItemHandler.getItems(Id);
        if (items.size() > 0) {
            for(Item item : items){
                oldItems.add(item);
                addAllChilds(item.getId(), oldItems);
            }
        }
    }

    public void updateTpl() {
        ArrayList<Item> oldItems = new ArrayList<>();
        oldItems.add(root);
        addAllChilds(root.getId(), oldItems);
        if (oldItems.size() > 0) {
            updateItem(root);
            for (Item item : oldItems) {
                if (!hasItem(item.getId())) {
                    deleteItem(item.getId());
                }
            }
            for (Item item : items) {
                if (item.getId() == -1) {
                    DbItemHandler.addItem(item);
                } else if (item.getUpdated()) {
                    updateDependantFormulas(item);
                    updateItem(item);
                }
            }
        }
    }

    public static void updateDependantFormulas(Item item) {
        try {
            if (item.getShortNameUpdated()) {
                Item starter = DbItemHandler.getItem(item.getId());
                if (starter != null && starter.getShortName().length() > 0) {
                    Map<Integer, Formula> usages = FormulaUsage.findUsage(
                            starter.getShortName(),
                            TemplateEditTable.getRoot()
                    );
                    if (usages != null && usages.size() > 0) {
                        for (Map.Entry<Integer, Formula> entry : usages.entrySet()) {
                            Formula formula = entry.getValue();
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