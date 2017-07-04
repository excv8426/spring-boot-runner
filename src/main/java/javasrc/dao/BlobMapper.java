package javasrc.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.RowMapper;

public class BlobMapper implements RowMapper<Map<String, Object>> {
	private File outputFile;
	private String columnLabel;
	
	public BlobMapper(File outputFile,String columnLabel){
		this.outputFile=outputFile;
		this.columnLabel=columnLabel;
	}

	@Override
	public Map<String, Object> mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		try {
			IOUtils.copy(rs.getBlob(columnLabel).getBinaryStream(), new FileOutputStream(outputFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
