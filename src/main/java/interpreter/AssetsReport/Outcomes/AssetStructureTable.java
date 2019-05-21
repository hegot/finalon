package interpreter.AssetsReport.Outcomes;

import database.setting.DbSettingHandler;
import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import interpreter.ReusableComponents.IndexChangeTable;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeTableView;

import java.util.ArrayList;

public class AssetStructureTable extends IndexChangeTable {
    private DbSettingHandler dbSettingHandler = new DbSettingHandler();
    private Periods periods;
    private int rootId;
    private ObservableList<Item> items;

    public AssetStructureTable(
            ObservableList<Item> items,
            Periods periods,
            int rootId
    ) {
        super(items, periods, rootId);
        this.items = items;
        this.periods = periods;
        this.rootId = rootId;
    }

    public TreeTableView<Item> get() {
        TreeTableView<Item> table = getTable(rootId);
        table.getStyleClass().add("assets-report");
        table.setPrefWidth(880);
        table.getColumns().addAll(getNameCol());
        ArrayList<String> arr = periods.getPeriodArr();
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
