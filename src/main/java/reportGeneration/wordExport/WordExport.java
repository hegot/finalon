package reportGeneration.wordExport;

import javafx.collections.ObservableMap;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import reportGeneration.storage.ResultItem;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

import java.io.File;
import java.util.Map;

public class WordExport {

    private WordprocessingMLPackage wordPackage;

    public WordExport() throws Docx4JException {
        this.wordPackage = WordprocessingMLPackage.createPackage();
    }


    public void exportDoc() throws Docx4JException {
        ObservableMap<String, ResultItem> items = ResultsStorage.getItems();
        if (items.size() > 0) {
            for (Map.Entry<String, ResultItem> entry : items.entrySet()) {
                ResultItem item = entry.getValue();
                Object obj = item.get();
                if (obj.getClass() == String.class) {
                    wordPackage.getMainDocumentPart().addParagraphOfText((String) obj);
                } else {
                    createTable((TwoDList) obj);
                }
            }
        }
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory to save the file");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File dir = directoryChooser.showDialog(new Stage());
        if (dir != null) {
            String path = dir.getAbsolutePath() + "/financialAnalysis.docx";
            wordPackage.save(new java.io.File(path));
        }
    }


    private void createTable(TwoDList data) {
        int colsN = data.get(0).size();
        Double[] cols = new Double[colsN];
        int restCols = colsN - 1;
        cols[0] = 0.3;
        for (int j = 1; j < colsN; j++) {
            cols[j] = 0.7 / restCols;
        }

        int writableWidthTwips = wordPackage.getDocumentModel()
                .getSections().get(0).getPageDimensions().getWritableWidthTwips();

        TblConstructor constructor = new TblConstructor(data, writableWidthTwips, cols);

        wordPackage.getMainDocumentPart().addObject(constructor.getTable());
    }

}
