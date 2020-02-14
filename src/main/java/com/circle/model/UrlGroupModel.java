package com.circle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "URL_GROUPS")
// @AttributeOverrides({
// @AttributeOverride(name = "createdAt", column = @Column(name =
// "CREATED_AT")),
// @AttributeOverride(name = "createdBy", column = @Column(name =
// "CREATED_BY")),
// @AttributeOverride(name = "updatedAt", column = @Column(name =
// "UPDATED_AT")),
// @AttributeOverride(name = "updatedBy", column = @Column(name =
// "UPDATED_BY")),
// @AttributeOverride(name = "recordStatus", column = @Column(name =
// "RECORD_STATUS")),
//
// })
public class UrlGroupModel extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	@Column(name = "URL_GROUP_NAME")
	protected String urlGroupName;

	@Column(name = "DISPLAY_ORDER")
	protected int displayOrder;

	@Column(name = "CREATED_AT")
	protected long createdAt;

	@Column(name = "CREATED_BY")
	protected String createdBy;
	@Column(name = "UPDATED_AT")
	protected long updatedAt;

	@Column(name = "UPDATED_BY")
	protected String updatedBy;

	@Column(name = "RECORD_STATUS")
	protected String recordStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrlGroupName() {
		return urlGroupName;
	}

	public void setUrlGroupName(String urlGroupName) {
		this.urlGroupName = urlGroupName;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

}
