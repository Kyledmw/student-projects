package com.appl_maint_mngt.property.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "addresses")
public class Address {
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="address_line_1")
	@NotNull(message="{}")
	@Size(min=1, max=20, message="{}")
	private String addressLine1;
	
	@Column(name="address_line_2")
	@NotNull(message="{}")
	@Size(min=1, max=20, message="{}")
	private String addressLine2;
	
	@Column(name="city")
	@NotNull(message="{}")
	@Size(min=1, max=20, message="{}")
	private String city;
	
	@Column(name="state")
	@NotNull(message="{}")
	@Size(min=1, max=20, message="{}")
	private String state;
	
	@Column(name="country")
	@NotNull(message="{}")
	@Size(min=1, max=20, message="{}")
	private String country;
	
	@Column(name="postal_code")
	@NotNull(message="{}")
	@Size(min=4, max=6, message="{}")
	private String postalCode;
	
	@Column(name="longitude")
	private float longitude;
	
	@Column(name="latitude")
	private float latitude;
	
	@JsonIgnore
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	
}
