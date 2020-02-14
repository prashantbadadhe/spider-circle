package com.circle.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "CLIENT")
public class ClientModel extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS_LINE1")
	private String addressLine1;

	@Column(name = "ADDRESS_LINE2")
	private String addressLine2;

	@OneToOne
	@JoinColumn(name = "STATE_ID")
	private State state;

	@OneToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

	@OneToOne
	@JoinColumn(name = "TALUKA_ID")
	private Taluka city;

	@Column(name = "ZIPCODE")
	private String zipcode;

	@Column(name = "LATITUDE")
	private String latitude;
	@Column(name = "LONGITUDE")
	private String longitude;

	@Column(name = "PHONE1")
	private String phone1;

	@Column(name = "PHONE2")
	private String phone2;

	@Column(name = "EMAIL1")
	private String email1;

	@Column(name = "EMAIL2")
	private String email2;

	@Column(name = "SUBSCRIPTION_ID")
	private long subscriptionId;

	@Column(name = "SUBSCR_EXP_AT")
	private Date subscriptionExpireAt;

	@Column(name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT")
	private Date updatedAt;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "STATUS")
	private char status;

}
