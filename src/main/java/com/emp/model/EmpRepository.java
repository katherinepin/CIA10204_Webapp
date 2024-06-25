// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.emp.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EmpRepository extends JpaRepository<EmpVO, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from employee where emp_id =?1", nativeQuery = true)
	void deleteByEmpId(int empId);

	//● (自訂)條件查詢
	@Query(value = "from EmpVO where emp_id=?1 and emp_name like?2 and emp_hiredate=?3 order by emp_id")
	List<EmpVO> findByOthers(int empId , String empName , java.sql.Date empHiredate);
}