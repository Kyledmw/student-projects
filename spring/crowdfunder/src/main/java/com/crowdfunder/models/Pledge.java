package com.crowdfunder.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 ********************************************************************
 * Domain model object for a Pledge. 
 * 
 * A pledge represents the money User gives towards a project.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Entity
@Table(name = "pledges")
public class Pledge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pledge_id")
	private Long _pledgeId;

	@Column(name="active_pledge")
	private boolean _active;
	
	@Column(name="amount_pledged")
	private BigDecimal _amount;

	@ManyToOne(optional=false)
	@JoinColumn(name="user_id")
	private User _user;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="project_id")
	private Project _project;
	
	public Long getPledgeId() {
		return _pledgeId;
	}
	
	public void setPledgeId(Long id) {
		_pledgeId = id;
	}
	
	public boolean getActive() {
		return _active;
	}
	
	public void setActive(boolean active) {
		_active = active;
	}
	
	public BigDecimal getAmount() {
		return _amount;
	}
	
	public void setAmount(BigDecimal amount) {
		_amount = amount;
	}
	
	@JsonIgnore
	public User getUser() {
		return _user;
	}
	
	public void setUser(User user) {
		_user = user;
	}
	
	@JsonIgnore
	public Project getProject() {
		return _project;
	}
	
	public void setProject(Project proj) {
		_project = proj;
	}
}
