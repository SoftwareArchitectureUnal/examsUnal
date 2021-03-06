/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Controller.Management;

import co.unal.examsUnal.DataAccess.DAO.ExamDAO;
import co.unal.examsUnal.DataAccess.DAO.ResultExamDAO;
import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.DataAccess.Entity.Resultexam;
import co.unal.examsUnal.DataAccess.Entity.User;
import co.unal.examsUnal.Utilities.Util.Pair;
import java.util.Collection;




import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;



/**
 *
 * @author yeisondavid
 */
public class ExamRegisterController {
        public Collection<Exam> allExams()
       {
           ExamDAO myDao = new ExamDAO();
           return myDao.findAllExams();
       }
       public Collection<Pair<Exam, Boolean>> ExamsUser(String idUser)
       {
           ExamDAO myDao = new ExamDAO();
           ResultExamDAO myRelationDAO = new ResultExamDAO();
           Collection<Exam> myCollectionExams = myDao.findAllExams();
           System.out.println(":::::::: "+idUser);
           Collection<Resultexam> myCollectionRelation = myRelationDAO.findRelationByIdUser(idUser);
           TreeSet<Integer> mySet = new TreeSet<Integer>();
           for( Resultexam myRelation : myCollectionRelation )
           {
               mySet.add(myRelation.getIdExam().getExamId());
           }
           Iterator iter = myCollectionExams.iterator();
           Exam myExam;
           Collection result = new ArrayList<Pair<Exam, Boolean>>();
           while( iter.hasNext())
           {
               myExam = (Exam)iter.next();
               if ( mySet.contains(myExam.getExamId()))
               {
                   result.add( new Pair(myExam, true));
               }
               else
               {
                   result.add( new Pair(myExam, false));
               }
           }
           return result;
       }
       public void RegisterExam(User user, Exam exam)
       {
           ResultExamDAO myRelationDAO = new ResultExamDAO();
           Resultexam myRelation = new Resultexam();
           myRelation.setApproved(0);
           myRelation.setStatus(0);
           
           //myRelation.setResultExamId(System.currentTimeMillis());
           myRelation.setIdExam(exam);
           myRelation.setIdUser(user);
           
           myRelationDAO.insert(myRelation);
       }
       
       
       public void unSubcribeExam(String idUser, int idExam)
       {
           ResultExamDAO myRelationDAO = new ResultExamDAO();
           Collection<Resultexam> collection = myRelationDAO.findRelationByIdUser(idUser);
           for( Resultexam myRelation : collection)
           {
               if ( myRelation.getIdExam().getExamId() == idExam)
               {
                    myRelationDAO.deleteByRelationId(myRelation);
                    break;
               }
           }
       }


}
