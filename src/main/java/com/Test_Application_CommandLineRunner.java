package com;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.assign.model.AssignRepository;
import com.emp.model.EmpRepository;
import com.emp.model.EmpVO;
import com.leave.model.LeaveRepository;
import com.leave.model.LeaveVO;

@SpringBootApplication
public class Test_Application_CommandLineRunner implements CommandLineRunner {
 
	@Autowired
	LeaveRepository repository ;
	
	@Autowired
	EmpRepository repository2 ;
	
	@Autowired
	AssignRepository repository3 ;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public static void main(String[] args) {
        SpringApplication.run(Test_Application_CommandLineRunner.class);
    }
//=============================== leave ===================================
	@Override
	public void run(String...args) throws Exception {

    	//● 新增	
    	EmpVO empVO = new EmpVO();
    	empVO.setEmpId(2); 
    	EmpVO assignee = new EmpVO();
    	assignee.setEmpId(1);
    	
    	LeaveVO leaveVO = new LeaveVO();
		leaveVO.setLeaveEmpId(empVO);
    	leaveVO.setLeaveDate(java.sql.Date.valueOf("2024-01-02"));
    	leaveVO.setLeaveStatus(Byte.valueOf("0"));
    	leaveVO.setLeaveApprovaldate(java.sql.Timestamp.valueOf("2024-01-03 00:00:00"));
    	leaveVO.setLeaveAssigneeId(assignee);
    	
		repository.save(leaveVO);
		
		//● 修改
//		LeaveVO leaveVO2 = new LeaveVO();
//		
//    	leaveVO2.setLeaveId(2);
//    	leaveVO2.setLeaveEmpId(empVO);
//    	leaveVO2.setLeaveDate(java.sql.Date.valueOf("2024-01-03"));
//    	leaveVO2.setLeaveStatus(Byte.valueOf("0"));
//    	leaveVO2.setLeaveApprovaldate(java.sql.Timestamp.valueOf("2024-01-03 00:00:00"));
//    	leaveVO2.setLeaveAssigneeId(assignee);
//
//		repository.save(leaveVO2);
    	
		//● 刪除   //●●● --> EmployeeRepository.java 內自訂的刪除方法
//		repository.deleteByLeaveId(6);

    	//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//		System.out.println("\n---------------------");
//    	Optional<LeaveVO> optional = repository.findById(1);
//    	LeaveVO leaveVO3 = optional.get();
//    	
//		System.out.print(leaveVO3.getLeaveEmpId().getEmpId() + ",");
//		System.out.print(leaveVO3.getLeaveFilingdate() + ",");
//		System.out.print(leaveVO3.getLeaveDate() + ",");
//		System.out.print(leaveVO3.getLeaveStatus() + ",");
//		System.out.print(leaveVO3.getLeaveApprovaldate() + ",");
//		System.out.print(leaveVO3.getLeaveAssigneeId().getEmpId() + ",");
//	
//		System.out.println("\n---------------------");
//		
//		// 注意以下三行的寫法 (優!)
//		System.out.print(leaveVO3.getLeaveEmpId().getEmpId() + ",");
//		System.out.print(leaveVO3.getLeaveEmpId().getEmpName() + ",");
//		System.out.print(leaveVO3.getLeaveAssigneeId().getEmpId() + ",");
//		System.out.print(leaveVO3.getLeaveAssigneeId().getEmpName() + ",");
//		
//		System.out.println("\n---------------------");
      
    	
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<LeaveVO> list = repository.findAll();
//		for (LeaveVO aLeave : list) {
//			System.out.print(aLeave.getLeaveEmpId().getEmpId() + ",");
//			System.out.print(aLeave.getLeaveFilingdate() + ",");
//			System.out.print(aLeave.getLeaveDate() + ",");
//			System.out.print(aLeave.getLeaveStatus() + ",");
//			System.out.print(aLeave.getLeaveApprovaldate() + ",");
//			System.out.print(aLeave.getLeaveAssigneeId().getEmpId() + ",");
//
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aLeave.getLeaveEmpId().getEmpId() + ",");
//			System.out.print(aLeave.getLeaveEmpId().getEmpName() + ",");
//			System.out.print(aLeave.getLeaveAssigneeId().getEmpId() + ",");
//			System.out.print(aLeave.getLeaveAssigneeId().getEmpName() + ",");
//			System.out.println();
//		}
//=============================== emp ===================================
//	@Override
//	public void run(String...args) throws Exception {
//
//    	//● 新增
//    	EmpVO empVO = new EmpVO();
//
//		empVO.setEmpName("Katherine");
//		empVO.setEmpAccount("katherine");
//		empVO.setEmpPassword("333333");
//		empVO.setEmpPhone("0933333333");
//		empVO.setEmpAddress("苗栗市");
//		empVO.setEmpEmail("cccc@ccccc.com");
//		empVO.setEmpHiredate(java.sql.Date.valueOf("1961-01-01"));
//		repository2.save(empVO);
    	
		//● 修改
//		AssignVO assignVO2 = new AssignVO();
//    	EmpVO empVO = new EmpVO();    //emp的fk
//    	empVO.setEmpId(3);
//		
//		assignVO2.setAssignId(1);
//		assignVO2.setAssignDate(java.sql.Date.valueOf("2024-07-06"));
//		assignVO2.setEmployee(empVO);
//
//		repository.save(assignVO2);
		
		//● 刪除   //●●● --> EmployeeRepository.java 內自訂的刪除方法
//		repository.deleteByAssignId(6);
		

    	//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	Optional<AssignVO> optional = repository.findById(1);
//    	AssignVO empVO3 = optional.get();
//		System.out.print(empVO3.getAssignId() + ",");
//		System.out.print(empVO3.getAssignDate() + ",");
//		System.out.print(empVO3.getEmployee().getEmpId() + ",");
//		System.out.println("\n---------------------");
		
//		// 注意以下三行的寫法 (優!)
//		System.out.print(empVO3.getDeptVO().getDeptno() + ",");
//		System.out.print(empVO3.getDeptVO().getDname() + ",");
//		System.out.print(empVO3.getDeptVO().getLoc());
//		System.out.println("\n---------------------");

    	
	
//=============================== assign ===================================	
//    @Override
//    public void run(String...args) throws Exception {
//
//    	//● 新增
//    	EmpVO empVO = new EmpVO();
//    	empVO.setEmpId(1);
//		AssignVO assignVO1 = new AssignVO();
//		
//    	
//		assignVO1.setAssignDate(java.sql.Date.valueOf("2024-07-06"));
//		assignVO1.setEmpVO(empVO);
//
//		repository3.save(assignVO1);

		//● 修改
//		AssignVO assignVO2 = new AssignVO();
//    	EmpVO empVO = new EmpVO();    //emp的fk
//    	empVO.setEmpId(3);
//		
//		assignVO2.setAssignId(1);
//		assignVO2.setAssignDate(java.sql.Date.valueOf("2024-07-06"));
//		assignVO2.setEmployee(empVO);
//
//		repository.save(assignVO2);
		
		//● 刪除   //●●● --> EmployeeRepository.java 內自訂的刪除方法
//		repository.deleteByAssignId(6);
		
		//● 刪除   //XXX --> Repository內建的刪除方法目前無法使用，因為有@ManyToOne
		//System.out.println("--------------------------------");
		//repository.deleteById(7001);      
		//System.out.println("--------------------------------");

    	//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	Optional<AssignVO> optional = repository.findById(1);
//    	AssignVO empVO3 = optional.get();
//		System.out.print(empVO3.getAssignId() + ",");
//		System.out.print(empVO3.getAssignDate() + ",");
//		System.out.print(empVO3.getEmployee().getEmpId() + ",");
//		System.out.println("\n---------------------");
		
//		// 注意以下三行的寫法 (優!)
//		System.out.print(empVO3.getDeptVO().getDeptno() + ",");
//		System.out.print(empVO3.getDeptVO().getDname() + ",");
//		System.out.print(empVO3.getDeptVO().getLoc());
//		System.out.println("\n---------------------");
      
    	
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<EmpVO> list = repository.findAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}


		//● 複合查詢-getAll(map) 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("empno", new String[] { "7001" });
//		map.put("ename", new String[] { "KING" });
//		map.put("job", new String[] { "PRESIDENT" });
//		map.put("hiredate", new String[] { "1981-11-17" });
//		map.put("sal", new String[] { "5000.5" });
//		map.put("comm", new String[] { "0.0" });
//		map.put("deptno", new String[] { "1" });
//		
//		List<EmpVO> list2 = HibernateUtil_CompositeQuery_Emp3.getAllC(map,sessionFactory.openSession());
//		for (EmpVO aEmp : list2) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}
		

		//● (自訂)條件查詢
//		List<EmpVO> list3 = repository.findByOthers(7001,"%K%",java.sql.Date.valueOf("1981-11-17"));
//		if(!list3.isEmpty()) {
//			for (EmpVO aEmp : list3) {
//				System.out.print(aEmp.getEmpno() + ",");
//				System.out.print(aEmp.getEname() + ",");
//				System.out.print(aEmp.getJob() + ",");
//				System.out.print(aEmp.getHiredate() + ",");
//				System.out.print(aEmp.getSal() + ",");
//				System.out.print(aEmp.getComm() + ",");
//				// 注意以下三行的寫法 (優!)
//				System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//				System.out.print(aEmp.getDeptVO().getDname() + ",");
//				System.out.print(aEmp.getDeptVO().getLoc());
//				System.out.println();
//			}
//		} else System.out.print("查無資料\n");
//		System.out.println("--------------------------------");

    }

}
