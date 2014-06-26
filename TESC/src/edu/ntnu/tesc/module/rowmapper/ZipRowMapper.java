package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.Zip;

public class ZipRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Zip zip = new Zip();
		
		zip.setZip(rs.getInt("zip"));
		zip.setTitle(rs.getString("title"));
		
		return zip;
	}

}
