package reportGeneration.wordExport;

import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.docx4j.wml.*;

import javax.xml.bind.JAXBException;
import java.math.BigInteger;
import java.util.ArrayList;

public class TblConstructor {

    private int tableWidth;
    private ObjectFactory factory;
    private Double[] colWidth;
    private ArrayList<ArrayList<String>> data;
    private int rows;
    private int cols;

    public TblConstructor(ArrayList<ArrayList<String>> data, int tableWidth, Double[] colWidth) {
        this.data = data;
        this.factory = Context.getWmlObjectFactory();
        this.tableWidth = tableWidth;
        this.colWidth = colWidth;
        this.rows = data.size();
        this.cols = data.get(0).size();
    }

    private int getColWidth(int key) {
        key = key - 1;
        if (key < colWidth.length) {
            Double val = colWidth[key];
            Double colWidth = (double) tableWidth * val;
            return colWidth.intValue();
        }
        return 0;
    }


    public Tbl getTable() {
        Tbl tbl = Context.getWmlObjectFactory().createTbl();
        String strTblPr = "<w:tblPr " + Namespaces.W_NAMESPACE_DECLARATION + ">"
                + "<w:tblStyle w:val=\"TableGrid\"/>"
                + "<w:tblW w:w=\"0\" w:type=\"auto\"/>"
                + "<w:tblLook w:val=\"04A0\"/>"
                + "</w:tblPr>";
        TblPr tblPr = null;
        try {
            tblPr = (TblPr) XmlUtils.unmarshalString(strTblPr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        tbl.setTblPr(tblPr);
        TblGrid tblGrid = Context.getWmlObjectFactory().createTblGrid();
        tbl.setTblGrid(tblGrid);
        for (int i = 1; i <= cols; i++) {
            TblGridCol gridCol = Context.getWmlObjectFactory().createTblGridCol();
            gridCol.setW(BigInteger.valueOf(
                    getColWidth(i)
            ));
            tblGrid.getGridCol().add(gridCol);
        }
        for (int j = 1; j <= rows; j++) {
            Tr tr = Context.getWmlObjectFactory().createTr();
            tbl.getEGContentRowContent().add(tr);
            for (int i = 1; i <= cols; i++) {
                addTableCell(
                        tr,
                        getCellContent(j, i),
                        getColWidth(i)
                );
            }
        }
        return tbl;
    }

    private String getCellContent(int key1, int key2) {
        key1 = key1 - 1;
        key2 = key2 - 1;
        ArrayList<String> row = data.get(key1);
        String content = "";
        if (row != null) {
            content = row.get(key2);
        }
        return content;
    }


    private void addTableCell(Tr tr, String content, int width) {
        Tc tc = Context.getWmlObjectFactory().createTc();
        tr.getEGContentCellContent().add(tc);
        setCellWidth(tc, width);
        P paragraph = factory.createP();
        Text text = factory.createText();
        text.setValue(content);
        R run = factory.createR();
        setFontSize(run, "20");
        run.getContent().add(text);
        paragraph.getContent().add(run);
        tc.getContent().add(paragraph);
    }


    private void setCellWidth(Tc tc, int width) {
        if (width > 0) {
            TcPr tcPr = Context.getWmlObjectFactory().createTcPr();
            tc.setTcPr(tcPr);
            TblWidth cellWidth = Context.getWmlObjectFactory().createTblWidth();
            tcPr.setTcW(cellWidth);

            //margins
            TcMar margins = new TcMar();
            TblWidth tW = new TblWidth();
            tW.setType("dxa");
            tW.setW(BigInteger.valueOf(0));
            margins.setTop(tW);
            tcPr.setTcMar(margins);

            //valign
            CTVerticalJc valign = new CTVerticalJc();
            valign.setVal(STVerticalJc.TOP);
            tcPr.setVAlign(valign);

            cellWidth.setType("dxa");
            cellWidth.setW(BigInteger.valueOf(width));
        }
    }

    private void setFontSize(R run, String fontSize) {
        RPr runProperties = factory.createRPr();
        HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(fontSize));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
        run.setRPr(runProperties);
    }

}
