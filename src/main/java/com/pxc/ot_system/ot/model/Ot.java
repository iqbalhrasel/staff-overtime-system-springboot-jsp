package com.pxc.ot_system.ot.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int staffId;
	private String name;
	private String username;
	private String role;
	private String otType;
	private LocalDate otDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalTime otTime;
	
	public Ot() {
	}

	public Ot(int id, int staffId, String name, String username, String role, String otType, LocalDate otDate,
			LocalTime startTime, LocalTime endTime, LocalTime otTime) {
		this.id = id;
		this.staffId = staffId;
		this.name = name;
		this.username = username;
		this.role = role;
		this.otType = otType;
		this.otDate = otDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.otTime = otTime;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOtType() {
		return otType;
	}

	public void setOtType(String otType) {
		this.otType = otType;
	}

	public LocalDate getOtDate() {
		return otDate;
	}

	public void setOtDate(LocalDate otDate) {
		this.otDate = otDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalTime getOtTime() {
		return otTime;
	}

	public void setOtTime(LocalTime otTime) {
		this.otTime = otTime;
	}

	@Override
	public String toString() {
		return "Ot [id=" + id + ", staffId=" + staffId + ", name=" + name + ", username=" + username + ", role=" + role
				+ ", otType=" + otType + ", otDate=" + otDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", otTime=" + otTime + "]";
	}

}
