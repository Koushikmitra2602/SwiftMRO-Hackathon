package com.ge.aviation.cube.engine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dropdowns implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<String> warehouseList;
	List<String> equSerNumList;
	List<String> locationList;
	List<String> batchNumList;
	List<String> shopList;
	List<String> engineModelList;
	
	public List<String> getWarehouseList() {
		if(warehouseList == null){
			warehouseList = new ArrayList<String>();
		}
		return warehouseList;
	}
	public void setWarehouseList(List<String> warehouseList) {
		this.warehouseList = warehouseList;
	}
	
	public List<String> getEquSerNumList() {
		if(equSerNumList == null){
			equSerNumList = new ArrayList<String>();
		}
		return equSerNumList;
	}
	public void setEquSerNumList(List<String> equSerNumList) {
		this.equSerNumList = equSerNumList;
	}
	public List<String> getStatusList() {
		if(locationList == null){
			locationList = new ArrayList<String>();
		}
		return locationList;
	}
	public void setLocationList(List<String> locationList) {
		this.locationList = locationList;
	}
	
	public List<String> getBatchNumList() {
		if(batchNumList == null){
			batchNumList = new ArrayList<String>();
		}
		return batchNumList;
	}
	public void setBatchNumList(List<String> batchNumList) {
		this.batchNumList = batchNumList;
	}
	
	public List<String> getShopList() {
		if(shopList == null){
			shopList = new ArrayList<String>();
		}
		return shopList;
	}
	public void setShopList(List<String> shopList) {
		this.shopList = shopList;
	}
	
	public List<String> getEngineModelList() {
		if(engineModelList == null){
			engineModelList = new ArrayList<String>();
		}
		return engineModelList;
	}
	public void setEngineModelList(List<String> engineModelList) {
		this.engineModelList = engineModelList;
	}
	
}
