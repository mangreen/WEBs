package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.Growl;
import edu.ntnu.tesc.module.idao.IGrowlDAO;
import edu.ntnu.tesc.module.rowmapper.GrowlRowMapper;

public class GrowlDAO implements IGrowlDAO{
    private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delGrowl(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_growl where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public Growl getGrowl(int autoindex) {
        final Growl growl = new Growl();
		
		jdbcTemplate.query("select * from Db_growl where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
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
						
					}	
				}
		);
		
		return growl;
	}

	@Override
	public List<Growl> getGrowlList() {
		List<Growl> growlList = (List<Growl>)jdbcTemplate.query("select * from Db_growl",
		        new RowMapperResultSetExtractor(new GrowlRowMapper()));
		
		return growlList;
	}

	@Override
	public int insertGrowl(Growl growl) {
		int returnCode = 1;
		Object[] obj = new Object[] {growl.getUserID(), growl.getTargetUserID(), growl.getType(), growl.getState(), growl.getCDate(), growl.getActionIP(), growl.getSummary(), growl.getGDate(), growl.getGActionIP()};
		
		try {
			jdbcTemplate.update("insert into Db_growl (userID, targetUserID, type, state, cDate, actionIP, summary, gDate, gActionIP) values(?,?,?,?,?,?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateGrowl(Growl growl) {
		int returnCode = 1;
		Object[] obj = new Object[] {growl.getUserID(), growl.getTargetUserID(), growl.getType(), growl.getState(), growl.getCDate(), growl.getActionIP(), growl.getSummary(), growl.getGDate(), growl.getGActionIP(), growl.getAutoindex()};
		
		try {
			jdbcTemplate.update("update Db_growl set userID = ?, targetUserID = ?, type = ?, state = ?, cDate = ?, actionIP = ?, summary = ?, gDate = ?, gActionIP = ? where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
