package javasrc.dao;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

public class BlobCallBack extends AbstractLobCreatingPreparedStatementCallback {
	private Integer paramIndex;
	private InputStream contentStream;
	private Integer contentLength;

	public BlobCallBack(LobHandler lobHandler) {
		super(lobHandler);
	}
	
	public BlobCallBack(Integer paramIndex,InputStream contentStream,Integer contentLength) {
		super(new DefaultLobHandler());
		this.paramIndex=paramIndex;
		this.contentStream=contentStream;
		this.contentLength=contentLength;
	}

	@Override
	protected void setValues(PreparedStatement ps, LobCreator lobCreator)
			throws SQLException, DataAccessException {
		lobCreator.setBlobAsBinaryStream(ps, paramIndex, contentStream, contentLength);

	}

}
