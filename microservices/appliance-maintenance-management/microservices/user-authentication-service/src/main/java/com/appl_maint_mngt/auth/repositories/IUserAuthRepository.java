package com.appl_maint_mngt.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appl_maint_mngt.auth.models.UserAuth;

@Repository
public interface IUserAuthRepository extends JpaRepository<UserAuth, Long> {

	UserAuth findByEmail(String email);
	
}
