/**
 * 
 */
package com.ge.aviation.cube.engine.service.ifc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;

import com.ge.aviation.cube.engine.model.BarChart;
import com.ge.aviation.cube.engine.model.Dropdowns;
import com.ge.aviation.cube.engine.model.RepaireOrderWrapper;
import com.ge.aviation.cube.engine.model.User;

/**
 * @author TCS
 *
 */
@Qualifier("RepaireOrderServiceImpl")
public interface IRepaireOrderService {

	public RepaireOrderWrapper fetchRepairOrderByUser(Integer userId, String usPlant);
	
	public RepaireOrderWrapper fetchClosedRepairOrderByUser(Integer userId, String usPlant);

	public User fetchUser(String userId);

	public List<User> fetchFieldEngineer();

	public Dropdowns fetchDropdowns();

	//public List<HashMap<String, Object>> reportRepaireOrderCount();
	public BarChart reportRepaireOrderCount();

	public BarChart reportDaysLeftCount();

	public BarChart getRepaireCountPerShopChartData();
	
	
}
