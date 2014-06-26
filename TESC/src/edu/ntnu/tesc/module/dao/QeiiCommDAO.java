package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.QeiiComm;
import edu.ntnu.tesc.module.idao.IQeiiCommDAO;
import edu.ntnu.tesc.module.rowmapper.QeiiCommRowMapper;

public class QeiiCommDAO implements IQeiiCommDAO{
    private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delQeiiComm(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_qeii_comm where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public QeiiComm getQeiiComm(int autoindex) {
        final QeiiComm qeiiComm = new QeiiComm();
		
		jdbcTemplate.query("select * from Db_qeii_comm where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						qeiiComm.setAutoindex(rs.getInt("autoindex"));
						qeiiComm.setQeiiID(rs.getInt("qeiiID"));
						qeiiComm.setUserID(rs.getInt("userID"));
						qeiiComm.setType(rs.getString("type"));
						qeiiComm.setComm(rs.getString("comm"));
						
					}	
				}
		);
		
		return qeiiComm;
	}

	@Override
	public List<QeiiComm> getQeiiCommList() {
		List<QeiiComm> QeiiCommList = (List<QeiiComm>)jdbcTemplate.query("select * from Db_qeii_comm",
		        new RowMapperResultSetExtractor(new QeiiCommRowMapper()));
		
		return QeiiCommList;
	}

	@Override
	public int insertQeiiComm(QeiiComm qeiiComm) {
		int returnCode = 1;
		Object[] obj = new Object[] {qeiiComm.getAutoindex(), qeiiComm.getQeiiID(), qeiiComm.getUserID(), qeiiComm.getType(), qeiiComm.getComm()};
		
		try {
			jdbcTemplate.update("insert into Db_qeii_comm (autoindex, qeiiID, userID, type, comm) values(?,?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateQeiiComm(QeiiComm qeiiComm) {
		int returnCode = 1;
		Object[] obj = new Object[] {qeiiComm.getQeiiID(), qeiiComm.getUserID(), qeiiComm.getType(), qeiiComm.getComm(), qeiiComm.getAutoindex()};
		
		try {
			jdbcTemplate.update("update Db_qeii_comm set qeiiID = ?, userID = ?, type = ?, comm = ? where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}
}
