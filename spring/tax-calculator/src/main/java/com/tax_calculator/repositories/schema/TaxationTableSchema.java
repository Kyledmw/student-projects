package com.tax_calculator.repositories.schema;

/**
 ********************************************************************
 * Interface containing constants related to the Taxation Table Schema
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface TaxationTableSchema {
	String TAXATION_TABLE = "taxation";
	
	String TAXATION_ID_COL = "taxation_id";
	String TAX_FROM_COL = "tax_from";
	String AMOUNT_ELIGABLE_FOR_TAX_COL = "amount_eligable_for_tax";
	String TAX_PERCENT_COL = "tax_percent";
	String TAX_LIMITED_COL = "tax_limited";
}
