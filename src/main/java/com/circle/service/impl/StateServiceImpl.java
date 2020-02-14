package com.circle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.StateDao;
import com.circle.model.State;
import com.circle.service.StateService;

@Service("stateService")
@Transactional
public class StateServiceImpl implements StateService{
	
	@Autowired
	private StateDao stateDao;
	
	public List<State> findAllStates(String noOfRecord,String pageNumber, String searchKeyword) {
		return stateDao.findAllStates(noOfRecord,pageNumber,searchKeyword);
	}
	
	@Override
	public State findById(long id) {
		
		return stateDao.findById(id);
	}
	@Override
	public State findByName(String userName) {
		return stateDao.findByName(userName);
	}
	@Override
	public void saveState(State state) {
		stateDao.saveState(state);
	}
	@Override
	public void updateState(State state) {
		stateDao.updateState(state);
	}
	@Override
	public int deleteStateById(long id) {

		return stateDao.deleteStateById(id);
	}

	@Override
	public boolean isStateExist(State state) {
		return findByName(state.getName())!=null;
	}
	@Override
	public void deleteAllStates(){
		stateDao.deleteAllStates();
	}

	@Override
	public List<State> filteredState(String searchKey, String itemsPerPage) {
		return stateDao.filteredState(searchKey, itemsPerPage);

	}

	@Override
	public int countRecord(String searchKey) {
		return stateDao.countRecords(searchKey);
	}

	@Override
	public List<State> getStates() {
		
		return stateDao.getStates();
	}

 

	 
}
