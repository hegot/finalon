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
    private ArrayList<String[]> data;
    private int rows;
    private int cols;

    public TblConstructor(ArrayList<String[]> data, int tableWidth, Double[] colWidth) {
        this.data = data;
        this.factory = Context.getWmlObjectFactory();
        this.tableWidth = tableWidth;
        this.colWidth = colWidth;
        this.rows = data.size();
        this.cols = data.get(0).length;
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
        String content = data.get(key1)[key2];
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
            cellWidth.setType("dxa");
            cellWidth.setW(BigInteger.valueOf(width));
            tc.getEGBlockLevelElts().add(
                    Context.getWmlObjectFactory().createP()
            );
        }
    }

}
