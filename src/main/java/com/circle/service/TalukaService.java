package com.circle.service;

import java.util.List;

import com.circle.model.Taluka;

public interface TalukaService {
	
	Taluka findById(long id);
	
	Taluka findByName(String name);
	
	void saveTaluka(Taluka taluka);
	
	void updateTaluka(Taluka taluka);
	
	int deleteTalukaById(long id);

	List<Taluka> findAllTalukas(); 
	
	void deleteAllTalukas();
	
	public boolean isTalukaExist(Taluka taluka);

	public List<Taluka> listAllTaluka(String noOfRecord, String pageNumber, String searchKeyword);

	public List<Taluka> filteredTaluka(String searchKey, String itemsPerPage);

	public int countRecord(String searchKeyword);
}
