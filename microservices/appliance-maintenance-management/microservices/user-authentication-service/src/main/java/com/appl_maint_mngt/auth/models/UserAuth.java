package com.appl_maint_mngt.auth.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.appl_maint_mngt.auth.models.roles.UserRole;

@Entity
@Table(name="auth_users")
public class UserAuth extends AUserAuth {
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="password") 
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name="user_type")
	private UserType userType;
	
	@OneToMany
	@JoinColumn(name="user_auth_id", referencedColumnName="id")
	private List<UserRole> roles;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public UserType getUserType() {
		return userType;
	}
	
	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public List<UserRole> getRoles() {
		return roles;
	}

	@Override
	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

}
