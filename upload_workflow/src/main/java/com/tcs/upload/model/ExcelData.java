package com.tcs.upload.model;


import java.util.List;
import java.util.Map;

public class ExcelData {

	private String tableName;
	private List<String> listOfColumn;
	private Map<Integer,String> listOfTypes;
	private List<Object[]> listOfData;
	private String query;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<String> getListOfColumn() {
		return listOfColumn;
	}
	public void setListOfColumn(List<String> listOfColumn) {
		this.listOfColumn = listOfColumn;
	}
	public List<Object[]> getListOfData() {
		return listOfData;
	}
	public void setListOfData(List<Object[]> listOfData) {
		this.listOfData = listOfData;
	}
	public Map<Integer, String> getListOfTypes() {
		return listOfTypes;
	}
	public void setListOfTypes(Map<Integer, String> listOfTypes) {
		this.listOfTypes = listOfTypes;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
