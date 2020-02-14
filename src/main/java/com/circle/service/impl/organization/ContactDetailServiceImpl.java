package com.circle.service.impl.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.organization.ContactDetailDao;
import com.circle.model.organization.ContactDetail;
import com.circle.service.organization.ContactDetailService;

@Service("contactDetailService")
@Transactional
public class ContactDetailServiceImpl implements ContactDetailService{
	
	@Autowired
	private ContactDetailDao contactDetailDao;
	
	public List<ContactDetail> findAllContactDetails(String noOfRecord,String pageNumber,String searchKeyword) {
		return contactDetailDao.findAllContactDetails(noOfRecord,pageNumber,searchKeyword);
	}
	
	@Override
	public ContactDetail findById(long id) {
		
		return contactDetailDao.findById(id);
	}
	@Override
	public ContactDetail findByName(String userName) {
		return contactDetailDao.findByName(userName);
	}
	@Override
	public void saveContactDetail(ContactDetail contactDetail) {
		contactDetailDao.saveContactDetail(contactDetail);
	}
	@Override
	public void updateContactDetail(ContactDetail contactDetail) {
		contactDetailDao.updateContactDetail(contactDetail);
	}
	@Override
	public int deleteContactDetailById(long id) {

		return contactDetailDao.deleteContactDetailById(id);
	}

	@Override
	public boolean isContactDetailExist(ContactDetail contactDetail) {
		//return findByName(contactDetail.getName())!=null;
		//TODO :  implement based on phone number or mail id 
		return false;
	}
	@Override
	public void deleteAllContactDetails(){
		contactDetailDao.deleteAllContactDetails();
	}

	@Override
	public List<ContactDetail> filteredContactDetail(String searchKey,String itemsPerPage) {
		return contactDetailDao.filteredContactDetail(searchKey, itemsPerPage);
	}

	@Override
	public int countRecord(String searchKeyword) {
		return contactDetailDao.countRecords(searchKeyword);
	}

	@Override
	public List<ContactDetail> getContactDetails() {
		return contactDetailDao.getContactDetails();

	}

 

	 
}
