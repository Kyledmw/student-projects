package com.crowdfunder.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 ********************************************************************
 * Modal object representing a user of the application.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long _userId;
	
	@Column(name="user_name", unique = true)
	private String _userName;
	
	@Column(name="password") 
	private String _password;
	
	@Transient
	@Column(name="password_confirm")
	private String _passwordConfirm;
	
	@Column(name="email", unique = true)
	private String _email;
	
	@Column(name="credit_limit")
	private BigDecimal _creditLimit;
	
	@OneToMany(mappedBy="_user", targetEntity=Project.class, fetch=FetchType.EAGER)
	private List<Project> _projects;
	
	@OneToMany(mappedBy="_user", targetEntity=Pledge.class, fetch=FetchType.EAGER)
	private List<Pledge> _pledges;
	
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> _roles;
	
	public Long getUserId() {
		return _userId;
	}
	
	public void setUserId(Long userId) {
		_userId = userId;
	}
	
	@JsonIgnore
	public String getPassword() {
		return _password;
	}
	
	public void setPassword(String password) {
		_password = password;
	}
	
	public String getEmail() {
		return _email;
	}
	
	public void setEmail(String email) {
		_email = email;
	}
	
	public String getUserName() {
		return _userName;
	}
	
	public void setUserName(String userName) {
		_userName = userName;
	}
	
	public BigDecimal getCreditLimit() {
		return _creditLimit;
	}
	
	public void setCreditLimit(BigDecimal creditLimit) {
		_creditLimit = creditLimit;
	}
	
	@JsonIgnore
	public List<Project> getProjects() {
		return _projects;
	}
	
	public void setProjects(List<Project> projects) {
		_projects = projects;
	}
	
	@JsonIgnore
	public List<Pledge> getPledges() {
		return _pledges;
	}
	
	public void setPledges(List<Pledge> pledges) {
		_pledges = pledges;
	}
	
    public Set<Role> getRoles() {
        return _roles;
    }

    public void setRoles(Set<Role> roles) {
        this._roles = roles;
    }
    
    @JsonIgnore
	public String getPasswordConfirm() {
		return _passwordConfirm;
	}
	
	public void setPasswordConfirm(String pwConfirm) {
		_passwordConfirm = pwConfirm;
	}
}
