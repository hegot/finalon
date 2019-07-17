package reportGeneration.wordExport;

import javafx.collections.ObservableMap;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Br;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.STBrType;
import reportGeneration.storage.ResultItem;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

import java.io.File;
import java.util.SortedSet;
import java.util.TreeSet;

public class WordExport {

    private WordprocessingMLPackage wordPackage;

    public WordExport() throws Docx4JException {
        this.wordPackage = WordprocessingMLPackage.createPackage();
    }

    public void exportDoc() throws Docx4JException, Exception {
        ObservableMap<Integer, ResultItem> items = ResultsStorage.getItems();
        if (items.size() > 0) {
            SortedSet<Integer> keys = new TreeSet<Integer>(items.keySet());
            for (Integer key : keys) {
                ResultItem item = items.get(key);
                Object obj = item.get();
                if (obj.getClass() == String.class) {
                    if (item.getType().equals("sectionTitle")) {
                        wordPackage.getMainDocumentPart().getContent().add(getPageBreak());
                    }
                    wordPackage.getMainDocumentPart().getContent().add(
                            new AddText(item).getStyledText()
                    );
                } else if (obj.getClass() == TwoDList.class) {
                    createTable((TwoDList) obj);
                } else if (obj.getClass() == BarChart.class) {
                    BarChart ch = (BarChart) obj;
                    WritableImage image = ch.snapshot(new SnapshotParameters(), null);
                    new AddImage(wordPackage).addChartToDoc(image);
                } else if (obj.getClass() == PieChart.class) {
                    PieChart ch = (PieChart) obj;
                    WritableImage image = ch.snapshot(new SnapshotParameters(), null);
                    new AddImage(wordPackage).addChartToDoc(image);
                }
            }
        }
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory to save the file");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File dir = directoryChooser.showDialog(new Stage());
        if (dir != null) {
            String path = dir.getAbsolutePath() + "/finReport_" + System.currentTimeMillis() + ".docx";
            wordPackage.save(new java.io.File(path));
        }
    }

    public static P getPageBreak() {
        org.docx4j.wml.ObjectFactory wmlObjectFactory = new org.docx4j.wml.ObjectFactory();
        P p = wmlObjectFactory.createP();
        R r = wmlObjectFactory.createR();
        p.getContent().add(r);
        Br br = wmlObjectFactory.createBr();
        r.getContent().add(br);
        br.setType(org.docx4j.wml.STBrType.PAGE);
        return p;
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
        wordPackage.getMainDocumentPart().addParagraphOfText("\n");
    }
}
