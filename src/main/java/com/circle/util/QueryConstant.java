package com.circle.util;

public interface QueryConstant {

	String FILTER_QUERY = "from Country c where c.name like :searchKey or c.capital like :searchKey or c.code like :searchKey or str(c.id) like :searchKey or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like :searchKey and status !=:status ";
	String FIND_BY_NAME = "from Country where name=:countryName and status !=:status ";
	String FIND_BY_NAME_DOMAIN= "from Domain where name=:domainName and status !=:status ";
	String FIND_BY_NAME_CONTACT_DETAILS= "from ContactDetail where firstName=:contactDetailName and status !=:status ";

	String FILTER_QUERY_STATE = "from State c where c.name like :searchKey or c.capital like :searchKey or c.code like :searchKey or str(c.id) like :searchKey or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like s:searchKey and status !=:status ";
	String FILTER_QUERY_DISTRICT = "from District c where c.name like :searchKey or c.headQuarter like :searchKey or c.code like :searchKey or str(c.id) like :searchKey or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like :searchKey and status !=:status";
	String FILTER_QUERY_TALUKA = "from Taluka c where c.name like :searchKey or c.code like :searchKey or str(c.id) like :searchKey or c.city =:booleanSearch or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like :searchKey and status !=:status";
	String FILTER_QUERY_WARD = "from Ward c where c.name like :searchKey or c.number like :searchKey or str(c.id) like :searchKey or c.address like :searchKey or c.pincode like :searchKey or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like :searchKey or c.population like :searchKey or c.area like :searchKey and status !=:status";
	String FILTER_QUERY_VILLAGE = "from Village c where c.name like :searchKey or str(c.id) like :searchKey or c.address like :searchKey or c.pincode like :searchKey or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like :searchKey and status !=:status";
	String FILTER_QUERY_DOMAIN = "from Domain c where c.name like :searchKey  or str(c.id) like :searchKey or c.descr like :searchKey and status !=:status ";
	String FILTER_QUERY_CONTACT_DETAIL = " from ContactDetail c where "
																+ " c.firstName like :searchKey  or "
																+ " c.middleName like :searchKey or "
																+ " c.lastName like :searchKey or "
																+ " c.nickName like :searchKey or "
																+ " c.phone like :searchKey or "
																+ " c.email like :searchKey or"
																+ " str(c.id) like :searchKey or "
																+ " c.descr like :searchKey "
																+ " and status !=:status ";
	String FILTER_QUERY_DESIGNATION = "from Designation d where d.name like :searchKey or d.descr like :searchKey or d.sortOrder like :searchKey or d.level like :searchKey and status !=:status ";


	
	String COUNT_COUNTRY = "select count(*) from Country where status !=:status ";
	String COUNT_STATE = "select count(*) from State where status !=:status";
	String COUNT_DISTRICT = "select count(*) from District where status !=:status";
	String COUNT_TALUKA = "select count(*) from Taluka where status !=:status";
	String COUNT_WARD = "select count(*) from Ward where status !=:status";
	String COUNT_VILLAGE = "select count(*) from Village where status !=:status";
	String COUNT_DOMAIN = "select count(*) from Domain where status !=:status ";
	String COUNT_CONTACT_DETAIL = "select count(*) from ContactDetail where status !=:status ";
	String COUNT_DESIGNATION = "select count(*) from Designation where status !=:status ";

	String COUNT_COUNTRY_WITH_SEARCH = "select count(*) from Country c where c.name like :searchKey or c.capital like :searchKey or c.code like :searchKey or str(c.id) like :searchKey or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like :searchKey and status !=:status ";
	String COUNT_STATE_WITH_SEARCH = "select count(*) from State c where c.name like :searchKey or c.capital like :searchKey or c.code like :searchKey or str(c.id) like :searchKey or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like s:searchKey and status !=:status ";
	String COUNT_DISTRICT_WITH_SEARCH = "select count(*) from District c where c.name like :searchKey or c.headQuarter like :searchKey or c.code like :searchKey or str(c.id) like :searchKey or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like :searchKey and status !=:status";

	String COUNT_TALUKA_WITH_SEARCH = "select count(*) from Taluka c where c.name like :searchKey or c.code like :searchKey or str(c.id) like :searchKey or c.city =:booleanSearchand status !=:status  ";
	String COUNT_WARD_WITH_SEARCH = "select count(*) from Ward c where c.name like :searchKey or c.number like :searchKey or str(c.id) like :searchKey or c.address like :searchKey or c.pincode like :searchKey or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like :searchKey or c.population like :searchKey or c.area like :searchKey and status !=:status";

	String COUNT_VILLAGE_WITH_SEARCH = "select count(*) from Village c where c.name like :searchKey or str(c.id) like :searchKey or c.address like :searchKey or c.pincode like :searchKey or c.descr like :searchKey or c.latitude like :searchKey or c.longitude like :searchKey and status !=:status";
	String COUNT_DOMAIN_WITH_SEARCH = "select count(*) from Domain  c where c.name like :searchKey or str(c.id) like :searchKey or c.descr like :searchKey  and status !=:status ";
	String COUNT_CONTACT_DETAIL_WITH_SEARCH = " select count(*) from ContactDetail c where "
			+ " c.firstName like :searchKey  or "
			+ " c.middleName like :searchKey or "
			+ " c.lastName like :searchKey or "
			+ " c.nickName like :searchKey or "
			+ " c.phone like :searchKey or "
			+ " c.email like :searchKey or"
			+ " str(c.id) like :searchKey or "
			+ " c.descr like :searchKey "
			+ " and status !=:status ";

	String COUNT_DESIGNATION_WITH_SEARCH = "select count(*) from Designation d where d.name like :searchKey or d.descr like :searchKey or d.sortOrder like :searchKey or d.level like :searchKey and status !=:status ";
}
