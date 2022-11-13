package com.example.jobcnx;

public class User {
	private int minSalary;
	private boolean degree;
	private String name;
	private String email;
	private String field;
	private boolean masters;
	
	public User (int minSalary, boolean degree, String name, String email, String field, boolean masters) {
		this.name = name;
		this.minSalary = minSalary;
		this.degree = degree;
		this.email = email;
		this.field = field;
		this.masters = masters;
	}
	
	public int getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
	public boolean isDegree() {
		return degree;
	}
	public void setDegree(boolean degree) {
		this.degree = degree;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public String toString() {
		String toReturn;
		toReturn = this.getName() + " " + this.email + " " + this.getMinSalary() + " " + this.getField() + " " + this.degree + " " + this.masters;
		return toReturn;
	}
	
	
}
