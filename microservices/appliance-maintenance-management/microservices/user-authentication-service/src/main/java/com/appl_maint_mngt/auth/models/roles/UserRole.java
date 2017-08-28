package com.appl_maint_mngt.auth.models.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {

	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="user_auth_id")
	private Long userAuthId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role", insertable=false, updatable=false)
	private Role role;
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserAuthId() {
		return userAuthId;
	}

	public void setUserAuthId(Long userAuthId) {
		this.userAuthId = userAuthId;
	}
	
}
