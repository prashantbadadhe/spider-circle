package com.circle.dao.organization;

import java.util.List;

import com.circle.model.Domain;

public interface DomainDao {

Domain findById(long id);
	
	Domain findByName(String name);
	
	void saveDomain(Domain domain);
	
	void updateDomain(Domain domain);
	
	public int deleteDomainById(long id);

	List<Domain> findAllDomains(String noOfRecord, String pageNumber, String searchKeyword); 
	
	void deleteAllDomains();
	
	public boolean isDomainExist(Domain domain);

	List<Domain> filteredDomain(String searchKey, String itemsPerPage);

	public int countRecords(String searchKeyword);

	public List<Domain> getDomains();
}
