package com.Student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Score {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(name = "Scorecard_ID")
	    private int id;

	 	@Column(name = "Student_ID")
	    private int studentId;
	 	@Column(name = "Course_ID")
	    private int courseId;
	 	@Column(name = "Grade")
	    private String grade;
	 	
	 	
		public Score() {
			super();
		}
		public Score(int id, int studentId, int courseId, String grade) {
			super();
			this.id = id;
			this.studentId = studentId;
			this.courseId = courseId;
			this.grade = grade;
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
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}

}
