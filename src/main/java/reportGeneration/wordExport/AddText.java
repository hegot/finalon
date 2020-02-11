package reportGeneration.wordExport;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.wml.*;
import reportGeneration.storage.ResultItem;

import java.math.BigInteger;

public class AddText {

    private ObjectFactory factory;
    private RPr runProperties;
    private String type;
    private String text;

    public AddText(ResultItem item) throws Docx4JException {
        this.factory = Context.getWmlObjectFactory();
        this.runProperties = factory.createRPr();
        this.type = item.getType();
        this.text = (String) item.get();
    }

    public AddText(String item) throws Docx4JException {
        this.factory = Context.getWmlObjectFactory();
        this.runProperties = factory.createRPr();
        this.type = "tableName";
        this.text = item;
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

    private R createRun() {
        Text t = factory.createText();
        if (type.equals("sectionTitle")) {
            t.setValue(text.replace("\n", ""));
        } else {
            t.setValue(text.trim().replaceAll("[\r\n]+", "\n"));
        }
        org.docx4j.wml.R run = factory.createR();
        run.getContent().add(t);
        run.setRPr(runProperties);
        return run;
    }


    public P getStyledText() {
        P para = factory.createP();
        R run = createRun();
        para.getContent().add(run);
        switch (type) {
            case "h1":
                setColor("#2c3e50");
                setFontSize("40");
                setBold();
                break;
            case "h2":
                setBold();
                setColor("000000");
                setFontSize("30");
                break;
            case "h3":
                setFontSize("22");
                setColor("black");
                break;
            case "tableName":
                setBold();
                setFontSize("20");
                setColor("000000");
                break;
            case "text":
                setFontSize("20");
                setColor("black");
                break;
            case "sectionTitle":
                setBold();
                setColor("000000");
                setFontSize("30");
                break;
        }

        return para;
    }
}
