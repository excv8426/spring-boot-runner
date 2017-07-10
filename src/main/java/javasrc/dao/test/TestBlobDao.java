package javasrc.dao.test;

import java.io.File;
import java.io.FileNotFoundException;

import javasrc.dao.BlobDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TestBlobDao {
	@Autowired
	private BlobDao blobDao;
	
	public void update() throws DataAccessException, FileNotFoundException{
		blobDao.update("insert into MYLOG (COLUMN1,COLUMN3) values ('000000',?)", new File("D:\\档案相关\\文档\\SIEA系统API使用说明1.6_20170417.docx"));
	}
	
	public void query() throws DataAccessException, FileNotFoundException{
		blobDao.query("select * from MYLOG where COLUMN1='000000'","COLUMN3", new File("D:\\档案相关\\文档\\aaa.docx"));
	}
}
