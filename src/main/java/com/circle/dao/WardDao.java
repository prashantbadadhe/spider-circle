package com.circle.dao;

import java.util.List;

import com.circle.model.Ward;

public interface WardDao {

Ward findById(long id);
	
	Ward findByName(String name);
	
	void saveWard(Ward ward);
	
	void updateWard(Ward ward);
	
	int deleteWardById(long id);

	List<Ward> findAllWards(); 
	
	void deleteAllWards();
	
	public boolean isWardExist(Ward ward);

	public List<Ward> listAllWard(String noOfRecord, String pageNumber, String searchKeyword);

	public List<Ward> filteredWard(String searchKey, String itemsPerPage);

	public int countRecord(String searchKeyword);
}
