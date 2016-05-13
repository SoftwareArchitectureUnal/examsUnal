/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Service;

import co.unal.examsUnal.BusinessLogic.Controller.Management.ExamController;
import co.unal.examsUnal.DataAccess.Entity.Resultexam;
import co.unal.examsUnal.Utilities.Util.ExamUser;

import co.unal.examsUnal.Utilities.Util.UserResult;
import co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusRequestDto;
import co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusResponseDto;
import co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusResponseDto.ResultDto;
import co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusResponseDto.TestResultDto;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author alej0
 */

@WebService(serviceName = "VerifyEmployeesStatus")
public class VerifyEmployeesStatus {
    private EntityManagerFactory entityManagerFactory;
    
    public VerifyEmployeesStatus() {
        entityManagerFactory = 
                Persistence.createEntityManagerFactory("ExamsUnalPU");
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "verifyEmployees")
    public VerifyEmployeesStatusResponseDto verifyEmployees(@WebParam(name = "parameter") VerifyEmployeesStatusRequestDto parameter) {
        VerifyEmployeesStatusResponseDto response = new VerifyEmployeesStatusResponseDto();
        
        List<ResultDto> results = new ArrayList<>();
        
        EntityManager em = entityManagerFactory.createEntityManager();
        
        em.getTransaction().begin();
        for(String document:parameter.getEmployees()){
            Query query;      
            query = em.createNativeQuery("SELECT * FROM  resultexam AS r  WHERE (r.idUser=? );",Resultexam.class);
            query.setParameter(1,document);
            
            List<Resultexam> exams = query.getResultList();
            ResultDto result = new ResultDto();
            List<TestResultDto> tests =  new ArrayList<>();
            result.setDocument(document);
            for(Resultexam exam:exams){
                TestResultDto test = new TestResultDto();
                test.setName(exam.getIdExam().getName());
                test.setComment(exam.getIdExam().getDescription());
                
                if(exam.getStatus() == 0){ 
                    test.setStatus(VerifyEmployeesStatusResponseDto.Status.PENDING);
                }else{
                    if(exam.getApproved() == 0){
                        test.setStatus(VerifyEmployeesStatusResponseDto.Status.FAIL);
                    }else{
                        test.setStatus(VerifyEmployeesStatusResponseDto.Status.PASS);
                    }
                }
                tests.add(test);
            }
            result.setTests(tests);
            results.add(result);
        }
        
        em.getTransaction().commit();
        
        response.setResults(results);
        
        return response;
    }
}

