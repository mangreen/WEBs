package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.History;
import edu.ntnu.tesc.module.idao.IHistoryDAO;
import edu.ntnu.tesc.module.rowmapper.HistoryRowMapper;

public class HistoryDAO implements IHistoryDAO{
    private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public History getHistory(int userID) {
        final History history = new History();
		
		jdbcTemplate.query("select * from Db_history where userID = ?",
				new Object[]{userID},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						history.setUserID(rs.getInt("userID"));
						history.setCDate(rs.getTimestamp("cDate"));
						history.setActionIP(rs.getString("actionIP"));
						history.setSummary(rs.getString("summary"));
						history.setActionSQL(rs.getString("actionSQL"));
					}	
				}
		);
		
		return history;
	}

	@Override
	public List<History> getHistoryList() {
		List<History> historyList = (List<History>)jdbcTemplate.query("select * from Db_history",
		        new RowMapperResultSetExtractor(new HistoryRowMapper()));
		
		return historyList;
	}

	@Override
	public int delHistory(int userID) {
		int returnCode = 1;
		Object[] obj = new Object[]{userID};
		
		try {
			jdbcTemplate.update("delete from Db_history where userID = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public int insertHistory(History history) {
		int returnCode = 1;
		Object[] obj = new Object[] {history.getUserID(), history.getCDate(), history.getActionIP(), history.getSummary(), history.getActionSQL()};
		
		try {
			jdbcTemplate.update("insert into Db_history (userID, cDate, actionIP, summary, actionSQL) values(?,?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateHistory(History history) {
		int returnCode = 1;
		Object[] obj = new Object[] {history.getCDate(), history.getActionIP(), history.getSummary(), history.getActionSQL(), history.getUserID()};
		
		try {
			jdbcTemplate.update("update Db_history set cDate = ?, actionIP = ?, summary = ?, actionSQL = ? where userID = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
