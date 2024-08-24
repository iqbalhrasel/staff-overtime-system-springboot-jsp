package com.pxc.ot_system.ot.service;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pxc.ot_system.ot.dao.OfficeExitTimeRepo;
import com.pxc.ot_system.ot.dao.OtRepo;
import com.pxc.ot_system.ot.dao.StaffRepo;
import com.pxc.ot_system.ot.model.OfficeExitTime;
import com.pxc.ot_system.ot.model.Ot;
import com.pxc.ot_system.ot.model.Staff;
import com.pxc.ot_system.ot.security.UserInfo;

@Service
public class OtService {
	
	@Autowired
	private OtRepo otRepo;
	
	@Autowired
	private StaffRepo staffRepo;
	
	@Autowired
	private OfficeExitTimeRepo exitTimeRepo;
	
	@Autowired
	private UserInfo userInfo;

	public void createOt(Ot ot) {
		OfficeExitTime endTime = exitTimeRepo.findById((byte)1).orElse(null);
		if(ot.getOtType().equals("General"))
			ot.setStartTime(endTime.getGeneralExitTime());
		else if(ot.getOtType().equals("Ramadan"))
			ot.setStartTime(endTime.getRamadanExitTime());
		else ot.setStartTime(endTime.getNonOfficeExitTime());
		
		Staff staff = staffRepo.findByUsername(ot.getUsername());
		
		if(!userInfo.getUserRole().contains("ADMIN")) {
			ot.setEndTime(LocalTime.now().plusMinutes(5));
		}
		//ot.setEndTime(LocalTime.now().plusMinutes(5));
		ot.setName(staff.getName());
		ot.setRole(staff.getRole());
		ot.setStaffId(staff.getId());
		
		if(ot.getEndTime().isBefore(ot.getStartTime().plusHours(1))) {
			ot.setOtTime(LocalTime.MIN);
		}
		else {
			Duration duration = Duration.between(ot.getStartTime(), ot.getEndTime());
			int hours = (int) duration.toHours();
			int minutes = (int) duration.toMinutes()%60;
			
			ot.setOtTime(LocalTime.of(hours, minutes));
		}
				
		otRepo.save(ot);
	}
	
	public List<Ot> findAllOtBetweenDates(LocalDate startDate, LocalDate endDate) {
		return otRepo.findByOtDateBetween(startDate, endDate);
	}

	public Ot findOtById(int id) {
		
		return otRepo.findById(id).orElse(null);
	}

	public void updateOt(Ot ot) {
		
		if(!userInfo.getUserRole().contains("ADMIN")) {
			ot.setEndTime(LocalTime.now().plusMinutes(5));
		} else ot.setEndTime(ot.getEndTime().plusMinutes(5));
				
		ot.setOtTime(calculateOtDuration(ot.getStartTime(), ot.getEndTime()));
		
		otRepo.save(ot);
		
	}
	
	private LocalTime calculateOtDuration(LocalTime starTime, LocalTime endTime) {
		
		if(endTime.isBefore(starTime)) {
			return LocalTime.of(0, 0);
		}
		Duration duration = Duration.between(starTime, endTime);
		int hours = (int) duration.toHours();
		int minutes = (int) duration.toMinutes()%60;
		return LocalTime.of(hours, minutes);
	}

	public Ot findByUsernameAndOtDate(Ot ot) {
		List<Ot> getOtList = otRepo.findByUsernameAndOtDate(ot.getUsername(), ot.getOtDate());
		if(getOtList.isEmpty()) {
			return null;
		}
		return getOtList.getFirst();
	}

	public void deleteOtById(int id) {
		otRepo.deleteById(id);
	}

}
