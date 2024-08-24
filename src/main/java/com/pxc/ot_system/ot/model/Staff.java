package com.pxc.ot_system.ot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 2, message = "Enter minimum 2 characters")
	private String name;
	
	private String role;
	
	@Size(min = 2, message = "Enter minimum 2 characters")
	private String username;
	
	private String finalOt;
	
	public Staff() {
	}

	public Staff(int id, String name, String role, String username, String finalOt) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.username = username;
		this.finalOt = finalOt;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFinalOt() {
		return finalOt;
	}

	public void setFinalOt(String finalOt) {
		this.finalOt = finalOt;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", role=" + role + ", username=" + username + ", finalOt="
				+ finalOt + "]";
	}

}
