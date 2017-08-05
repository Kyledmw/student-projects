package com.tax_calculator.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax_calculator.models.Citizen;
import com.tax_calculator.models.CitizenWithNetPay;
import com.tax_calculator.models.Tax;
import com.tax_calculator.util.calculators.INetPayCalculator;

/**
 ********************************************************************
 * @Implements ICitizenNetPayCompositeService
 * 
 * Generic implementation using other existing service beans to retrieve data
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Service
public class CitizenNetPayCompositeService  implements ICitizenNetPayCompositeService {
	
	@Autowired
	private ITaxationService _taxationService;
	
	@Autowired
	private ICitizensService _citizensService;
	
	@Autowired
	private INetPayCalculator _netpayCalc;	

	@Override
	public List<CitizenWithNetPay> getCitizensWithNetPay() {
		List<CitizenWithNetPay> citizensWithNetPay = new ArrayList<>();
		List<Citizen> citizens = _citizensService.getAll();
		List<Tax> taxRates = _taxationService.getAll();
		
		for(Citizen citizen : citizens) {
			BigDecimal netPay = _netpayCalc.calculate(taxRates, citizen);
			CitizenWithNetPay citizenWithNetPay = new CitizenWithNetPay();
			citizenWithNetPay.setCitizen(citizen);
			citizenWithNetPay.setNetPay(netPay);
			citizensWithNetPay.add(citizenWithNetPay);
		}
		
		return citizensWithNetPay;
	}

}
