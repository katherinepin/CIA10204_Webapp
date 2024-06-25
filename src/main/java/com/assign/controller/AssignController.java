package com.assign.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assign.model.AssignService;
import com.assign.model.AssignVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;


@Controller
@RequestMapping("/assign")
public class AssignController {

	@Autowired
	AssignService assignSvc;

	@Autowired
	EmpService empSvc;

	/*
	 * This method will serve as addEmp.html handler.
	 */
	@GetMapping("addAssign")
	public String addAssign(ModelMap model) {
		AssignVO assignVO = new AssignVO();
		model.addAttribute("assignVO", assignVO);
		return "back-end/assign/addAssign";
	}

	/*
	 * This method will be called on addEmp.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("insert")
	public String insert(@Valid AssignVO assignVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			return "back-end/assign/addAssign";
		}
		/*************************** 2.開始新增資料 *****************************************/
		// EmpService assignSvc = new EmpService();
		assignSvc.addAssign(assignVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<AssignVO> list = assignSvc.getAll();
		model.addAttribute("assignListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/assign/listAllAssign"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("assignId") String assignId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
	

		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService assignSvc = new EmpService();
		AssignVO assignVO = assignSvc.getOneAssign(Integer.valueOf(assignId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("assignVO", assignVO);
		return "back-end/assign/update_assign_input"; // 查詢完成後轉交update_emp_input.html
	}

	/*
	 * This method will be called on update_emp_input.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid AssignVO assignVO, BindingResult result, ModelMap model) throws IOException {
//
//		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		if (result.hasErrors()) {
			return "back-end/assign/update_assign_input";
		}
//		/*************************** 2.開始修改資料 *****************************************/
		// EmpService assignSvc = new EmpService();
		assignSvc.updateAssign(assignVO);

//		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		assignVO = assignSvc.getOneAssign(Integer.valueOf(assignVO.getAssignId()));
		model.addAttribute("assignVO", assignVO);
		
		return "back-end/assign/listOneAssign"; // 修改成功後轉交listOneEmp.html
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("assignId") String assignId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		// EmpService assignSvc = new EmpService();
		assignSvc.deleteAssign(Integer.valueOf(assignId));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<AssignVO> list = assignSvc.getAll();
		model.addAttribute("assignListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "back-end/assign/listAllAssign"; // 刪除完成後轉交listAllEmp.html
	}

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : 
	 * <form:select path="deptno" id="deptno" items="${deptListData}" itemValue="deptno" itemLabel="dname" />
	 */
	@ModelAttribute("empListData")
	protected List<EmpVO> referenceListData() {
		// DeptService deptSvc = new DeptService();
		List<EmpVO> list = empSvc.getAll();
		return list;
	}

	/*
	 * 【 第二種作法 】 Method used to populate the Map Data in view. 如 : 
	 * <form:select path="deptno" id="deptno" items="${depMapData}" />
	 */
//	@ModelAttribute("deptMapData") //
//	protected Map<Integer, String> referenceMapData() {
//		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
//		map.put(10, "財務部");
//		map.put(20, "研發部");
//		map.put(30, "業務部");
//		map.put(40, "生管部");
//		return map;
//	}

	// 去除BindingResult中某個欄位的FieldError紀錄
//	public BindingResult removeFieldError(AssignVO AssignVO, BindingResult result, String removedFieldname) {
//		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
//				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
//				.collect(Collectors.toList());
//		result = new BeanPropertyBindingResult(AssignVO, "AssignVO");
//		for (FieldError fieldError : errorsListToKeep) {
//			result.addError(fieldError);
//		}
//		return result;
//	}
	
	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 */
	@PostMapping("listAssigns_ByCompositeQuery")
	public String listAllAssign(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<AssignVO> list = assignSvc.getAll(map); 
		model.addAttribute("assignListData", list); // for listAllEmp.html 第85行用
		return "back-end/assign/listAllAssign";
	}

}