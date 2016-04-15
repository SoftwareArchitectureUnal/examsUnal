/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Controller.Management;

import co.unal.examsUnal.DataAccess.DAO.ExamDAO;
import co.unal.examsUnal.DataAccess.DAO.ResultExamDAO;
import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.DataAccess.Entity.ResultExam;
import co.unal.examsUnal.DataAccess.Entity.User;
import co.unal.examsUnal.Utilities.Util.ExamResult;
import co.unal.examsUnal.Utilities.Util.ExamUser;
import co.unal.examsUnal.Utilities.Util.UserResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author AndresGutierrez
 */
public class ExamController {
    
    public Exam insert(String examName, Date expeditionDate, Date realizationDate, Date certificationDate, String description){
        System.out.println("---insert---- "+examName+" "+expeditionDate+" "+realizationDate+" "+description);
        ExamDAO examDAO = new ExamDAO();
        Exam exam = new Exam();
        exam.setName(examName);
        exam.setExpeditionDate(expeditionDate);
        exam.setRealizationDate(realizationDate);
        exam.setCertificationDate(certificationDate);
        exam.setDescription(description);
        Exam temp = examDAO.insert(exam);
        return temp;
    }
    
    public Exam update(int examId, String examName, Date expeditionDate, Date realizationDate, Date certificationDate, String description){
        System.out.println("---update---- "+examName+" "+expeditionDate+" "+realizationDate+" "+description);
        ExamDAO examDAO = new ExamDAO();
        Exam exam = new Exam();
        exam.setExamId(examId);
        exam.setName(examName);
        exam.setExpeditionDate(expeditionDate);
        exam.setRealizationDate(realizationDate);
        exam.setCertificationDate(certificationDate);
        exam.setDescription(description);
        Exam temp = examDAO.update(exam);
        return temp;
    }
    
    public boolean deleteExam(Exam exam){
        System.out.println("---delete----"+exam);
        ExamDAO examDAO = new ExamDAO();
        ResultExamDAO resultExamDAO = new ResultExamDAO();
        resultExamDAO.deleteByExamId(exam);
        return examDAO.deleteExam(exam);
    }
    
    public Collection<Exam> findAll(){
        ExamDAO examDAO = new ExamDAO();
        return examDAO.findAllExams();
    }
    
    public Exam findById( int id ){
        ExamDAO examDAO = new ExamDAO();
        return examDAO.findById(id);
    }
    
    public Collection<Exam> findExamsUser(String username){
        ExamDAO examDAO = new ExamDAO();
        return examDAO.findExamsUser(username);
    }
    
    public Collection<ExamResult> getResultsByExam(){
        ResultExamDAO resultExamDAO = new ResultExamDAO();
        ExamDAO examsDAO = new ExamDAO();
        Collection<ResultExam> results = resultExamDAO.findAllRelation();
        Collection<Exam> exams = examsDAO.findAllExams();
        ArrayList<ExamResult> examResults = new ArrayList<>();
        ArrayList<ExamResult> examResultsToRemove = new ArrayList<>();
        ExamResult resultToRemove = new ExamResult();
        for(Exam exam: exams){
            examResults.add(new ExamResult(exam, 0, 0));
        }
        for(ResultExam result: results){
            if(result.getStatus() == 1){
                if(result.getApproved() == 1){
                    for(ExamResult examResult: examResults)
                        if(result.getIdExam().getExamId() == examResult.getExam().getExamId())
                            examResult.setPassed(examResult.getPassed()+1);
                }else{
                    for(ExamResult examResult: examResults)
                        if(examResult.getExam().getExamId() == result.getIdExam().getExamId())
                            examResult.setFailed(examResult.getFailed()+1);
                }
            }else{
                for(ExamResult examResult: examResults){
                    if( examResult.getExam().equals(result.getIdExam())){
                        if(!examResultsToRemove.contains(examResult)){
                            examResultsToRemove.add(examResult);
                        }
                    }
                }
            }
            
        }
        for(ExamResult examResult: examResultsToRemove){
            examResults.remove(examResult);
        }
        return examResults;
    }
    
    public Collection<UserResult> getUsersResults(){
        ResultExamDAO resultExamDAO = new ResultExamDAO();
        Collection<ResultExam> results = resultExamDAO.findAllRelation();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<UserResult> usersResults = new ArrayList<>();
                
        for(ResultExam result: results){
            if(result.getStatus() == 1){
                if( !users.contains(result.getIdUser()) ){
                    users.add(result.getIdUser());
                    UserResult userResult = new UserResult( result.getIdUser(), new ArrayList<>() );
                    userResult.addExamUser( new ExamUser(result.getIdExam(), result.getApproved() == 1) );
                    usersResults.add( userResult );
                }else{
                   for(UserResult userResult : usersResults){
                       if( userResult.getUser().equals( result.getIdUser() ) ){
                           userResult.addExamUser( new ExamUser(result.getIdExam(), result.getApproved() == 1) );
                       }
                   }
                }
            }
        }
        return usersResults;
    }
    
    public ExamResult getResultByIdExam( int idExam ){
        ResultExamDAO resultExamDAO = new ResultExamDAO();
        ExamDAO examDAO = new ExamDAO();
        Collection<ResultExam> results = resultExamDAO.findAllRelation();
        Exam exam = examDAO.findById(idExam);
        System.out.println("exam controller id: " + exam.getExamId());
        ExamResult examResult = new ExamResult(exam, 0, 0);
        for(ResultExam result: results){
            if(result.getApproved() >= 0){
                if(result.getApproved() == 1){
                    if(result.getIdExam().getExamId() == examResult.getExam().getExamId())
                        examResult.setPassed(examResult.getPassed()+1);
                }else{
                    if(result.getIdExam().getExamId() == examResult.getExam().getExamId())
                        examResult.setFailed(examResult.getFailed()+1);
                }
            }
        }
        return examResult;
    }
    
    public Collection<ExamResult> getRegisteredByExam(){
        ResultExamDAO relationDAO = new ResultExamDAO();
        ExamDAO examsDAO = new ExamDAO();
        Collection<ResultExam> results = relationDAO.findAllRelation();
        Collection<Exam> exams = examsDAO.findAllExams();
        ArrayList<ExamResult> examResults = new ArrayList<>();
        for(Exam exam: exams){
            examResults.add(new ExamResult(exam, 0, 0));
        }
        for(ResultExam result: results){
            for(ExamResult examResult: examResults)
                if(result.getIdExam().getExamId() == examResult.getExam().getExamId())
                    examResult.setPassed(examResult.getPassed()+1);
        }
        return examResults;
    }
}
