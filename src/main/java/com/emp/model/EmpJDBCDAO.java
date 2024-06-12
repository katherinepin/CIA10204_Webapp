package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/morningcode04?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123123dd";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(Integer empId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public EmpVO findByPrimaryKey(Integer empId) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empId);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		EmpJDBCDAO dao = new EmpJDBCDAO();

		// 新增
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEmpName("Katherine");
//		empVO1.setEmpAccount("katherine");
//		empVO1.setEmpPassword("333333");
//		empVO1.setEmpPhone("09333333333");
//		empVO1.setEmpAddress("苗栗市");
//		empVO1.setEmpEmail("cccc@ccccc.com");
//		empVO1.setEmpHiredate(java.sql.Date.valueOf("1960-01-01"));
//		dao.insert(empVO1);

		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpId(1);		
//		empVO2.setEmpName("Kathy");
//		empVO2.setEmpAccount("kathy");
//		empVO2.setEmpPassword("123456");
//		empVO2.setEmpPhone("0912345678");
//		empVO2.setEmpAddress("台北市");
//		empVO2.setEmpEmail("aaaa@aaaaa.com");
//		empVO2.setEmpHiredate(java.sql.Date.valueOf("1993-01-01"));
//		empVO2.setEmpStatus(Byte.valueOf("1"));
//		dao.update(empVO2);
//		System.out.println("OK");


		// 刪除
//		dao.delete(3);

		// 查詢
//		EmpVO empVO3 = dao.findByPrimaryKey(1);
//		System.out.print(empVO3.getEmpName() + ",");
//		System.out.print(empVO3.getEmpAccount() + ",");
//		System.out.print(empVO3.getEmpPassword() + ",");
//		System.out.print(empVO3.getEmpPhone() + ",");
//		System.out.print(empVO3.getEmpAddress() + ",");
//		System.out.print(empVO3.getEmpEmail() + ",");
//		System.out.print(empVO3.getEmpHiredate() + ",");
//		System.out.print(empVO3.getEmpStatus() + "");
//		System.out.println();
//		System.out.println("---------------------");

		// 查詢
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list) {
			System.out.print(aEmp.getEmpId() + ",");
			System.out.print(aEmp.getEmpName() + ",");
			System.out.print(aEmp.getEmpAccount() + ",");
			System.out.print(aEmp.getEmpPassword() + ",");
			System.out.print(aEmp.getEmpPhone() + ",");
			System.out.print(aEmp.getEmpAddress() + ",");
			System.out.print(aEmp.getEmpEmail() + ",");			
			System.out.print(aEmp.getEmpHiredate() + ",");
			System.out.print(aEmp.getEmpStatus() + ",");

			System.out.println();
		}
	}
}