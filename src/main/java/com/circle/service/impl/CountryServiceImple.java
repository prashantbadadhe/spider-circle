package com.circle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.CountryDao;
import com.circle.model.Country;
import com.circle.service.CountryService;

@Service("countryrService")
@Transactional
public class CountryServiceImple implements CountryService{
	
	@Autowired
	private CountryDao countryDao;
	
	public List<Country> findAllCountries(String noOfRecord,String pageNumber,String searchKeyword) {
		return countryDao.findAllCountries(noOfRecord,pageNumber,searchKeyword);
	}
	
	@Override
	public Country findById(long id) {
		
		return countryDao.findById(id);
	}
	@Override
	public Country findByName(String userName) {
		return countryDao.findByName(userName);
	}
	@Override
	public void saveCountry(Country country) {
		countryDao.saveCountry(country);
	}
	@Override
	public void updateCountry(Country country) {
		countryDao.updateCountry(country);
	}
	@Override
	public int deleteCountryById(long id) {

		return countryDao.deleteCountryById(id);
	}

	@Override
	public boolean isCountryExist(Country country) {
		return findByName(country.getName())!=null;
	}
	@Override
	public void deleteAllCountries(){
		countryDao.deleteAllCountries();
	}

	@Override
	public List<Country> filteredCountry(String searchKey,String itemsPerPage) {
		return countryDao.filteredCountry(searchKey, itemsPerPage);
	}

	@Override
	public int countRecord(String searchKeyword) {
		return countryDao.countRecords(searchKeyword);
	}

	@Override
	public List<Country> getCountries() {
		return countryDao.getCountries();

	}

 

	 
}
