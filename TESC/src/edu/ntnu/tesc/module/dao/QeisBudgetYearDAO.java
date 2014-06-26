package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.QeisBudgetYear;
import edu.ntnu.tesc.module.idao.IQeisBudgetYearDAO;
import edu.ntnu.tesc.module.rowmapper.QeisBudgetYearRowMapper;

public class QeisBudgetYearDAO implements IQeisBudgetYearDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delQeisBudgetYear(int autoindex) {
		
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_qeis_budget_year where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public QeisBudgetYear getQeisBudgetYear(int autoindex) {
		
		final QeisBudgetYear qeisBudgetYear = new QeisBudgetYear();
		
		jdbcTemplate.query("select * from Db_qeis_budget_year where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						qeisBudgetYear.setAutoindex(rs.getInt("autoindex"));
						qeisBudgetYear.setBudgetYear(rs.getInt("budgetYear"));
						qeisBudgetYear.setCurrentPrice(rs.getInt("currentPrice"));
						qeisBudgetYear.setCapitalPrice(rs.getInt("capitalPrice"));
					}	
				}
		);
		
		return qeisBudgetYear;
	}

	@Override
	public List<QeisBudgetYear> getQeisBudgetYearList() {
		List<QeisBudgetYear> qeisBudgetYearList = (List<QeisBudgetYear>)jdbcTemplate.query("select * from Db_qeis_budget_year",
		        new RowMapperResultSetExtractor(new QeisBudgetYearRowMapper()));
		return qeisBudgetYearList;
	}

	@Override
	public int insertQeisBudgetYear(QeisBudgetYear qeisBudgetYear) {
		
		int returnCode = 1;
		Object[] obj = new Object[] {
				qeisBudgetYear.getBudgetYear(),
				qeisBudgetYear.getCurrentPrice(),
				qeisBudgetYear.getCapitalPrice()
		};
		
		try {
			jdbcTemplate.update("insert into Db_qeis_budget_year (budgetYear, currentPrice, capitalPrice) values(?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateQeisBudgetYear(QeisBudgetYear qeisBudgetYear) {
		
		int returnCode = 1;
		Object[] obj = new Object[] {
				qeisBudgetYear.getBudgetYear(),
				qeisBudgetYear.getCurrentPrice(),
				qeisBudgetYear.getCapitalPrice(),
				qeisBudgetYear.getAutoindex()
		};
		
		try {
			jdbcTemplate.update("update Db_qeis_budget_year set budgetYear = ?, currentPrice = ?, capitalPrice = ?  where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
