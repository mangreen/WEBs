package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.Department;

public class DepartmentRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department department = new Department();
		
		department.setAutoindex(rs.getInt("autoindex"));
		department.setTitle(rs.getString("title"));
		department.setCode(rs.getString("code"));
		department.setAddress(rs.getString("Address"));
		department.setPhone(rs.getString("Phone"));
		department.setMobile(rs.getString("Mobile"));
		
		return department;
	}

}
