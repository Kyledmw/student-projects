package com.tax_calculator.util.sql;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SQLBuilderTests {
	
	private static ISQLBuilder _sqlBuilder = new SQLBuilder();

	@Test
	public void deleteStatement() {
		final String SQL = "DELETE FROM dummy_table WHERE id=?";
		
		String builtSql = _sqlBuilder.deleteStatement("dummy_table", "id");
		assertEquals(SQL, builtSql);
	}
	
	@Test
	public void selectWhereStatement() {
		final String SQL = "SELECT * FROM dummy_table WHERE id=?";
		
		String builtSql = _sqlBuilder.selectWhereStatement("dummy_table", "id");
		assertEquals(SQL, builtSql);
	}
	
	@Test
	public void selectAllStatement() {
		final String SQL = "SELECT * FROM dummy_table";
		
		String builtSql = _sqlBuilder.selectAllStatement("dummy_table");
		assertEquals(SQL, builtSql);
	}
	
	@Test
	public void insert() {
		final String SQL = "INSERT INTO dummy_table(id,name) values(?,?)";
		
		List<String> columns = new ArrayList<String>();
		columns.add("id");
		columns.add("name");
		
		String builtSql = _sqlBuilder.insert("dummy_table", columns);
		assertEquals(SQL, builtSql);
	}
	
	@Test
	public void update() {
		final String SQL = "UPDATE dummy_table SET id=?,name=? WHERE cId=?";
		
		List<String> columns = new ArrayList<String>();
		columns.add("id");
		columns.add("name");
		
		String builtSql = _sqlBuilder.updateStatement("dummy_table", columns, "cId");
		assertEquals(SQL, builtSql);
	}
	

}