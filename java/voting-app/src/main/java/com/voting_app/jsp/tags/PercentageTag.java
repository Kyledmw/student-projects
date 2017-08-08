package com.voting_app.jsp.tags;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 ********************************************************************
 * Custom tag which calculates and displays the percentage of the given values.
 * 
 * It prints out the percentage of _amount in _total
 * 
 * {@extends {@link TagSupport}}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class PercentageTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private static final int ONE_HUNDRED = 100;
	
	private int _total = 0;
	private int _amount = 0;

    /**
     * Set the total to be used in the calculation
     *
     * @param total to calculate percentage of
     */
	public void setTotal(int total) {
		this._total = total;
	}

    /**
     * Set the amount to be used in the calculation
     *
     * @param amount to calculate percentage of
     */
	public void setAmount(int amount) {
		this._amount = amount;
	}
	
	/**
	 * Calculates the percentage from the given values.
	 * 
	 * This is printed out through a {@link JspWirter}
	 * 
	 * @return SKIP_BODY
	 * @throws {@link JspException}
	 */
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			if(_total != 0) {
				double percentage = ((double)_amount / _total) * ONE_HUNDRED;
				NumberFormat formatter = new DecimalFormat("#0.00");     
				out.println("" + formatter.format(percentage) + "%");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
