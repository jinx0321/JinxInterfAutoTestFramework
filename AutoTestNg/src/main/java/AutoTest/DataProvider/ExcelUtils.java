package AutoTest.DataProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;


public class ExcelUtils {

	public static Map<String,List<List<String>>> readExcel(String filedir) throws Exception, IOException{
		 File file=new File(filedir);
		 Workbook wb = null;
		 if(file.getName().endsWith("xlsx")) {
			 wb=new XSSFWorkbook(file);			 
		 }else if(file.getName().endsWith("xls")) {
			 wb=new HSSFWorkbook(new FileInputStream(file));		 
		 }
		 Map<String,List<List<String>>> excelmap=new LinkedHashMap<String,List<List<String>>>();
		 Iterator<Sheet> sheets= wb.sheetIterator();
		 while(sheets.hasNext()) {
			 Sheet sheet=sheets.next();
			 String sheetname=sheet.getSheetName();
			 List<List<String>> collist=new LinkedList<List<String>>();
			 for(int i=sheet.getFirstRowNum();i<=sheet.getLastRowNum();i++) {
				 Row row=sheet.getRow(i);
				 if(row!=null) {
				 List<String> celllist=new LinkedList<String>();
				 for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++) {
					 Cell cell=row.getCell(j);
					 if(cell!=null) {
					 if(cell.getCellType()==cell.CELL_TYPE_NUMERIC) {
						 celllist.add(String.valueOf(cell.getNumericCellValue()));
					 }else if(cell.getCellType()==cell.CELL_TYPE_STRING) {
						 celllist.add(String.valueOf(cell.getStringCellValue()));
					 }
					 }else {
						 celllist.add("");
					 }
				 }
				 collist.add(celllist);
				 }
			 }
			 excelmap.put(sheetname, compList(collist));
		 }
		return excelmap;
	}
	
	/**
	 * 列表按照第一行补全
	 * @param list
	 */
	private static List<List<String>> compList(List<List<String>> list) {
		List<String> firstline =new LinkedList<String>();
		if(firstline.size()>0) {
			firstline=list.get(0);
		}
		for(int i=1;i<list.size();i++) {
		   if(list.get(i).size()<firstline.size()) {
			   for(int j=list.get(i).size();j<firstline.size();j++) {
				   list.get(i).add("");
			   }
		   }	
			
		}
		
		return list;
	}
	
	@Test
	public void test() throws IOException, Exception {
		
		readExcel("D:\\workspace\\test.xlsx").forEach((k,v)->{
			v.forEach(e->{
			   e.forEach(x->{
				   System.out.print(x+"--");
			   });
			   System.out.println(e.size());
			});
		});
	}

}
