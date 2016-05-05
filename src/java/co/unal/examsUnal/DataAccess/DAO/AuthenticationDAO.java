/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.DAO;

import co.unal.examsUnal.DataAccess.Entity.Authentication;
import co.unal.examsUnal.DataAccess.Entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author AndresGutierrez
 */
public class AuthenticationDAO {
    public EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("ExamsUnalPU");
    public Authentication persist(Authentication authentication){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(authentication);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            authentication = null;
        }finally{
            em.close();
            return authentication;
        }
    }
    public Authentication findAutenticationByID(String authenticationId){
        EntityManager em = emf.createEntityManager();
        Authentication authentication = null;
        try{
            authentication = em.createNamedQuery("Authentication.findByAuthenticationId",
                    Authentication.class)
                    .setParameter("authenticationId", authenticationId)
                    .getSingleResult();
        }catch(Exception e){}
        finally{
            return authentication;
        }
    }
}
