package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.Department;
import edu.ntnu.tesc.module.idao.IDepartmentDAO;
import edu.ntnu.tesc.module.rowmapper.DepartmentRowMapper;

public class DepartmentDAO implements IDepartmentDAO{
    private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delDepartment(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_department where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public Department getDepartment(int autoindex) {
        final Department department = new Department();
		
		jdbcTemplate.query("select * from Db_department where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						department.setAutoindex(rs.getInt("autoindex"));
						department.setTitle(rs.getString("title"));
						department.setCode(rs.getString("code"));
						department.setAddress(rs.getString("Address"));
						department.setPhone(rs.getString("Phone"));
						department.setMobile(rs.getString("Mobile"));
					}	
				}
		);
		
		return department;
	}

	@Override
	public List<Department> getDepartmentList() {
		List<Department> departmentList = (List<Department>)jdbcTemplate.query("select * from Db_department",
		        new RowMapperResultSetExtractor(new DepartmentRowMapper()));
		
		return departmentList;
	}

	@Override
	public int insertDepartment(Department department) {
		int returnCode = 1;
		Object[] obj = new Object[] {department.getTitle(), department.getCode(), department.getAddress(), department.getPhone(), department.getMobile()};
		
		try {
			jdbcTemplate.update("insert into Db_department (title, code, Address, Phone, Mobile) values(?,?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateDepartment(Department department) {
		int returnCode = 1;
		Object[] obj = new Object[] {department.getTitle(), department.getCode(), department.getAddress(), department.getPhone(), department.getMobile(), department.getAutoindex()};
		
		try {
			jdbcTemplate.update("update Db_department set title = ?, code = ?, Address = ?, Phone = ?, Mobile = ? where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
