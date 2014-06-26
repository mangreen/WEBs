package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.User;

public class UserRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		
		user.setId(rs.getInt("autoindex"));
		user.setRoleId(rs.getInt("roleID"));
		user.setAccount(rs.getString("account"));
		user.setEmail(rs.getString("Email"));
		user.setPassword(rs.getString("password"));
		user.setLastLoginDateTime(rs.getTimestamp("lastLoginDateTime"));
		user.setLastLoginIP(rs.getString("lastLoginDateTime"));
		
		return user;
	}

}
