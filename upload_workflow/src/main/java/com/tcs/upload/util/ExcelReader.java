package com.tcs.upload.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tcs.upload.model.ExcelData;

public class ExcelReader {

	public static List<ExcelData> readExcel(InputStream inputStream){
		List<ExcelData> listOfExcelData = new ArrayList<>();
        try {
			Workbook workbook = new HSSFWorkbook(inputStream);
			int noOfSheet= workbook.getNumberOfSheets();
			int sheetNo = 0;
			int startColumn = 1;
			while(sheetNo != noOfSheet){
				int rowIterator = 0;
				ExcelData excelData = new ExcelData();
				List<String> listOfColumns = new ArrayList<>();
				Map<Integer,String> listOfTypes = new HashMap<>();
				List<Object[]> listOfData = new ArrayList<>();
				Sheet sheet = workbook.getSheetAt(sheetNo);
				Row firstRow = sheet.getRow(rowIterator++);
				String tableName = firstRow.getCell(0).getStringCellValue();
				Row secondRow = sheet.getRow(rowIterator++);
				for(int i = startColumn; i<secondRow.getLastCellNum(); i++){
					listOfColumns.add(secondRow.getCell(i).getStringCellValue());
				}
				Row thirdRow = sheet.getRow(rowIterator++);
				for(int i = startColumn; i<thirdRow.getLastCellNum(); i++){
					listOfTypes.put(i,thirdRow.getCell(i).getStringCellValue());
				}
				
				while(rowIterator != sheet.getPhysicalNumberOfRows()){
					List<Object> rowData = new ArrayList<>();
					Row row =  sheet.getRow(rowIterator++);
					for(int i = startColumn; i<row.getLastCellNum(); i++){
						Cell cell = row.getCell(i);
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								rowData.add(String.valueOf(cell.getDateCellValue()));
						    }else{
						    	DecimalFormat df = new DecimalFormat("#");
						        df.setMaximumFractionDigits(8);
						    	rowData.add(String.valueOf(df.format(cell.getNumericCellValue())));
						    }
                        }else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        	rowData.add(cell.getStringCellValue());
                        }else{
                        	rowData.add(null);
                        }
					}
					
					listOfData.add(rowData.toArray());
				}
				excelData.setTableName(tableName);
				excelData.setListOfColumn(listOfColumns);
				excelData.setListOfTypes(listOfTypes);
				excelData.setListOfData(listOfData);
				excelData.setQuery(createQuery(tableName, listOfColumns));
				listOfExcelData.add(excelData);
				sheetNo++;
			}
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         return listOfExcelData;
       
	}
	
	public static String createQuery(String tableName, List<String> listOfColumns){
		String query ="Insert into "+ tableName +" (";
		String columnQuery1 = "";
		String columnQuery2 = " values";
		for(String column : listOfColumns){
			if(listOfColumns.indexOf(column) == 0){
				columnQuery1 = columnQuery1 + column ;
				columnQuery2 = columnQuery2 + " (?";
				continue;
			}
			columnQuery1 = columnQuery1 + ", " +column +" ";
			columnQuery2 =  columnQuery2 + ", ? ";
		}
		query = query + columnQuery1 +" ) " + columnQuery2 +" )";
		return query;
	}
}
