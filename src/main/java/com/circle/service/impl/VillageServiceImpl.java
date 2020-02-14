package com.circle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.VillageDao;
import com.circle.model.Village;
import com.circle.service.VillageService;

@Service("villageService")
@Transactional
public class VillageServiceImpl implements VillageService{
	
	@Autowired
	private VillageDao villageDao;
	
	public List<Village> findAllVillages() {
		return villageDao.findAllVillages();
	}
	
	@Override
	public Village findById(long id) {
		
		return villageDao.findById(id);
	}
	@Override
	public Village findByName(String userName) {
		return villageDao.findByName(userName);
	}
	@Override
	public void saveVillage(Village village) {
		villageDao.saveVillage(village);
	}
	@Override
	public void updateVillage(Village village) {
		villageDao.updateVillage(village);
	}
	@Override
	public int deleteVillageById(long id) {

		return villageDao.deleteVillageById(id);
	}

	@Override
	public boolean isVillageExist(Village village) {
		return findByName(village.getName())!=null;
	}
	@Override
	public void deleteAllVillages(){
		villageDao.deleteAllVillages();
	}

	@Override
	public List<Village> listAllVillage(String noOfRecord, String pageNumber, String searchKeyword) {
		return villageDao.listAllVillage(noOfRecord, pageNumber, searchKeyword);
	}

	@Override
	public int countRecord(String searchKeyword) {
		return villageDao.countRecord(searchKeyword) ;
	}

	@Override
	public List<Village> filteredVillage(String searchKey, String itemsPerPage) {
		return  villageDao.filteredVillage(searchKey, itemsPerPage);
	}

 

	 
}
