package javasrc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlobDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void update(String sql,File file) throws DataAccessException, FileNotFoundException{
		jdbcTemplate.execute(sql, new BlobCallBack(1, new FileInputStream(file), (int) file.length()));
	}
	
	public void query(String sql,String columnLabel,File file) throws DataAccessException, FileNotFoundException{
		jdbcTemplate.queryForObject(sql, new BlobMapper(file,columnLabel));
	}
}
