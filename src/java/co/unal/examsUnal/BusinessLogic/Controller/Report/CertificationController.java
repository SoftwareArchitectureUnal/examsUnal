/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Controller.Report;

import co.unal.examsUnal.DataAccess.DAO.ExamDAO;
import co.unal.examsUnal.DataAccess.DAO.ResultExamDAO;
import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.DataAccess.Entity.ResultExam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author yeisondavid
 */
public class CertificationController {
    public static Collection<Exam> getPasExamsofUser(String idUser)
    {
        ResultExamDAO myRelationDAO = new ResultExamDAO();
        Collection<ResultExam> query = myRelationDAO.findRelationByIdUser(idUser);
        Iterator iter = query.iterator();
        ResultExam auxRelation;
        int idExam;
        ExamDAO myExamsDAO = new ExamDAO();
        Collection<Exam> result = new ArrayList<Exam>();
        while( iter.hasNext())
        {
            auxRelation = (ResultExam)iter.next();
            if ( auxRelation.getApproved() == 0) continue;
            idExam = auxRelation.getIdExam().getExamId();
            result.add(myExamsDAO.findById(idExam));
        }
        return result;
    }
}
