package com.tax_calculator.util.sql;


import java.util.List;

/**
 ********************************************************************
 * Interface for a generic SQL statement builder
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ISQLBuilder {
	
	/**
	 * Build an sql statement for an insert to the given table for the given columns
	 * 
	 * @param table Table to insert into
	 * @param columns List of columns to insert data to
	 * @return SQL statement
	 */
	String insert(String table, List<String> columns);
	/**
	 * Build an sql statement to select all from a table with a where clause on the given column
	 * 
	 * @param table Table to select from
	 * @param column to perform where clause on
	 * @return SQL statement
	 */
	String selectWhereStatement(String table, String column);
	
	/**
	 * Build an sql statement to select all from a table
	 * 
	 * @param table table to select from
	 * @return SQL statement
	 */
	String selectAllStatement(String table);
	
	/**
	 * Build an sql statement for deleting a row for the given table with a where clause on a given column
	 * 
	 * @param table Table to delete from
	 * @param column to perform where clause on
	 * @return SQL statement
	 */
	String deleteStatement(String table, String column);
	
	/**
	 * Build an sql statement for updating a row for the given list of columns with a where clause on a given column
	 * 
	 * @param table Table to update
	 * @param columns columns in which data will be update dfor
	 * @param whereCol column to perform where clause on
	 * @return SQL statement
	 */
	String updateStatement(String table, List<String> columns, String whereCol);
}

