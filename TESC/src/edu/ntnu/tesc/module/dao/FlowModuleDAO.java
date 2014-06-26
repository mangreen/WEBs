package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.FlowModule;
import edu.ntnu.tesc.module.idao.IFlowModuleDAO;
import edu.ntnu.tesc.module.rowmapper.FlowModuleRowMapper;

public class FlowModuleDAO implements IFlowModuleDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delFlowModule(int autoindex) {
		
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_flow_module where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public FlowModule getFlowModule(int autoindex) {
		final FlowModule flowModule = new FlowModule();
		
		jdbcTemplate.query("select * from Db_flow_module where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						flowModule.setAutoindex(rs.getInt("autoindex"));
						flowModule.setTitle(rs.getString("title"));
						flowModule.setFlowXML(rs.getString("Flow_xml"));
					}	
				}
		);
		
		return flowModule;
	}

	@Override
	public List<FlowModule> getFlowModuleList() {
		List<FlowModule> roleList = (List<FlowModule>)jdbcTemplate.query("select * from Db_flow_module",
		        new RowMapperResultSetExtractor(new FlowModuleRowMapper()));
		return roleList;
	}

	@Override
	public int insertFlowModule(FlowModule flowModule) {
		int returnCode = 1;
		Object[] obj = new Object[] {
				flowModule.getTitle(),
				flowModule.getFlowXML()
		};
		
		try {
			jdbcTemplate.update("insert into Db_flow_module (title, Flow_xml) values(?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateFlowModule(FlowModule flowModule) {

		int returnCode = 1;
		Object[] obj = new Object[] {
				flowModule.getTitle(),
				flowModule.getFlowXML(),
				flowModule.getAutoindex()
		};
		
		try {
			jdbcTemplate.update("update Db_flow_module set title = ?, Flow_xml = ? where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
