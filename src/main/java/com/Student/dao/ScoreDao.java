package com.Student.dao;

import com.Student.configuration.SessionConfig;
import com.Student.entity.Score;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;

public class ScoreDao {

    public void addScore(Score score) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();
            session.persist(score);
            tx.commit();
            System.out.println("Score added.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding score: " + e.getMessage());
        }
    }

    public List<Score> getAllScores() {
        try (Session session = SessionConfig.getSessionFactory()) {
            String hql = "FROM Score";
            Query<Score> query = session.createQuery(hql, Score.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error fetching scores: " + e.getMessage());
            return List.of();
        }
    }
    
    public void updateScore(Score updatedScore) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();

            String hql = "UPDATE Score SET studentId = :studentId, courseId = :courseId, grade = :grade WHERE id = :id";

            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("studentId", updatedScore.getStudentId());
            query.setParameter("courseId", updatedScore.getCourseId());
            query.setParameter("grade", updatedScore.getGrade());
            query.setParameter("id", updatedScore.getId());

            int rows = query.executeUpdate();
            tx.commit();

            System.out.println(rows > 0 ? "Score updated." : "No score found with ID " + updatedScore.getId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating score: " + e.getMessage());
        }
    }

    public void deleteScoreById(int id) {
        Transaction tx = null;
        try (Session session = SessionConfig.getSessionFactory()) {
            tx = session.beginTransaction();
            String hql = "DELETE FROM Score WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("id", id);
            int rows = query.executeUpdate();
            tx.commit();

            System.out.println(rows > 0 ? "Score deleted." : "No score found with ID " + id);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting score: " + e.getMessage());
        }
    }
}