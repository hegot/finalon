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

        //add Header
        addRow(tbl, 1, "34495E", "FFFFFF");

        //adding Rows
        for (int j = 2; j <= rows; j++) {
            addRow(tbl, j, "", "");
        }
        return tbl;
    }

    private void addRow(Tbl tbl, int rowId, String background, String color){
        Tr tr = Context.getWmlObjectFactory().createTr();
        tbl.getEGContentRowContent().add(tr);
        for (int i = 1; i <= cols; i++) {
            addTableCell(
                    tr,
                    getCellContent(rowId, i),
                    getColWidth(i),
                    background,
                    color
            );
        }
    }

    private String getCellContent(int key1, int key2) {
        key1 = key1 - 1;
        key2 = key2 - 1;
        ArrayList<String> row = data.get(key1);
        String content = "";
        if (row != null && row.size() > key2) {
            content = row.get(key2);
        }
        return content;
    }

    private void addTableCell(Tr tr, String content, int width, String background, String color) {
        CellCorstructor con = new CellCorstructor(content, width, "20", background, color);
        Tc tc = con.getCell();
        tr.getEGContentCellContent().add(tc);
    }
}
