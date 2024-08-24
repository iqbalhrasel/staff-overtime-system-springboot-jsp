package com.pxc.ot_system.ot.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pxc.ot_system.ot.dao.DesignationRepo;
import com.pxc.ot_system.ot.dao.OtRepo;
import com.pxc.ot_system.ot.dao.StaffRepo;
import com.pxc.ot_system.ot.model.Designation;
import com.pxc.ot_system.ot.model.Ot;
import com.pxc.ot_system.ot.model.Staff;

@Service
public class StaffService {
	
	@Autowired
	private OtRepo otRepo;
	
	@Autowired
	private StaffRepo staffRepo;
	
	@Autowired
	private DesignationRepo designationRepo;
	
	public void createStaff(Staff staff) {
		staff.setFinalOt("00:00");
		staffRepo.save(staff);
	}
	
	public void updateStaff(Staff staff) {
		staffRepo.save(staff);
	}

	public Staff findByUsername(String username) {
		
		return staffRepo.findByUsername(username);
	}

	
	public List<Staff> findAllStaff() {
		return staffRepo.findAll();
	}
	
	public List<Staff> allStaffotSummary(LocalDate startDate, LocalDate endDate) {
	    List<Staff> staffList = staffRepo.findAll();
	    List<Ot> otListBetweenDates = otRepo.findByOtDateBetween(startDate, endDate);
	    for (var staff : staffList) {
	    	Duration getSingleStaffDuration = singleStaffDuration(otListBetweenDates, staff.getUsername());
	        
	    	if(getSingleStaffDuration.toMinutesPart() <10) {
	    		staff.setFinalOt(getSingleStaffDuration.toHoursPart() +":0" +getSingleStaffDuration.toMinutesPart());
	    	}
	        else staff.setFinalOt(getSingleStaffDuration.toHoursPart() +":" +getSingleStaffDuration.toMinutesPart());
	    }
	    return staffList;
	}

	private Duration singleStaffDuration(List<Ot> otListBetweenDates, String username) {
		List<Ot> singleStaffOtList = otListBetweenDates.stream()
				.filter(ot -> ot.getUsername().equals(username)).toList();
		Duration accumulatedDuration = Duration.ZERO;
		for(var eachOt : singleStaffOtList) {
			accumulatedDuration = accumulatedDuration
					.plus(Duration.between(LocalTime.MIN, eachOt.getOtTime()));
		}
		return accumulatedDuration;
	}

	
	
	public Staff findById(int id) {
		
		return staffRepo.findById(id).orElse(null);
	}

	public List<Designation> getAllDesignations() {
		
		return designationRepo.findAll();
	}

	public void createDesignation(Designation designation) {
		designationRepo.save(designation);
	}

	public Designation findByRole(String role) {
		
		return designationRepo.findByRole(role).orElse(null);
	}

	public Designation findDesignationById(byte id) {
		
		return designationRepo.findById(id).orElse(null);
	}

	public void deleteById(int id) {
		staffRepo.deleteById(id);
	}

}
