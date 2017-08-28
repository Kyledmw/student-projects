package com.appl_maint_mngt.profile.models.converters;

import com.appl_maint_mngt.profile.models.UserProfile;
import com.appl_maint_mngt.profile.models.web.IUserProfilePayload;

public interface IUserProfilePayloadConverter {

	UserProfile toUserProfile(IUserProfilePayload payload);
}
