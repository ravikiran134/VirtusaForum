package com.vforum.model;

public class Employee {
	
	private int empId;
	private String firstName;
	private String lastName;
	private String email;
	private String location;
	private String password;
	private String designation;
	
	public Employee(int empId, String empDesignation, String empLocation,
			String empPassword, String empEmail) {
		super();
		this.empId = empId;
		password = empPassword;
		designation = empDesignation;
		location = empLocation;
		this.email = empEmail;
	}
	
	public Employee(int empid, String fName, String lName,  String designation, String email, String hashedPassword)
	{
		empId = empid;
		firstName = fName;
		lastName = lName;
		this.designation = designation;
		password = hashedPassword;
		this.email = email;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
