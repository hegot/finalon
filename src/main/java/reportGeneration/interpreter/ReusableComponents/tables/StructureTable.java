package reportGeneration.interpreter.ReusableComponents.tables;

import entities.Item;
import javafx.scene.control.TreeTableView;
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

    public TreeTableView<Item> get() {
        TreeTableView<Item> table = getTable(rootId);
        table.getStyleClass().add("assets-report");
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
