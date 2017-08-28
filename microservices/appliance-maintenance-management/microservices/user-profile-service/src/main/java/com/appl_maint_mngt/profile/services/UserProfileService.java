package com.appl_maint_mngt.profile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.profile.models.IUserProfileReadable;
import com.appl_maint_mngt.profile.models.UserProfile;
import com.appl_maint_mngt.profile.models.converters.IUserProfilePayloadConverter;
import com.appl_maint_mngt.profile.models.web.IUserProfilePayload;
import com.appl_maint_mngt.profile.repositories.IUserProfileRepository;

@Service
public class UserProfileService implements IUserProfileService {
	
	@Autowired
	private IUserProfileRepository repo;
	
	@Autowired
	private IUserProfilePayloadConverter converter;

	@Override
	public IUserProfileReadable getByAccountId(Long accountId) {
		return repo.findOne(accountId);
	}

	@Override
	public IUserProfileReadable save(IUserProfilePayload payload) {
		UserProfile profile = converter.toUserProfile(payload);
		repo.save(profile);
		return profile;
	}

}
