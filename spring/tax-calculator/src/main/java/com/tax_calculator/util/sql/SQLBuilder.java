package com.tax_calculator.util.sql;


import java.util.List;

import org.springframework.stereotype.Component;

/**
 ********************************************************************
 * Generic implemention of ISQLBuilder
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class SQLBuilder implements ISQLBuilder {
	
	private static final String DELETE_FROM = "DELETE FROM ";
	private static final String SELECT_ALL_FROM = "SELECT * FROM ";
	private static final String INSERT_INTO = "INSERT INTO ";
	private static final String UPDATE = "UPDATE ";
	private static final String WHERE = " WHERE ";
	private static final String SET = " SET ";
	private static final String VALUES = " values";
	private static final String EQUALS = "=";
	private static final String OPEN_PARANTHESIS = "(";
	private static final String CLOSE_PARANTHESIS = ")";
	private static final String COMMA = ",";
	private static final String QUESTION_MARK = "?";

	@Override
	public String deleteStatement(String table, String column) {
		return DELETE_FROM + table + WHERE + column + EQUALS + QUESTION_MARK;
	}

	@Override
	public String selectWhereStatement(String table, String column) {
		return SELECT_ALL_FROM + table + WHERE + column + EQUALS + QUESTION_MARK;
	}

	@Override
	public String selectAllStatement(String table) {
		return SELECT_ALL_FROM + table;
	}

	@Override
	public String insert(String table, List<String> columns) {
		StringBuilder builder = new StringBuilder();
		builder.append(INSERT_INTO);
		builder.append(table);
		builder.append(OPEN_PARANTHESIS);
		for(int i = 0; i < columns.size(); i++) {
			builder.append(columns.get(i));
			if((i + 1) != columns.size()) {
				builder.append(COMMA);
			}
		}
		builder.append(CLOSE_PARANTHESIS);
		builder.append(VALUES);
		builder.append(OPEN_PARANTHESIS);
		for(int i = 0; i < columns.size(); i++) {
			builder.append(QUESTION_MARK);
			if((i + 1) != columns.size()) {
				builder.append(COMMA);
			}
		}
		builder.append(CLOSE_PARANTHESIS);
		return builder.toString();
	}

	@Override
	public String updateStatement(String table, List<String> columns, String whereCol) {
		StringBuilder builder = new StringBuilder();
		builder.append(UPDATE);
		builder.append(table);
		builder.append(SET);
		for(int i = 0; i < columns.size(); i++) {
			builder.append(columns.get(i));
			builder.append(EQUALS);
			builder.append(QUESTION_MARK);
			if((i + 1) != columns.size()) {
				builder.append(COMMA);
			}
		}
		builder.append(WHERE);
		builder.append(whereCol);
		builder.append(EQUALS);
		builder.append(QUESTION_MARK);
		return builder.toString();
	}
}
