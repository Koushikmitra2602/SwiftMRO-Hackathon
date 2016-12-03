package com.tcs.upload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.upload.model.ExcelData;
import com.tcs.upload.service.ifc.ISampleService;
import com.tcs.upload.util.ExcelReader;



@RestController
public class SampleController {

	//private static final Logger LOGGER = Logger.getLogger(SampleController.class);
	
	@Autowired
	ISampleService sampleService;
	
	@RequestMapping(
			value ="/api/readExcel/data",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<ExcelData>> readLocalExcelData(@RequestParam("file") MultipartFile file){
		//String excelFilePath = "C:/Users/502021924/Documents/Datamodel.xls";
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ExcelData> outputDataList = ExcelReader.readExcel(inputStream);
		return new ResponseEntity<List<ExcelData>>(outputDataList, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value ="/api/uploadExcel/data",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<String>> readExcelData(@RequestParam("file") MultipartFile file){
		System.out.println(file.getOriginalFilename());
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ExcelData> listOfExcelData = ExcelReader.readExcel(inputStream);
		List<String> outMessageList = sampleService.insertData(listOfExcelData);
		return new ResponseEntity<List<String>>(outMessageList, HttpStatus.OK);
		//return new ResponseEntity<List<ExcelData>>(outputDataList, HttpStatus.OK);
	}
	
	@RequestMapping(
			value ="/api/insert/data",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<String>> uploadData(@RequestBody List<ExcelData> listOfExcelData){
		//call insert code
		List<String> outMessageList = sampleService.insertData(listOfExcelData);
		return new ResponseEntity<List<String>>(outMessageList, HttpStatus.OK);
	}
	
	@RequestMapping(
			value ="/api/deleteTable/data",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<String>> deleteData(@RequestBody List<String> listOftable){
		//call insert code
		List<String> outMessageList = sampleService.deleteData(listOftable);
		return new ResponseEntity<List<String>>(outMessageList, HttpStatus.OK);
	}
}