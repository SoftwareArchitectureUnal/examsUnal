/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.DAO;

import co.unal.examsUnal.DataAccess.Entity.ResultExam;
import java.util.Collection;
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
    public Collection<ResultExam>  findRelationByIdUser(String idUser)
    {
        EntityManager em = emf.createEntityManager();
        Query query;
        try
        {
            //query = em.createNamedQuery("ResultExam.findByIdUser").setParameter("idUser", idUser);
            query = em.createNativeQuery("SELECT * FROM  ResultExam AS r  WHERE (r.idUser=? );",ResultExam.class);
            query.setParameter(1,idUser);
            return query.getResultList();
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public boolean deleteByRelationId(ResultExam relation) {
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
    
    public ResultExam insert(ResultExam relation){
        System.out.println("::: in insert");
        System.out.println(relation.getIdExam().getExamId());
        System.out.println(relation.getIdUser().getIdAuthentication());
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try{
            System.out.println("1");
            em.persist(relation);
            System.out.println("2");
            relation.getIdExam().getResultExamCollection().add(relation);
            relation.getIdUser().getResultExamCollection().add(relation);
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
    
    public Collection<ResultExam> findAllRelation(){
        EntityManager em = emf.createEntityManager();
        Query query;
        try{
            query = em.createNamedQuery("ResultExam.findAll");
            return (Collection<ResultExam>) query.getResultList();
        }catch(Exception e){
            return null;
        }
    }
}
