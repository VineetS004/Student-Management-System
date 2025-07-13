package com.Student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Feedback {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "Feedback_ID")
	    private int id;

		@Column(name = "Student_ID")
	    private int studentId;
		@Column(name = "Instructor_ID")
	    private int instructorId;
		@Column(name = "Message")
	    private String message;
		
		
		public Feedback() {
			super();
		}
		public Feedback(int id, int studentId, int instructorId, String message) {
			super();
			this.id = id;
			this.studentId = studentId;
			this.instructorId = instructorId;
			this.message = message;
		}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getStudentId() {
			return studentId;
		}
		public void setStudentId(int studentId) {
			this.studentId = studentId;
		}
		public int getInstructorId() {
			return instructorId;
		}
		public void setInstructorId(int instructorId) {
			this.instructorId = instructorId;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

}
