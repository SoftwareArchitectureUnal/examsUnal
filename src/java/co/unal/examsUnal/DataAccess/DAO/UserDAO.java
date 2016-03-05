/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.DAO;

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
    
    public User persist(User user,Role role){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(user);
            user.setIdRole(role);
            role.getUserCollection().add(user);
            em.getTransaction().commit();
            em.merge(role);
        }catch(Exception e){
            em.getTransaction().rollback();
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
            user = em.createNamedQuery("User.findByUserId",User.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        }catch(Exception e){}
        finally{
            return user;
        }
    }
    
}