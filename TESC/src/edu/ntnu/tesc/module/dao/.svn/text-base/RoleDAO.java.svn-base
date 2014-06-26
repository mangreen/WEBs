package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.Role;
import edu.ntnu.tesc.module.idao.IRoleDAO;
import edu.ntnu.tesc.module.rowmapper.RoleRowMapper;

public class RoleDAO implements IRoleDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delRole(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_role where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public Role getRole(int autoindex) {
		final Role role = new Role();
		
		jdbcTemplate.query("select * from Db_role where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						role.setAutoindex(rs.getInt("autoindex"));
						role.setState(rs.getInt("state"));
						role.setTitle(rs.getString("title"));
						role.setDetailTable(rs.getString("detailTable"));
						role.setPs(rs.getString("ps"));
					}	
				}
		);
		
		return role;
	}

	@Override
	public List<Role> getRoleList() {
		List<Role> roleList = (List<Role>)jdbcTemplate.query("select * from Db_role",
		        new RowMapperResultSetExtractor(new RoleRowMapper()));
		return roleList;
	}

	@Override
	public int insertRole(Role role) {
		
		int returnCode = 1;
		Object[] obj = new Object[] {role.getState(),role.getTitle(),role.getDetailTable(),role.getPs()};
		
		try {
			jdbcTemplate.update("insert into Db_role (state, title, detailTable, ps) values(?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateRole(Role role) {
		int returnCode = 1;
		Object[] obj = new Object[] {role.getState(),role.getTitle(),role.getDetailTable(),role.getPs(), role.getAutoindex()};
		
		try {
			jdbcTemplate.update("update Db_role set state = ?, title = ?, detailTable = ?, ps = ?  where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
