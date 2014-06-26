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
import edu.ntnu.tesc.module.beans.User;
import edu.ntnu.tesc.module.idao.IUser;
import edu.ntnu.tesc.module.rowmapper.RoleRowMapper;
import edu.ntnu.tesc.module.rowmapper.UserRowMapper;

public class UserDAO implements IUser {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public void delete(int id) {
		jdbcTemplate.update("delete from Db_user where autoindex = ?", new Object[]{id});
	}
	public User findUser(String account,String password){
		final User user = new User();
		jdbcTemplate.query("select * from Db_user where account = ? AND password = ?",
				new Object[]{account,password},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						user.setId(rs.getInt("autoindex"));
						user.setRoleId(rs.getInt("roleID"));
						user.setAccount(rs.getString("account"));
						user.setEmail(rs.getString("Email"));
						user.setPassword(rs.getString("password"));
						user.setLastLoginDateTime(rs.getTimestamp("lastLoginDateTime"));
						user.setLastLoginIP(rs.getString("lastLoginDateTime"));
					}
					
				}
		);
		
		return user;		
	}	
	public User findEmail(String email){
		final User user = new User();
		jdbcTemplate.query("select * from Db_user where Email = ?",
				new Object[]{email},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						user.setId(rs.getInt("autoindex"));
						user.setRoleId(rs.getInt("roleID"));
						user.setAccount(rs.getString("account"));
						user.setEmail(rs.getString("Email"));
						user.setPassword(rs.getString("password"));
						user.setLastLoginDateTime(rs.getTimestamp("lastLoginDateTime"));
						user.setLastLoginIP(rs.getString("lastLoginDateTime"));
					}
					
				}
		);
		
		return user;		
	}
	@Override
	public User find(int id) {
		final User user = new User();
		jdbcTemplate.query("select * from Db_user where autoindex = ?",
				new Object[]{id},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						user.setId(rs.getInt("autoindex"));
						user.setRoleId(rs.getInt("roleID"));
						user.setAccount(rs.getString("account"));
						user.setEmail(rs.getString("Email"));
						user.setPassword(rs.getString("password"));
						user.setLastLoginDateTime(rs.getTimestamp("lastLoginDateTime"));
						user.setLastLoginIP(rs.getString("lastLoginDateTime"));
					}
					
				}
		);
		
		return user;
	}

	@Override
	public void insert(User user) {
		
		user.setState(0);
		Object[] obj = new Object[] {user.getRoleId(),user.getState(),user.getAccount(),user.getPassword(),user.getEmail()};
		jdbcTemplate.update("insert into Db_user (roleID,state,account,password,Email) values(?,?,?,?,?)", obj);

	}

	@Override
	public void update(User user) {
		
		Object[] obj = new Object[] {
				user.getRoleId(),
				user.getState(),
				user.getAccount(),
				user.getPassword(),
				user.getEmail(),
				user.getLastLoginDateTime(),
				user.getLastLoginIP(),
				user.getId()
		};
		jdbcTemplate.update("update Db_user set roleID=?, state=?, account=?, password=?, Email=?, lastLoginDateTime=?, lastLoginIP=?  where autoindex = ?",
				obj);

	}

	@Override
	public User find(String account) {
		final User user = new User();
		jdbcTemplate.query("select * from Db_user where account = ?",
				new Object[]{account},
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						user.setId(rs.getInt("autoindex"));
						user.setRoleId(rs.getInt("roleID"));
						user.setAccount(rs.getString("account"));
						user.setEmail(rs.getString("Email"));
						user.setPassword(rs.getString("password"));
						user.setLastLoginDateTime(rs.getTimestamp("lastLoginDateTime"));
						user.setLastLoginIP(rs.getString("lastLoginDateTime"));
					}
					
				}
		);
		
		return user;
	}

	@Override
	public int deleteUser(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_user where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public List<User> getUserList() {
		List<User> userList = (List<User>)jdbcTemplate.query("select * from Db_user",
		        new RowMapperResultSetExtractor(new UserRowMapper()));
		return userList;
	}

	@Override
	public int insertUser(User user) {
		int returnCode = 1;
		
		user.setState(0);
		Object[] obj = new Object[] {user.getRoleId(),user.getState(),user.getAccount(),user.getPassword(),user.getEmail()};
		try {
			jdbcTemplate.update("insert into Db_user (roleID,state,account,password,Email) values(?,?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateUser(User user) {
		int returnCode = 1;
		
		Object[] obj = new Object[] {
				user.getRoleId(),
				user.getState(),
				user.getAccount(),
				user.getPassword(),
				user.getEmail(),
				user.getLastLoginDateTime(),
				user.getLastLoginIP(),
				user.getId()
		};
		
		try {
			jdbcTemplate.update("update Db_user set roleID=?, state=?, account=?, password=?, Email=?, lastLoginDateTime=?, lastLoginIP=?  where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
