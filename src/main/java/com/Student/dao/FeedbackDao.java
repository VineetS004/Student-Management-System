package com.Student.dao;

import com.Student.configuration.SessionConfig;
import com.Student.entity.Feedback;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import java.util.List;

public class FeedbackDao {

    public void addFeedback(Feedback feedback) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();
            session.persist(feedback);
            tx.commit();
            System.out.println("✅ Feedback added.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding feedback: " + e.getMessage());
        }
    }

    public List<Feedback> getAllFeedbacks() {
        try (Session session = SessionConfig.getSessionFactory()) {
            String hql = "FROM Feedback";
            Query<Feedback> query = session.createQuery(hql, Feedback.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error fetching feedbacks: " + e.getMessage());
            return List.of();
        }
    }

    public void updateFeedback(Feedback updatedFeedback) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();

            String hql = " UPDATE Feedback SET message = :message, feedbackDate = :date WHERE id = :id";

            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("message", updatedFeedback.getMessage());
            query.setParameter("id", updatedFeedback.getId());

            int rows = query.executeUpdate();
            tx.commit();

            System.out.println(rows > 0 ? "Feedback updated." : "No feedback found with ID " + updatedFeedback.getId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating feedback: " + e.getMessage());
        }
    }

    public void deleteFeedbackById(int id) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();
            String hql = "DELETE FROM Feedback WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("id", id);
            int rows = query.executeUpdate();
            tx.commit();

            System.out.println(rows > 0 ? "Feedback deleted." : "No feedback found with ID " + id);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting feedback: " + e.getMessage());
        }
    }
}
