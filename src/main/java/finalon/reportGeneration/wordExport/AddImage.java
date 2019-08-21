package finalon.reportGeneration.wordExport;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

public class AddImage {

    private WordprocessingMLPackage wordPackage;

    public AddImage(WordprocessingMLPackage wordPackage) throws Docx4JException {
        this.wordPackage = wordPackage;
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

    public void addChartToDoc(WritableImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", baos);
            addImageToPackage(wordPackage, baos.toByteArray());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
