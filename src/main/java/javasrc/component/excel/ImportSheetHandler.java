package javasrc.component.excel;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

public class ImportSheetHandler implements SheetContentsHandler {
	private boolean firstCellOfRow = false;
    private int currentRow = -1;
    private int currentCol = -1;
    private Map<Integer, String> rowMap;

	@Override
	public void startRow(int rowNum) {
		this.firstCellOfRow = true;
		this.currentRow = rowNum;
        this.currentCol = -1;
	}

	@Override
	public void endRow(int rowNum) {
		System.out.println("endRow");
		System.out.println(this.rowMap);
	}

	@Override
	public void cell(String cellReference, String formattedValue, XSSFComment comment) {
		if(cellReference == null) {
            cellReference = new CellAddress(this.currentRow, this.currentCol).formatAsString();
        }
        int thisCol = (new CellReference(cellReference)).getCol();
        this.currentCol = thisCol;
		importTo(cellReference,formattedValue);
	}

	@Override
	public void headerFooter(String text, boolean isHeader, String tagName) {

	}
	
	private void importTo(String cellReference, String formattedValue){
		if (this.firstCellOfRow) {
			this.firstCellOfRow=false;
			this.rowMap=new HashMap<>();
		}
		this.rowMap.put(this.currentCol, formattedValue);
	}
}
