package com.crowdfunder.util.helpers;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.Project;
import com.crowdfunder.util.calculators.interfaces.IPledgeCalculator;
import com.crowdfunder.util.helpers.interfaces.IProjectHelper;

/**
 ********************************************************************
 * Standard implementation of the IProjectHelper interface.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class ProjectHelper implements IProjectHelper {
	
	@Autowired
	private IPledgeCalculator _pledgeCalc;

	@Override
	public boolean isProjectFinished(Project proj) {
		return new Timestamp(System.currentTimeMillis()).after(proj.getFinishTime());
	}

	@Override
	public List<Pledge> updateProjectsStatus(List<Project> projects) {
		List<Pledge> pledgesToDeactivate = new ArrayList<Pledge>();
		for(Project proj : projects) {
			BigDecimal totalPledged = _pledgeCalc.calculatePledgeAmount(proj.getPledges());
			if(isProjectFinished(proj)) {
				if(totalPledged.compareTo(proj.getGoalAmount()) < 0) {
					pledgesToDeactivate.addAll(proj.getPledges());
					proj.setCancelled(true);
					proj.setCompleted(false);
				} else {
					proj.setCancelled(false);
					proj.setCompleted(true);
				}
			}
		}
		return pledgesToDeactivate;
	}
	
}
