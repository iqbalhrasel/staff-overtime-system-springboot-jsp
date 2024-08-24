package com.pxc.ot_system.ot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Designation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;
	
	private String role;

	public Designation() {
	}

	public Designation(byte id, String role) {
		this.id = id;
		this.role = role;
	}

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Designation [id=" + id + ", role=" + role + "]";
	}
	
	

}
