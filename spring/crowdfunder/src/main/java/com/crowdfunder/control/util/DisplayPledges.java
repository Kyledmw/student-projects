package com.crowdfunder.control.util;

import java.util.ArrayList;
import java.util.List;

import com.crowdfunder.control.util.interfaces.IDisplayPledges;
import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.Project;

/**
 ********************************************************************
 * Standard implementation of IDisplayPledges
 * 
 * Used for th:if statements
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class DisplayPledges implements IDisplayPledges {

	@Override
	public List<Pledge> getPledgesToDisplay(Project proj) {
		List<Pledge> activePledges = new ArrayList<Pledge>();
		List<Pledge> cancPledges = new ArrayList<Pledge>();
		boolean active = !proj.getCancelled() && !proj.getCompleted();
		for(Pledge p : proj.getPledges()) {
			if(active || proj.getCompleted() && p.getActive()) {
				activePledges.add(p);
			} else if(proj.getCancelled()) {
				cancPledges.add(p);
			}
		}
		
		return (proj.getCompleted() || active) ? activePledges : cancPledges;
	}

	@Override
	public int getValidPledgeAmount(Project proj) {
		int activePledges = 0;
		int cancPledges = 0;
		boolean active = !proj.getCancelled() && !proj.getCompleted();
		for(Pledge p : proj.getPledges()) {
			if(active || proj.getCompleted() && p.getActive()) {
				activePledges++;
			} else if(proj.getCancelled()) {
				cancPledges++;
			}
		}
		
		return (proj.getCompleted() || active) ? activePledges : cancPledges;
	}

}
