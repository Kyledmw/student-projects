package com.crowdfunder.control.forms;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.crowdfunder.control.validation.annotations.ValidYTubeLink;

/**
 ********************************************************************
 * Class representing a form for creating a new project.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ProjectForm {

	@Valid 
	@NotNull(message = "{projectForm.projName.null}")
	@Size(min=3, max=30, message = "{projectForm.projName.size}")
	private String projName;
	
	@Valid
	@NotNull(message = "{projectForm.desc.null}")
	@Size(min=10, max=100, message = "{projectForm.desc.size}")
	private String desc;
	
	@Valid
	@NotNull(message = "{projectForm.yTubeLink.null}")
	@ValidYTubeLink
	private String yTubeLink;
	
	@DecimalMax(value = "100000", message = "{projectForm.goalAmount.minMax}")
	@DecimalMin(value = "1000",  message = "{projectForm.goalAmount.minMax}")
	private BigDecimal goalAmount;
	
	@Min(value=7, message = "{projectForm.duration.minMax}")
	@Max(value=30, message = "{projectForm.duration.minMax}")
	private long duration;
	
	@NotNull
	private MultipartFile image;
	
	public String getProjName() {
		return projName;
	}
	
	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getYTubeLink() {
		return yTubeLink;
	}
	
	public void setYTubeLink(String yTubeLink) {
		this.yTubeLink = yTubeLink;
	}
	
	public BigDecimal getGoalAmount() {
		return goalAmount;
	}
	
	public void setGoalAmount(BigDecimal goalAmount) {
		this.goalAmount = goalAmount;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public MultipartFile getImage() {
		return image;
	}
	
	public void setImage(MultipartFile file) {
		image = file;
	}
}
