package com.ajay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import com.ajay.entity.Employee;
import com.ajay.entity.NameAndEmailProjection;
import com.ajay.entity.ResultView;
import com.ajay.entity.ResultView2;
import com.ajay.service.EmployeeService;

@Controller("controller")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	//**Insert Operations
	
	public String insertEmployee(Employee emp) {
		return service.insertEmployee(emp);
		
	}
	
	public String  insertAllEmployee(List<Employee> emplist) {
		List<Employee> savedEmployees= service.insertAllEmployees(emplist);
		String result="Employees saved with Following Ids::";
		for(Employee emp:savedEmployees) {
			result=result+emp.getEmpId()+"-->"+emp.getEmpName()+" ,";
		}
		return result;
	}
	
 //****************Retrieval
	public Employee getEmployeeById(int id) {
		return service.getEmployeeById(id);
	}
	
	public List<Employee> getEmployeesByNames(List<String> names){
		return service.getEmployeesByNames(names);
	}
	
	public List<Employee> getAllEmployeeSorted(boolean asc,String...Props){
		return service.getAllEmployeesSorted(asc, Props);
	}

	public List<Employee> getEmployeePage(int pageno,int size,boolean asc,String...properties){
		Page page=service.getEmpPage(pageno, size, asc, properties);
		List<Employee> emplist=page.getContent();
		return emplist;
	}
	
	public ResultView getEmpNameAndSalById(int id) {
		return service.getNameAndSalById(id);
		
	}
	public ResultView2 getEmpEmailAndPhone(String name) {
		return service.getEmailAndPhone(name);
	}
	
	public List<String> findAllNames(){
		return service.getAllEmployeeName();
	}
	
	public List<NameAndEmailProjection> getNameAndEmailByIds(Integer id1,Integer id2){
		return service.getEmpNameAndEmailByIds(id1, id2);
	}
	
	public  List<ResultView> getAllNameAndSal(){
		return service.getAllNameAndSal();
		
	}
	
	
	//***********update operations
	public String updateEmailByName(String newEmail,String name) {
		return service.updateEmailByName(newEmail,name);
	}
	
	public String updateSalById(float sal,int id) {
		return service.updateSalById(sal,id);
	}


public String updateDetails(Employee emp,int id) {
	Employee updated=service.updateDetails(emp, id);
	return "employee updated with following details::"+updated;}
	
	//***********delete operations
	public  String deleteEmployeeByName(String name) {
		int res=service.deleteEmployeeByName(name);
		if (res==0) {
				return "employee not found";
		}
		return res+" no of emplyees deleted";
	}
	
	
	   public String deleteEmployeesByNames(List<String> names) {
		   
		   int res=service.deleteEmployeesByNames(names);
		   if (res==0) {
				return "employees not found";
		}
		return res+" no of emplyees deleted"; 
	   }
	   
	   
   public String deleteEmplyeesSalLesserThan(float sal) {
	   int res=service.deleteEmplyeesSalLesserThan(sal);
	   if(res==0) {
		   return "no employee found with salary less than::"+sal;
	   }
	   return res+" employee deleted who was under the salary::"+sal;
	   
   }
   public String deleteEmployeeDesgEquals(String desg) {
	   int res=service.deleteEmployeeDesgEquals(desg);
	   if(res==0) {
		   return "employee not found with desg";
	   }
	  
	   return res+" no of employee deleted";
   }

}


