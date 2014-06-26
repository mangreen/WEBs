package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.QeiiItem;

public class QeiiItemRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		QeiiItem qeiiItem = new QeiiItem();
		
		qeiiItem.setAutoindex(rs.getInt("autoindex"));
		qeiiItem.setQeiiID(rs.getInt("qeiiID"));
		qeiiItem.setRefereeID(rs.getInt("refereeID"));
		qeiiItem.setState(rs.getString("state"));
		qeiiItem.setType(rs.getString("type"));
		qeiiItem.setKind(rs.getString("kind"));
		qeiiItem.setScore_ary(rs.getString("Score_ary"));
		qeiiItem.setPs(rs.getString("ps"));
		
		return qeiiItem;
	}

}
