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
            System.out.println("::::::: Here");
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
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(relation);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            return null;
        }finally{
            em.close();
        }
        return relation;
    }
}
