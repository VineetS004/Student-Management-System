package com.Student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Student_ID")
    private int id;
	
	@Column(name = "Student_Name")
    private String name;
	@Column(name = "Email")
    private String email;
	@Column(name = "DOB")
	private String dob;
	
	
    public Student() {
		super();
	}
	public Student(int id, String name, String email, String dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
}
