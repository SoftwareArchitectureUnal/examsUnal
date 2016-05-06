/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.DAO;

import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.DataAccess.Entity.Resultexam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author yeisondavid
 */
public class ResultExamDAO {
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamsUnalPU");
    
    public Collection<Resultexam>  findRelationByIdUser(String idUser)
    {
        EntityManager em = emf.createEntityManager();
        Query query;
        try
        {
            //query = em.createNamedQuery("ResultExam.findByIdUser").setParameter("idUser", idUser);
            query = em.createNativeQuery("SELECT * FROM  resultexam AS r  WHERE (r.idUser=? );",Resultexam.class);
            query.setParameter(1,idUser);
            return query.getResultList();
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public boolean deleteByRelationId(Resultexam relation) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            relation = em.merge(relation);
            em.remove(relation);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        return true;
    }
    
    public boolean deleteByExamId(Exam exam) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            ArrayList<Resultexam> results = new ArrayList<>(findAllRelation());
            for(Resultexam result : results){
                if( Objects.equals(result.getIdExam().getExamId(), exam.getExamId()) ){
                    result = em.merge(result);
                    em.remove(result);
                }
            }
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        return true;
    }
    
    public Resultexam insert(Resultexam relation){
        System.out.println("::: in insert");
        System.out.println(relation.getIdExam().getExamId());
        System.out.println(relation.getIdUser().getIdAuthentication());
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try{
            System.out.println("1");
            em.persist(relation);
            System.out.println("2");
            relation.getIdExam().getResultexamCollection().add(relation);
            relation.getIdUser().getResultexamCollection().add(relation);
            em.getTransaction().commit();
            em.merge(relation.getIdExam());
            em.merge(relation.getIdUser());
        }catch(Exception e){
            em.getTransaction().rollback();
            System.out.println("fail");
            return null;
        }finally{
            em.close();
        }
        return relation;
    }
    
    public Collection<Resultexam> findAllRelation(){
        EntityManager em = emf.createEntityManager();
        Query query;
        try{
            query = em.createNamedQuery("Resultexam.findAll");
            return (Collection<Resultexam>) query.getResultList();
        }catch(Exception e){
            System.out.println("Exception result " + e);
            e.printStackTrace();
            return null;
        }
    }
    
    public Resultexam update(Resultexam resultExam){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            Resultexam resulExamUpdate = em.find(Resultexam.class, resultExam.getResultExamId() );
            resulExamUpdate.setApproved(resultExam.getApproved());
            resulExamUpdate.setIdExam(resultExam.getIdExam());
            resulExamUpdate.setIdUser(resultExam.getIdUser());
            resulExamUpdate.setResultExamId(resultExam.getResultExamId());
            resulExamUpdate.setStatus(resultExam.getStatus());
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            return null;
        }finally{
            em.close();
        }
        return resultExam;
    }
    
}
