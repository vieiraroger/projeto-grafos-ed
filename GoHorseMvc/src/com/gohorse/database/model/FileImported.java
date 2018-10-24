package com.gohorse.database.model;

public class FileImported {
	private Courses course;
	private String phaseStart;
	private String phaseFinish;
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Courses getCourse() {
		return course;
	}
	public void setCourse(Courses course) {
		this.course = course;
	}
	public String getPhaseStart() {
		return phaseStart;
	}
	public void setPhaseStart(String phaseStart) {
		this.phaseStart = phaseStart;
	}
	public String getPhaseFinish() {
		return phaseFinish;
	}
	public void setPhaseFinish(String phaseFinish) {
		this.phaseFinish = phaseFinish;
	}
	
}
