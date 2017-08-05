package com.tax_calculator.view.util;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

import com.tax_calculator.models.Tax;
import com.tax_calculator.util.general.ExternalisedStrings;

/**
 ********************************************************************
 * Factory Bean.
 * Factory methods for creating MenuOption Strings.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class OptionStringFactory {

	/**
	 * Build a tax menu option off a given tax object
	 * 
	 * @param tax Taxation object to create option String
	 * @return Built option string
	 */
	public String createTaxOptionString(Tax tax) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		StringBuilder builder = new StringBuilder();
		builder.append(ExternalisedStrings.DATA_TAX_TAX_FROM + ": ");
		builder.append(df.format(tax.getTaxFrom()));
		builder.append("\t");
		builder.append(ExternalisedStrings.DATA_TAX_AMOUNT_ELIGIBLE + ": ");
		builder.append(df.format(tax.getAmountEligableForTax()));
		builder.append("\t\t\t");
		builder.append(ExternalisedStrings.DATA_TAX_TAX_PERCENT + ": ");
		builder.append(tax.getTaxPercent());
		builder.append("\t");
		builder.append(ExternalisedStrings.DATA_TAX_TAX_LIMITED + ": ");
		builder.append(tax.isTaxLimited());
		return builder.toString();
	}
	
}
