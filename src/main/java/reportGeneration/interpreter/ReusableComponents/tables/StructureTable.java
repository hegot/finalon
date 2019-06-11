package reportGeneration.interpreter.ReusableComponents.tables;

import entities.Item;
import javafx.scene.control.TableView;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class StructureTable extends IndexChangeTable {
    private int rootId;

    public StructureTable(
            int rootId
    ) {
        super(rootId);
        this.rootId = rootId;
    }

    public TableView<Item> get() {
        TableView<Item> table = getTable(rootId);
        table.getStyleClass().add("report-table");
        table.getColumns().addAll(getNameCol());
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        for (String col : arr) {
            table.getColumns().add(getPeriodCol(col));
        }
        int count = arr.size() - 1;
        if (count > 0) {
            for (int j = 0; j < count; j++) {
                String colStart = arr.get(j);
                String colEnd = arr.get(j + 1);
                table.getColumns().add(getAbsoluteComparisonCol(colStart, colEnd));
            }
        }
        return table;
    }
}
