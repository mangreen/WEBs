package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.Stage;
import edu.ntnu.tesc.module.idao.IStageDAO;
import edu.ntnu.tesc.module.rowmapper.StageRowMapper;

public class StageDAO implements IStageDAO {
    private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delStage(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_stage where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public Stage getStage(int autoindex) {
		final Stage stage = new Stage();
		
		jdbcTemplate.query("select * from Db_stage where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						stage.setAutoindex(rs.getInt("autoindex"));
						stage.setTitle(rs.getString("title"));
					}	
				}
		);
		
		return stage;
	}

	@Override
	public List<Stage> getStageList() {
		List<Stage> stageList = (List<Stage>)jdbcTemplate.query("select * from Db_stage",
		        new RowMapperResultSetExtractor(new StageRowMapper()));
		
		return stageList;
	}

	@Override
	public int insertStage(Stage stage) {
		int returnCode = 1;
		Object[] obj = new Object[] {stage.getTitle()};
		
		try {
			jdbcTemplate.update("insert into Db_stage (title) values(?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateStage(Stage stage) {
		int returnCode = 1;
		Object[] obj = new Object[] {stage.getTitle(), stage.getAutoindex()};
		
		try {
			jdbcTemplate.update("update Db_stage set title = ? where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
