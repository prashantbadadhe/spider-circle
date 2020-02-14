package com.circle.dao;

import java.util.List;

import com.circle.model.State;

public interface StateDao {

State findById(long id);
	
	State findByName(String name);
	
	void saveState(State state);
	
	void updateState(State state);
	
	public int deleteStateById(long id);

	List<State> findAllStates(String noOfRecord, String pageNumber, String searchKeyword); 
	
	void deleteAllStates();
	
	public boolean isStateExist(State state);

	List<State> filteredState(String searchKey, String itemsPerPage);

	public int countRecords(String searchKey);

	public List<State> getStates();
}
