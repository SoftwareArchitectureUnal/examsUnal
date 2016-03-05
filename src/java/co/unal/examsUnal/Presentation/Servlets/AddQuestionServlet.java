/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Presentation.Servlets;

import co.unal.examsUnal.BusinessLogic.Controller.User.ExamController;
import co.unal.examsUnal.BusinessLogic.Controller.User.QuestionController;
import co.unal.examsUnal.BusinessLogic.Controller.User.UserController;
import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.DataAccess.Entity.Question;
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
public class AddQuestionServlet extends HttpServlet{
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{  
        
        String questionId = (String) req.getParameter("inputQuestion");
        String category = (String) req.getParameter("inputCategory");
        String description = (String) req.getParameter("inputDescription");
        System.out.println(questionId+" "+category+" "+description);
        QuestionController questionController = new QuestionController();
        Question question =  questionController.addQuestion(questionId, category, description);
        if(question!=null){//We can add the question
            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
        }else{//We have an error
            req.getSession().setAttribute("error", "error");
            req.getRequestDispatcher("/admin/addQuestions.jsp").forward(req, resp);
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
