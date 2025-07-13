package com.Student.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import com.Student.configuration.SessionConfig;
import com.Student.entity.Student;

public class StudentDao {
	
	 public void addStudent(Student student) {
	        Transaction tx = null;
	        try (Session session = SessionConfig.getSessionFactory()) {
	            tx = session.beginTransaction();
	            session.persist(student);
	            tx.commit();
	            System.out.println("Student added successfully.");
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            System.err.println("Error adding student: " + e.getMessage());
	        }
	    }

	    public List<Student> getAllStudents() {
	        try (Session session = SessionConfig.getSessionFactory()) {
	            String hql = "FROM Student";
	            Query<Student> query = session.createQuery(hql, Student.class);
	            return query.getResultList();
	        } catch (Exception e) {
	            System.err.println("Error fetching students: " + e.getMessage());
	            return List.of();
	        }
	    }

	    public void updateStudent(Student updatedStudent) {
	        Transaction tx = null;
	        try (Session session = SessionConfig.getSessionFactory()) {
	            tx = session.beginTransaction();

	            String hql = "UPDATE Student SET name = :name, email = :email, dob = :dob WHERE id = :id";

	            MutationQuery query = session.createMutationQuery(hql);
	            query.setParameter("name", updatedStudent.getName());
	            query.setParameter("email", updatedStudent.getEmail());
	            query.setParameter("dob", updatedStudent.getDob());
	            query.setParameter("id", updatedStudent.getId());

	            int rows = query.executeUpdate();
	            tx.commit();

	            System.out.println(rows > 0 ? "Student updated successfully." : "No student found with ID " + updatedStudent.getId());

	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            System.err.println("Error updating student: " + e.getMessage());
	        }
	    }

	    public void deleteStudentById(int id) {
	        Transaction tx = null;
	        try (Session session = SessionConfig.getSessionFactory()) {
	            tx = session.beginTransaction();
	            String hql = "DELETE FROM Student WHERE id = :id";
	            MutationQuery query = session.createMutationQuery(hql);
	            query.setParameter("id", id);
	            int rows = query.executeUpdate();
	            tx.commit();

	            System.out.println(rows > 0 ? "Student deleted successfully." : "No student found with ID " + id);

	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            System.err.println("Error deleting student: " + e.getMessage());
	        }
	    }
}
