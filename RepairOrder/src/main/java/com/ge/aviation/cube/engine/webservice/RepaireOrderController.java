package com.ge.aviation.cube.engine.webservice;

import java.util.List;
import java.util.Map;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.aviation.cube.engine.model.BarChart;
import com.ge.aviation.cube.engine.model.Dropdowns;
import com.ge.aviation.cube.engine.model.RepaireOrderWrapper;
import com.ge.aviation.cube.engine.model.User;
import com.ge.aviation.cube.engine.service.ifc.IRepaireOrderService;

@RestController
public class RepaireOrderController {
	@Autowired
	IRepaireOrderService repaireOrderService;

	@RequestMapping(value = "/services/repaireOrder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RepaireOrderWrapper> fetchRepairOrdersByUser(@RequestParam Integer userId, @RequestParam(value = "usPlant", defaultValue = "") String usPlant) {
		RepaireOrderWrapper repaireOrder = repaireOrderService.fetchRepairOrderByUser(userId, usPlant);
		return new ResponseEntity<RepaireOrderWrapper>(repaireOrder, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/services/repaireOrderClosed", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RepaireOrderWrapper> fetchClosedRepairOrdersByUser(@RequestParam Integer userId, @RequestParam(value = "usPlant", defaultValue = "") String usPlant) {
		RepaireOrderWrapper repaireOrder = repaireOrderService.fetchRepairOrderByUser(userId, usPlant);
		return new ResponseEntity<RepaireOrderWrapper>(repaireOrder, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/services/userRole", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> fetchUser(@RequestParam String userId) {
		User user = repaireOrderService.fetchUser(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/services/fieldEngineer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> fetchFieldEngineer() {
		List<User> userList = repaireOrderService.fetchFieldEngineer();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
/*	@RequestMapping(value = "/services/fieldEngineer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> fetchFieldEngineer() {
		List<User> userList = repaireOrderService.fetchFieldEngineer();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/services/searchList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dropdowns> fetchSearchDropdowns() {
		Dropdowns dropDowns = repaireOrderService.fetchDropdowns();
		return new ResponseEntity<Dropdowns>(dropDowns, HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/services/repaireOrderCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HashMap<String, Object>>> reportRepaireOrderCount() {
		List<HashMap<String, Object>> repaireOrderCount = repaireOrderService.reportRepaireOrderCount();
		return new ResponseEntity<List<HashMap<String, Object>>>(repaireOrderCount, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/services/repaireOrderCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BarChart> reportRepaireOrderCount() {
		BarChart repaireOrderCount = repaireOrderService.reportRepaireOrderCount();
		return new ResponseEntity<BarChart>(repaireOrderCount, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/services/daysLeftCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BarChart> reportDaysLeftCount() {
		BarChart repaireDaysLeftCount = repaireOrderService.reportDaysLeftCount();
		return new ResponseEntity<BarChart>(repaireDaysLeftCount, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/services/repaireCountPerShopChart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BarChart> reportRepaireCountPerShopChar() {
		BarChart repaireDaysLeftCount = repaireOrderService.getRepaireCountPerShopChartData();
		return new ResponseEntity<BarChart>(repaireDaysLeftCount, HttpStatus.OK);
	}
}