package com.circle.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DESIGNATION")
public class Designation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String descr;

	@Column(name = "SORT_ORDER")
	private int sortOrder;

	@Column(name = "level")
	private int level;

	@OneToOne
	@JoinColumn(name = "PARENT_DESIGNATION_ID")
	private Designation parentDesignation;

	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private Domain domain;

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Designation getParentDesignation() {
		return parentDesignation;
	}

	public void setParentDesignation(Designation parentDesignation) {
		this.parentDesignation = parentDesignation;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	@Column(name = "CREATED_AT")
	private Date createdAt;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "UPDATED_AT")
	private Date updatedAt;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "STATUS")
	private char status;

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

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
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

}
