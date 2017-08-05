package com.crowdfunder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdfunder.models.Pledge;
import com.crowdfunder.repositories.interfaces.IPledgeRepository;
import com.crowdfunder.services.interfaces.IPledgeService;

@Service
public class PledgeService implements IPledgeService {
	
	@Autowired
	private IPledgeRepository _pledgeRepo;

	@Override
	public boolean cancelPledge(Pledge pledge) {
		if(pledge.getProject().getCompleted()) {
			return false;
		}
		pledge.setActive(false);
		return true;
	}

	@Override
	public void cancelPledges(List<Pledge> pledges) {
		pledges.forEach(pledge -> {
			cancelPledge(pledge);
			_pledgeRepo.save(pledge);
		});
		
	}

	@Override
	public void save(Pledge pledge) {
		_pledgeRepo.save(pledge);
		
	}

}
