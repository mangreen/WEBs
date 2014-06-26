package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.History;

public class HistoryRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		History history = new History();
		
		history.setUserID(rs.getInt("userID"));
		history.setCDate(rs.getTimestamp("cDate"));
		history.setActionIP(rs.getString("actionIP"));
		history.setSummary(rs.getString("summary"));
		history.setActionSQL(rs.getString("actionSQL"));
		
		return history;
	}

}
