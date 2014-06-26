package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.QeiiFile;

public class QeiiFileRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		QeiiFile qeiiFile = new QeiiFile();
		
		qeiiFile.setAutoindex(rs.getInt("autoindex"));
		qeiiFile.setQeiiID(rs.getInt("qeiiID"));
		qeiiFile.setKind(rs.getString("kind"));
		qeiiFile.setFileUrl(rs.getString("fileUrl"));
		qeiiFile.setPs(rs.getString("ps"));
		
		return qeiiFile;
	}

}
