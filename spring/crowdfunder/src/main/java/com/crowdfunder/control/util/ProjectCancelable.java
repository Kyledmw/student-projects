package com.crowdfunder.control.util;

import org.springframework.stereotype.Component;

import com.crowdfunder.models.Project;

@Component
public class ProjectCancelable {

	public boolean isCancelable(Project proj) {
		return (!proj.getCancelled() && !proj.getCompleted());
	}
}
