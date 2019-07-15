package reportGeneration.wordExport;

import javafx.collections.ObservableMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.*;
import reportGeneration.storage.ResultItem;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.SortedSet;
import java.util.TreeSet;

public class WordExport {

    private WordprocessingMLPackage wordPackage;

    public WordExport() throws Docx4JException {
        this.wordPackage = WordprocessingMLPackage.createPackage();
    }

    private static void addImageToPackage(WordprocessingMLPackage wordMLPackage, byte[] bytes) throws Exception {
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);
        Inline inline = imagePart.createImageInline("", "", 1, 2, false);
        P paragraph = addInlineImageToParagraph(inline);
        wordMLPackage.getMainDocumentPart().getContent().add(paragraph);
    }

    private static P addInlineImageToParagraph(Inline inline) {
        ObjectFactory factory = new ObjectFactory();
        P paragraph = factory.createP();
        R run = factory.createR();
        paragraph.getContent().add(run);
        Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return paragraph;
    }

    public void exportDoc() throws Docx4JException, Exception {
        ObservableMap<Integer, ResultItem> items = ResultsStorage.getItems();
        if (items.size() > 0) {
            SortedSet<Integer> keys = new TreeSet<Integer>(items.keySet());
            for (Integer key : keys) {
                ResultItem item = items.get(key);
                Object obj = item.get();
                if (obj.getClass() == String.class) {
                    AddText creator = new AddText();
                    wordPackage.getMainDocumentPart().getContent().add(creator.getStyledText(item));
                } else if (obj.getClass() == TwoDList.class) {
                    createTable((TwoDList) obj);
                } else if (obj.getClass() == BarChart.class) {
                    BarChart ch = (BarChart) obj;
                    WritableImage image = ch.snapshot(new SnapshotParameters(), null);
                    addChartToDoc(image);
                } else if (obj.getClass() == PieChart.class) {
                    PieChart ch = (PieChart) obj;
                    WritableImage image = ch.snapshot(new SnapshotParameters(), null);
                    addChartToDoc(image);
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

    private void addChartToDoc(WritableImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", baos);
            addImageToPackage(wordPackage, baos.toByteArray());
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        wordPackage.getMainDocumentPart().addParagraphOfText("\n");
    }
}
