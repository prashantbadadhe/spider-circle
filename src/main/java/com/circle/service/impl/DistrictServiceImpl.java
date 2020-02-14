package com.circle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.DistrictDao;
import com.circle.model.District;
import com.circle.service.DistrictService;

@Service("districtService")
@Transactional
public class DistrictServiceImpl implements DistrictService{
	
	@Autowired
	private DistrictDao districtDao;
	
	public List<District> findAllDistricts() {
		return districtDao.findAllDistricts();
	}
	
	@Override
	public District findById(long id) {
		
		return districtDao.findById(id);
	}
	@Override
	public District findByName(String userName) {
		return districtDao.findByName(userName);
	}
	@Override
	public void saveDistrict(District district) {
		districtDao.saveDistrict(district);
	}
	@Override
	public void updateDistrict(District district) {
		districtDao.updateDistrict(district);
	}
	@Override
	public int deleteDistrictById(long id) {

		return districtDao.deleteDistrictById(id);
	}

	@Override
	public boolean isDistrictExist(District district) {
		return findByName(district.getName())!=null;
	}
	@Override
	public void deleteAllDistricts(){
		districtDao.deleteAllDistricts();
	}

	@Override
	public List<District> listAllDistrict(String noOfRecord, String pageNumber,String searchKey) {
		return districtDao.listAllDistrict(noOfRecord,pageNumber,searchKey);
	}

	@Override
	public int countRecords(String searchKey) {
		return districtDao.countRecord(searchKey);
	}

	@Override
	public List<District> filteredDistrict(String searchKey, String itemsPerPage) {
		return districtDao.filteredDistrict(searchKey,itemsPerPage);
	}

 

	 
}
