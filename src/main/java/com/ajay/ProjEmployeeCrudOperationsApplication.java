package com.ajay;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ajay.controller.EmployeeController;
import com.ajay.entity.Employee;
import com.ajay.entity.NameAndEmailProjection;
import com.ajay.entity.ResultView;
import com.ajay.entity.ResultView2;

@SpringBootApplication
public class ProjEmployeeCrudOperationsApplication {

	public static void main(String[] args) {
		ApplicationContext ctxt=SpringApplication.run(ProjEmployeeCrudOperationsApplication.class, args);
		EmployeeController controller=ctxt.getBean("controller",EmployeeController.class);
		
	
		
		System.out.println("******************************* save operation************************************************");
//		Employee emp=new Employee("krishna","yadav","Krishna@gamil.com",75765777777L,1200000,"hr");
//		String res=controller.insertEmployee(emp);
//		System.out.println(res);
//		
//		Employee emp1=new Employee("sudama","onkar","sudama@gamil.com",75765777777L,1200000,"developer");
//		Employee emp2=new Employee("prasad","Bhoyar","prasad@gamil.com",7456744543L,1800000,"hr");
//		Employee emp3=new Employee("Darshan","tone","@Darshangamil.com",7342776666L,100000,"tester");
//		
//		String result=controller.insertAllEmployee(List.of(emp1,emp2,emp3));
//		System.out.println(result);
		
		System.out.println("************************************Read Operation*************************************************************");
		//getEmployee(id);
		//getEmployeeByName(List<string>); return List<Employee>
		//getEmployeesSorted(asc/desc,property)  //return list<Employee>
		//getEmployeePage(int pno(start from 1) ,int size,asc/desc, property)
		//getNameAndSalById(int id);  //partial retrival resultview
		//getNameById(int id);      //return String 
		//getAllNameAndSal();  //partial return List<ResultView>
		//getAllNames()  ;  //List<String>
		
		Employee emp=controller.getEmployeeById(102);
		
		System.out.println("**********employee with name=ajay,ram,krishna**********");
		List<String> names=List.of("ajay","ram","krishna");
		List<Employee> emplist=controller.getEmployeesByNames(names);
		emplist.forEach(emps->System.out.println(emps));
		
		System.out.println("**********Sorted By sal*******************");
		List<Employee> emplist1=controller.getAllEmployeeSorted(false, "empSal","empName");
		emplist1.forEach(empss->System.out.println(empss));
		
		System.out.println("************page 1 of size 3 sorted by empsal,empname ");
		List<Employee> emplist2=controller.getEmployeePage(2, 3, false, "empSal","empName");
		emplist2.forEach(empsss->System.out.println(empsss));
		
		System.out.println("***************** Partial Retrival");
		ResultView view=controller.getEmpNameAndSalById(2);
		System.out.println(view.getEmpName()+"-->"+view.getEmpSal());
		
	    ResultView2 view2=controller.getEmpEmailAndPhone("sudama");
	    System.out.println(view2.getEmpEmail()+"--->"+view2.getEmpPhoneNo());
	    
	    System.out.println("Names only******************");
	    List<String> AllEmpNames=controller.findAllNames();
	    AllEmpNames.forEach(st->System.out.println(st));
		
	    List<NameAndEmailProjection> lst=controller.getNameAndEmailByIds(52,153);
	    for(NameAndEmailProjection obj:lst) {
	    	String name=obj.getEmpName();
	    	String email= obj.getEmpEmail();
	    	System.out.println(name+"-->"+email);
	    }
	    
	    System.out.println("****get all names and sal");
	    List<ResultView> ls=controller.getAllNameAndSal();
	   for(ResultView rs:ls) {
		   System.out.println(rs.getEmpName()+"-->"+rs.getEmpSal());
	   }
		
	   
	   
	   //***************Update operations
	   //updateEmployeeEmailByname
	   //UpdateEmployeeSalById
	   //updateWholeDetailsById()
	   System.out.println("*****************************update operations *************************************************************");

	   String result=controller.updateEmailByName("bhartiaj47@gmail.com","jay");
	   System.out.println(result);
	   
	   String result1=controller.updateSalById(3450000,103);
	   System.out.println(result1);
	   
	   Employee emp2=new Employee("ram","raghuvanshi","ramRRR@gmail.com",7777777773l,4300000.f,"developer");
	   String result2=controller.updateDetails(emp2, 2);
	   System.out.println(result2);
	   
	   
	   System.out.println("****************************************Delete operations****************************************************");
	   String res=controller.deleteEmployeeByName("Darshan");
	   System.out.println(res);
	   
	   String res2=controller.deleteEmployeesByNames(List.of("sudama","Dashrath"));
	   System.out.println(res2);
	   
	   String res3=controller.deleteEmplyeesSalLesserThan(1000001);
	   System.out.println(res3);
	   
	   String res4=controller.deleteEmployeeDesgEquals("tester");
	   System.out.println(res4);
	    
		
	}

}
