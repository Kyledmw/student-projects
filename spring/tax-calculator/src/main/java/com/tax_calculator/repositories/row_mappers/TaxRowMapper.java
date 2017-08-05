package com.tax_calculator.repositories.row_mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tax_calculator.models.Tax;
import com.tax_calculator.repositories.schema.TaxationTableSchema;

/**
 ********************************************************************
 * RowMapper object for Tax objects
 * 
 * This is used by jdbc to convert a cursor row into an object
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class TaxRowMapper implements RowMapper<Tax> {

	@Override
	public Tax mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tax tax = new Tax();
		tax.setId(rs.getLong(TaxationTableSchema.TAXATION_ID_COL));
		tax.setTaxFrom(rs.getBigDecimal(TaxationTableSchema.TAX_FROM_COL));
		tax.setAmountEligableForTax(rs.getBigDecimal(TaxationTableSchema.AMOUNT_ELIGABLE_FOR_TAX_COL));
		tax.setTaxLimited(rs.getBoolean(TaxationTableSchema.TAX_LIMITED_COL));
		tax.setTaxPercent(rs.getDouble(TaxationTableSchema.TAX_PERCENT_COL));
		return tax;
	}

}
