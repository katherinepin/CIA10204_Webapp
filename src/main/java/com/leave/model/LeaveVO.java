package com.leave.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.emp.model.EmpVO;

@Entity
@Table(name="`leave`")
public class LeaveVO {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="leave_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer leaveId;
	
	@Column(name="leave_filingdate", updatable = false)
	private Timestamp leaveFilingdate;
	
	@ManyToOne
	@JoinColumn(name="emp_id")
	private EmpVO leaveEmpId;
	
	@Column(name="leave_Date")
	private Date leaveDate;
	
	@Column(name="leave_status")
	private Byte leaveStatus = 0;
	
	@Column(name="leave_approvaldate")
	private Timestamp leaveApprovaldate;
	
	@ManyToOne
	@JoinColumn(name="assignee_id")
	private EmpVO leaveAssigneeId;
	
	public LeaveVO() {
        this.leaveFilingdate = new Timestamp(System.currentTimeMillis());
        this.leaveApprovaldate = new Timestamp(System.currentTimeMillis());
    }

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public Timestamp getLeaveFilingdate() {
		return leaveFilingdate;
	}

	public void setLeaveFilingdate(Timestamp leaveFilingdate) {
		this.leaveFilingdate = leaveFilingdate;
	}



	public EmpVO getLeaveEmpId() {
		return leaveEmpId;
	}

	public void setLeaveEmpId(EmpVO leaveEmpId) {
		this.leaveEmpId = leaveEmpId;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Byte getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(Byte leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public Timestamp getLeaveApprovaldate() {
		return leaveApprovaldate;
	}

	public void setLeaveApprovaldate(Timestamp leaveApprovaldate) {
		this.leaveApprovaldate = leaveApprovaldate;
	}

	public EmpVO getLeaveAssigneeId() {
		return leaveAssigneeId;
	}

	public void setLeaveAssigneeId(EmpVO leaveAssigneeId) {
		this.leaveAssigneeId = leaveAssigneeId;
	}


	
}
