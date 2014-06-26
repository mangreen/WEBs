package edu.ntnu.tesc.module.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ntnu.tesc.module.beans.Qeii;

public class QeiiRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Qeii qeii = new Qeii();
		
		qeii.setAutoindex(rs.getInt("autoindex"));
		qeii.setUserID(rs.getInt("userID"));
		qeii.setSchoolID(rs.getInt("schoolID"));
		qeii.setState(rs.getString("state"));
		qeii.setCdate(rs.getTimestamp("cdate"));
		qeii.setTCCode(rs.getString("TCCode"));
		qeii.setName(rs.getString("name"));
		qeii.setSchoolstage(rs.getInt("schoolstage"));
		qeii.setTeachyear(rs.getString("Teachyear"));
		qeii.setEICode(rs.getString("EICode"));
		qeii.setEICode2(rs.getString("EICode2"));
		qeii.setPhone(rs.getString("phone"));
		qeii.setEmail(rs.getString("email"));
		qeii.setWebsite(rs.getString("website"));
		qeii.setType(rs.getString("type"));
		qeii.setSchoolTitle(rs.getString("schoolTitle"));
		qeii.setSchoolzip(rs.getInt("schoolzip"));
		qeii.setSchooladdr(rs.getString("schooladdr"));
		qeii.setExperience(rs.getString("experience"));
		qeii.setSummary(rs.getString("summary"));
		
		return qeii;
	}

}
