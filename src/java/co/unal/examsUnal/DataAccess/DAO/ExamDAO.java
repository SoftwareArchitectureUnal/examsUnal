/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.DAO;

import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.DataAccess.Entity.ResultExam;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author AndresGutierrez
 */
public class ExamDAO {
        public EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamsUnalPU");

    public Collection<Exam> findExamsUser(String userId){
        EntityManager em = emf.createEntityManager();
        Query query;
        try{
            query = em.createNativeQuery("SELECT * FROM Exam AS e INNER JOIN ResultExam AS r ON r.idExam=e.examId WHERE (r.idUser=? && (e.realizationDate<=DATE_ADD(CURDATE(),INTERVAL 2 DAY) && e.realizationDate>CURDATE()));",Exam.class);
            query.setParameter(1,userId);
            return query.getResultList();
        }catch(Exception e){
            //System.out.println(e.toString());
            return new ArrayList<Exam>();
        }
    }
    
    public Collection<Exam> findAllExams() {
        EntityManager em = emf.createEntityManager();
        Query query;
        try{
            query = em.createNamedQuery("Exam.findAll");
            return (Collection<Exam>) query.getResultList();
        }catch(Exception e){
            return null;
        }
    }

    
    public Exam findById( int id ) {
        EntityManager em = emf.createEntityManager();
        Query query;
        try{
            query = em.createNamedQuery("Exam.findByExamId").setParameter("examId", id);
            return (Exam)query.getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Exam findByName( String name ) {
        EntityManager em = emf.createEntityManager();
        Query query;
        try{
            query = em.createNamedQuery("Exam.findByName").setParameter("name", name);
            return (Exam)query.getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
     
    public Exam insert(Exam exam){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(exam);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            return null;
        }finally{
            em.close();
        }
        return exam;
    }
    
    public Exam update(Exam exam){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            Exam exUpdate = em.find(Exam.class, exam.getExamId());
            exUpdate.setName(exam.getName());
            exUpdate.setExpeditionDate(exam.getExpeditionDate());
            exUpdate.setRealizationDate(exam.getRealizationDate());
            exUpdate.setCertificationDate(exam.getCertificationDate());
            exUpdate.setDescription(exam.getDescription());
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            return null;
        }finally{
            em.close();
        }
        return exam;
    }
    
    public boolean deleteExam(Exam exam) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            exam = em.merge(exam);
            em.remove(exam);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        return true;
    }
    
}
