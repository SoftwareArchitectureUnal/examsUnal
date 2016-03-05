/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Controller.User;

import co.unal.examsUnal.DataAccess.DAO.QuestionDAO;
import co.unal.examsUnal.DataAccess.Entity.Question;
import java.util.Collection;

/**
 *
 * @author AndresGutierrez
 */
public class QuestionController {
    public Question addQuestion(String questionId,String category, String description){
        Question question = new Question(questionId, category, description);
        QuestionDAO questionDAO = new QuestionDAO();
        Question temp = questionDAO.persist(question);
        return temp;
    }
    public Collection<Question> findAllQuestions(){
        QuestionDAO questionDAO = new QuestionDAO();
        Collection<Question> questions = questionDAO.findAllQuestions();
        return questions; 
    }
    
}
