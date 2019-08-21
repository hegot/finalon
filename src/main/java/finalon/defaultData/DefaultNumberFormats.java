package finalon.defaultData;

import finalon.entities.NumberFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*public class DefaultNumberFormats {
    public static ObservableList<NumberFormat> getFormats() {
        ObservableList<NumberFormat> Formats = FXCollections.observableArrayList();
        Formats.add(new NumberFormat("Default", "default", "", "4 294 967 295.000"));
        Formats.add(new NumberFormat("Canadian (English and French), Danish, Finnish, French", "CDFF", "", "4 294 967 295,000"));
        Formats.add(new NumberFormat("German", "FRG", "", "4 294 967.295,000"));
        Formats.add(new NumberFormat("Italian, Norwegian, Spanish", "INS", "", "4.294.967.295,000"));
        Formats.add(new NumberFormat("Swedish", "SW", "", "4 294 967 295,000"));
        Formats.add(new NumberFormat("GB-English, US-English, Thai", "ENG", "", " 4,294,967,295.00"));
        return Formats;
    }
}*/


public class DefaultNumberFormats {
    public static ObservableList<NumberFormat> getFormats() {
        ObservableList<NumberFormat> Formats = FXCollections.observableArrayList();
        Formats.add(new NumberFormat("Default (separation with dot)", "default", "", "295.00"));
        Formats.add(new NumberFormat("Separation with comma", "comma", "", "295,00"));
        return Formats;
    }
}



