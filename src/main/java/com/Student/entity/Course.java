package com.Student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(name = "Course_ID")
	    private int id;
	 
	 	@Column(name = "Course_Name")
	    private String title;
	 	@Column(name = "Description")
	    private String description;
	 	@Column(name = "Credits")
	    private int credits;
	 	
	 	
		public Course() {
			super();
		}
		public Course(int id, String title, String description, int credits) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			this.credits = credits;
		}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getCredits() {
			return credits;
		}
		public void setCredits(int credits) {
			this.credits = credits;
		}
}
