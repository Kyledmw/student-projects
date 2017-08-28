package com.appl_maint_mngt.profile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appl_maint_mngt.profile.models.UserProfile;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile, Long> {

}
