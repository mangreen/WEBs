package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.Flow;
import edu.ntnu.tesc.module.idao.IFlowDAO;
import edu.ntnu.tesc.module.rowmapper.FlowRowMapper;

public class FlowDAO implements IFlowDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public Flow getFlow(int autoindex) {
		final Flow flow = new Flow();
		
		jdbcTemplate.query("select * from Db_flow where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						flow.setRoleID(rs.getInt("roleID"));
						flow.setUserID(rs.getInt("userID"));
						flow.setFlowModuleID(rs.getInt("flowModuleID"));
						flow.setFlowStageID(rs.getInt("flowStageID"));
						flow.setType(rs.getString("type"));
						flow.setState(rs.getString("state"));
						flow.setcDate(rs.getTimestamp("cDate"));
						flow.setpDate(rs.getTimestamp("pDate"));
					}	
				}
		);
		
		return flow;
	}

	@Override
	public List<Flow> getFlowList() {
		List<Flow> flowList = (List<Flow>)jdbcTemplate.query("select * from Db_flow",
		        new RowMapperResultSetExtractor(new FlowRowMapper()));
		
		return flowList;
	}

	@Override
	public int insertFlow(Flow flow) {
		int returnCode = 1;
		Object[] obj = new Object[] {
				flow.getRoleID(),
				flow.getUserID(),
				flow.getFlowModuleID(),
				flow.getFlowStageID(),
				flow.getType(),
				flow.getState(),
				flow.getcDate(),
				flow.getpDate()
		};
		
		try {
			jdbcTemplate.update("insert into Db_flow " +
					"(roleID, userID, flowModuleID, flowStageID, type, state, cDate, pDate) values(?,?,?,?,?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int delFlow(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_flow where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public int updateFlow(Flow flow) {
		int returnCode = 1;
				
		Object[] obj = new Object[] {
				flow.getRoleID(),
				flow.getUserID(),
				flow.getFlowModuleID(),
				flow.getFlowStageID(),
				flow.getType(),
				flow.getState(),
				flow.getcDate(),
				flow.getpDate(),
				flow.getAutoindex()
		};
		
		try {
			jdbcTemplate.update("update Db_flow set roleID=?, userID=?, flowModuleID=?, flowStageID=?, type=?, state=?, cDate=?, pDate=?   where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
