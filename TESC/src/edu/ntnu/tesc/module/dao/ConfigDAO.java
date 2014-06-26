package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.Config;
import edu.ntnu.tesc.module.idao.IConfigDAO;
import edu.ntnu.tesc.module.rowmapper.ConfigRowMapper;

public class ConfigDAO implements IConfigDAO{
    private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delConfig(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_config where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public Config getConfig(int autoindex) {
        final Config config = new Config();
		
		jdbcTemplate.query("select * from Db_config where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						config.setAutoindex(rs.getInt("autoindex"));
						config.setTitle(rs.getString("title"));
						config.setVal(rs.getString("val"));
						config.setVal2(rs.getString("val2"));
					}	
				}
		);
		
		return config;
	}

	@Override
	public List<Config> getConfigList() {
		List<Config> configList = (List<Config>)jdbcTemplate.query("select * from Db_config",
		        new RowMapperResultSetExtractor(new ConfigRowMapper()));
		
		return configList;
	}

	@Override
	public int insertConfig(Config config) {
		int returnCode = 1;
		Object[] obj = new Object[] {config.getTitle(), config.getVal(), config.getVal2()};
		
		try {
			jdbcTemplate.update("insert into Db_config (title, val, val2) values(?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateConfig(Config config) {
		int returnCode = 1;
		Object[] obj = new Object[] {config.getTitle(), config.getVal(), config.getVal2(), config.getAutoindex()};
		
		try {
			jdbcTemplate.update("update Db_config set title = ?, val = ?, val2 = ? where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
