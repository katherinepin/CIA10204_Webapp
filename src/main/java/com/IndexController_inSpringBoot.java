package com;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.assign.model.AssignService;
import com.assign.model.AssignVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.leave.model.LeaveService;
import com.leave.model.LeaveVO;



//@PropertySource("classpath:application.properties") // 於https://start.spring.io建立Spring Boot專案時, application.properties文件預設已經放在我們的src/main/resources 目錄中，它會被自動檢測到
@Controller
public class IndexController_inSpringBoot {
	
	// @Autowired (●自動裝配)(Spring ORM 課程)
	// 目前自動裝配了EmpService --> 供第66使用
	@Autowired
	EmpService empSvc;
	
	@Autowired
	AssignService assignSvc;
	
	@Autowired
	LeaveService leaveSvc;
	
    // inject(注入資料) via application.properties
    @Value("${welcome.message}")
    private String message;
	
    private List<String> myList = Arrays.asList("Spring Boot Quickstart 官網 : https://start.spring.io", "IDE 開發工具", "直接使用(匯入)官方的 Maven Spring-Boot-demo Project + pom.xml", "直接使用官方現成的 @SpringBootApplication + SpringBootServletInitializer 組態檔", "依賴注入(DI) HikariDataSource (官方建議的連線池)", "Thymeleaf", "Java WebApp (<font color=red>快速完成 Spring Boot Web MVC</font>)");
    @GetMapping("/")
    public String index(Model model) {
    	model.addAttribute("message", message);
        model.addAttribute("myList", myList);
        return "index"; //view
    }
    
    // http://......../hello?name=peter1
    @GetMapping("/hello")
    public String indexWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("message", name);
        return "index"; //view
    }
    
  
    //======================== assign畫面 ========================
    @GetMapping("/assign/select_page")
	public String select_page(Model model) {
		return "back-end/assign/select_page";
	}
    
    @GetMapping("/assign/listAllAssign")
	public String listAllAssign(Model model) {
		return "back-end/assign/listAllAssign";
	}
    
    
    @ModelAttribute("assignListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
	protected List<AssignVO> referenceListData(Model model) {
		
    	List<AssignVO> list = assignSvc.getAll();
    	
		return list;
	}
  //======================== Leave畫面 ===========================
    @GetMapping("/leave/select_page")
	public String select_page1(Model model) {
		return "back-end/leave/select_page";
	}
    
    @GetMapping("/leave/listAllLeave")
	public String listAllLeave(Model model) {
		return "back-end/leave/listAllLeave";
	}
    @GetMapping("/leave/listAllLeaveforEmp")
	public String listAllLeaveforEmp(Model model) {
		return "back-end/leave/listAllLeaveforEmp";
	}
    
    @ModelAttribute("leaveListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
	protected List<LeaveVO> referenceListData1(Model model) {
		
    	List<LeaveVO> list = leaveSvc.getAll();
    	
		return list;
	}
    //======================== emp畫面 =========================== 
    @GetMapping("/emp/select_page")
	public String select_page2(Model model) {
		return "back-end/emp/select_page";
	}
    
    @GetMapping("/emp/listAllEmp")
	public String listAllEmp(Model model) {
		return "back-end/emp/listAllEmp";
	}
    
    
	@ModelAttribute("empListData") // for select_page.html 第135行用 empId用
	protected List<EmpVO> referenceListData_emp(Model model) {
		model.addAttribute("empVO", new EmpVO()); // for select_page.html 第133行用
		List<EmpVO> list = empSvc.getAll();
		return list;
	}
	
	

}