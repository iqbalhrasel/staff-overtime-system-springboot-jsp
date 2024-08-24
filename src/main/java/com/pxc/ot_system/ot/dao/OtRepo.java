package com.pxc.ot_system.ot.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pxc.ot_system.ot.model.Ot;

public interface OtRepo extends JpaRepository<Ot, Integer> {

	List<Ot> findByOtDateBetween(LocalDate startDate, LocalDate endDate);
	List<Ot> findByUsernameAndOtDate(String username, LocalDate otDate);
}
