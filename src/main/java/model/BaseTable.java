package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity  
@Inheritance(strategy = InheritanceType.JOINED)  
@Table(name = "BASETABLE")  
public class BaseTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6690874915240865521L;

	@Id
	@Column(name="BASECOLUMN1")
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid")
	private String basecolumn1;
	
	@Column(name="BASECOLUMN2")
	private String basecolumn2;

	public String getBasecolumn1() {
		return basecolumn1;
	}

	public void setBasecolumn1(String basecolumn1) {
		this.basecolumn1 = basecolumn1;
	}

	public String getBasecolumn2() {
		return basecolumn2;
	}

	public void setBasecolumn2(String basecolumn2) {
		this.basecolumn2 = basecolumn2;
	}
	
	
	
}
