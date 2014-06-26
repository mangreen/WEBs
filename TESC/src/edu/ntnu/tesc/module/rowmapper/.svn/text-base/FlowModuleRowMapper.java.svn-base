package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.FlowModule;

public class FlowModuleRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		FlowModule flowModule = new FlowModule();
		
		flowModule.setAutoindex(rs.getInt("autoindex"));
		flowModule.setTitle(rs.getString("title"));
		flowModule.setFlowXML(rs.getString("Flow_xml"));
		
		return flowModule;
	}

}
