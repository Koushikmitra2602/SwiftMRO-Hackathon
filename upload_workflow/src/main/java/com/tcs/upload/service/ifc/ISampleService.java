/**
 * 
 */
package com.tcs.upload.service.ifc;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.tcs.upload.model.ExcelData;

/**
 * @author TCS
 *
 */
@Qualifier("SampleServiceImpl")
public interface ISampleService {
	public List<String> insertData(List<ExcelData> excelDataList);

	public List<String> deleteData(List<String> listOftable);
}
