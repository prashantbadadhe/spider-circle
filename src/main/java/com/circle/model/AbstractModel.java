package com.circle.model;

import java.io.Serializable;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ACTIVE_RECORD_STATUS = "A";
	public static final String DELETED_RECORD_STATUS = "D";
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "ID")
//	protected long id;
//
//	@Column(name = "CREATED_AT")
//	protected long createdAt;
//
//	@Column(name = "CREATED_BY")
//	protected String createdBy;
//	@Column(name = "UPDATED_AT")
//	protected long updatedAt;
//
//	@Column(name = "UPDATED_BY")
//	protected String updatedBy;
//
//	@Column(name = "RECORD_STATUS")
//	protected String recordStatus;
//
//	public long getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(long createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public long getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(long updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	public String getUpdatedBy() {
//		return updatedBy;
//	}
//
//	public void setUpdatedBy(String updatedBy) {
//		this.updatedBy = updatedBy;
//	}
//
//	public String getRecordStatus() {
//		return recordStatus;
//	}
//
//	public void setRecordStatus(String recordStatus) {
//		this.recordStatus = recordStatus;
//	}
}
