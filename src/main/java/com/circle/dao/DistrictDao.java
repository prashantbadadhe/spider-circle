package com.circle.dao;

import java.util.List;

import com.circle.model.District;

public interface DistrictDao {

District findById(long id);
	
	District findByName(String name);
	
	void saveDistrict(District district);
	
	void updateDistrict(District district);
	
	public int deleteDistrictById(long id);

	List<District> findAllDistricts(); 
	
	void deleteAllDistricts();
	
	public boolean isDistrictExist(District district);

	public List<District> listAllDistrict(String noOfRecord, String pageNumber, String searchKey);

	public int countRecord(String searchKey);

	public List<District> filteredDistrict(String searchKey, String itemsPerPage);

}
