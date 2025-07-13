package com.Student.main;

import com.Student.dao.*;
import com.Student.entity.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDAO = new StudentDao();
        CourseDao courseDAO = new CourseDao();
        InstructorDao instructorDAO = new InstructorDao();
        EnrollmentDao enrollmentDAO = new EnrollmentDao();
        ScoreDao scoreDAO = new ScoreDao();
        FeedbackDao feedbackDAO = new FeedbackDao();
        
        
        Student s1 = new Student();
        s1.setName("Vineet");
        s1.setEmail("Vineet@example.com");
        s1.setDob("2000-07-15");
        studentDAO.addStudent(s1);

        List<Student> students = studentDAO.getAllStudents();
        for(Student s : students) {
        	System.out.println("Student Records!");
        	System.out.println();
        	System.out.println("Id: " + s.getId());
        	System.out.println("Name: " + s.getName());
        	System.out.println("DOB: " + s.getDob());
        	System.out.println("Email: " + s.getEmail());
        }

        
        Course c1 = new Course();
        c1.setTitle("Java Programming");
        c1.setDescription("Intro to core Java concepts");
        c1.setCredits(4);
        courseDAO.addCourse(c1);

        
        Instructor i1 = new Instructor();
        i1.setName("Dr. Gupta");
        i1.setEmail("gupta@university.com");
        i1.setDepartment("Computer Science");
        instructorDAO.addInstructor(i1);

        
        Enrollment e1 = new Enrollment();
        e1.setStudentId(s1.getId());
        e1.setCourseId(c1.getId());
        e1.setEnrollmentDate("2025-07-10");
        enrollmentDAO.addEnrollment(e1);

        Score sc1 = new Score();
        sc1.setStudentId(s1.getId());
        sc1.setCourseId(c1.getId());
        sc1.setGrade("A+");
        scoreDAO.addScore(sc1);

        
        Feedback f1 = new Feedback();
        f1.setStudentId(s1.getId());
        f1.setInstructorId(i1.getId());
        f1.setMessage("Great course, excellent teaching!");
        feedbackDAO.addFeedback(f1);

    }
}