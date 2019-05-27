package reportGeneration.interpreter.ReusableComponents;

import entities.Formula;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class FormulaTable {
    protected TableColumn getNameCol() {
        TableColumn<Formula, String> col = new TableColumn<Formula, String>("Formula");
        col.setMinWidth(350);
        col.setCellValueFactory(new PropertyValueFactory<Formula, String>("name"));
        return col;
    }

    protected TableColumn getPeriodCol(String colname) {
        TableColumn<Formula, String> col = new TableColumn<Formula, String>(colname);
        col.setMinWidth(100);
        col.setCellValueFactory(cellData -> {
            Formula formula = (Formula) cellData.getValue();
            if (formula != null && formula.getPeriods().size() > 0) {
                String period = formula.getPeriods().get(colname);
                if (period != null) {
                    return new SimpleStringProperty(period);
                }
            }
            return null;
        });
        return col;
    }
}
