package com.pxc.ot_system.ot.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pxc.ot_system.ot.model.Designation;

public interface DesignationRepo extends JpaRepository<Designation, Byte> {
	Optional<Designation> findByRole(String role);

}
