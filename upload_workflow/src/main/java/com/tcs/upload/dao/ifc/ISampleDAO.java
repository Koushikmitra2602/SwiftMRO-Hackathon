/**
 * 
 */
package com.tcs.upload.dao.ifc;

import org.springframework.beans.factory.annotation.Qualifier;

import com.tcs.upload.model.ExcelData;

/**
 * @author TCS
 *
 */
@Qualifier("SampleDAOImpl")
public interface ISampleDAO {

	public void insertData(ExcelData excelData);

	public void deleteData(String table);
}
