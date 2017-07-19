package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity  
@Table(name = "SUBTABLE2")  
@PrimaryKeyJoinColumn(name = "BASECOLUMN1")  
@SecondaryTable(name="SUBTABLE2_PART1",pkJoinColumns={@PrimaryKeyJoinColumn(name="SUBPART3_ID",referencedColumnName="BASECOLUMN1")})
public class SubTable2 extends BaseTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8152522076099835134L;
	
	@Column(name="SUBCOLUMN6")
	private String subcolumn6;
	
	@Column(table="SUBTABLE2_PART1",name="SUBPARTCOLUMN3")
	private String subpartcolumn3;

	public String getSubcolumn6() {
		return subcolumn6;
	}

	public void setSubcolumn6(String subcolumn6) {
		this.subcolumn6 = subcolumn6;
	}

	public String getSubpartcolumn3() {
		return subpartcolumn3;
	}

	public void setSubpartcolumn3(String subpartcolumn3) {
		this.subpartcolumn3 = subpartcolumn3;
	}
	
}
