package com.crowdfunder.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowdfunder.models.Role;

/**
 ********************************************************************
 * Repository / DAO for the Role model
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

}
