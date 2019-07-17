package reportGeneration.wordExport;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.*;

import java.math.BigInteger;

public class CellCorstructor {
    private String content;
    private int width;
    private String fontSize;
    private String background;
    private String color;
    private TcPr tcPr;
    private ObjectFactory factory;

    public CellCorstructor(String content, int width, String fontSize, String background, String color){
        this.content = content;
        this.width = width;
        this.fontSize = fontSize;
        this.background = background;
        this.tcPr = Context.getWmlObjectFactory().createTcPr();
        this.factory = Context.getWmlObjectFactory();
        this.color = (color.length() > 0) ? color : "000000";
    }

    public Tc getCell() {
        Tc tc = Context.getWmlObjectFactory().createTc();
        setCellWidth();
        setCellValign();
        setCellMargins();
        if(background.length() > 0){
            setBackground();
        }
        tc.getContent().add(createPar());
        tc.setTcPr(tcPr);
        return tc;
    }

    private P createPar(){
        P paragraph = factory.createP();
        Text text = factory.createText();
        text.setValue(content);
        R run = factory.createR();
        setFontSize(run);
        run.getContent().add(text);
        paragraph.getContent().add(run);
        return paragraph;
    }


    private void setBackground(){
        CTShd shd = factory.createCTShd();
        shd.setVal(org.docx4j.wml.STShd.CLEAR);
        shd.setColor("auto");
        shd.setFill(background);
        shd.setThemeFill(org.docx4j.wml.STThemeColor.ACCENT_1);
        shd.setThemeFillTint( "66");
        tcPr.setShd(shd);
    }


    private void setCellWidth(){
        if (width > 0) {
            TblWidth cellWidth = Context.getWmlObjectFactory().createTblWidth();
            tcPr.setTcW(cellWidth);
            cellWidth.setType("dxa");
            cellWidth.setW(BigInteger.valueOf(width));
        }
    }

    private void setCellValign(){
        CTVerticalJc valign = new CTVerticalJc();
        valign.setVal(STVerticalJc.CENTER);
        tcPr.setVAlign(valign);
    }


    private void setCellMargins(){
        TcMar margins = new TcMar();
        TblWidth tW = new TblWidth();
        tW.setType("dxa");
        tW.setW(BigInteger.valueOf(0));
        margins.setTop(tW);
        tcPr.setTcMar(margins);
    }


    private void setFontSize(R run) {
        RPr runProperties = factory.createRPr();
        HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(fontSize));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
        Color co = factory.createColor();
        co.setVal(color);
        runProperties.setColor(co);
        run.setRPr(runProperties);
    }
}
