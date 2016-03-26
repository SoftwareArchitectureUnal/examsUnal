/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Presentation.Servlets;

import co.unal.examsUnal.BusinessLogic.Controller.Management.ExamController;
import co.unal.examsUnal.BusinessLogic.Controller.User.UserController;
import co.unal.examsUnal.DataAccess.DAO.ExamDAO;
import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.DataAccess.Entity.User;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AndresGutierrez
 */
public class LoginServlet extends HttpServlet {
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("inputUsername");
        String password = req.getParameter("inputPassword");
        UserController userController = new UserController();
        User user = userController.login(username, password);
        if(user!=null){//The login was successful
            
            //req.getSession().setAttribute("totalNotifications");
            //req.getSession().setAttribute("notifications");
            req.getSession().invalidate();
            if(user.getIdRole().getRoleId().equals("admin")){
                req.getSession().setAttribute("admin", user.getIdRole().getRoleId());
                req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
            }else{
                ExamController examController = new ExamController();
                Collection<Exam> exmas = examController.findExamsUser(username);
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("totalNotifications",exmas.size());
                req.getSession().setAttribute("notifications",exmas);
                req.getRequestDispatcher("/user/index.jsp").forward(req, resp);
            }
        }else{
            req.getSession().setAttribute("login", "error");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
}
