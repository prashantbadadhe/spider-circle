package com.circle.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SUB_DISTRICT")
public class SubDistrict {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="DESCRIPTION")
	private String descr;
	@Column(name="LATITUDE")
	private String latitude;
	@Column(name="LONGITUDE")
	private String longitude;
	@Column(name="CREATED_AT")
	private Date createdAt;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="UPDATED_AT")
	private Date updatedAt;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="STATUS")
	private char status;
	@ManyToOne
	@JoinColumn(name="DISTRICT")
	private District district;
 
}
