package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.Config;

public class ConfigRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Config config = new Config();
		
		config.setAutoindex(rs.getInt("autoindex"));
		config.setTitle(rs.getString("title"));
		config.setVal(rs.getString("val"));
		config.setVal2(rs.getString("val2"));
		
		return config;
	}

}
