/**
 * 
 */
package com.ge.aviation.cube.engine.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ge.aviation.cube.engine.dao.ifc.IRepaireOrderDAO;
import com.ge.aviation.cube.engine.model.RepaireOrder;
import com.ge.aviation.cube.engine.model.User;

/**
 * @author TCS
 *
 */
@Component
public class RepaireOrderDAOImpl implements IRepaireOrderDAO {

	@Autowired
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public RepaireOrderDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<RepaireOrder> fetchRepaireOrderByUser(Integer userId, String usPlant) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		param.add(userId);
		stringBuilder.append("select RO.status_code, RO.rep_ord_num, EQM.equ_num, EQM.equ_desc, EQM.equ_ser_num, EQM.batch_num, SM.store_name, SM.store_loc, EM.engine_model ");
		stringBuilder.append(" , RO.rep_ord_desc, RO.received_date, RO.creation_date, RO.expected_close_date, RO.created_by, RO.last_update_by, RO.last_update_date, RO.rep_int_ext ");
		stringBuilder.append(", repshop.repair_shop_name , repshop.repair_shop_loc ");
		stringBuilder.append(" from swift_mro_rep_ord_det RO left outer join swift_mro_repair_shop repshop on RO.rep_shop_id=repshop.rep_shop__id, swift_mro_equ_master EQM, ");
		stringBuilder.append(" swift_mro_store_master SM, swift_mro_eng_master EM ");
		stringBuilder.append(" WHERE RO.equ_ser_seq_id = EQM.equ_ser_seq_id AND RO.store_id = SM.store_id AND EQM.esn_seq_id = EM.esn_seq_id AND RO.status_code<> 'CL' AND RO.req_created_by = ? ");
		if(usPlant.length() > 0){
			stringBuilder.append(" AND UPPER(SM.store_name) like ?");
			param.add("%"+usPlant.toUpperCase()+"%");
		}
		
		stringBuilder.append(" ORDER BY RO.rep_ord_num ");
		
		//System.out.println("stringBuilder.toString() >>> " + stringBuilder.toString());
		return jdbcTemplate.query(stringBuilder.toString(), param.toArray(), new RepaireOrderRowMapper());
	}
	
	@Override
	public List<RepaireOrder> fetchClosedRepaireOrderByUser(Integer userId, String usPlant) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		param.add(userId);
		stringBuilder.append("select RO.status_code, RO.rep_ord_num, EQM.equ_num, EQM.equ_desc, EQM.equ_ser_num, EQM.batch_num, SM.store_name, SM.store_loc, EM.engine_model ");
		stringBuilder.append(" , RO.rep_ord_desc, RO.received_date, RO.creation_date, RO.expected_close_date, RO.created_by, RO.last_update_by, RO.last_update_date, RO.rep_int_ext ");
		stringBuilder.append(", repshop.repair_shop_name , repshop.repair_shop_loc ");
		stringBuilder.append(" from swift_mro_rep_ord_det RO left outer join swift_mro_repair_shop repshop on RO.rep_shop_id=repshop.rep_shop__id, swift_mro_equ_master EQM, ");
		stringBuilder.append(" swift_mro_store_master SM, swift_mro_eng_master EM ");
		stringBuilder.append(" WHERE RO.equ_ser_seq_id = EQM.equ_ser_seq_id AND RO.store_id = SM.store_id AND EQM.esn_seq_id = EM.esn_seq_id AND RO.status_code = 'CL' AND RO.req_created_by = ? ");
		if(usPlant.length() > 0){
			stringBuilder.append(" AND UPPER(SM.store_name) like ?");
			param.add("%"+usPlant.toUpperCase()+"%");
		}
		
		stringBuilder.append(" ORDER BY RO.rep_ord_num ");
		
		//System.out.println("stringBuilder.toString() >>> " + stringBuilder.toString());
		return jdbcTemplate.query(stringBuilder.toString(), param.toArray(), new RepaireOrderRowMapper());
	}

	class RepaireOrderRowMapper implements RowMapper<RepaireOrder> {
		@Override
		public RepaireOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
			RepaireOrder repOrder = new RepaireOrder();
			repOrder.setStatusCode(rs.getString("status_code"));
			repOrder.setRepaireOrderNum(rs.getInt("rep_ord_num"));
			repOrder.setEquNum(rs.getInt("equ_num"));
			repOrder.setEquSerNum(rs.getString("equ_ser_num"));
			repOrder.setEquDesc(rs.getString("equ_desc"));
			repOrder.setBatchNum(rs.getLong("batch_num"));
			repOrder.setStoreName(rs.getString("store_name"));
			repOrder.setStoreLoc(rs.getString("store_loc"));
			repOrder.setEngineModel(rs.getString("engine_model"));
			repOrder.setRepairOrderoDesc(rs.getString("rep_ord_desc"));
			String repainrShop = rs.getString("repair_shop_name");
			String repainrShopLoc = rs.getString("repair_shop_loc");
			repOrder.setRepaireShop(repainrShop == null ? "" : repainrShop);
			repOrder.setRepaireShopLoc(repainrShopLoc == null ? "" : repainrShopLoc );
			repOrder.setRepairOrderoDesc(rs.getString("rep_ord_desc"));
			repOrder.setReceivedDate(rs.getDate("received_date"));
			repOrder.setCreatedDate(rs.getDate("creation_date"));
			repOrder.setExpectedCloseDate(rs.getDate("expected_close_date"));
			repOrder.setCreatedBy(rs.getString("created_by"));
			repOrder.setLastUpdatedBy(rs.getString("last_update_by"));
			repOrder.setLastUpdatedDate(rs.getDate("last_update_date"));
			String capibility = rs.getString("rep_int_ext");
			repOrder.setVendor(capibility.equalsIgnoreCase("Internal") ? "NO" : "YES");
			repOrder.setCapability(capibility);
			return repOrder;
		}
	}

	@Override
	public User fetchUser(String userId) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select user_sso, user__first_name, user_last_name, role_code, role_desc, phone, email, user_id ");
		stringBuilder.append("from swift_mro_users where user__first_name=?");
		
		List<Object> param = new ArrayList<Object>();
		param.add(userId);
		
		return jdbcTemplate.query(stringBuilder.toString(), param.toArray(), new UserMapper()).get(0);
	}

	
	class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setSso(rs.getLong("user_sso"));
			user.setFirstName(rs.getString("user__first_name"));
			user.setLastName(rs.getString("user_last_name"));
			user.setRoleCode(rs.getString("role_code"));
			user.setRoleDesc(rs.getString("role_desc"));
			user.setPhone(rs.getString("phone"));
			user.setEmail(rs.getString("email"));
			user.setUserId(rs.getInt("user_id"));
			return user;
		}
	}
	
	class UserTaskMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setSso(rs.getLong("user_sso"));
			user.setFirstName(rs.getString("user__first_name"));
			user.setLastName(rs.getString("user_last_name"));
			user.setRoleCode(rs.getString("role_code"));
			user.setRoleDesc(rs.getString("role_desc"));
			user.setPhone(rs.getString("phone"));
			user.setEmail(rs.getString("email"));
			user.setUserId(rs.getInt("user_id"));
			//TODO:
			user.setNumOfOpenTask(rs.getInt("taskCnt"));
			user.setRepaireShop(rs.getString("repair_shop_name"));
			return user;
		}
	}

	@Override
	public List<User> fetchFieldEngineer() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select usr.user_sso, usr.user__first_name, usr.user_last_name, usr.role_code, usr.role_desc, usr.phone, usr.email, usr.user_id , shop.repair_shop_name, ");
		stringBuilder.append(" (select count(*) from swift_mro_task_det tsk where tsk.status_code <> 'CL' and tsk.assigned_to = usr.user_id) taskCnt ");
		stringBuilder.append(" from swift_mro_users usr ,swift_mro_user_rep_shop_map map, swift_mro_repair_shop shop where usr.role_code='FE' and usr.user_id = map.user_id ");
		stringBuilder.append(" and shop.rep_shop__id=map.rep_shop_id ");
		
		return jdbcTemplate.query(stringBuilder.toString(), new UserTaskMapper());
	}

	@Override
	public List<String> fetchWarehouseList() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT distinct(store_name) store_name FROM swift_mro_store_master");
		
		List<String> warehouseList = jdbcTemplate.query(stringBuilder.toString(), new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("store_name");
			}
		}); 
		//System.out.println("stringBuilder.toString() >>> " + stringBuilder.toString());
		return warehouseList;
	}
	
	@Override
	public List<String> fetchShopList() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT distinct(repair_shop_name) shop_name FROM swift_mro_repair_shop");
		
		List<String> shopList = jdbcTemplate.query(stringBuilder.toString(), new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("shop_name");
			}
		}); 
		//System.out.println("stringBuilder.toString() >>> " + stringBuilder.toString());
		return shopList;
	}
	
	@Override
	public List<String> fetchStatusList() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT distinct(status_desc) status_desc FROM swift_mro_status_master where status_code IN ('Created', 'WIP', 'CL', 'PC')");
		
		List<String> statusList = jdbcTemplate.query(stringBuilder.toString(), new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("status_desc");
			}
		}); 
		//System.out.println("stringBuilder.toString() >>> " + stringBuilder.toString());
		return statusList;
	}
	
	@Override
	public List<String> fetchEquSerNumList() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT distinct(equ_ser_num) equ_ser_num FROM swift_mro_equ_master");
		
		List<String> equSerNumList = jdbcTemplate.query(stringBuilder.toString(), new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("equ_ser_num");
			}
		}); 
		//System.out.println("stringBuilder.toString() >>> " + stringBuilder.toString());
		return equSerNumList;
	}
	
	@Override
	public List<String> fetchEngineModelList() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT distinct(engine_model) engine_model FROM swift_mro_eng_master");
		
		List<String> engineModelList = jdbcTemplate.query(stringBuilder.toString(), new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("engine_model");
			}
		}); 
		//System.out.println("stringBuilder.toString() >>> " + stringBuilder.toString());
		return engineModelList;
	}
	
	@Override
	public List<String> fetchBatchNumList() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT distinct(batch_num) batch_num FROM swift_mro_equ_master");
		
		List<String> batchNumList = jdbcTemplate.query(stringBuilder.toString(), new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("batch_num");
			}
		}); 
		//System.out.println("stringBuilder.toString() >>> " + stringBuilder.toString());
		return batchNumList;
	}

	@Override
	public Map<String, Integer> getRepaireCountPerShop() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select distinct b.store_loc , count(0) repcnt from swift_mro_store_master b left outer join swift_mro_rep_ord_det a on  "); 
		stringBuilder.append(" a.store_id=b.store_id and a.status_code<> 'CL' group by b.store_loc");
		
		List<Map<String, Integer>> repaireCountList = jdbcTemplate.query(stringBuilder.toString(), new RowMapper<Map<String, Integer>>() {
			Map<String, Integer> repaireCount = new HashMap<String, Integer>();
			@Override
			public Map<String, Integer> mapRow(ResultSet rs, int arg1) throws SQLException {
				repaireCount.put(rs.getString("store_loc"), rs.getInt("repcnt"));
				return repaireCount;
			}
		}); 
		//System.out.println("stringBuilder.toString() >>> " + stringBuilder.toString());
		return repaireCountList.get(0);
	}

	@Override
	public Map<String, Integer> getReportDaysLeftCount() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select   rep.tskclass, count(0) repcnt from "); 
		stringBuilder.append(" (select a.status_code, ");
		stringBuilder.append(" case when a.status_code<> 'CL' and (date_part('day' , a.expected_close_date - current_timestamp) )>-99999 and (date_part('day' , a.expected_close_date - current_timestamp) )<=5 then 'High' "); 
		stringBuilder.append(" when a.status_code<> 'CL' and (date_part('day' , a.expected_close_date - current_timestamp) )>5 and (date_part('day' , a.expected_close_date - current_timestamp) )<=10 then 'Medium' ");
		stringBuilder.append(" when a.status_code<> 'CL' and (date_part('day' , a.expected_close_date - current_timestamp) )>10 and (date_part('day' , a.expected_close_date - current_timestamp) )<=99999 then 'Low' ");
		stringBuilder.append(" else 'Closed' end tskclass   from swift_mro_rep_ord_det a  where a.status_code<> 'CL') rep    group by  rep.tskclass");
		
		List<Map<String, Integer>> daysLeftCountList = jdbcTemplate.query(stringBuilder.toString(), new RowMapper<Map<String, Integer>>() {
			Map<String, Integer> daysLeftCount = new HashMap<String, Integer>();
			@Override
			public Map<String, Integer> mapRow(ResultSet rs, int arg1) throws SQLException {
				daysLeftCount.put(rs.getString("tskclass"), rs.getInt("repcnt"));
				return daysLeftCount;
			}
		}); 
		//System.out.println("stringBuilder.toString() >>> " + stringBuilder.toString());
		return daysLeftCountList.get(0);
	}
	
	@Override
	public Map<String, Map<String, Integer>> getRepaireOrderCount() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select shop.repair_shop_name,tsk.status_code, count(0) count from swift_mro_task_det tsk , swift_mro_repair_shop shop ");
		stringBuilder.append(" where shop.rep_shop__id = tsk.rep_shop_id group by shop.repair_shop_name,tsk.status_code ");
		
		List<Map<String, Map<String, Integer>>> storeReqMapList = jdbcTemplate.query(stringBuilder.toString(), new RowMapper<Map<String, Map<String, Integer>>>() {
			Map<String, Map<String, Integer>> storeMap = new HashMap<String, Map<String,Integer>>();
			@Override
			public Map<String, Map<String, Integer>> mapRow(ResultSet rs, int arg1) throws SQLException {
				String store = rs.getString("repair_shop_name");
				if(storeMap.containsKey(store)){
					storeMap.get(store).put(rs.getString("status_code"), rs.getInt("count"));
				} else {
					Map<String, Integer> statusCountMap = new HashMap<String, Integer>();
					statusCountMap.put(rs.getString("status_code"), rs.getInt("count"));
					storeMap.put(store, statusCountMap);
				}
				return storeMap;
			}
		}); 
		return storeReqMapList.get(0);
	}
	
	
}
