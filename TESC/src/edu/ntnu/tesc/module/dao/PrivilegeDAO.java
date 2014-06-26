package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.Privilege;
import edu.ntnu.tesc.module.idao.IPrivilegeDAO;
import edu.ntnu.tesc.module.rowmapper.PrivilegeRowMapper;

public class PrivilegeDAO implements IPrivilegeDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delPrivilege(int autoindex) {
		
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_priv where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public Privilege getPrivilege(int autoindex) {
		
		final Privilege privilege = new Privilege();
		
		jdbcTemplate.query("select * from Db_priv where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						privilege.setAutoindex(rs.getInt("autoindex"));
						privilege.setState(rs.getInt("state"));
						privilege.setTitle(rs.getString("title"));
						privilege.setPrivLevel(rs.getInt("privLevel"));
						privilege.setPs(rs.getString("ps"));
					}	
				}
		);
		
		return privilege;
	}

	@Override
	public List<Privilege> getPrivilegeList() {
		List<Privilege> privilegeList = (List<Privilege>)jdbcTemplate.query("select * from Db_priv",
		        new RowMapperResultSetExtractor(new PrivilegeRowMapper()));
		return privilegeList;
	}

	@Override
	public int insertPrivilege(Privilege privilege) {
		
		int returnCode = 1;
		Object[] obj = new Object[] {
				privilege.getState(),
				privilege.getTitle(),
				privilege.getPrivLevel(),
				privilege.getPs()
		};
		
		try {
			jdbcTemplate.update("insert into Db_priv (state, title, privLevel, ps) values(?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updatePrivilege(Privilege privilege) {
		int returnCode = 1;
		Object[] obj = new Object[] {
				privilege.getState(),
				privilege.getTitle(),
				privilege.getPrivLevel(),
				privilege.getPs(),
				privilege.getAutoindex()
		};
		
		try {
			jdbcTemplate.update("update Db_priv set state = ?, title = ?, privLevel = ?, ps = ?  where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
