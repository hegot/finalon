package finalon.finalonWindows.formulaScene;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;

public interface SortedSections {
    default ObservableList<Formula> getSections(int parentId) {
        ObservableList<Formula> childs = DbFormulaHandler.getFormulas(parentId);
        ArrayList<Formula> sortedChilds = new ArrayList<Formula>(childs);
        for (Formula child : childs) {
            String val = child.getValue();
            if (NumberUtils.isNumber(val)) {
                int index = Integer.valueOf(val);
                try {
                    sortedChilds.set(index, child);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return FXCollections.observableArrayList(sortedChilds);
    }
}
