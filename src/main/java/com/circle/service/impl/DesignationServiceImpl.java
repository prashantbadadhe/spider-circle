package com.circle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.DesignationDao;
import com.circle.model.Designation;
import com.circle.service.DesignationService;

@Service("designationService")
@Transactional
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	private DesignationDao designationDao;

	public List<Designation> findAllDesignations(String noOfRecord, String pageNumber, String searchKeyword) {
		return designationDao.findAllDesignations(noOfRecord, pageNumber, searchKeyword);
	}

	@Override
	public Designation findById(long id) {

		return designationDao.findById(id);
	}

	@Override
	public Designation findByName(String userName) {
		return designationDao.findByName(userName);
	}

	@Override
	public void saveDesignation(Designation designation) {
		designationDao.saveDesignation(designation);
	}

	@Override
	public void updateDesignation(Designation designation) {
		designationDao.updateDesignation(designation);
	}

	@Override
	public int deleteDesignationById(long id) {

		return designationDao.deleteDesignationById(id);
	}

	@Override
	public boolean isDesignationExist(Designation designation) {
		return findByName(designation.getName()) != null;
	}

	@Override
	public void deleteAllDesignations() {
		designationDao.deleteAllDesignations();
	}

	@Override
	public List<Designation> filteredDesignation(String searchKey, String itemsPerPage) {
		return designationDao.filteredDesignation(searchKey, itemsPerPage);
	}

	@Override
	public int countRecord(String searchKeyword) {
		return designationDao.countRecords(searchKeyword);
	}

	@Override
	public List<Designation> getDesignations() {
		return designationDao.getDesignations();

	}

}
