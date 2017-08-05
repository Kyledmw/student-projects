package com.crowdfunder.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 ********************************************************************
 * Domain model object for a Project
 * 
 * A project is a goal in which a User sets out to deliver if it gets funded.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Entity
@Table(name = "projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="project_id")
	private Long _projectId;
	
	@Column(name="proj_name")
	private String _name;
	
	@Column(name="description")
	private String _description;

	@Column(name="yt_vid_code")
	private String _youtubeVidCode;

	@Column(name="goal_amount")
	private BigDecimal _goalAmount;

	@Column(name="finish_time")
	private Timestamp _finishTime;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="user_id")
	private User _user;
	
	@Column(name="cancelled")
	private boolean _cancelled;
	
	@Column(name="completed")
	private boolean _completed;
	
	@Column(name="image_name")
	private String _imageName;
	
	@OneToMany(mappedBy="_project", targetEntity=Pledge.class, fetch=FetchType.EAGER)
	private List<Pledge> _pledges;
	
	public Long getProjectId() {
		return _projectId;
	}
	
	public void setProjectId(Long id) {
		_projectId = id;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public String getDescription() {
		return _description;
	}
	
	public void setDescription(String desc) {
		_description = desc;
	}
	
	public String getYtVidCode() {
		return _youtubeVidCode;
	}
	
	public void setYTVidCode(String ytVidCode) {
		_youtubeVidCode = ytVidCode;
	}
	
	public BigDecimal getGoalAmount() {
		return _goalAmount;
	}
	
	public void setGoalAmount(BigDecimal goalAmount) {
		_goalAmount = goalAmount;
	}
	
	public Timestamp getFinishTime() {
		return _finishTime;
	}
	
	public void setFinishTime(Timestamp finishTime) {
		_finishTime = finishTime;
	}
	
	public boolean getCancelled() {
		return _cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		_cancelled = cancelled;
	}
	
	public boolean getCompleted() {
		return _completed;
	}
	
	public void setCompleted(boolean completed) {
		_completed = completed;
	}
	
	public User getUser() {
		return _user;
	}
	
	public void setUser(User user) {
		_user = user;
	}
	
	public List<Pledge> getPledges() {
		return _pledges;
	}
	
	public void setPledges(List<Pledge> pledges) {
		_pledges = pledges;
	}
	
	public String getImageName() {
		return _imageName;
	}
	
	public void setImageName(String imageName) {
		_imageName = imageName;
	}
	
}
