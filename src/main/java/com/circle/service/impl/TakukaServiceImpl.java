package com.circle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.TalukaDao;
import com.circle.model.Taluka;
import com.circle.service.TalukaService;

@Service("talukaService")
@Transactional
public class TakukaServiceImpl implements TalukaService{
	
	@Autowired
	private TalukaDao talukaDao;
	
	public List<Taluka> findAllTalukas() {
		return talukaDao.findAllTalukas();
	}
	
	@Override
	public Taluka findById(long id) {
		
		return talukaDao.findById(id);
	}
	@Override
	public Taluka findByName(String userName) {
		return talukaDao.findByName(userName);
	}
	@Override
	public void saveTaluka(Taluka taluka) {
		talukaDao.saveTaluka(taluka);
	}
	@Override
	public void updateTaluka(Taluka taluka) {
		talukaDao.updateTaluka(taluka);
	}
	@Override
	public int deleteTalukaById(long id) {

		return talukaDao.deleteTalukaById(id);
	}

	@Override
	public boolean isTalukaExist(Taluka taluka) {
		return findByName(taluka.getName())!=null;
	}
	@Override
	public void deleteAllTalukas(){
		talukaDao.deleteAllTalukas();
	}

	@Override
	public List<Taluka> listAllTaluka(String noOfRecord, String pageNumber, String searchKeyword) {
	
		return talukaDao.listAllTaluka(noOfRecord,pageNumber,searchKeyword);
	}

	@Override
	public List<Taluka> filteredTaluka(String searchKey, String itemsPerPage) {
		return talukaDao.filteredTaluka(searchKey,itemsPerPage);
	}

	@Override
	public int countRecord(String searchKey) {
		return talukaDao.countRecord(searchKey);
	}

 

	 
}
