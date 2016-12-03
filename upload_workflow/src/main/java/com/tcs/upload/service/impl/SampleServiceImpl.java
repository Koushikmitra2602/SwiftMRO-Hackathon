/**
 * 
 */
package com.tcs.upload.service.impl;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.upload.dao.ifc.ISampleDAO;
import com.tcs.upload.model.ExcelData;
import com.tcs.upload.service.ifc.ISampleService;

/**
 * @author TCS
 *
 */
@Service
public class SampleServiceImpl implements ISampleService {

	//private static final Logger LOGGER = Logger.getLogger(SampleServiceImpl.class.getName());
	@Autowired
	private ISampleDAO sampleDAO;

	@Override
	public List<String> insertData(List<ExcelData> excelDataList) {
		// TODO Auto-generated method stub
		List<String> messageList = new ArrayList<>();	
		for(ExcelData excelData : excelDataList){
			try{
				this.sampleDAO.insertData(excelData);
				messageList.add(excelData.getTableName() +": Data Inserted Successfully");
			}catch(Exception e){
				e.printStackTrace();
				messageList.add(excelData.getTableName() +": Data Inserted Falied --"+e.getMessage());
			}
		}
		return messageList;
	}

	@Override
	public List<String> deleteData(List<String> listOftable) {
		// TODO Auto-generated method stub
		List<String> messageList = new ArrayList<>();	
		for(String table : listOftable){
			try{
				this.sampleDAO.deleteData(table);
				messageList.add(table +": Data deleted Successfully");
			}catch(Exception e){
				e.printStackTrace();
				messageList.add(table +": Data deleted Falied --"+e.getMessage());
			}
		}
		return messageList;
	}

}
