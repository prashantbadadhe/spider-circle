package com.circle.dao;

import java.util.List;

import com.circle.model.Designation;

public interface DesignationDao {

	Designation findById(long id);

	Designation findByName(String name);

	void saveDesignation(Designation designation);

	void updateDesignation(Designation designation);

	public int deleteDesignationById(long id);

	List<Designation> findAllDesignations(String noOfRecord, String pageNumber, String searchKeyword);

	void deleteAllDesignations();

	public boolean isDesignationExist(Designation designation);

	List<Designation> filteredDesignation(String searchKey, String itemsPerPage);

	public int countRecords(String searchKeyword);

	public List<Designation> getDesignations();
}
