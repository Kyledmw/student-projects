package com.voting_app.dao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.voting_app.dao.interfaces.IPoliticalPartyDAO;
import com.voting_app.models.PoliticalParty;

/**
 ********************************************************************
 * List {@link List} Implementation of a {@link PoliticalParty} DAO
 * <br>
 * <br>
 * @implements {@link IPoliticalPartyDAO}
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class PoliticalPartyListDAO implements IPoliticalPartyDAO {
	
	private List<PoliticalParty> _politicalPartyList;
	private int _curHighestPoliticalPartyId;
	
	public PoliticalPartyListDAO() {
		_politicalPartyList = new CopyOnWriteArrayList<PoliticalParty>();
		_curHighestPoliticalPartyId = 0;
	}

	public synchronized PoliticalParty addPoliticalParty(int id, String name) {
		for(PoliticalParty cur: _politicalPartyList) {
			if(cur.getId() == id) {
				return cur;
			}
		}
		if(id > _curHighestPoliticalPartyId) {
			_curHighestPoliticalPartyId = id;
		}
		PoliticalParty ppartyToAdd = new PoliticalParty(id, name);
		_politicalPartyList.add(ppartyToAdd);
		return ppartyToAdd;
	}

	public synchronized PoliticalParty addPoliticalParty(String name) {
		_curHighestPoliticalPartyId++;
		PoliticalParty ppartyToAdd = new PoliticalParty(_curHighestPoliticalPartyId, name);
		_politicalPartyList.add(ppartyToAdd);
		return ppartyToAdd;
	}

	public synchronized void removePoliticalParty(int id) {
		_politicalPartyList.remove(getPoliticalParty(id));
		
	}

	public synchronized PoliticalParty getPoliticalParty(int id) {
		for(PoliticalParty cur: _politicalPartyList) {
			if(cur.getId() == id) {
				return cur;
			}
		}
		throw new ModelNotFoundException();
	}

	public List<PoliticalParty> getPoliticalParties() {
		return _politicalPartyList;
	}

}
