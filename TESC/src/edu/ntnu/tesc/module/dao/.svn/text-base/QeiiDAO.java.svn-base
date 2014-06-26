package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.Qeii;
import edu.ntnu.tesc.module.idao.IQeiiDAO;
import edu.ntnu.tesc.module.rowmapper.QeiiRowMapper;

public class QeiiDAO implements IQeiiDAO{
    private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delQeii(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_qeii where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public Qeii getQeii(int autoindex) {
        final Qeii qeii = new Qeii();
		
		jdbcTemplate.query("select * from Db_qeii where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
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
						
					}	
				}
		);
		
		return qeii;
	}

	@Override
	public List<Qeii> getQeiiList() {
		List<Qeii> qeiiList = (List<Qeii>)jdbcTemplate.query("select * from Db_qeii",
		        new RowMapperResultSetExtractor(new QeiiRowMapper()));
		
		return qeiiList;
	}

	@Override
	public int insertQeii(Qeii qeii) {
		int returnCode = 1;
		Object[] obj = new Object[] {qeii.getAutoindex(), qeii.getUserID(), qeii.getSchoolID(), qeii.getState(), qeii.getCdate(), qeii.getTCCode(), qeii.getName(), qeii.getSchoolstage(), qeii.getTeachyear(), qeii.getEICode(), qeii.getEICode2(), qeii.getPhone(), qeii.getEmail(), qeii.getWebsite(), qeii.getType(), qeii.getSchoolTitle(), qeii.getSchoolzip(), qeii.getSchooladdr(), qeii.getExperience(), qeii.getSummary()};
		
		try {
			jdbcTemplate.update("insert into Db_qeii (autoindex, userID, schoolID, state, cdate, TCCode, name, schoolstage, Teachyear, EICode, EICode2, phone, email, website, type, schoolTitle, schoolzip, schooladdr, experience, summary) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateQeii(Qeii qeii) {
		int returnCode = 1;
		Object[] obj = new Object[] {qeii.getUserID(), qeii.getSchoolID(), qeii.getState(), qeii.getCdate(), qeii.getTCCode(), qeii.getName(), qeii.getSchoolstage(), qeii.getTeachyear(), qeii.getEICode(), qeii.getEICode2(), qeii.getPhone(), qeii.getEmail(), qeii.getWebsite(), qeii.getType(), qeii.getSchoolTitle(), qeii.getSchoolzip(), qeii.getSchooladdr(), qeii.getExperience(), qeii.getSummary(), qeii.getAutoindex()};
		
		try {
			jdbcTemplate.update("update Db_qeii set userID = ?, schoolID = ?, state = ?, cdate = ?, TCCode = ?, name = ?, schoolstage = ?, Teachyear = ?, EICode = ?, EICode2 = ?, phone = ?, email = ?, website = ?, type = ?, schoolTitle = ?, schoolzip = ?, schooladdr = ?, experience = ?, summary = ? where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
