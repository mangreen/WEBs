package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.Privilege;

public class PrivilegeRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Privilege privilege = new Privilege();
		
		privilege.setAutoindex(rs.getInt("autoindex"));
		privilege.setState(rs.getInt("state"));
		privilege.setTitle(rs.getString("title"));
		privilege.setPrivLevel(rs.getInt("privLevel"));
		privilege.setPs(rs.getString("ps"));
		
		return privilege;
	}

}
