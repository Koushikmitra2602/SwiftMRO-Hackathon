package com.ge.aviation.cube.engine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarChart implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<String> xAxisList;
	List<Map<String, Object>> dataMap;
	List<Integer> wipList;
	List<Integer> partillyColsedList;
	List<Integer> colsedList;
	List<Integer> repaireOrderCountList;
	Integer total;
	
	public List<String> getxAxisList() {
		if(xAxisList == null){
			xAxisList = new ArrayList<String>();
		}
		return xAxisList;
	}
	public void setxAxisList(List<String> xAxisList) {
		this.xAxisList = xAxisList;
	}
	
	public List<Map<String, Object>> getDataMap() {
		if(dataMap == null) {
			dataMap = new ArrayList<Map<String, Object>>();
		}
		return dataMap;
	}
	public void setDataMap(List<Map<String, Object>> dataMap) {
		this.dataMap = dataMap;
	}
	
	public List<Integer> getWipList() {
		if(wipList == null){
			wipList = new ArrayList<Integer>();
		}
		return wipList;
	}
	public void setWipList(List<Integer> wipList) {
		this.wipList = wipList;
	}
	
	public List<Integer> getPartillyColsedList() {
		if(partillyColsedList == null){
			partillyColsedList = new ArrayList<Integer>();
		}
		return partillyColsedList;
	}
	public void setPartillyColsedList(List<Integer> partillyColsedList) {
		this.partillyColsedList = partillyColsedList;
	}
	
	public List<Integer> getColsedList() {
		if(colsedList == null){
			colsedList = new ArrayList<Integer>();
		}
		return colsedList;
	}
	public void setColsedList(List<Integer> colsedList) {
		this.colsedList = colsedList;
	}
	public List<Integer> getRepaireOrderCountList() {
		if(repaireOrderCountList == null){
			repaireOrderCountList = new ArrayList<Integer>();
		}
		return repaireOrderCountList;
	}
	public void setRepaireOrderCountList(List<Integer> repaireOrderCountList) {
		this.repaireOrderCountList = repaireOrderCountList;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
