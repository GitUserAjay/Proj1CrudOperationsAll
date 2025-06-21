package com.ajay.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ajay.entity.Employee;
import com.ajay.entity.NameAndEmailProjection;
import com.ajay.entity.ResultView;
import com.ajay.entity.ResultView2;

public interface IEmployeeService {

	public String insertEmployee(Employee emp);
	
	public List<Employee> insertAllEmployees(List<Employee> emp) ;
	
	//** retrival operations
	public Employee getEmployeeById(int id);
	public List<Employee> getEmployeesByNames(List<String> namelist);
	public List<Employee> getAllEmployeesSorted(boolean asc,String...properties);
	public Page<Employee> getEmpPage(int pageno,int size,boolean asc,String... Properties);
	public ResultView getNameAndSalById(int id);
	public ResultView2 getEmailAndPhone(String name);
	
	public List<String> getAllEmployeeName();
	public List<NameAndEmailProjection> getEmpNameAndEmailByIds(Integer id1,Integer id2);
	public List<ResultView> getAllNameAndSal();
	
	public String updateEmailByName(String newEmail,String name);
	
	public String updateSalById(float sal,int id);
	
   public Employee updateDetails(Employee emp,int id);
   
   //delete
   
   public int deleteEmployeeByName(String name);
   public int deleteEmployeesByNames(List<String> names);
   public int deleteEmplyeesSalLesserThan(float sal);
   public int deleteEmployeeDesgEquals(String desg);

}
