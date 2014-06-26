package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.QeiiFile;
import edu.ntnu.tesc.module.idao.IQeiiFileDAO;
import edu.ntnu.tesc.module.rowmapper.QeiiFileRowMapper;

public class QeiiFileDAO implements IQeiiFileDAO{
    private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delQeiiFile(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_qeii_file where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public QeiiFile getQeiiFile(int autoindex) {
        final QeiiFile qeiiFile = new QeiiFile();
		
		jdbcTemplate.query("select * from Db_qeii_file where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						qeiiFile.setAutoindex(rs.getInt("autoindex"));
						qeiiFile.setQeiiID(rs.getInt("qeiiID"));
						qeiiFile.setKind(rs.getString("kind"));
						qeiiFile.setFileUrl(rs.getString("fileUrl"));
						qeiiFile.setPs(rs.getString("ps"));
						
					}	
				}
		);
		
		return qeiiFile;
	}

	@Override
	public List<QeiiFile> getQeiiFileList() {
		List<QeiiFile> QeiiFileList = (List<QeiiFile>)jdbcTemplate.query("select * from Db_qeii_file",
		        new RowMapperResultSetExtractor(new QeiiFileRowMapper()));
		
		return QeiiFileList;
	}

	@Override
	public int insertQeiiFile(QeiiFile qeiiFile) {
		int returnCode = 1;
		Object[] obj = new Object[] {qeiiFile.getAutoindex(), qeiiFile.getQeiiID(), qeiiFile.getKind(), qeiiFile.getFileUrl(), qeiiFile.getPs()};
		
		try {
			jdbcTemplate.update("insert into Db_qeii_file (autoindex, qeiiID, kind, fileUrl, ps) values(?,?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateQeiiFile(QeiiFile qeiiFile) {
		int returnCode = 1;
		Object[] obj = new Object[] {qeiiFile.getQeiiID(), qeiiFile.getKind(), qeiiFile.getFileUrl(), qeiiFile.getPs(), qeiiFile.getAutoindex()};
		
		try {
			jdbcTemplate.update("update Db_qeii_file set qeiiID = ?, kind = ?, fileUrl = ?, ps = ? where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}
}
