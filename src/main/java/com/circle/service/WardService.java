package com.circle.service;

import java.util.List;

import com.circle.model.Ward;

public interface WardService {
	
	Ward findById(long id);
	
	Ward findByName(String name);
	
	void saveWard(Ward ward);
	
	void updateWard(Ward ward);
	
	int deleteWardById(long id);

	List<Ward> findAllWards(); 
	
	void deleteAllWards();
	
	public boolean isWardExist(Ward ward);

	public List<com.circle.model.Ward> listAllWard(String noOfRecord, String pageNumber, String searchKeyword);

	public int countRecord(String searchKeyword);

	public List<com.circle.model.Ward> filteredWard(String searchKey, String itemsPerPage);
}
