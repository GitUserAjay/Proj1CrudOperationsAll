package com.ajay.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ajay.entity.Employee;
import com.ajay.entity.NameAndEmailProjection;
import com.ajay.entity.ResultView;
import com.ajay.entity.ResultView2;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	public List<Employee> findByEmpNameIn(List<String> nameList);
	
	public  ResultView findByEmpIdEquals(Integer id);
	
	public ResultView2 findByEmpNameEquals(String name);
	
	
	@Query("select empName from Employee")
	public List<String> findAllEmployeeName();
	
	@Query("SELECT empName AS empName, empEmail AS empEmail FROM Employee WHERE empId IN(?1,?2)")
	public List<NameAndEmailProjection> findEmpNameAndEmailById(Integer id1, Integer id2);
	
	@Query("select empName AS empName,empSal AS empSal from Employee")
	public List<ResultView> getAllEmpNameAndSal();
	
	@Query("update Employee e set e.empEmail=?1 where e.empName=?2")
	@Modifying
	@Transactional
	public int updateEmailByName(String email,String name);
	
	@Query("update Employee e set e.empSal=:sal where e.empId=:id")
	@Modifying
	@Transactional
	public int updateSalById(@Param("sal")float sal,@Param("id")Integer id);
	
	@Query("delete from Employee where empName=:empName")
	@Modifying
	@Transactional
	public int deleteEmpByName(@Param("empName") String empName);
	
	@Query("delete from Employee where empName In:empNameList")
	@Modifying
	@Transactional
	public int deleteEmpsByNames(@Param("empNameList")List<String> empNames);
	
	@Query("delete from Employee where empSal<:empSal")
	@Modifying
	@Transactional
	public int deleteEmpsSalLessThan(@Param("empSal")float salary);
	
	@Query("delete from Employee where empDesg=:empDesg")
	@Modifying
	@Transactional
	public int deleteEmpsDesgEquals(@Param("empDesg")String desg);
	
	
	
	
	
	

}
