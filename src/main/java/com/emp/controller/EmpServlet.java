package com.emp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class EmpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("empId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer empId = null;
				try {
					empId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empId);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer empId = Integer.valueOf(req.getParameter("empId"));
				
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empId);	
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
Integer empId = Integer.valueOf(req.getParameter("empId").trim());
				
String empName = req.getParameter("empName");
				String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!empName.trim().matches(empNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
String empAccount = req.getParameter("empAccount").trim();
				if (empAccount == null || empAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}	
String empPassword = req.getParameter("empPassword");
				String empPasswordReg = "^[(a-zA-Z0-9_)]{6,10}$";
				if (empPassword == null || empPassword.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if(!empPassword.trim().matches(empPasswordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 須為英文字母、數字和_ , 且長度必需在6到10之間");
	            }
String empPhone = req.getParameter("empPhone");
				String empPhoneReg = "^09\\d{8}$";
				if (empPhone == null || empPhone.trim().length() == 0) {
					errorMsgs.add("電話: 請勿空白");
				} else if(!empPhone.trim().matches(empPhoneReg)) { 
					errorMsgs.add("電話: 須為09開頭, 且總長度必需是10");
	            }
				
String empAddress = req.getParameter("empAddress").trim();
				if (empAddress == null || empAddress.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

					
String empEmail = req.getParameter("empEmail");
				String empEmailReg = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
				if (empEmail == null || empEmail.trim().length() == 0) {
					errorMsgs.add("電子郵件: 請勿空白");
				} else if(!empEmail.trim().matches(empEmailReg)) { 
					errorMsgs.add("電子郵件: 格式不正確");
			    }
				
				
				java.sql.Date empHiredate = null;
				try {
empHiredate = java.sql.Date.valueOf(req.getParameter("empHiredate").trim());
				} catch (IllegalArgumentException e) {
					empHiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
Byte empStatus = Byte.valueOf(req.getParameter("empStatus").trim());
				
//InputStream in = req.getPart("empPhoto").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
//				byte[]  empPhoto= null;
//				if(in.available()!=0){
//					empPhoto = new byte[in.available()];
//					in.read(empPhoto);
//					in.close();
//				}  else {
//					EmpService empSvc = new EmpService();
//					empPhoto = empSvc.getOneEmp(empId).getEmpPhoto();
//				}


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
//					empVO.setEmpPhoto(empPhoto);
					
					


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(empId, empName, empAccount, empPassword, empPhone, empAddress, empEmail, empHiredate, empStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
String empName = req.getParameter("empName");
				String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!empName.trim().matches(empNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

				

String empAccount = req.getParameter("empAccount");
				String empAccounteReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (empAccount == null || empAccount.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!empAccount.trim().matches(empAccounteReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
	            }				
				
				
				
String empPassword = req.getParameter("empPassword");
				String empPasswordReg = "^[(a-zA-Z0-9_)]{6,10}$";
				if (empPassword == null || empPassword.trim().length() == 0) {
					errorMsgs.add("員工密碼: 請勿空白");
				} else if(!empPassword.trim().matches(empPasswordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼: 須為英文字母、數字和_ , 且長度必需在6到10之間");
	            }
String empPhone = req.getParameter("empPhone");
				String empPhoneReg = "^09\\d{8}$";
				if (empPhone == null || empPhone.trim().length() == 0) {
					errorMsgs.add("員工電話: 請勿空白");
				} else if(!empPhone.trim().matches(empPhoneReg)) { 
					errorMsgs.add("員工電話: 須為09開頭, 且總長度必需是10");
	            }
				
String empAddress = req.getParameter("empAddress").trim();
				if (empAddress == null || empAddress.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

					
String empEmail = req.getParameter("empEmail");
				String empEmailReg = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
				if (empEmail == null || empEmail.trim().length() == 0) {
					errorMsgs.add("電子郵件: 請勿空白");
				} else if(!empEmail.trim().matches(empEmailReg)) { 
					errorMsgs.add("電子郵件: 格式不正確");
			    }
				
				
				java.sql.Date empHiredate = null;
				try {
empHiredate = java.sql.Date.valueOf(req.getParameter("empHiredate").trim());
				} catch (IllegalArgumentException e) {
					empHiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}	
//Byte empStatus = Byte.valueOf(req.getParameter("empStatus").trim());


//InputStream in = req.getPart("emp_photo").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
//				byte[] empPhoto= null;
//				if(in.available()!=0){
//					empPhoto = new byte[in.available()];
//					in.read(empPhoto);
//					in.close();
//				}  else errorMsgs.add("員工照片: 請上傳照片");
				

				EmpVO empVO = new EmpVO();

				empVO.setEmpName(empName);
				empVO.setEmpAccount(empAccount);
				empVO.setEmpPassword(empPassword);
				empVO.setEmpPhone(empPhone);
				empVO.setEmpAddress(empAddress);
				empVO.setEmpEmail(empEmail);
				empVO.setEmpHiredate(empHiredate);
//				empVO.setEmpStatus(empStatus);

//				empVO.setEmpPhoto(empPhoto);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
						req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(empName, empAccount, empPassword, empPhone, empAddress, empEmail, empHiredate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer empId = Integer.valueOf(req.getParameter("empId"));
				
				/***************************2.開始刪除資料***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(empId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
