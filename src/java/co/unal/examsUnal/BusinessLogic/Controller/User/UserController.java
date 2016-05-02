/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Controller.User;

import co.unal.examsUnal.DataAccess.DAO.AuthenticationDAO;
import co.unal.examsUnal.DataAccess.DAO.RoleDAO;
import co.unal.examsUnal.DataAccess.DAO.UserDAO;
import co.unal.examsUnal.DataAccess.Entity.Authentication;
import co.unal.examsUnal.DataAccess.Entity.Role;
import co.unal.examsUnal.DataAccess.Entity.User;

/**
 *
 * @author AndresGutierrez
 */
public class UserController {
    public User login(String userId,String password){
        User user = null;
        UserDAO userDAO = new UserDAO();
        AuthenticationDAO authenticationDAO = new AuthenticationDAO();
        Authentication authentication = authenticationDAO.findAutenticationByID(userId);
        if(authentication!=null){
            user = userDAO.findUserByUserId(userId);
            if(authentication.getPassword().equals(password)){ //Login was successful
                return user;
            }
        }
        return null;
        
    }
    
    public User register(String userId,String name,String email,String password,
            int gender,String role){
        AuthenticationDAO authenticationDAO = new AuthenticationDAO();
        UserDAO userDAO = new UserDAO();
        RoleDAO roleDAO = new RoleDAO();
        Authentication authentication = new Authentication();
        authentication.setAuthenticationId(userId);
        authentication.setPassword(password);
        authenticationDAO.persist(authentication);
        User user = new User();
        user.setName(name);
        user.setGender(gender);
        user.setEmail(email);
        user.setIdAuthentication(userId);
        if(userDAO.findUserByUserId(userId)!=null){
            return null;
        }
        
        Role  roleUser = roleDAO.findRoleByRoleId(role);
        User userTemp = userDAO.persist(user, roleUser,authentication);
        return userTemp;
    }
}
