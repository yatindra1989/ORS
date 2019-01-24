
package com.raystech.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Marksheet POJO class. It is persistent object.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Entity
@Table(name = "st_marksheet")
public class MarksheetDTO extends BaseDTO {

	/**
	 * Roll Number of Student
	 */
	@Column(name = "ROLL_NO")
	private String rollNo;

	/**
	 * ID of Student
	 */
	@Column(name = "STUDENT_ID")
	private long studentId;
	/**
	 * Name of Student
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * Marks of Physics
	 */
	@Column(name = "PHYSICS")
	private Integer physics;
	/**
	 * Marks of Chemistry
	 */
	@Column(name = "CHEMISTRY")
	private Integer chemistry;

	/**
	 * Marks of Math
	 */
	@Column(name = "MATHS")
	private Integer maths;

	/**
	 * Total Marks
	 */
	private Integer total;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
