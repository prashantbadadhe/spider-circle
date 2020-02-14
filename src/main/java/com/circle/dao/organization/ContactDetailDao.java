package com.circle.dao.organization;

import java.util.List;

import com.circle.model.organization.ContactDetail;

public interface ContactDetailDao {

	ContactDetail findById(long id);

	ContactDetail findByName(String name);

	void saveContactDetail(ContactDetail ContactDetail);

	void updateContactDetail(ContactDetail ContactDetail);

	 int deleteContactDetailById(long id);

	List<ContactDetail> findAllContactDetails(String noOfRecord, String pageNumber, String searchKeyword);

	void deleteAllContactDetails();

	 boolean isContactDetailExist(ContactDetail ContactDetail);

	List<ContactDetail> filteredContactDetail(String searchKey, String itemsPerPage);

	 int countRecords(String searchKeyword);

	List<ContactDetail> getContactDetails();
}
