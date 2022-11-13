package com.example.jobcnx;

public class Job {
	private boolean degree;
	private String position;
	private int minSalary;
	private boolean wanted;
	
	// CONSTRUCTOR
	public Job (boolean degree, String position, int minSalary) {
		this.degree = degree;
		this.position = position;
		this.minSalary = minSalary;
	}
	
	// GETTERS AND SETTERS
	public boolean isDegree() {
		return degree;
	}
	public void setDegree(boolean degree) {
		this.degree = degree;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(int minSal) {
		this.minSalary = minSal;
	}
	public boolean isWanted() {
		return wanted;
	}
	public void setWanted(boolean wanted) {
		this.wanted = wanted;
	}
	
	
}
