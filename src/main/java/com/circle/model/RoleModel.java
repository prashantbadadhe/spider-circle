package com.circle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ROLE")
public class RoleModel {

	public static final String SUPER_ADMIN_ROLE = "superadmin";
	public static final long SUPER_ADMIN_ROLE_ID = 1;

	public static final String ADMIN_ROLE = "admin";
	public static final long ADMIN_ROLE_ID = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	
	@Column(name = "name")
	protected String name;

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


}