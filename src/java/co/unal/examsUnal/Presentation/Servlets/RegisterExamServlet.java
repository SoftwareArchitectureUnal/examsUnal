/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Presentation.Servlets;

import co.unal.examsUnal.BusinessLogic.Controller.User.ExamRegisterController;
import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.DataAccess.Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import javafx.util.Pair;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yeisondavid
 */
@WebServlet(name = "RegisterExamServlet", urlPatterns = {"/RegisterExamServlet"})
public class RegisterExamServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            User myUser = (User)request.getSession().getAttribute("user");
            String myIdUser = myUser.getUserId();
            Exam auxExam;
            String value;
            Boolean flag;
            Pair auxP;
            Collection<Pair<Exam, Boolean>> lista = ExamRegisterController.ExamsUser(myIdUser);
            Iterator<Pair<Exam,Boolean>> iter = lista.iterator();
            while( iter.hasNext())
            {
                auxP = iter.next();
                flag = (Boolean)auxP.getValue();
                auxExam = (Exam)auxP.getKey();
                value = request.getParameter("checkb"+auxExam.getExamId());
                if ( value == null && flag)
                {
                    System.out.println("unSubcribeExam");
                    ExamRegisterController.unSubcribeExam(myIdUser, auxExam.getExamId());
                }    
                else if ( value != null && !flag)
                {
                    ExamRegisterController.RegisterExam(myUser, auxExam);
                }
                   
            }
            request.getRequestDispatcher("/user/index.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
