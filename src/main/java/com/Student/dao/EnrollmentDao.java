package com.Student.dao;

import com.Student.configuration.SessionConfig;
import com.Student.entity.Enrollment;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import java.util.List;

public class EnrollmentDao {

    public void addEnrollment(Enrollment enrollment) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();
            session.persist(enrollment);
            tx.commit();
            System.out.println("Enrollment added.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding enrollment: " + e.getMessage());
        }
    }

    public List<Enrollment> getAllEnrollments() {
        try (Session session = SessionConfig.getSessionFactory()) {
            String hql = "FROM Enrollment";
            Query<Enrollment> query = session.createQuery(hql, Enrollment.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error fetching enrollments: " + e.getMessage());
            return List.of();
        }
    }

    public void updateEnrollment(Enrollment updatedEnrollment) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();

            String hql = " UPDATE Enrollment SET studentId = :studentId, courseId = :courseId, enrollmentDate = :enrollmentDate WHERE id = :id";

            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("studentId", updatedEnrollment.getStudentId());
            query.setParameter("courseId", updatedEnrollment.getCourseId());
            query.setParameter("enrollmentDate", updatedEnrollment.getEnrollmentDate());
            query.setParameter("id", updatedEnrollment.getId());

            int rows = query.executeUpdate();
            tx.commit();

            System.out.println(rows > 0 ? "Enrollment updated.": "No enrollment found with ID " + updatedEnrollment.getId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating enrollment: " + e.getMessage());
        }
    }

    public void deleteEnrollmentById(int id) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();
            String hql = "DELETE FROM Enrollment WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("id", id);
            int rows = query.executeUpdate();
            tx.commit();

            System.out.println(rows > 0 ? "Enrollment deleted." : "No enrollment found with ID " + id);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting enrollment: " + e.getMessage());
        }
    }
}