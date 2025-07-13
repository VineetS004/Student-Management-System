package com.Student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Enrollment {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(name = "Enrollment_ID")
	    private int id;

	 	@Column(name = "Student_ID")
	    private int studentId;
	 	@Column(name = "Course_ID")
	    private int courseId;
	 	@Column(name = "Enrollment_Date")
	    private String enrollmentDate;
	 	
	 	
		public Enrollment() {
			super();
		}
		public Enrollment(int id, int studentId, int courseId, String enrollmentDate) {
			super();
			this.id = id;
			this.studentId = studentId;
			this.courseId = courseId;
			this.enrollmentDate = enrollmentDate;
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
		public int getCourseId() {
			return courseId;
		}
		public void setCourseId(int courseId) {
			this.courseId = courseId;
		}
		public String getEnrollmentDate() {
			return enrollmentDate;
		}
		public void setEnrollmentDate(String enrollmentDate) {
			this.enrollmentDate = enrollmentDate;
		}
}
