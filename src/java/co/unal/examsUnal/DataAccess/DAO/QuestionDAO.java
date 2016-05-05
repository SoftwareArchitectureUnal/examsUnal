/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.DAO;

import co.unal.examsUnal.DataAccess.Entity.Question;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author AndresGutierrez
 */
public class QuestionDAO {
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamsUnalPU");
    
    public Question persist(Question question){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(question);
            em.getTransaction().commit();
        }catch(Exception e){
            question = null;
            em.getTransaction().rollback();
        }finally{
            em.close();
            return question;
        }
    }
    public Collection<Question> findAllQuestions(){
        EntityManager em = emf.createEntityManager();
        Collection<Question> questions;
        try{
           questions = em.createNamedQuery("Question.findAll", Question.class)
                   .getResultList();
        }catch(Exception e){
            questions = new ArrayList<>();
        }
        return questions;
    }
    public Question deleteQuestion(String questionId){
        EntityManager em = emf.createEntityManager();
        Question  question = null;
        em.getTransaction().begin();
        try{
            question = em.find(Question.class, questionId);
            em.remove(question);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            question = null;
        }finally{
            em.close();
            return question;
        }
        
    }
}