package javasrc.component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javasrc.importfiles.ImportFilesService;

import org.spring_boot_runner.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class LoadDataRunner implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*@Autowired
	private TestBlobDao testBlobDao;*/
	/*@Autowired
	private SubTable1Reposity subTable1Reposity;
	@Autowired
	private SubTable2Reposity subTable2Reposity;*/
	@Autowired
	private ImportFilesService importFilesService;
		
	@Override
	public void run(String... args) throws Exception {
		importFilesService.getAjxxs();
		//lostFiles.aaa();
		//testlarge();
		//insertbusiness();
		//insertpmodle();
		//insert();
		//insertfiletype();
	}
	
	public void insertbusiness(){
		int[] insertcolumns={0,1,2};
		String sql="insert into PBUSINESS (BAZ001,BAZ002,BZE091,BZE090,BZE647,CLIENTID,BAE573) "
				+ "values (SEQ_A_BAZ001.nextval,SEQ_A_BAZ002.nextval,?,?,?,'qyylbxClient','1')";
		List<Object> params;
		List<List<String>> rows=ExcelService.getcells(1, 38, new File("D:\\石家庄业务整理.xlsx"));
		for (List<String> row : rows) {
			params=new ArrayList<Object>();
			for (int column : insertcolumns) {
				params.add(row.get(column));
			}
			jdbcTemplate.update(sql, params.toArray(new Object[params.size()]));
		}
	}
	
	public void insertpmodle(){
		int[] insertcolumns={1,4};
		String sql="insert into PBUSSARCHIVEMODLE (BAZ001,BAZ002,BZE090,AAC661,AAC671,AAC681,AAE011,AAE035,AAB034,CLIENTID,DEFAULT_FILETYPE) "
				+ "values (SEQ_A_BAZ001.nextval,SEQ_A_BAZ002.nextval,?,?,'1',?,'档案管理员',sysdate,'130100','qyylbxClient','1')";
		List<Object> params;
		List<List<String>> rows=ExcelService.getcells(1, 65, new File("D:\\石家庄材料和模板整理.xlsx"));
		String querysql="select PFILETYPECODE from PFILETYPE where PFILETYPENAME=?";
		String querysql2="select * from PFILETYPE where PFILETYPEMAIN='1' and PFILETYPENAME=?";
		String aac661;
		Integer aac681=1;
		String pfiletypename;
		for (List<String> row : rows) {
			params=new ArrayList<Object>();
			params.add(row.get(insertcolumns[0]));
			pfiletypename=row.get(insertcolumns[1]);
			System.out.println("row.get(insertcolumns[1])"+pfiletypename);
			aac661=jdbcTemplate.queryForObject(querysql, new Object[]{pfiletypename}, String.class);
			params.add(aac661);
			List<Map<String, Object>> pfiletypeList=jdbcTemplate.queryForList(querysql2, new Object[]{pfiletypename});
			if (pfiletypeList.size()==1) {
				Map<String, Object> pfiletypeMap=pfiletypeList.get(0);
				if (pfiletypeMap.get("PFILETYPEMAIN").equals("1")) {
					aac681=1;
				}
			} else if (pfiletypeList.size()==0) {
				System.out.println("不是表单。");
			} else {
				try {
					throw new Exception("error");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			params.add(aac681.toString());
			jdbcTemplate.update(sql, params.toArray(new Object[params.size()]));
			aac681++;
		}
	}
	
	public void insertbiaodan(){
		int[] insertcolumns={0,1};
		String sql="insert into PFILETYPE (IOBJECT,PFILETYPENAME,PFILETYPECODE,PFILETYPECREATEDATE,CLIENTID,PFILETYPECREATER,PISSYNCHRO,PFILETYPEMAIN) values "
				+ "(SEQ_A_IOBJECT.nextval,?,?,sysdate,'qyylbxClient','企业养老保险','0','1')";
		List<Object> params;
		List<List<String>> rows=ExcelService.getcells(1, 38, new File("D:\\石家庄表单整理.xlsx"));
		for (List<String> row : rows) {
			params=new ArrayList<Object>();
			for (int column : insertcolumns) {
				params.add(row.get(column));
			}
			jdbcTemplate.update(sql, params.toArray(new Object[params.size()]));
		}
	}
	
	public void insertpfiletype(){
		int[] insertcolumns={4};
		String querysql="select * from PFILETYPE where PFILETYPENAME=?";
		
		String sql="insert into PFILETYPE (IOBJECT,PFILETYPENAME,PFILETYPECODE,PFILETYPECREATEDATE,CLIENTID,PFILETYPECREATER,PISSYNCHRO,PFILETYPEMAIN) values "
				+ "(SEQ_A_IOBJECT.nextval,?,SEQ_A_FILETYPE.nextval,sysdate,'qyylbxClient','企业养老保险','0','0')";
		List<Object> params;
		List<List<String>> rows=ExcelService.getcells(1, 65, new File("D:\\石家庄材料和模板整理.xlsx"));
		for (List<String> row : rows) {

			params=new ArrayList<Object>();
			for (int column : insertcolumns) {
				params.add(row.get(column));
			}
			if (jdbcTemplate.queryForList(querysql,new Object[]{row.get(4)}).size()==0) {
				jdbcTemplate.update(sql, params.toArray(new Object[params.size()]));
			}
			
		}
	}
	
	/*public void testmodel() throws JsonProcessingException{
		System.out.println("********************CommandLineRunner***************************");
		ObjectMapper mapper=new ObjectMapper();
		SubTable1 subTable1=new SubTable1();
    	subTable1.setBasecolumn2("aaa");
    	subTable1.setSubcolumn4("bbb");
    	subTable1.setSubpartcolumn1("ccc");
    	subTable1.setSubpartcolumn2("ddd");
    	//subTable1Reposity.save(subTable1);
    	
    	List<SubTable1> subTable1s=subTable1Reposity.findAll();
    	System.out.println(mapper.writeValueAsString(subTable1s));
    	subTable2Reposity.findAll();
	}*/
	
    public void testlarge() throws Exception{
    	ExcelService.importLargeExcel(new File("D:\\石家庄材料和模板整理.xlsx"));
    }

}
