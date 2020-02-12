package reportGeneration.wordExport;

import javafx.collections.ObservableMap;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.docx4j.model.structure.PageDimensions;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.*;
import reportGeneration.storage.ResultItem;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TitledItem;
import reportGeneration.storage.TwoDList;

import java.io.File;
import java.math.BigInteger;
import java.util.SortedSet;
import java.util.TreeSet;

public class WordExport {

    private WordprocessingMLPackage wordPackage;

    public WordExport() throws Docx4JException {
        this.wordPackage = WordprocessingMLPackage.createPackage();
        setPageMargins(wordPackage);
    }

    public static P getPageBreak() {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        p.getContent().add(r);
        Br br = factory.createBr();
        r.getContent().add(br);
        br.setType(org.docx4j.wml.STBrType.PAGE);
        return p;
    }

    public void exportDoc() throws Docx4JException, Exception {
        ObservableMap<Integer, ResultItem> items = ResultsStorage.getItems();
        if (items.size() > 0) {
            SortedSet<Integer> keys = new TreeSet<Integer>(items.keySet());
            ResultItem item;
            Object obj;
            TitledItem titledItem;
            Object titledItemInner;
            BarChart ch;
            WritableImage image;
            PieChart pch;
            for (Integer key : keys) {
                item = items.get(key);
                obj = item.get();
                if (obj.getClass() == String.class) {
                    if (item.getType().equals("sectionTitle")) {
                        wordPackage.getMainDocumentPart().getContent().add(getPageBreak());
                    }
                    if (item.getType().equals("text")) {
                        String val = (String) item.get();
                        String[] split = val.split("\\r?\\n");
                        if(split.length > 1){
                            for(String chunk : split){
                                ResultItem<String> rowItem = new ResultItem<>(chunk, "text");
                                wordPackage.getMainDocumentPart().getContent().add(
                                        new AddText(rowItem).getStyledText()
                                );
                            }
                        }else{
                            wordPackage.getMainDocumentPart().getContent().add(
                                    new AddText(item).getStyledText()
                            );
                        }
                    }else{
                        wordPackage.getMainDocumentPart().getContent().add(
                                new AddText(item).getStyledText()
                        );
                    }

                } else {
                    titledItem = (TitledItem) obj;
                    wordPackage.getMainDocumentPart().getContent().add(
                            new AddText(
                                    titledItem.getTitle()
                            ).getStyledText()
                    );
                    titledItemInner = titledItem.get();
                    if (titledItemInner.getClass() == TwoDList.class) {
                        if (item.getType().equals("scaleTable")) {
                            createTable((TwoDList) titledItemInner, "scaleTable");
                        } else {
                            createTable((TwoDList) titledItemInner, "table");
                        }
                    } else if (titledItemInner.getClass() == BarChart.class) {
                        ch = (BarChart) titledItemInner;
                        image = ch.snapshot(new SnapshotParameters(), null);
                        new AddImage(wordPackage).addChartToDoc(image);
                    } else if (titledItemInner.getClass() == PieChart.class) {
                        pch = (PieChart) titledItemInner;
                        image = pch.snapshot(new SnapshotParameters(), null);
                        new AddImage(wordPackage).addChartToDoc(image);
                    }
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

    private void createTable(TwoDList data, String type) {
        int colsN = data.get(0).size();
        Double[] cols = new Double[colsN];
        int restCols = colsN - 1;
        cols[0] = 0.3;
        for (int j = 1; j < colsN; j++) {
            cols[j] = 0.7 / restCols;
        }
        int writableWidthTwips = wordPackage.getDocumentModel()
                .getSections().get(0).getPageDimensions().getWritableWidthTwips();

        if (type.equals("scaleTable")) {
            ScaleTblConstructor constructor = new ScaleTblConstructor(data, writableWidthTwips, cols);
            wordPackage.getMainDocumentPart().addObject(constructor.getTable());
        } else {
            TblConstructor constructor = new TblConstructor(data, writableWidthTwips, cols);
            wordPackage.getMainDocumentPart().addObject(constructor.getTable());
        }

        wordPackage.getMainDocumentPart().addParagraphOfText("\n");
    }

    public void setPageMargins(WordprocessingMLPackage wordMLPackage) {
        try {
            ObjectFactory factory = new ObjectFactory();
            Body body = wordMLPackage.getMainDocumentPart().getContents()
                    .getBody();
            PageDimensions page = new PageDimensions();
            SectPr.PgMar pgMar = page.getPgMar();
            pgMar.setBottom(BigInteger.valueOf(800));
            pgMar.setTop(BigInteger.valueOf(800));
            pgMar.setLeft(BigInteger.valueOf(800));
            pgMar.setRight(BigInteger.valueOf(800));
            SectPr sectPr = factory.createSectPr();
            body.setSectPr(sectPr);
            sectPr.setPgMar(pgMar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
