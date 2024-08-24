package com.pxc.ot_system.ot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pxc.ot_system.ot.model.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer> {
	
	Staff findByUsername(String username);

}
