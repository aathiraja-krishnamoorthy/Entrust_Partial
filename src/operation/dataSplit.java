package operation;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class dataSplit {

	public static Properties CONFIG;
	public static Properties OBJECT;
	public static Properties OR;
	int colNum;
	boolean b1;

	public String[] dataFetch(String value, Sheet testSheet) throws IOException {
		ReadProperties object = new ReadProperties();
		ReadProperties config = new ReadProperties();
		ReadProperties or = new ReadProperties();
		OBJECT = object.getObjectRepository();
		CONFIG = config.getConfigRepository();
		OR = or.getDataRepository();
		Workbook wb = null;
		String[] data = null;

		if (value.startsWith("object")) {
			// read actual data value from the corresponding column
			value = OBJECT.getProperty(value.split("\\|")[1]);
		} else if (value.startsWith("config")) {
			// read actual data value from config.properties
			value = CONFIG.getProperty(value.split("\\|")[1]);
		} else if (value.startsWith("or")) {
			// read actual data value from config.properties
			value = OR.getProperty(value.split("\\|")[1]);

		} else {
			b1 = true;
			String sheetName = value.split("\\|")[0];
			value = value.split("\\|")[1];
			wb = testSheet.getWorkbook();
			data = getData(wb, sheetName, value);
		}
		return data;

	}

	public String[] getData(Workbook wb, String sheetName, String value) {
		Sheet paramSheet = wb.getSheet(sheetName);
		String[] cellValue;
		int rows = paramSheet.getLastRowNum() + 1;
		int cols = paramSheet.getRow(0).getLastCellNum();
		for (int i = 0; i < cols; i++) {
			String colName = paramSheet.getRow(0).getCell(i)
					.getStringCellValue();
			if (colName == value)
				colNum = i;
		}
		cellValue = new String[rows];
		if (colNum == -1)
			System.out.println("Data Column Not Found");
		for (int j = 1; j < rows; j++) {
			cellValue[j] = paramSheet.getRow(j).getCell(colNum)
					.getStringCellValue();
		}
		return cellValue;
	}
}
