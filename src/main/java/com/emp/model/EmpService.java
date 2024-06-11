package com.emp.model;

import java.util.List;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
	}


	public EmpVO addEmp(String empName, String empAccount, String empPassword, String empPhone,
			String empAddress, String empEmail, java.sql.Date empHiredate) {
		
		EmpVO empVO = new EmpVO();

		empVO.setEmpName(empName);
		empVO.setEmpAccount(empAccount);
		empVO.setEmpPassword(empPassword);
		empVO.setEmpPhone(empPhone);
		empVO.setEmpAddress(empAddress);
		empVO.setEmpEmail(empEmail);
		empVO.setEmpHiredate(empHiredate);
//		empVO.setEmpStatus(empStatus);
//		empVO.setEmpPhoto(empPhoto);
		
		dao.insert(empVO);

		return empVO;
	}
	
	public EmpVO updateEmp(Integer empId, String empName, String empAccount, String empPassword, String empPhone,
			String empAddress, String empEmail, java.sql.Date empHiredate,Byte empStatus) {
		EmpVO empVO = new EmpVO();

		empVO.setEmpId(empId);
		empVO.setEmpName(empName);
		empVO.setEmpAccount(empAccount);
		empVO.setEmpPassword(empPassword);
		empVO.setEmpPhone(empPhone);
		empVO.setEmpAddress(empAddress);
		empVO.setEmpEmail(empEmail);
		empVO.setEmpHiredate(empHiredate);
		empVO.setEmpStatus(empStatus);
//		empVO.setEmpPhoto(empPhoto);
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(Integer empId) {
		dao.delete(empId);
	}

	public EmpVO getOneEmp(Integer empId) {
		return dao.findByPrimaryKey(empId);
	}

	public List<EmpVO> getAll() {

		return dao.getAll();
	}

}
