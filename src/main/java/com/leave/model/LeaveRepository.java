// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.leave.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface LeaveRepository extends JpaRepository<LeaveVO, Integer> {

	//刪除
	@Transactional
	@Modifying
	@Query(value = "delete from `leave` where leave_id =?1", nativeQuery = true)
	void deleteByLeaveId(int leaveId);
	
	// 查詢所有請假申請並按申請時間降序排序
    @Query("SELECT l FROM LeaveVO l ORDER BY l.leaveFilingdate DESC")
    List<LeaveVO> findAllOrderByLeaveFilingdateDesc();



//	//● (自訂)條件查詢
//	@Query(value = "from LeaveVO where leave_id=?1 and emp_hiredate=?2 order by Assign_id")
//	List<LeaveVO> findByOthers(int empId , java.sql.Date empHiredate);
	
	
	
}