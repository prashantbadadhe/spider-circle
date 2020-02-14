package com.circle.service;

import java.util.List;

import com.circle.model.Designation;

public interface DesignationService {

	Designation findById(long id);

	Designation findByName(String name);

	void saveDesignation(Designation designation);

	void updateDesignation(Designation designation);

	public int deleteDesignationById(long id);

	List<Designation> findAllDesignations(String noOfRecord, String pageNumber, String searchKeyword);

	void deleteAllDesignations();

	public boolean isDesignationExist(Designation designation);

	List<Designation> filteredDesignation(String searchKey, String itemsPerPage);

	public int countRecord(String searchKeyword);

	public List<Designation> getDesignations();
}
