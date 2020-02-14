package com.circle.service;

import java.util.List;

import com.circle.model.Village;

public interface VillageService {
	
	Village findById(long id);
	
	Village findByName(String name);
	
	void saveVillage(Village village);
	
	void updateVillage(Village village);
	
	int deleteVillageById(long id);

	List<Village> findAllVillages(); 
	
	void deleteAllVillages();
	
	public boolean isVillageExist(Village village);

	public List<Village> listAllVillage(String noOfRecord, String pageNumber, String searchKeyword);

	public int  countRecord(String searchKeyword);

	public List<Village> filteredVillage(String searchKey, String itemsPerPage);
}
