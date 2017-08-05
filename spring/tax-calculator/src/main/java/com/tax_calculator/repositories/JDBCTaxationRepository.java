package com.tax_calculator.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tax_calculator.models.Tax;
import com.tax_calculator.repositories.row_mappers.TaxRowMapper;
import com.tax_calculator.repositories.schema.TaxationTableSchema;
import com.tax_calculator.util.sql.ISQLBuilder;

/**
 ********************************************************************
 * JdbcTemplate implementation of ITaxationRepository
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Repository
public class JDBCTaxationRepository implements ITaxationRepository {
	
	@Autowired
	private JdbcTemplate _jdbc;
	
	@Autowired
	private ISQLBuilder _sqlBuilder;

	@Override
	@Transactional(readOnly=true)
	public Tax get(long id) {
		String query = _sqlBuilder.selectWhereStatement(TaxationTableSchema.TAXATION_TABLE, TaxationTableSchema.TAXATION_ID_COL);
		Tax tax = _jdbc.queryForObject(query, new TaxRowMapper(), id);
		return tax;
	}

	@Override
	public void create(Tax obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(TaxationTableSchema.TAX_FROM_COL);
		columns.add(TaxationTableSchema.AMOUNT_ELIGABLE_FOR_TAX_COL);
		columns.add(TaxationTableSchema.TAX_PERCENT_COL);
		columns.add(TaxationTableSchema.TAX_LIMITED_COL);
		
		final String sql = _sqlBuilder.insert(TaxationTableSchema.TAXATION_TABLE, columns);
		KeyHolder holder = new GeneratedKeyHolder();
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setBigDecimal(1, obj.getTaxFrom());
				ps.setBigDecimal(2, obj.getAmountEligableForTax());
				ps.setDouble(3,  obj.getTaxPercent());
				ps.setBoolean(4, obj.isTaxLimited());
				return ps;
			}
			
		}, holder);
		obj.setId(holder.getKey().longValue());
	}

	@Override
	public void update(Tax obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(TaxationTableSchema.TAX_FROM_COL);
		columns.add(TaxationTableSchema.AMOUNT_ELIGABLE_FOR_TAX_COL);
		columns.add(TaxationTableSchema.TAX_PERCENT_COL);
		columns.add(TaxationTableSchema.TAX_LIMITED_COL);
		final String sql = _sqlBuilder.updateStatement(TaxationTableSchema.TAXATION_TABLE, columns, TaxationTableSchema.TAXATION_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setBigDecimal(1, obj.getTaxFrom());
				ps.setBigDecimal(2, obj.getAmountEligableForTax());
				ps.setDouble(3,  obj.getTaxPercent());
				ps.setBoolean(4, obj.isTaxLimited());
				ps.setLong(5, obj.getId());
				return ps;
			}
			
		});
		
	}

	@Override
	public void delete(Tax obj) {
		final String sql = _sqlBuilder.deleteStatement(TaxationTableSchema.TAXATION_TABLE, TaxationTableSchema.TAXATION_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setLong(1, obj.getId());
				return ps;
			}
			
		});
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Tax> getAll() {
		String sql = _sqlBuilder.selectAllStatement(TaxationTableSchema.TAXATION_TABLE);
		List<Tax> taxRates = _jdbc.query(sql, new TaxRowMapper());
		return taxRates;
	}

}
