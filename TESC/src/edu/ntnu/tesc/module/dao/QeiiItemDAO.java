package edu.ntnu.tesc.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import edu.ntnu.tesc.module.beans.QeiiItem;
import edu.ntnu.tesc.module.idao.IQeiiItemDAO;
import edu.ntnu.tesc.module.rowmapper.QeiiItemRowMapper;

public class QeiiItemDAO implements IQeiiItemDAO{
    private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int delQeiiItem(int autoindex) {
		int returnCode = 1;
		Object[] obj = new Object[]{autoindex};
		
		try {
			jdbcTemplate.update("delete from Db_qeii_item where autoindex = ?", obj);
		} catch (DataAccessException  dae) {
		    returnCode = 0;	
		}
		
		return returnCode;
	}

	@Override
	public QeiiItem getQeiiItem(int autoindex) {
        final QeiiItem qeiiItem = new QeiiItem();
		
		jdbcTemplate.query("select * from Db_qeii_item where autoindex = ?",
				new Object[]{autoindex},
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						qeiiItem.setAutoindex(rs.getInt("autoindex"));
						qeiiItem.setQeiiID(rs.getInt("qeiiID"));
						qeiiItem.setRefereeID(rs.getInt("refereeID"));
						qeiiItem.setState(rs.getString("state"));
						qeiiItem.setType(rs.getString("type"));
						qeiiItem.setKind(rs.getString("kind"));
						qeiiItem.setScore_ary(rs.getString("Score_ary"));
						qeiiItem.setPs(rs.getString("ps"));
						
					}	
				}
		);
		
		return qeiiItem;
	}

	@Override
	public List<QeiiItem> getQeiiItemList() {
		List<QeiiItem> QeiiItemList = (List<QeiiItem>)jdbcTemplate.query("select * from Db_qeii_item",
		        new RowMapperResultSetExtractor(new QeiiItemRowMapper()));
		
		return QeiiItemList;
	}

	@Override
	public int insertQeiiItem(QeiiItem qeiiItem) {
		int returnCode = 1;
		Object[] obj = new Object[] {qeiiItem.getAutoindex(), qeiiItem.getQeiiID(), qeiiItem.getRefereeID(), qeiiItem.getState(), qeiiItem.getType(), qeiiItem.getKind(), qeiiItem.getScore_ary(), qeiiItem.getPs()};
		
		try {
			jdbcTemplate.update("insert into Db_qeii_item (autoindex, qeiiID, refereeID, state, type, kind, Score_ary, ps) values(?,?,?,?,?,?,?,?)", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

	@Override
	public int updateQeiiItem(QeiiItem qeiiItem) {
		int returnCode = 1;
		Object[] obj = new Object[] {qeiiItem.getQeiiID(), qeiiItem.getRefereeID(), qeiiItem.getState(), qeiiItem.getType(), qeiiItem.getKind(), qeiiItem.getScore_ary(), qeiiItem.getPs(), qeiiItem.getAutoindex()};
		
		try {
			jdbcTemplate.update("update Db_qeii_item set qeiiID = ?, refereeID = ?, state = ?, type = ?, kind = ?, Score_ary = ?, ps = ? where autoindex = ?", obj);
		} catch (DataAccessException dae) {
			returnCode = 0;
		}
		
		return returnCode;
	}

}
