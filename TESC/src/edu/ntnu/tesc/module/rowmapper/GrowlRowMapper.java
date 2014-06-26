package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.Growl;

public class GrowlRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Growl growl = new Growl();
		
		growl.setAutoindex(rs.getInt("autoindex"));
		growl.setUserID(rs.getInt("userID"));
		growl.setTargetUserID(rs.getInt("targetUserID"));
		growl.setType(rs.getString("type"));
		growl.setState(rs.getString("state"));
		growl.setCDate(rs.getTimestamp("cDate"));
		growl.setActionIP(rs.getString("actionIP"));
		growl.setSummary(rs.getString("summary"));
		growl.setGDate(rs.getTimestamp("gDate"));
		growl.setGActionIP(rs.getString("gActionIP"));
		
		return growl;
	}

}
