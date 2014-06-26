package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.Stage;

public class StageRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Stage stage = new Stage();
		
		stage.setAutoindex(rs.getInt("autoindex"));
		stage.setTitle(rs.getString("title"));
		
		return stage;
	}

}
