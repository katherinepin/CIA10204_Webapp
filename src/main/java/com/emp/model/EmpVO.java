package com.emp.model;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class EmpVO implements java.io.Serializable{

	private Integer empId;
	private String empName;
	private String empAccount;
	private String empPassword;
	private String empPhone;
	private String empAddress;
	private String empEmail;
	private Date empHiredate;
	private Byte empStatus;
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