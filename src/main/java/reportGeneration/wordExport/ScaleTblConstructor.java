package reportGeneration.wordExport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.docx4j.wml.*;
import reportGeneration.interpreter.FinancialRating.Outcomes.ScaleItem;

import javax.xml.bind.JAXBException;
import java.math.BigInteger;
import java.util.ArrayList;

public class ScaleTblConstructor {
    private int tableWidth;
    private ObjectFactory factory;
    private Double[] colWidth;
    private ArrayList<ArrayList<String>> data;
    private int rows;
    private int cols;

    public ScaleTblConstructor(ArrayList<ArrayList<String>> data, int tableWidth, Double[] colWidth) {
        this.data = data;
        this.factory = Context.getWmlObjectFactory();
        this.tableWidth = tableWidth;
        this.colWidth = colWidth;
        this.rows = data.size();
        this.cols = data.get(0).size();
    }

    private int getColWidth() {
        Double width = tableWidth * 0.25;
        return width.intValue();
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
            gridCol.setW(BigInteger.valueOf(getColWidth()));
            tblGrid.getGridCol().add(gridCol);
        }
        //add Header
        addHeader(tbl);
        //adding Rows
        for (ScaleItem row : getItems()) {
            addRow(tbl, row);
        }
        return tbl;
    }

    private void addHeader(Tbl tbl) {
        Tr tr = Context.getWmlObjectFactory().createTr();
        tbl.getEGContentRowContent().add(tr);
        String[] headArray = new String[]{"Score from (inclusive)", "to", "Sign", "Current financial \n condition"};

        for (int i = 0; i < headArray.length; i++) {
            addTableCell(
                    tr,
                    headArray[i],
                    getColWidth(),
                    "34495E",
                    "FFFFFF"
            );
        }
    }

    private void addRow(Tbl tbl, ScaleItem row) {
        Tr tr = Context.getWmlObjectFactory().createTr();
        tbl.getEGContentRowContent().add(tr);
        addTableCell(tr, Double.toString(row.getCol1()), getColWidth(), "FFFFFF", "000000");
        addTableCell(tr, Double.toString(row.getCol2()), getColWidth(), "FFFFFF", "000000");
        addTableCell(tr, row.getCol3(), getColWidth(), row.getColor(), "000000");
        addTableCell(tr, row.getCol4(), getColWidth(), "FFFFFF", "000000");
    }


    private void addTableCell(Tr tr, String content, int width, String background, String color) {
        CellCorstructor con = new CellCorstructor(content, width, "20", background, color);
        Tc tc = con.getCell();
        tr.getEGContentCellContent().add(tc);
    }

    public ObservableList<ScaleItem> getItems() {
        ObservableList<ScaleItem> output = FXCollections.observableArrayList();
        output.add(new ScaleItem(1.0, 0.8, "AAA", "Excellent", "0C9500"));
        output.add(new ScaleItem(0.8, 0.6, "AA", "Very good", "72FD37"));
        output.add(new ScaleItem(0.6, 0.4, "A", "Good", "8FFC60"));
        output.add(new ScaleItem(0.4, 0.2, "BBB", "Positive", "9DFE72"));
        output.add(new ScaleItem(0.2, 0.0, "BB", "Normal", "BEFDA3"));
        output.add(new ScaleItem(0.0, -0.2, "B", "Satisfactory", "EDFCE7"));
        output.add(new ScaleItem(-0.2, -0.4, "CCC", "Unsatisfactory", "FCE8E7"));
        output.add(new ScaleItem(-0.4, -0.6, "CC", "Adverse", "FC6666"));
        output.add(new ScaleItem(-0.6, -0.8, "C", "Bad", "FE1F1F"));
        output.add(new ScaleItem(-0.8, -1.0, "D", "Critical", "FE0000"));
        return output;
    }
}
