package com.crowdfunder.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 ********************************************************************
 * Model object representing a user role for giving users permissions.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Entity
@Table(name = "role")
public class Role {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long _id;
    

	@Column(name="name")
    private String _name;
    
    @ManyToMany(mappedBy = "_roles")
    private Set<User> _users;

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }
    
    @JsonIgnore
    public Set<User> getUsers() {
        return _users;
    }

    public void setUsers(Set<User> users) {
        this._users = users;
    }
}