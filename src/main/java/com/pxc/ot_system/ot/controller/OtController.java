package com.pxc.ot_system.ot.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pxc.ot_system.ot.model.Ot;
import com.pxc.ot_system.ot.model.Staff;
import com.pxc.ot_system.ot.security.UserInfo;
import com.pxc.ot_system.ot.service.OtService;
import com.pxc.ot_system.ot.service.StaffService;

@Controller
@RequestMapping("/user/")
public class OtController {
	
	@Autowired
	private OtService service;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private UserInfo userInfo;
	
	@ModelAttribute
	public void navBarUsername(ModelMap modelMap) {
		modelMap.put("username", userInfo.getLoggedInUsername());
	}
	
	@GetMapping("ot-entry-gen")
	public String otEntryPageGen(ModelMap modelMap) {
		List<Staff> staffs = staffService.findAllStaff();
		
		var ot = new Ot(0, 0, "", "","", "General", LocalDate.now(), 
				LocalTime.now(), LocalTime.now(), LocalTime.now());
		
		modelMap.put("staffs", staffs);
		modelMap.put("ot", ot);
		
		return "otForm";
	}
	
	@GetMapping("ot-entry-r")
	public String otEntryPageR(ModelMap modelMap) {
		List<Staff> staffs = staffService.findAllStaff();		
		var ot = new Ot(0, 0, "", "","","Ramadan", LocalDate.now(), 
				LocalTime.now(), LocalTime.now(), LocalTime.now());
		
		modelMap.put("staffs", staffs);
		modelMap.put("ot", ot);
		
		return "otForm";
	}
	
	@GetMapping("ot-entry-n")
	public String otEntryPageN(ModelMap modelMap) {
		List<Staff> staffs = staffService.findAllStaff();
		
		var ot = new Ot(0, 0, "", "","", "Non-Office Day", LocalDate.now(), 
				LocalTime.now(), LocalTime.now(), LocalTime.now());
		
		modelMap.put("staffs", staffs);
		modelMap.put("ot", ot);
		
		return "otForm";
	}
	
	@PostMapping("ot-entry")
	public String otEntrySubmit(Ot ot, RedirectAttributes ra) {
		Ot ot2 = service.findByUsernameAndOtDate(ot);
		if(ot2 != null) {
			if(ot.getOtType().equals("General")) {
				ra.addFlashAttribute("message", "Already exists! Can not create overtime entry.");
				return "redirect:ot-entry-gen";
			}
			else if(ot.getOtType().equals("Ramadan")) {
				ra.addFlashAttribute("message", "Already exists! Can not create overtime entry.");
				return "redirect:ot-entry-r";
			}
			else {
				ra.addFlashAttribute("message", "Already exists! Can not create overtime entry.");
				return "redirect:ot-entry-n";
			}
		}
		service.createOt(ot);
		
		if(ot.getOtType().equals("General")) {
			ra.addFlashAttribute("message", "Overtime added successfully.");
			return "redirect:ot-entry-gen";
		}
		else if(ot.getOtType().equals("Ramadan")) {
			ra.addFlashAttribute("message", "Overtime added successfully.");
			return "redirect:ot-entry-r";
		}
		else {
			ra.addFlashAttribute("message", "Overtime added successfully.");
			return "redirect:ot-entry-n";
		}
	}
	
	@GetMapping("ot-summary")
	public String otListPage(ModelMap modelMap) {
		LocalDate startDate = YearMonth.now().atDay(1);
	    LocalDate endDate = YearMonth.now().atEndOfMonth();
	    modelMap.put("dateRange", startDate+" to "+endDate);
		modelMap.put("staffs", staffService.allStaffotSummary(startDate, endDate));
		return "otSummary";
	}
	
	@PostMapping("ot-summary")
	public String otList(LocalDate startDate, LocalDate endDate, ModelMap modelMap) {
		modelMap.put("dateRange", startDate+" to "+endDate);
		modelMap.put("staffs", staffService.allStaffotSummary(startDate, endDate));
		return "otSummary";
	}
	
	@GetMapping("ot-log")
	public String otLogPage(ModelMap modelMap) {
		LocalDate startDate = YearMonth.now().atDay(1);
	    LocalDate endDate = YearMonth.now().atEndOfMonth();
		modelMap.put("ots", service.findAllOtBetweenDates(startDate, endDate));
		
		modelMap.put("staffs", staffService.findAllStaff());
		modelMap.put("daterange", startDate + " - "+ endDate);
		return "otList";
	}
	
	@PostMapping("ot-log")
	public String otLog(String username, LocalDate startDate, LocalDate endDate, ModelMap modelMap) {
		List<Ot> allOtBetweenDates = new ArrayList<>();
		if(username.equals("ALL")) {
			allOtBetweenDates = service.findAllOtBetweenDates(startDate,endDate);
		} else {
			allOtBetweenDates = service.findAllOtBetweenDates(startDate,endDate)
				.stream().filter(ot -> ot.getUsername().equals(username)).toList();
		}
		
		modelMap.put("ots", allOtBetweenDates);
		modelMap.put("staffs", staffService.findAllStaff());
		modelMap.put("daterange", startDate + " to " + endDate);
		return "otList";
	}
	
	@GetMapping("update-ot")
	public String updateOtPage(int id, RedirectAttributes ra) {
		Ot ot = service.findOtById(id);
		
		if(ot.getOtDate().isEqual(LocalDate.now())) {
			
			service.updateOt(ot);
			ra.addFlashAttribute("isMessage", "yes");
			ra.addFlashAttribute("message", true);
			return "redirect:ot-log";
		}
		
		ra.addFlashAttribute("isMessage", "yes");
		ra.addFlashAttribute("message", false);
		return "redirect:ot-log";
	}

}
