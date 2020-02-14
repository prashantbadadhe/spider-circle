package com.circle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.WardDao;
import com.circle.model.Ward;
import com.circle.service.WardService;

@Service("wardService")
@Transactional
public class WardServiceImpl implements WardService{
	
	@Autowired
	private WardDao wardDao;
	
	public List<Ward> findAllWards() {
		return wardDao.findAllWards();
	}
	
	@Override
	public Ward findById(long id) {
		
		return wardDao.findById(id);
	}
	@Override
	public Ward findByName(String userName) {
		return wardDao.findByName(userName);
	}
	@Override
	public void saveWard(Ward ward) {
		wardDao.saveWard(ward);
	}
	@Override
	public void updateWard(Ward ward) {
		wardDao.updateWard(ward);
	}
	@Override
	public int deleteWardById(long id) {

		return wardDao.deleteWardById(id);
	}

	@Override
	public boolean isWardExist(Ward ward) {
		return findByName(ward.getName())!=null;
	}
	@Override
	public void deleteAllWards(){
		wardDao.deleteAllWards();
	}

	@Override
	public List<Ward> listAllWard(String noOfRecord, String pageNumber, String searchKeyword) {
		return wardDao.listAllWard(noOfRecord,pageNumber,searchKeyword);

	}

	@Override
	public int countRecord(String searchKeyword) {
		
		return wardDao.countRecord(searchKeyword);
	}

	@Override
	public List<Ward> filteredWard(String searchKey, String itemsPerPage) {
		return wardDao.filteredWard(searchKey,itemsPerPage);
	}

 

	 
}
