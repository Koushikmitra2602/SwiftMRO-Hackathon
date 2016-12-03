package com.ge.aviation.cube.engine.model;

import java.util.ArrayList;
import java.util.List;

public class RepaireOrderWrapper {
	
	private List<RepaireOrder> repairOrderList;
	private Integer roCountLessThanFive;
	private Integer roCountFiveToTen;
	private Integer roCountGreaterThanTen;
	
	public List<RepaireOrder> getRepairOrderList() {
		if(repairOrderList == null){
			repairOrderList = new ArrayList<RepaireOrder>();
		}
		return repairOrderList;
	}
	public void setRepairOrderList(List<RepaireOrder> repairOrderList) {
		this.repairOrderList = repairOrderList;
	}
	public Integer getRoCountLessThanFive() {
		return roCountLessThanFive;
	}
	public void setRoCountLessThanFive(Integer roCountLessThanFive) {
		this.roCountLessThanFive = roCountLessThanFive;
	}
	public Integer getRoCountFiveToTen() {
		return roCountFiveToTen;
	}
	public void setRoCountFiveToTen(Integer roCountFiveToTen) {
		this.roCountFiveToTen = roCountFiveToTen;
	}
	public Integer getRoCountGreaterThanTen() {
		return roCountGreaterThanTen;
	}
	public void setRoCountGreaterThanTen(Integer roCountGreaterThanTen) {
		this.roCountGreaterThanTen = roCountGreaterThanTen;
	}
	
}
