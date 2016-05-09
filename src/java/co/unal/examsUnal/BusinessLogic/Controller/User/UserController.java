/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Controller.User;

import co.unal.examsUnal.DataAccess.DAO.RoleDAO;
import co.unal.examsUnal.DataAccess.DAO.UserDAO;
import co.unal.examsUnal.DataAccess.Entity.Role;
import co.unal.examsUnal.DataAccess.Entity.User;
import co.unal.examsUnal.Utilities.Util.LDAPFunctions;

/**
 *
 * @author AndresGutierrez
 */
public class UserController {
    
            
    public User login(String userId,String password){
        User user = null;
        UserDAO userDAO = new UserDAO();
        LDAPFunctions authentication = new LDAPFunctions();
        user = userDAO.findUserByUserId(userId);
        if(user!=null){
            if(authentication.login(userId, password).equals("user") || 
                    authentication.login(userId, password).equals("admin")){ //Login was successful
                return user;
            }
        }
            
        return null;
        
    }
    
    public User register(String userId,String name,String email,String password,
            int gender,String role,String document){
        
        UserDAO userDAO = new UserDAO();
        RoleDAO roleDAO = new RoleDAO();
        User user = new User();
        user.setName(name);
        user.setGender(gender);
        user.setEmail(email);
        user.setDocument(document);
        user.setIdAuthentication(userId);
        if(userDAO.findUserByUserId(userId)!=null){
            return null;
        }
        
        
        LDAPFunctions register = new LDAPFunctions();
        if(register.addUser(role, userId, password)){
            Role  roleUser = roleDAO.findRoleByRoleId(role);
            User userTemp = userDAO.persist(user, roleUser);
            return userTemp;
        }
        return null;
        
    }
    
    public User getUserByUserId(String userId){
        UserDAO userDAO = new UserDAO();
        return userDAO.findUserByUserId(userId);
    }
}