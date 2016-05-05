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
import co.unal.examsUnal.Utilities.Util.ExamResult;
import co.unal.examsUnal.Utilities.Util.ExamUser;
import co.unal.examsUnal.Utilities.Util.UserResult;
import com.sun.faces.action.RequestMapping;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
    
    public Exam findByName( String  name ){
        ExamDAO examDAO = new ExamDAO();
        return examDAO.findByName(name);
    }
    public Collection<Exam> findExamsUser(String username){
        ExamDAO examDAO = new ExamDAO();
        return examDAO.findExamsUser(username);
    }
    
    public Collection<ExamResult> getResultsByExam(){
        ResultExamDAO resultExamDAO = new ResultExamDAO();
        ExamDAO examsDAO = new ExamDAO();
        Collection<Resultexam> results = resultExamDAO.findAllRelation();
        Collection<Exam> exams = examsDAO.findAllExams();
        ArrayList<ExamResult> examResults = new ArrayList<>();
        ArrayList<ExamResult> examResultsToRemove = new ArrayList<>();
        for(Exam exam: exams){
            examResults.add(new ExamResult(exam, 0, 0));
        }
        for(Resultexam result: results){
            if(result.getStatus() == 1){
                if(result.getApproved() == 1){
                    for(ExamResult examResult: examResults)
                        if(Objects.equals(result.getIdExam().getExamId(), examResult.getExam().getExamId()))
                            examResult.setPassed(examResult.getPassed()+1);
                }else{
                    for(ExamResult examResult: examResults)
                        if(Objects.equals(examResult.getExam().getExamId(), result.getIdExam().getExamId()))
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
        Collection<Resultexam> results = resultExamDAO.findAllRelation();
        System.out.println("REsultados: " + results.size());
        List<User> users = new ArrayList<>();
        Map<String,List<ExamUser>> resultsTemp = new HashMap<>();
        ArrayList<UserResult> usersResults = new ArrayList<>();
        for(Resultexam result: results){
            if(!resultsTemp.containsKey(result.getIdUser().getDocument())){
                users.add(result.getIdUser());
                resultsTemp.put(result.getIdUser().getDocument(), new ArrayList<>());
            }else{
                resultsTemp.get(result.getIdUser().getDocument()).add(new ExamUser(result.getIdExam(), result.getApproved() == 1, result.getStatus() == 1 ));
            }
        }
        for(String i: resultsTemp.keySet()){
            for(User j:users){
                if(j.getDocument().equals(i)){
                    usersResults.add(new UserResult(j, resultsTemp.get(i)));
                }
            }
        }
        
        return usersResults;
    }
    
    public ExamResult getResultByIdExam( int idExam ){
        ResultExamDAO resultExamDAO = new ResultExamDAO();
        ExamDAO examDAO = new ExamDAO();
        Collection<Resultexam> results = resultExamDAO.findAllRelation();
        Exam exam = examDAO.findById(idExam);
        ExamResult examResult = new ExamResult(exam, 0, 0);
        for(Resultexam result: results){
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
        Collection<Resultexam> results = relationDAO.findAllRelation();
        Collection<Exam> exams = examsDAO.findAllExams();
        ArrayList<ExamResult> examResults = new ArrayList<>();
        for(Exam exam: exams){
            examResults.add(new ExamResult(exam, 0, 0));
        }
        for(Resultexam result: results){
            for(ExamResult examResult: examResults)
                if(result.getIdExam().getExamId() == examResult.getExam().getExamId())
                    examResult.setPassed(examResult.getPassed()+1);
        }
        return examResults;
    }
}
