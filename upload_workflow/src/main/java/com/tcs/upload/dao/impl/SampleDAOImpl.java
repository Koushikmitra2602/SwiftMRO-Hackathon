/**
 * 
 */
package com.tcs.upload.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tcs.upload.dao.ifc.ISampleDAO;
import com.tcs.upload.model.ExcelData;

/**
 * @author TCS
 *
 */
@Component
public class SampleDAOImpl implements ISampleDAO {
	//static final Logger LOGGER = Logger.getLogger(SampleDAOImpl.class.getName());
	@Autowired
	private final JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public SampleDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertData(ExcelData excelData) {
		
		List<Object[]> objectList = new ArrayList<>();
		Map<Integer, String> typeList = excelData.getListOfTypes();
		// TODO Auto-generated method stub
		for(Object[] array:excelData.getListOfData()){
			Object[] values = new Object[array.length];
			int i=0;
			for(Object object: array){
				if(typeList.get(i+1) != null && typeList.get(i+1).contains("timestamp")){
					if(object != null){
						Date date = new Date((String)object);
						values[i] = date; //new Timestamp(date.getTime());
					}else{
						values[i] = null;
					}
					
				}else if(typeList.get(i+1) != null && typeList.get(i+1).contains("integer")){
					if(object != null)
						values[i] = (int)Double.parseDouble((String)object);
					else
						values[i] = null;
				}else{
					values[i] = object;
				}
				i++;
			}
			objectList.add(values);
		}
		int[] count = this.jdbcTemplate.batchUpdate(excelData.getQuery(), objectList );
		System.out.println(excelData.getTableName() + ": "+ count);
	}

	@Override
	public void deleteData(String table) {
		// TODO Auto-generated method stub
		 this.jdbcTemplate.execute("delete from "+table);
	}
	
	

}
