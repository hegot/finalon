package globalReusables;

import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.SettingsStorage;

public class AddNewReport {
    public static void run() {
        SettingsStorage.reInitStorage();
        SettingsStorage.put("reportId", "-1");
        ItemsStorage.emptyItems();
        SceneSwitcher.goTo(SceneName.ADDREPORT);
    }
}
