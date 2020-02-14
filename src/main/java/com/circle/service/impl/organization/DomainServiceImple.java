package com.circle.service.impl.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.organization.DomainDao;
import com.circle.model.Domain;
import com.circle.service.organization.DomainService;

@Service("domainService")
@Transactional
public class DomainServiceImple implements DomainService{
	
	@Autowired
	private DomainDao domainDao;
	
	public List<Domain> findAllDomains(String noOfRecord,String pageNumber,String searchKeyword) {
		return domainDao.findAllDomains(noOfRecord,pageNumber,searchKeyword);
	}
	
	@Override
	public Domain findById(long id) {
		
		return domainDao.findById(id);
	}
	@Override
	public Domain findByName(String userName) {
		return domainDao.findByName(userName);
	}
	@Override
	public void saveDomain(Domain domain) {
		domainDao.saveDomain(domain);
	}
	@Override
	public void updateDomain(Domain domain) {
		domainDao.updateDomain(domain);
	}
	@Override
	public int deleteDomainById(long id) {

		return domainDao.deleteDomainById(id);
	}

	@Override
	public boolean isDomainExist(Domain domain) {
		return findByName(domain.getName())!=null;
	}
	@Override
	public void deleteAllDomains(){
		domainDao.deleteAllDomains();
	}

	@Override
	public List<Domain> filteredDomain(String searchKey,String itemsPerPage) {
		return domainDao.filteredDomain(searchKey, itemsPerPage);
	}

	@Override
	public int countRecord(String searchKeyword) {
		return domainDao.countRecords(searchKeyword);
	}

	@Override
	public List<Domain> getDomains() {
		return domainDao.getDomains();

	}

 

	 
}
