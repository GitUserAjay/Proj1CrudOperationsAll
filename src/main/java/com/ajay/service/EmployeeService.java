package com.ajay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajay.entity.Employee;
import com.ajay.entity.NameAndEmailProjection;
import com.ajay.entity.ResultView;
import com.ajay.entity.ResultView2;
import com.ajay.repo.EmployeeRepo;


@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private EmployeeRepo repo;

	@Override
	public String insertEmployee(Employee emp) {
		Employee emp1=repo.save(emp);
		return "employee save with id:"+emp1.getEmpId();
	}

	@Override
	public List<Employee> insertAllEmployees(List<Employee> emplist) {
		Iterable<Employee> list=repo.saveAll(emplist);
		
		return (List<Employee>) list;
	}
	
	
	//*************Retrive/Read Operation
	public Employee getEmployeeById(int id) {
		Optional<Employee> opt=repo.findById(id);
	return opt.orElseThrow(()->new IllegalArgumentException("employee not found"));
}
	
	
	
	public List<Employee> getEmployeesByNames(List<String> namelist){
		return repo.findByEmpNameIn(namelist);
	}
	
	@Override
	public List<Employee> getAllEmployeesSorted(boolean asc,String...properties){
		Sort sort=Sort.by(asc?Direction.ASC:Direction.DESC,properties);
		return repo.findAll(sort);
	}

	@Override
	public Page<Employee> getEmpPage(int pageno, int size, boolean asc, String... Properties) {
		Sort sort=Sort.by(asc?Direction.ASC:Direction.DESC,Properties);
		Pageable pageable=PageRequest.of(pageno-1, size, sort);
		
		return repo.findAll(pageable);
	}

	@Override
	public ResultView getNameAndSalById(int id) {
		return repo.findByEmpIdEquals(id);
	}

	@Override
	public ResultView2 getEmailAndPhone(String name) {
		
		return repo.findByEmpNameEquals(name);
	}

	@Override
	public List<String> getAllEmployeeName() {
		
		return repo.findAllEmployeeName();
	}
	

	@Override
	public List<NameAndEmailProjection> getEmpNameAndEmailByIds(Integer id1, Integer id2){
		return repo.findEmpNameAndEmailById(id1, id2);
	}

	@Override
	public List<ResultView> getAllNameAndSal() {
		return repo.getAllEmpNameAndSal();
	}

	
	//***********update operations
	@Override
	@Transactional
	public String updateEmailByName(String newEmail, String name) {
	
		int result=repo.updateEmailByName(newEmail, name);
	if(result==0) {
		return "no employee found";
	}
	return "email updated successfully ";
	}
	
	@Transactional
	public String updateSalById(float sal,int id) {
		int res=repo.updateSalById(sal,id);
		if(res==0) {
			return "employee does not exist";
		}
		return "salary updated";
	}

	@Override
	@Transactional
	public Employee updateDetails(Employee emp, int id) {
		emp.setEmpId(id);
		Employee emp2=repo.save(emp);
		return emp2;
	}
	
	
	
	//*************delete

	@Override
	@Transactional
	public int deleteEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return repo.deleteEmpByName(name);
	}

	@Override
	public int deleteEmployeesByNames(List<String> names) {
		
		return repo.deleteEmpsByNames(names);
	}

	@Override
	public int deleteEmplyeesSalLesserThan(float sal) {
		
		return repo.deleteEmpsSalLessThan(sal);
	}

	@Override
	public int deleteEmployeeDesgEquals(String desg) {
		
		return repo.deleteEmpsDesgEquals(desg);
	}
	
	 

	
}
