/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Presentation.Servlets;

import co.unal.examsUnal.BusinessLogic.Controller.User.QuestionController;
import co.unal.examsUnal.DataAccess.Entity.Question;
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
public class ShowQuestionServlet extends HttpServlet{
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{  
        QuestionController questionController = new QuestionController();
        Collection<Question> questions =  questionController.findAllQuestions();
       
        req.getSession().setAttribute("questions", questions);
        req.getRequestDispatcher("/admin/showQuestions.jsp").forward(req, resp);
        
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
