/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Presentation.Servlets;

import co.unal.examsUnal.BusinessLogic.Controller.User.UserController;
import co.unal.examsUnal.DataAccess.Entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AndresGutierrez
 */
public class SignUpServlet extends HttpServlet {
    
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = (String) req.getParameter("inputUsername");
        String name = (String) req.getParameter("inputName");
        String password = (String) req.getParameter("inputPassword");
        String email = (String) req.getParameter("inputEmail");
        String document = (String) req.getParameter("inputUsername");
        int gender =  Integer.parseInt((String)req.getParameter("gender"));
        String role = (String) req.getParameter("role");
        UserController userController = new UserController();
        User user = userController.register(username, name, email, password, gender, role,document);
        if(user!=null){// The register was successful
            if(role.equals("admin")){
                req.getSession().setAttribute("admin", role);
                req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
            }else{
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("/user/index.jsp").forward(req, resp);
            }
        }else{
            req.setAttribute("signUp", "error");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
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
