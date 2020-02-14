package com.circle.service;

import java.util.List;

import com.circle.model.District;

public interface DistrictService {
	
	District findById(long id);
	
	District findByName(String name);
	
	void saveDistrict(District district);
	
	void updateDistrict(District district);
	
	public int deleteDistrictById(long id);

	List<District> findAllDistricts(); 
	
	void deleteAllDistricts();
	
	public boolean isDistrictExist(District district);

	public List<District> listAllDistrict(String noOfRecord, String pageNumber, String searchKeyword);

	public int countRecords(String searchKeyword);

	public List<District> filteredDistrict(String searchKey, String itemsPerPage);
}
