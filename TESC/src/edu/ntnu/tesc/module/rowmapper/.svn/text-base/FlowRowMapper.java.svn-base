package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.Flow;

public class FlowRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Flow flow = new Flow();
		
		flow.setRoleID(rs.getInt("roleID"));
		flow.setUserID(rs.getInt("userID"));
		flow.setFlowModuleID(rs.getInt("flowModuleID"));
		flow.setFlowStageID(rs.getInt("flowStageID"));
		flow.setType(rs.getString("type"));
		flow.setState(rs.getString("state"));
		flow.setcDate(rs.getTimestamp("cDate"));
		flow.setpDate(rs.getTimestamp("pDate"));
		
		return flow;
	}

}
