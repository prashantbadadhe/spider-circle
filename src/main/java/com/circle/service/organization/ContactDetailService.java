package com.circle.service.organization;

import java.util.List;

import com.circle.model.organization.ContactDetail;

public interface ContactDetailService {
	
	ContactDetail findById(long id);
	
	ContactDetail findByName(String name);
	
	void saveContactDetail(ContactDetail contactDetail);
	
	void updateContactDetail(ContactDetail contactDetail);
	
	public int deleteContactDetailById(long id);

	List<ContactDetail> findAllContactDetails(String noOfRecord, String pageNumber, String searchKeyword); 
	
	void deleteAllContactDetails();
	
	public boolean isContactDetailExist(ContactDetail contactDetail);

	List<ContactDetail> filteredContactDetail(String searchKey, String itemsPerPage);
	public int countRecord(String searchKeyword);

	public List<ContactDetail> getContactDetails();
}
