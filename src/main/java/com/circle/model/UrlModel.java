package com.circle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "URL")
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
public class UrlModel extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	@ManyToOne
	@JoinColumn(name = "URL_GROUP_ID")
	protected UrlGroupModel urlGroupId;

	@Column(name = "URL")
	protected String url;

	@Column(name = "URL_TITLE")
	private String urlTitle;

	@Column(name = "PARENT_URL_ID")
	private long parentUrlId;

	@Column(name = "MENU_CLASS")
	private String menuClass;

	@Column(name = "MENU_ICON")
	private String menuIcon;

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

	public UrlGroupModel getUrlGroupId() {
		return urlGroupId;
	}

	public void setUrlGroupId(UrlGroupModel urlGroupId) {
		this.urlGroupId = urlGroupId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}

	public long getParentUrlId() {
		return parentUrlId;
	}

	public void setParentUrlId(long parentUrlId) {
		this.parentUrlId = parentUrlId;
	}

	public String getMenuClass() {
		return menuClass;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
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
