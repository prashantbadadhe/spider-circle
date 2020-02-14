package com.circle.service;

import java.util.List;

import com.circle.model.State;

public interface StateService {
	
	State findById(long id);
	
	State findByName(String name);
	
	void saveState(State state);
	
	void updateState(State state);
	
	int deleteStateById(long id);

	public List<State> findAllStates(String noOfRecord,String pageNumber, String searchKeyword); 
	
	void deleteAllStates();
	
	public boolean isStateExist(State state);

	public List<State> filteredState(String searchKey, String itemsPerPage);

	public int countRecord(String searchKeyword);

	public List<State> getStates();

 }
