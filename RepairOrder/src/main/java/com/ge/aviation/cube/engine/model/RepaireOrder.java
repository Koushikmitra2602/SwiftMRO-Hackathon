package com.ge.aviation.cube.engine.model;

import java.util.Date;

public class RepaireOrder {
	
	private String statusCode;
	private Integer repaireOrderNum;
	private Integer equNum;
	private String equSerNum;
	private String equDesc;
	private Long batchNum;
	private String storeName;
	private String storeLoc;
	private String engineModel;
	private String repairOrderoDesc;
	private String repaireShop;
	private String repaireShopLoc;
	private Date receivedDate;
	private String createdBy;
	private Date createdDate;
	private Date expectedCloseDate;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;
	private String vendor;
	private String capability;
	private Long daysLeft;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Integer getRepaireOrderNum() {
		return repaireOrderNum;
	}
	public void setRepaireOrderNum(Integer repaireOrderNum) {
		this.repaireOrderNum = repaireOrderNum;
	}
	public Integer getEquNum() {
		return equNum;
	}
	public void setEquNum(Integer equNum) {
		this.equNum = equNum;
	}
	public String getEquDesc() {
		return equDesc;
	}
	public void setEquDesc(String equDesc) {
		this.equDesc = equDesc;
	}
	public String getEquSerNum() {
		return equSerNum;
	}
	public void setEquSerNum(String equSerNum) {
		this.equSerNum = equSerNum;
	}
	public Long getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(Long batchNum) {
		this.batchNum = batchNum;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreLoc() {
		return storeLoc;
	}
	public void setStoreLoc(String storeLoc) {
		this.storeLoc = storeLoc;
	}
	public String getEngineModel() {
		return engineModel;
	}
	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}
	public String getRepairOrderoDesc() {
		return repairOrderoDesc;
	}
	public void setRepairOrderoDesc(String repairOrderoDesc) {
		this.repairOrderoDesc = repairOrderoDesc;
	}
	public String getRepaireShop() {
		return repaireShop;
	}
	public void setRepaireShop(String repaireShop) {
		this.repaireShop = repaireShop;
	}
	public String getRepaireShopLoc() {
		return repaireShopLoc;
	}
	public void setRepaireShopLoc(String repaireShopLoc) {
		this.repaireShopLoc = repaireShopLoc;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getExpectedCloseDate() {
		return expectedCloseDate;
	}
	public void setExpectedCloseDate(Date expectedCloseDate) {
		this.expectedCloseDate = expectedCloseDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getCapability() {
		return capability;
	}
	public void setCapability(String capability) {
		this.capability = capability;
	}
	public Long getDaysLeft() {
		return daysLeft;
	}
	public void setDaysLeft(Long daysLeft) {
		this.daysLeft = daysLeft;
	}

	

}
