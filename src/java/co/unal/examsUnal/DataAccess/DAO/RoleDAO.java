/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.DAO;

import co.unal.examsUnal.DataAccess.Entity.Role;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author AndresGutierrez
 */
public class RoleDAO {
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamsUnalPU");
    
    //We don't have a persist method because the roles are statics
    
    public Role findRoleByRoleId(String roleId){
        EntityManager em = emf.createEntityManager();
        Role role = null;
        try{
            role = em.createNamedQuery("Role.findByRoleId", Role.class)
                    .setParameter("roleId", roleId)
                    .getSingleResult();
        }catch(Exception e){}
        finally{
            return role;
        }
    }
}
