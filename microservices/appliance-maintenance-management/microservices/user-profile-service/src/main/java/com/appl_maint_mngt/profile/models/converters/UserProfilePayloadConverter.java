package com.appl_maint_mngt.profile.models.converters;

import org.springframework.stereotype.Component;

import com.appl_maint_mngt.profile.models.UserProfile;
import com.appl_maint_mngt.profile.models.web.IUserProfilePayload;

@Component
public class UserProfilePayloadConverter implements IUserProfilePayloadConverter {

	@Override
	public UserProfile toUserProfile(IUserProfilePayload payload) {
		UserProfile profile = new UserProfile();
		profile.setAccountId(payload.getAccountId());
		profile.setDateOfBirth(payload.getDateOfBirth());
		profile.setFirstName(payload.getFirstName());
		profile.setSurname(payload.getSurname());
		return profile;
	}

}
