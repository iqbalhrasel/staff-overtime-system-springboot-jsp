package com.pxc.ot_system.ot.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OfficeExitTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;
	
	private LocalTime generalExitTime;
	private LocalTime ramadanExitTime;
	private LocalTime nonOfficeExitTime;

	public OfficeExitTime() {
	}

	public OfficeExitTime(byte id, LocalTime generalExitTime, LocalTime ramadanExitTime, LocalTime nonOfficeExitTime) {
		this.id = id;
		this.generalExitTime = generalExitTime;
		this.ramadanExitTime = ramadanExitTime;
		this.nonOfficeExitTime = nonOfficeExitTime;
	}

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public LocalTime getGeneralExitTime() {
		return generalExitTime;
	}

	public void setGeneralExitTime(LocalTime generalExitTime) {
		this.generalExitTime = generalExitTime;
	}

	public LocalTime getRamadanExitTime() {
		return ramadanExitTime;
	}

	public void setRamadanExitTime(LocalTime ramadanExitTime) {
		this.ramadanExitTime = ramadanExitTime;
	}

	public LocalTime getNonOfficeExitTime() {
		return nonOfficeExitTime;
	}

	public void setNonOfficeExitTime(LocalTime nonOfficeExitTime) {
		this.nonOfficeExitTime = nonOfficeExitTime;
	}

	@Override
	public String toString() {
		return "OfficeExitTime [id=" + id + ", generalExitTime=" + generalExitTime + ", ramadanExitTime="
				+ ramadanExitTime + ", nonOfficeExitTime=" + nonOfficeExitTime + "]";
	}

	

}
