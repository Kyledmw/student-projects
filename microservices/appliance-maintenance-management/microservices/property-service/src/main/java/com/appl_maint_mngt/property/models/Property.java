package com.appl_maint_mngt.property.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
	@NotNull(message="{property.address.null}")
	private Address address;
    
	@Column(name="age")
	@NotNull(message="{property.age.null}")
	@Min(value=1, message="{property.age.min}")
	@Max(value=200, message="{property.age.max}")
	private int age;
	
	@Column(name="bed_count") 
	@NotNull(message="{property.bed_count.null}")
	@Min(value=1, message="{property.bed_count.min}")
	@Max(value=10, message="{property.bed_count.max}")
	private int bedCount;
	
	@Column(name="bathroom_count") 
	@NotNull(message="{property.bahtroom_count.null}")
	@Min(value=1, message="{property.bathroom_count.min}")
	@Max(value=5, message="{property.bathroom_count.max}")
	private int bathroomCount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getBedCount() {
		return bedCount;
	}
	public void setBedCount(int bedCount) {
		this.bedCount = bedCount;
	}
	public int getBathroomCount() {
		return bathroomCount;
	}
	public void setBathroomCount(int bathroomCount) {
		this.bathroomCount = bathroomCount;
	}	
}
