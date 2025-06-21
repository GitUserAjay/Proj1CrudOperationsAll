package com.ajay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Employee_Table")
@Data
@NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer empId;
	
	
	
	public Employee(String empName, String empSirname, String empEmail, long empPhoneNo, float empSal, String empDesg) {
		super();
		this.empName = empName;
		this.empSirname = empSirname;
		this.empEmail = empEmail;
		this.empPhoneNo = empPhoneNo;
		this.empSal = empSal;
		this.empDesg = empDesg;
	}

	@Column(length=30,nullable=false)
	private String empName;
	
	private String empSirname;
	
	private String empEmail;
	
	
	private long empPhoneNo;
	
	private float empSal;
	
	private String empDesg;
	

}
