package com.emp.model;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.assign.model.AssignVO;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="employee")
public class EmpVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="emp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;
	
	@Column(name="emp_name")
	@NotEmpty(message="員工姓名: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$", message = "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間")
	private String empName;
	
	
	@Column(name="emp_account")
	@NotEmpty(message="帳號: 請勿空白")
	private String empAccount;
	
	
	@Column(name="emp_password")
	@NotEmpty(message="密碼: 請勿空白")
	@Pattern(regexp = "^[(a-zA-Z0-9_)]{6,10}$", message = "密碼: 須為英文字母、數字和_ , 且長度必需在6到10之間")
	private String empPassword;
	
	@Column(name="emp_phone")
	@NotEmpty(message="電話: 請勿空白")
	@Pattern(regexp = "^09\\d{8}$", message = "電話: 須為09開頭, 且總長度必需是10")
	private String empPhone;
	
	@Column(name="emp_address")
	@NotEmpty(message="電話: 請勿空白")
	private String empAddress;
	
	@Column(name="emp_email")
	@NotEmpty(message="電子郵件: 請勿空白")
	@Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "電子郵件: 格式不正確")
	private String empEmail;
	
	@Column(name="emp_hiredate")
	private Date empHiredate;
	
	@Column(name="emp_status")
	private Byte empStatus;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="empVO")
	@JsonBackReference
	private Set<AssignVO> assigns = new HashSet<AssignVO>();
//	
//	@OneToMany(mappedBy="leaveEmpId")
//	private Set<LeaveVO> leaveEmpId = new HashSet<LeaveVO>();
//
//	@OneToMany(mappedBy="leaveAssignId")
//	private Set<LeaveVO> leaveAssignId = new HashSet<LeaveVO>();
	
	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpAccount() {
		return empAccount;
	}
	public void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public Date getEmpHiredate() {
		return empHiredate;
	}
	public void setEmpHiredate(Date empHiredate) {
		this.empHiredate = empHiredate;
	}
	public Byte getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(Byte empStatus) {
		this.empStatus = empStatus;
	}

	public Set<AssignVO> getAssigns() {
		return assigns;
	}

	public void setAssigns(Set<AssignVO> assigns) {
		this.assigns = assigns;
	}

	
	
}