package com.crowdfunder.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowdfunder.models.User;

/**
 ********************************************************************
 * Repository / DAO for the User model
 *
 * @author Kyle Williamson
 * @version 2.0.0
 ********************************************************************
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Retrieves User object _userName field.
	 * 
	 * @param userName String representing _userName attribute of a User
	 * 
	 * @return retrieved user for userName
	 */
	User findBy_userName(String userName);
	
	/**
	 * Retrieves User object email field.
	 * 
	 * @param email String representing email attribute of a User
	 * 
	 * @return retrieved user for email
	 */
	User findBy_email(String email);
}
