package com.emp.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpDAO implements EmpDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO employee (emp_name, emp_account, emp_password, emp_phone, emp_address, emp_email, emp_hiredate) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM employee order by emp_id";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM employee where emp_id = ?";
	private static final String DELETE = 
		"DELETE FROM employee where emp_id = ?";
	private static final String UPDATE = 
		"UPDATE employee set emp_name=?, emp_account=?, emp_password=?, emp_phone=?, emp_address=?, emp_email=?, emp_hiredate=?,emp_status=? where emp_id = ?";

	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmpName());
			pstmt.setString(2, empVO.getEmpAccount());
			pstmt.setString(3, empVO.getEmpPassword());
			pstmt.setString(4, empVO.getEmpPhone());
			pstmt.setString(5, empVO.getEmpAddress());
			pstmt.setString(6, empVO.getEmpEmail());
			pstmt.setDate(7, empVO.getEmpHiredate());
//			pstmt.setBytes(9, empVO.getEmpPhoto());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmpName());
			pstmt.setString(2, empVO.getEmpAccount());
			pstmt.setString(3, empVO.getEmpPassword());
			pstmt.setString(4, empVO.getEmpPhone());
			pstmt.setString(5, empVO.getEmpAddress());
			pstmt.setString(6, empVO.getEmpEmail());
			pstmt.setDate(7, empVO.getEmpHiredate());
			pstmt.setByte(8, empVO.getEmpStatus());
//			pstmt.setBytes(9, empVO.getEmpPhoto());
			pstmt.setInt(9, empVO.getEmpId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public EmpVO findByPrimaryKey(Integer empno) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("emp_id"));
				empVO.setEmpName(rs.getString("emp_name"));
				empVO.setEmpAccount(rs.getString("emp_account"));
				empVO.setEmpPassword(rs.getString("emp_password"));
				empVO.setEmpPhone(rs.getString("emp_phone"));
				empVO.setEmpAddress(rs.getString("emp_address"));
				empVO.setEmpEmail(rs.getString("emp_email"));
				empVO.setEmpHiredate(rs.getDate("emp_hiredate"));
				empVO.setEmpStatus(rs.getByte("emp_status"));
//				empVO.setEmpPhoto(rs.getBytes("emp_photo"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("emp_id"));
				empVO.setEmpName(rs.getString("emp_name"));
				empVO.setEmpAccount(rs.getString("emp_account"));
				empVO.setEmpPassword(rs.getString("emp_password"));
				empVO.setEmpPhone(rs.getString("emp_phone"));
				empVO.setEmpAddress(rs.getString("emp_address"));
				empVO.setEmpEmail(rs.getString("emp_email"));				
				empVO.setEmpHiredate(rs.getDate("emp_hiredate"));
				empVO.setEmpStatus(rs.getByte("emp_status"));
				list.add(empVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}