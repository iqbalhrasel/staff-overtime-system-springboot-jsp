package com.pxc.ot_system.ot.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pxc.ot_system.ot.dao.OfficeExitTimeRepo;
import com.pxc.ot_system.ot.model.Designation;
import com.pxc.ot_system.ot.model.OfficeExitTime;
import com.pxc.ot_system.ot.model.Ot;
import com.pxc.ot_system.ot.model.Staff;
import com.pxc.ot_system.ot.security.UserInfo;
import com.pxc.ot_system.ot.service.OtService;
import com.pxc.ot_system.ot.service.StaffService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private OtService service;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private OfficeExitTimeRepo exitTimeRepo;
	
	@Autowired
	private UserInfo userInfo;
	
	@ModelAttribute
	public void navBarUsername(ModelMap modelMap) {
		modelMap.put("username", userInfo.getLoggedInUsername());
	}
	
	@GetMapping("create-ot")
	public String createOtPage(ModelMap modelMap) {
		Ot ot = new Ot();
		ot.setEndTime(LocalTime.now());
		ot.setOtDate(LocalDate.now());
		modelMap.put("ot", ot);
		modelMap.put("staffs", staffService.findAllStaff());
		return "admin/otFormAdmin";
	}
	
	@PostMapping("create-ot")
	public String otEntrySubmit(Ot ot, RedirectAttributes ra) {		
		service.createOt(ot);
		ra.addFlashAttribute("message", "Overtime added successfully.");
		return "redirect:create-ot";
	}
	
	@GetMapping("create-staff-id")
	public String createStaffPage(ModelMap modelMap) {
		Staff staff = new Staff(0, "", "", "", "");
		modelMap.put("staff", staff);
		modelMap.put("designations", staffService.getAllDesignations());
		modelMap.put("readOnlySettings", false);
		return "admin/createStaff";
	}
	
	@PostMapping("create-staff-id")
	public String createStaff(ModelMap modelMap, RedirectAttributes ra, @Valid Staff staff, BindingResult result) {
		Staff staff1 = staffService.findByUsername(staff.getUsername());
		
		if(result.hasErrors()) {
			modelMap.put("designations", staffService.getAllDesignations());
			return "admin/createStaff";
		}
		if(staff1 != null && staff1.getUsername().equals(staff.getUsername())) {
			ra.addFlashAttribute("msg", "Staff username already exists!");
			modelMap.put("msg", "Staff username already exists!");	
			return "redirect:create-staff-id";
		}
		if(staff1 == null) {
			staff.setId(0);
			staffService.createStaff(staff);
			ra.addFlashAttribute("msg", "Staff ID created successfully.");
			return "redirect:create-staff-id";
		}
		
		modelMap.put("msg", "Staff ID already exists!");	
		return "admin/createStaff";
	}
	
	@GetMapping("staffs")
	public String staffsPage(ModelMap modelMap) {
		modelMap.put("staffs", staffService.findAllStaff());
		return "admin/staffList";
	}
	
	@GetMapping("create-designation")
	public String createDesignationPage(ModelMap modelMap) {
		modelMap.put("designation", new Designation());
		modelMap.put("designations", staffService.getAllDesignations());
		return "admin/designationForm";
	}
	
	@PostMapping("create-designation")
	public String createDesignation(Designation designation, RedirectAttributes ra) {
		Designation getDesignation = staffService.findByRole(designation.getRole());
		if(getDesignation != null) {
			ra.addFlashAttribute("alert", "Designation already exists.");
			return "redirect:create-designation";
		}
		staffService.createDesignation(designation);
		ra.addFlashAttribute("alert", "Created successfully.");
		return "redirect:create-designation";
	}
	
	@GetMapping("edit-designation")
	public String editDesignationPage(byte id, ModelMap modelMap) {
		Designation getDesignation = staffService.findDesignationById(id);
		modelMap.put("designation", getDesignation);
		modelMap.put("designations", staffService.getAllDesignations());
		return "admin/designationForm";
	}
	
	@PostMapping("edit-designation")
	public String editDesignation(Designation designation, RedirectAttributes ra) {
		
		staffService.createDesignation(designation);
		ra.addFlashAttribute("alert", "Updated successfully.");
		return "redirect:create-designation";
	}
	
	@GetMapping("ot-log")
	public String otLogPage(ModelMap modelMap) {
		LocalDate startDate = YearMonth.now().atDay(1);
	    LocalDate endDate = YearMonth.now().atEndOfMonth();
		modelMap.put("ots", service.findAllOtBetweenDates(startDate, endDate));
		
		modelMap.put("staffs", staffService.findAllStaff());
		modelMap.put("daterange", startDate + " - "+ endDate);
		return "admin/otListAdmin";
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
		return "admin/otListAdmin";
	}
	
	@GetMapping("ot-summary")
	public String otListPage(ModelMap modelMap) {
		LocalDate startDate = YearMonth.now().atDay(1);
	    LocalDate endDate = YearMonth.now().atEndOfMonth();
	    modelMap.put("dateRange", startDate+" to "+endDate);
		modelMap.put("staffs", staffService.allStaffotSummary(startDate, endDate));
		return "admin/otSummaryAdmin";
	}
	
	@PostMapping("ot-summary")
	public String otList(LocalDate startDate, LocalDate endDate, ModelMap modelMap) {
		modelMap.put("dateRange", startDate+" to "+endDate);
		modelMap.put("staffs", staffService.allStaffotSummary(startDate, endDate));
		return "admin/otSummaryAdmin";
	}
	
	@GetMapping("update-staff")
	public String updateStaffPage(int id, ModelMap modelMap) {
		Staff staff = staffService.findById(id);
		modelMap.put("readOnlySettings", true);
		modelMap.put("designations", staffService.getAllDesignations());
		modelMap.put("staff", staff);		
		return "admin/createStaff";
	}
	
	@PostMapping("update-staff")
	public String updateStaff(@Valid Staff staff, BindingResult result) {
		if(result.hasErrors()) {
			return "admin/createStaff";
		}
		staffService.updateStaff(staff);
		return "redirect:staffs";
	}
	
	@GetMapping("delete-staff")
	public String deleteStaff(int id) {
		staffService.deleteById(id);
		return "redirect:staffs";
	}
	
	@GetMapping("update-time")
	public String updateTimePage(ModelMap modelMap) {
		OfficeExitTime exitTime = exitTimeRepo.findById((byte)1)
				.orElse(new OfficeExitTime((byte)0, LocalTime.of(0, 0), LocalTime.of(0, 0), LocalTime.of(0, 0)));
		modelMap.put("officeExitTime", exitTime);
		return "admin/updateExitTime";
	}
	
	@PostMapping("update-time")
	public String updateTime(OfficeExitTime exitTime, RedirectAttributes ra) {
		ra.addFlashAttribute("alert", "Updated successfully.");
		exitTimeRepo.save(exitTime);
		return "redirect:update-time";
	}
	
	@GetMapping("update-ot-admin")
	public String updateOtAdminPage(int id, ModelMap modelMap) {
		Ot ot = service.findOtById(id);
		
		modelMap.put("staffs", staffService.findAllStaff());
		modelMap.put("ot", ot);
		return "admin/otFormAdmin";
	}
	
	@PostMapping("update-ot-admin")
	public String updateOtAdmin(Ot ot, ModelMap modelMap) {
		modelMap.put("staffs", staffService.findAllStaff());
		modelMap.put("message", "Updated successfully.");
		
		service.updateOt(ot);
		return "admin/otFormAdmin";
	}
	
	@GetMapping("delete-ot")
	public String deleteOtAdminPage(int id, RedirectAttributes ra) {
		service.deleteOtById(id);
		ra.addFlashAttribute("alert", "item deleted successfully.");
		return "redirect:ot-log";
	}

}
