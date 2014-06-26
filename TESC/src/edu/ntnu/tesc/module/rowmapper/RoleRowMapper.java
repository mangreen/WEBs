package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.Role;

public class RoleRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		
		role.setAutoindex(rs.getInt("autoindex"));
		role.setState(rs.getInt("state"));
		role.setTitle(rs.getString("title"));
		role.setDetailTable(rs.getString("detailTable"));
		role.setPs(rs.getString("ps"));
		
		return role;
	}

}
