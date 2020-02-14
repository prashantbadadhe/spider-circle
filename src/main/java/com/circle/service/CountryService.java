package com.circle.service;

import java.util.List;

import com.circle.model.Country;

public interface CountryService {
	
	Country findById(long id);
	
	Country findByName(String name);
	
	void saveCountry(Country country);
	
	void updateCountry(Country country);
	
	public int deleteCountryById(long id);

	List<Country> findAllCountries(String noOfRecord, String pageNumber, String searchKeyword); 
	
	void deleteAllCountries();
	
	public boolean isCountryExist(Country country);

	List<Country> filteredCountry(String searchKey, String itemsPerPage);
	public int countRecord(String searchKeyword);

	public List<Country> getCountries();
}
