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
@Table(name="STATE")
public class State {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private long id;
	@Column(name="NAME")
	private String name;
	@Column(name="CODE")
	private String code;
	@Column(name="DESCRIPTION")
	private String descr;
	@Column(name="LATITUDE")
	private String latitude;
	@Column(name="LONGITUDE")
	private String longitude;
	@Column(name="CAPITAL")
	private String capital;
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
	@JoinColumn(name="COUNTRY")
	private Country country;
/*	@OneToMany(mappedBy="state")
	@LazyCollection(LazyCollectionOption.FALSE)
 	private List<District> district;*/
	
 
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
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
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	/*public List<District> getDistrict() {
		return district;
	}
	public void setDistrict(List<District> district) {
		this.district = district;
	}*/
	 
 
	 

}
