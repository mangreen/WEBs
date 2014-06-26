package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.QeisBudgetYear;

public class QeisBudgetYearRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		QeisBudgetYear qeisBudgetYear = new QeisBudgetYear();
		
		qeisBudgetYear.setAutoindex(rs.getInt("autoindex"));
		qeisBudgetYear.setBudgetYear(rs.getInt("budgetYear"));
		qeisBudgetYear.setCurrentPrice(rs.getInt("currentPrice"));
		qeisBudgetYear.setCapitalPrice(rs.getInt("capitalPrice"));
		
		return qeisBudgetYear;
	}

}
