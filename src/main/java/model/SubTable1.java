package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

@Entity  
@Table(name = "SUBTABLE1")  
@PrimaryKeyJoinColumn(name = "BASECOLUMN1")  
@SecondaryTables({@SecondaryTable(name="SUBTABLE1_PART1",pkJoinColumns={@PrimaryKeyJoinColumn(name="SUBPART1_ID",referencedColumnName="BASECOLUMN1")}),
	@SecondaryTable(name="SUBTABLE1_PART2",pkJoinColumns={@PrimaryKeyJoinColumn(name="SUBPART2_ID",referencedColumnName="BASECOLUMN1")})})
public class SubTable1 extends BaseTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3195328190834208478L;
	
	@Column(name="SUBCOLUMN4")
	private String subcolumn4;
	
	@Column(table="SUBTABLE1_PART1",name="SUBPARTCOLUMN1")
	private String subpartcolumn1;
	
	@Column(table="SUBTABLE1_PART2",name="SUBPARTCOLUMN2")
	private String subpartcolumn2;

	public String getSubcolumn4() {
		return subcolumn4;
	}

	public void setSubcolumn4(String subcolumn4) {
		this.subcolumn4 = subcolumn4;
	}

	public String getSubpartcolumn1() {
		return subpartcolumn1;
	}

	public void setSubpartcolumn1(String subpartcolumn1) {
		this.subpartcolumn1 = subpartcolumn1;
	}

	public String getSubpartcolumn2() {
		return subpartcolumn2;
	}

	public void setSubpartcolumn2(String subpartcolumn2) {
		this.subpartcolumn2 = subpartcolumn2;
	}
	
}
