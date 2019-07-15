package reportGeneration.wordExport;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.wml.*;
import reportGeneration.storage.ResultItem;

import java.math.BigInteger;

public class AddText {

    private ObjectFactory factory;
    private RPr runProperties;

    public AddText() throws Docx4JException {
        this.factory = Context.getWmlObjectFactory();
        this.runProperties = factory.createRPr();
    }

    private void setFontSize(String fontSize) {
        HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(fontSize));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
    }

    private void setColor(String color) {
        Color green = factory.createColor();
        green.setVal(color);
        runProperties.setColor(green);
    }

    private void setBold() {
        BooleanDefaultTrue booleandefaulttrue = factory.createBooleanDefaultTrue();
        runProperties.setB(booleandefaulttrue);
    }

    private R createRun(String text) {
        Text t = factory.createText();
        t.setValue(text.trim());
        org.docx4j.wml.R run = factory.createR();
        run.getContent().add(t);
        run.setRPr(runProperties);
        return run;
    }


    public P getStyledText(ResultItem item) {
        P para = factory.createP();
        R run = createRun((String) item.get());
        para.getContent().add(run);

        String type = item.getType();
        switch (type) {
            case "h1":
                setBold();
                setColor("6A6C6F");
                setFontSize("40");
                break;
            case "h2":
                setBold();
                setColor("6A6C6F");
                setFontSize("30");
                break;
            case "h3":
                setFontSize("25");
                setColor("black");
                break;
            case "text":
                setFontSize("20");
                setColor("black");
                break;
        }

        return para;
    }
}
