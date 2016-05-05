/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.DAO;

import co.unal.examsUnal.DataAccess.Entity.Authentication;
import co.unal.examsUnal.DataAccess.Entity.Role;
import co.unal.examsUnal.DataAccess.Entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author AndresGutierrez
 */
public class UserDAO {
    public EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("ExamsUnalPU");
    
    public User persist(User user,Role role,Authentication authentication){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(user);
            user.setIdRole(role);
            role.getUserCollection().add(user);
            authentication.setUser(user);
            em.merge(role);
            em.merge(authentication);
            em.getTransaction().commit();
            
        }catch(Exception e){
            em.getTransaction().rollback();
            System.out.println("algo1");
            user = null;
        }finally{
            em.close();
            return user;
        }
    }
    public User findUserByUserId(String userId){
        EntityManager em = emf.createEntityManager();
        User user = null;
        try{
            user = em.createNamedQuery("User.findByIdAuthentication",User.class)
                    .setParameter("idAuthentication", userId)
                    .getSingleResult();
        }catch(Exception e){}
        finally{
            return user;
        }
    }
    
}
