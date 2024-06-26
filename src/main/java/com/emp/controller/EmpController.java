package com.emp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.emp.model.EmpVO;
import com.emp.model.EmpService;

@Controller
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	EmpService empSvc;


	/*
	 * This method will serve as addEmp.html handler.
	 */
	@GetMapping("addEmp")
	public String addEmp(ModelMap model) {
		EmpVO empVO = new EmpVO();
		model.addAttribute("empVO", empVO);
		return "back-end/emp/addEmp";
	}

	/*
	 * This method will be called on addEmp.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("insert")
	public String insert(@Valid EmpVO empVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行

		if (result.hasErrors()) {
			return "back-end/emp/addEmp";
		}
		/*************************** 2.開始新增資料 *****************************************/
		// EmpService empSvc = new EmpService();
		empSvc.addEmp(empVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<EmpVO> list = empSvc.getAll();
		model.addAttribute("empListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/emp/listAllEmp"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("empId") String empId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		EmpVO empVO = empSvc.getOneEmp(Integer.valueOf(empId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("empVO", empVO);
		return "back-end/emp/update_emp_input"; // 查詢完成後轉交update_emp_input.html
	}

	/*
	 * This method will be called on update_emp_input.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid EmpVO empVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			return "back-end/emp/update_emp_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		// EmpService empSvc = new EmpService();
		empSvc.updateEmp(empVO);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		empVO = empSvc.getOneEmp(Integer.valueOf(empVO.getEmpId()));
		model.addAttribute("empVO", empVO);
		return "back-end/emp/listOneEmp"; // 修改成功後轉交listOneEmp.html
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("empId") String empId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		// EmpService empSvc = new EmpService();
		empSvc.deleteEmp(Integer.valueOf(empId));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<EmpVO> list = empSvc.getAll();
		model.addAttribute("empListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "back-end/emp/listAllEmp"; // 刪除完成後轉交listAllEmp.html
	}



	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 */
//	@PostMapping("listEmps_ByCompositeQuery")
//	public String listAllEmp(HttpServletRequest req, Model model) {
//		Map<String, String[]> map = req.getParameterMap();
//		List<EmpVO> list = empSvc.getAll(map);
//		model.addAttribute("empListData", list); // for listAllEmp.html 第85行用
//		return "back-end/emp/listAllEmp";
//	}

}