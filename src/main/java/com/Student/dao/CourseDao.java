package com.Student.dao;

import com.Student.configuration.SessionConfig;
import com.Student.entity.Course;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import java.util.List;

public class CourseDao {
	
    public void addCourse(Course course) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();
            session.persist(course);
            tx.commit();
            System.out.println("Course added successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding course: " + e.getMessage());
        }
    }

    public List<Course> getAllCourses() {
        try (Session session = SessionConfig.getSessionFactory()) {
            String hql = "FROM Course";
            Query<Course> query = session.createQuery(hql, Course.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error fetching courses: " + e.getMessage());
            return List.of();
        }
    }

    public void updateCourse(Course updatedCourse) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();

            String hql = "UPDATE Course SET title = :title, description = :description, credits = :credits WHERE id = :id";

            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("title", updatedCourse.getTitle());
            query.setParameter("description", updatedCourse.getDescription());
            query.setParameter("credits", updatedCourse.getCredits());
            query.setParameter("id", updatedCourse.getId());

            int rows = query.executeUpdate();
            tx.commit();

            System.out.println(rows > 0 ? "Course updated successfully." : "No course found with ID " + updatedCourse.getId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating course: " + e.getMessage());
        }
    }

    public void deleteCourseById(int id) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();
            String hql = "DELETE FROM Course WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("id", id);
            int rows = query.executeUpdate();
            tx.commit();

            System.out.println(rows > 0 ? "Course deleted successfully." : "No course found with ID " + id);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting course: " + e.getMessage());
        }
    }
}
