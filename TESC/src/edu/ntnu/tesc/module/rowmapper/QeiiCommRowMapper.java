package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.QeiiComm;

public class QeiiCommRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		QeiiComm qeiiComm = new QeiiComm();
		
		qeiiComm.setAutoindex(rs.getInt("autoindex"));
		qeiiComm.setQeiiID(rs.getInt("qeiiID"));
		qeiiComm.setUserID(rs.getInt("userID"));
		qeiiComm.setType(rs.getString("type"));
		qeiiComm.setComm(rs.getString("comm"));
		
		return qeiiComm;
	}

}
