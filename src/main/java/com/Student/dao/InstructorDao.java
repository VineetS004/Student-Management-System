package com.Student.dao;

import com.Student.configuration.SessionConfig;
import com.Student.entity.Instructor;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import java.util.List;

public class InstructorDao {

    public void addInstructor(Instructor instructor) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();
            session.persist(instructor);
            tx.commit();
            System.out.println("Instructor added.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding instructor: " + e.getMessage());
        }
    }

    public List<Instructor> getAllInstructors() {
        try (Session session = SessionConfig.getSessionFactory()) {
            String hql = "FROM Instructor";
            Query<Instructor> query = session.createQuery(hql, Instructor.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error fetching instructors: " + e.getMessage());
            return List.of();
        }
    }

    public void updateInstructor(Instructor updatedInstructor) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();

            String hql = "UPDATE Instructor SET name = :name, email = :email, department = :department WHERE id = :id";

            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("name", updatedInstructor.getName());
            query.setParameter("email", updatedInstructor.getEmail());
            query.setParameter("department", updatedInstructor.getDepartment());
            query.setParameter("id", updatedInstructor.getId());

            int rows = query.executeUpdate();
            tx.commit();

            System.out.println(rows > 0 ? "Instructor updated." : "No instructor found with ID " + updatedInstructor.getId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating instructor: " + e.getMessage());
        }
    }

    public void deleteInstructorById(int id) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();
            String hql = "DELETE FROM Instructor WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("id", id);
            int rows = query.executeUpdate();
            tx.commit();

            System.out.println(rows > 0 ? "Instructor deleted." : "No instructor found with ID " + id);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting instructor: " + e.getMessage());
        }
    }
}
