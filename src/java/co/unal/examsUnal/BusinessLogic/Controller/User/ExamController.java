/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Controller.User;

import co.unal.examsUnal.DataAccess.DAO.ExamDAO;
import co.unal.examsUnal.DataAccess.Entity.Exam;
import java.util.Collection;

/**
 *
 * @author AndresGutierrez
 */
public class ExamController {
    public Collection<Exam> findExamsUser(String username){
        ExamDAO examDAO = new ExamDAO();
        return examDAO.findExamsUser(username);
    }
}
