/**
 * 
 */
package com.ge.aviation.cube.engine.dao.ifc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;

import com.ge.aviation.cube.engine.model.RepaireOrder;
import com.ge.aviation.cube.engine.model.User;

/**
 * @author TCS
 *
 */
@Qualifier("RepaireOrderDAOImpl")
public interface IRepaireOrderDAO {

	List<RepaireOrder> fetchRepaireOrderByUser(Integer userId, String usPlant);
	
	List<RepaireOrder> fetchClosedRepaireOrderByUser(Integer userId, String usPlant);

	User fetchUser(String userId);

	List<User> fetchFieldEngineer();

	List<String> fetchWarehouseList();

	List<String> fetchShopList();

	List<String> fetchStatusList();

	List<String> fetchEquSerNumList();

	List<String> fetchEngineModelList();

	List<String> fetchBatchNumList();

	Map<String, Integer> getRepaireCountPerShop();
	
	Map<String, Integer> getReportDaysLeftCount();

	Map<String, Map<String, Integer>> getRepaireOrderCount();

}
