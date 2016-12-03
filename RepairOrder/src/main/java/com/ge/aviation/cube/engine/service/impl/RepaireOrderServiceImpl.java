/**
 * 
 */
package com.ge.aviation.cube.engine.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.aviation.cube.engine.dao.ifc.IRepaireOrderDAO;
import com.ge.aviation.cube.engine.model.BarChart;
import com.ge.aviation.cube.engine.model.Dropdowns;
import com.ge.aviation.cube.engine.model.RepaireOrder;
import com.ge.aviation.cube.engine.model.RepaireOrderWrapper;
import com.ge.aviation.cube.engine.model.User;
import com.ge.aviation.cube.engine.service.ifc.IRepaireOrderService;

/**
 * @author TCS
 *
 */
@Service
public class RepaireOrderServiceImpl implements IRepaireOrderService {

	@Autowired
	private IRepaireOrderDAO repaireOrderkDAO;

	@Override
	public RepaireOrderWrapper fetchRepairOrderByUser(Integer userId, String usPlant) {
		
		RepaireOrderWrapper repaireOrderWrapper = new RepaireOrderWrapper();
		
		List<RepaireOrder> repaireOrderList = repaireOrderkDAO.fetchRepaireOrderByUser(userId, usPlant);
		
		Integer roCountLessThanFive=0;
		Integer roCountFiveToTen=0;
		Integer roCountGreaterThanTen=0; 
		
		for(RepaireOrder repaireOrder : repaireOrderList) {
			if(!repaireOrder.getStatusCode().equals("CL")){ 
				System.out.println("repaireOrder.getExpectedCloseDate() >>> " + repaireOrder.getExpectedCloseDate());
				System.out.println("new Date() >>> " + new Date());
				Long diff = ((repaireOrder.getExpectedCloseDate().getTime()-new Date().getTime())/86400000)+1;
				System.out.println("diff >>> " + diff);
				repaireOrder.setDaysLeft(diff);
				if(diff.compareTo(5L)<= 0){
					roCountLessThanFive = roCountLessThanFive + 1;
				} else if(diff.compareTo(5L) > 0 && diff.compareTo(10L) <= 0){
					roCountFiveToTen = roCountFiveToTen + 1;
				} else if(diff.compareTo(10L) > 0){
					roCountGreaterThanTen = roCountGreaterThanTen + 1;
				}
			}
		}
		
		repaireOrderWrapper.setRepairOrderList(repaireOrderList);
		repaireOrderWrapper.setRoCountLessThanFive(roCountLessThanFive);
		repaireOrderWrapper.setRoCountFiveToTen(roCountFiveToTen);
		repaireOrderWrapper.setRoCountGreaterThanTen(roCountGreaterThanTen);
		
		return repaireOrderWrapper;
	}
	
	@Override
	public RepaireOrderWrapper fetchClosedRepairOrderByUser(Integer userId, String usPlant) {
		
		RepaireOrderWrapper repaireOrderWrapper = new RepaireOrderWrapper();
		
		repaireOrderWrapper.getRepairOrderList().addAll(repaireOrderkDAO.fetchRepaireOrderByUser(userId, usPlant));
	
		return repaireOrderWrapper;
	}

	@Override
	public User fetchUser(String userId) {
		return repaireOrderkDAO.fetchUser(userId);
	}

	@Override
	public List<User> fetchFieldEngineer() {
		return  repaireOrderkDAO.fetchFieldEngineer();
	}

	@Override
	public Dropdowns fetchDropdowns() {
		Dropdowns dropdowns = new  Dropdowns();
		dropdowns.getWarehouseList().addAll(repaireOrderkDAO.fetchWarehouseList());
		dropdowns.getShopList().addAll(repaireOrderkDAO.fetchShopList());
		dropdowns.getStatusList().addAll(repaireOrderkDAO.fetchStatusList());
		dropdowns.getEquSerNumList().addAll(repaireOrderkDAO.fetchEquSerNumList());
		dropdowns.getEngineModelList().addAll(repaireOrderkDAO.fetchEngineModelList());
		dropdowns.getBatchNumList().addAll(repaireOrderkDAO.fetchBatchNumList());
		
		return dropdowns;
	}

	/*@Override
	public List<HashMap<String, Object>> reportRepaireOrderCount() {
		Map<String, Integer> repaireCountPerShop = repaireOrderkDAO.getRepaireCountPerShop();
		return convertDataBarChart(repaireCountPerShop);
	}
	
	private List<HashMap<String, Object>> convertDataBarChart(Map<String, Integer> repaireCountPerShop) {
		//BarChart barChart = new BarChart();
		
		List<HashMap<String, Object>> repaireOrderCount = new ArrayList<HashMap<String,Object>>();
		for(String shop : repaireCountPerShop.keySet()) {
			HashMap<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("name", shop);
			tempMap.put("y", repaireCountPerShop.get(shop));
			tempMap.put("drilldown", shop);
			repaireOrderCount.add(tempMap);
		}
		return repaireOrderCount;

	}*/
	
	@Override
	public BarChart reportRepaireOrderCount() {
		Map<String, Integer> repaireCountPerShop = repaireOrderkDAO.getRepaireCountPerShop();
		return convertDataBarChart(repaireCountPerShop);
	}
	
	private BarChart convertDataBarChart(Map<String, Integer> repaireCountPerShop) {
		//BarChart barChart = new BarChart();
		System.out.println("repaireCountPerShop Map >>> " + repaireCountPerShop);
		BarChart barChart = new BarChart();
		Integer total = 0;
		for(String shop : repaireCountPerShop.keySet()) {
			barChart.getRepaireOrderCountList().add(repaireCountPerShop.get(shop));
			barChart.getxAxisList().add(shop);
			total = total + repaireCountPerShop.get(shop);
		}
		barChart.setTotal(total);
		System.out.println("repaireOrderCount List >>> " + barChart);
		return barChart;

	}

	@Override
	public BarChart reportDaysLeftCount() {
		Map<String, Integer> repaireCountPerShop = repaireOrderkDAO.getReportDaysLeftCount();
		return convertPieData(repaireCountPerShop);
	}

	private BarChart convertPieData(Map<String, Integer> repaireCountPerShop) {
		BarChart pieChart = new BarChart();
		int count = 0;
		for(String status : repaireCountPerShop.keySet()) {
			if(status.equals("High")){
				pieChart.setTotal(repaireCountPerShop.get(status));
			}
			HashMap<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("name", status);
			tempMap.put("y", repaireCountPerShop.get(status));
			if(count == 0){
				tempMap.put("sliced", true);
				tempMap.put("selected", true);
			}
			count++;
			pieChart.getDataMap().add(tempMap);
		}
		return pieChart;
	}

	@Override
	public BarChart getRepaireCountPerShopChartData() {
		Map<String, Map<String, Integer>> repaireCountPerShop = repaireOrderkDAO.getRepaireOrderCount();
		BarChart barChart = new BarChart();
		barChart.setxAxisList(new ArrayList<String>(repaireCountPerShop.keySet()));
		Integer total = 0;
		for(String shop : repaireCountPerShop.keySet()) {
			Map<String, Integer> map = repaireCountPerShop.get(shop);
			for(String status : map.keySet()){
				total = total + map.get(status);
				if(status.equals("WIP")){
					barChart.getWipList().add(map.get(status));
				} else if(status.equals("PC")){
					barChart.getPartillyColsedList().add(map.get(status));
				} else if(status.equals("CL")){
					barChart.getColsedList().add(map.get(status));
				} 
			}
		}
		
		Map<String, Object> wipMap = new HashMap<String, Object>();
		wipMap.put("name", "WIP");
		wipMap.put("data", barChart.getWipList());
		barChart.getDataMap().add(wipMap);
		
		Map<String, Object> pcMap = new HashMap<String, Object>();
		pcMap.put("name", "Partilly Closed");
		pcMap.put("data", barChart.getPartillyColsedList());
		barChart.getDataMap().add(pcMap);
		
		Map<String, Object> closeMap = new HashMap<String, Object>();
		closeMap.put("name", "Closed");
		closeMap.put("data", barChart.getColsedList());
		barChart.getDataMap().add(closeMap);
		
		barChart.setTotal(total);
		
		return barChart;
	}

}
