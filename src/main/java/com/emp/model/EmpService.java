package com.emp.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assign.model.AssignVO;
import com.leave.model.LeaveVO;

import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Emp3;
@Service("empService")
public class EmpService {

	@Autowired
	EmpRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;
	
//	private EmpDAO_interface dao;
//
//	public EmpService() {
//		dao = new EmpDAO();
//	}


	public void addEmp(EmpVO empVO) {
		repository.save(empVO);
	}

	public void updateEmp(EmpVO empVO) {
		repository.save(empVO);
	}

	public void deleteEmp(Integer empId) {
		if (repository.existsById(empId))
			repository.deleteByEmpId(empId);
//		    repository.deleteById(empno);
	}

	public EmpVO getOneEmp(Integer empId) {
		Optional<EmpVO> optional = repository.findById(empId);
//		return optional.get();
		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<EmpVO> getAll() {
		return repository.findAll();
	}
	
	public Set<AssignVO> getAssignsByEmpId(Integer empId) {
		return getOneEmp(empId).getAssigns();
	}




	public EmpVO addEmp(String empName, String empAccount, String empPassword, String empPhone, String empAddress,
			String empEmail, Date empHiredate) {
		EmpVO empVO = new EmpVO();

		empVO.setEmpName(empName);
		empVO.setEmpAccount(empAccount);
		empVO.setEmpPassword(empPassword);
		empVO.setEmpPhone(empPhone);
		empVO.setEmpAddress(empAddress);
		empVO.setEmpEmail(empEmail);
		empVO.setEmpHiredate(empHiredate);

		
//		dao.insert(empVO);

		return empVO;
	}


	public EmpVO updateEmp(Integer empId, String empName, String empAccount, String empPassword, String empPhone,
			String empAddress, String empEmail, Date empHiredate, Byte empStatus) {
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
//		dao.update(empVO);

		return empVO;
	}



}
	

