package com.emp.model;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class EmpVO implements java.io.Serializable{
	@Id
	@Column(name = "emp_id", updatable = false)
	private Integer empId;
	
	@Column(name = "emp_name")
	private String empName;
	
	@Column(name = "emp_account")
	private String empAccount;
	
	@Column(name = "emp_password")
	private String empPassword;
	
	@Column(name = "emp_phone")
	private String empPhone;
	
	@Column(name = "emp_address")
	private String empAddress;
	
	@Column(name = "emp_email")
	private String empEmail;
	
	@Column(name = "emp_hiredate")
	private Date empHiredate;
	
	@Column(name = "emp_status")
	private Byte empStatus;
	
	@Column(name = "emp_photo")
	private byte[] empPhoto;
	
	

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
	public byte[] getEmpPhoto() {
		return empPhoto;
	}
	public void setEmpPhoto(byte[] empPhoto) {
		this.empPhoto = empPhoto;
	}
	
	
}