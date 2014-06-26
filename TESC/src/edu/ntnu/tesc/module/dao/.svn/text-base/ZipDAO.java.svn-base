package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.Zip;
import edu.ntnu.tesc.module.idao.IZipDAO;
import edu.ntnu.tesc.module.rowmapper.ZipRowMapper;

public class ZipDAO implements IZipDAO{
    private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delZip(int zip_id) {
		int returnCode = 1;
		Object[] obj = new Object[]{zip_id};
		
		try {
			jdbcTemplate.update("delete from Db_zip where zip = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public Zip getZip(int zip_id) {
        final Zip zip = new Zip();
		
		jdbcTemplate.query("select * from Db_zip where zip = ?",
				new Object[]{zip_id},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						zip.setZip(rs.getInt("zip"));
						zip.setTitle(rs.getString("title"));
						
					}	
				}
		);
		
		return zip;
	}

	@Override
	public List<Zip> getZipList() {
		List<Zip> ZipList = (List<Zip>)jdbcTemplate.query("select * from Db_zip",
		        new RowMapperResultSetExtractor(new ZipRowMapper()));
		
		return ZipList;
	}

	@Override
	public int insertZip(Zip zip) {
		int returnCode = 1;
		Object[] obj = new Object[] {zip.getZip(), zip.getTitle()};
		
		try {
			jdbcTemplate.update("insert into Db_zip (zip, title) values(?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateZip(Zip zip) {
		int returnCode = 1;
		Object[] obj = new Object[] {zip.getTitle(), zip.getZip()};
		
		try {
			jdbcTemplate.update("update Db_zip set title = ? where zip = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}
}
