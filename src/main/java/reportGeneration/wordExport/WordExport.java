package reportGeneration.wordExport;

import javafx.collections.ObservableMap;
import javafx.stage.DirectoryChooser;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.PageDimensions;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import javafx.stage.Stage;
import org.docx4j.wml.Body;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.SectPr;
import reportGeneration.storage.ResultsStorage;

import java.awt.*;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;

public class WordExport {

    private WordprocessingMLPackage wordPackage;
    public WordExport() throws Docx4JException {
        this.wordPackage = WordprocessingMLPackage.createPackage();
    }





    public void exportDoc() throws Docx4JException{
        ObservableMap<String, String> items = ResultsStorage.getItems();
        if(items.size() > 0){
            for (Map.Entry<String, String> entry : items.entrySet()) {
                wordPackage.getMainDocumentPart().addParagraphOfText(entry.getValue());
            }
        }
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory to save the file");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File dir = directoryChooser.showDialog(new Stage());
        createTable();
        if (dir != null) {
            String path = dir.getAbsolutePath() + "/financialAnalysis.docx";
            wordPackage.save(new java.io.File(path));
        }
    }







    public ArrayList<String[]> getDemo() {
        ArrayList<String[]> outerArr = new ArrayList<String[]>();
        String[] myString1 = {"hey", "hey", "hey", "hey"};
        outerArr.add(myString1);
        String[] myString2 = {"you2", "you2", "you2", "you2"};
        outerArr.add(myString2);
        String[] myString3 = {"you3", "you3", "you3", "you3"};
        outerArr.add(myString3);
        return outerArr;
    }


    private void createTable(){

        int writableWidthTwips = wordPackage.getDocumentModel()
                .getSections().get(0).getPageDimensions().getWritableWidthTwips();
        Double[] cols = {0.4, 0.4, 0.1, 0.1} ;
        TblConstructor constructor = new TblConstructor(getDemo(), writableWidthTwips, cols);

        wordPackage.getMainDocumentPart().addObject(constructor.getTable());
    }

}
