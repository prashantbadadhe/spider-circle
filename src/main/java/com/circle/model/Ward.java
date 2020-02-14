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
@Table(name="WARD")
public class Ward {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private long id;
	@Column(name="NAME")
	private String name;
	@Column(name="WARD_NUMBER")
	private String number;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="PINCODE")
	private String  pincode;
	@Column(name="DESCRIPTION")
	private String descr;
	@Column(name="LATITUDE")
	private String latitude;
	@Column(name="LONGITUDE")
	private String longitude;
	@Column(name="POPULATION")
	private String population;
	@Column(name="AREA")
	private String area;
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
	@JoinColumn(name="TALUKA")
	private Taluka taluka;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Taluka getTaluka() {
		return taluka;
	}
	public void setTaluka(Taluka taluka) {
		this.taluka = taluka;
	}
	

}
