/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Service;

import co.unal.examsUnal.BusinessLogic.Controller.Management.ExamController;
import co.unal.examsUnal.Utilities.Util.ExamUser;

import co.unal.examsUnal.Utilities.Util.UserResult;
import co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusRequestDto;
import co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusResponseDto;
import co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusResponseDto.ResultDto;
import co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusResponseDto.TestResultDto;
import co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusResponseDto.Status;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author alej0
 */
@WebService(serviceName = "VerifyEmployeesStatus")
public class VerifyEmployeesStatus {
    /**
     * Web service operation
     * @param parameter
     * @return 
     */
    @WebMethod(operationName = "verifyEmployees")
    public VerifyEmployeesStatusResponseDto verifyEmployees(@WebParam(name = "parameter") VerifyEmployeesStatusRequestDto parameter) {
        List<String> documents = parameter.getEmployees();
        ExamController examController = new ExamController();
        Collection<UserResult> usersResults = examController.getUsersResults();
        VerifyEmployeesStatusResponseDto response = new VerifyEmployeesStatusResponseDto();
        List<TestResultDto> tests;
        List<ResultDto> resutls = new ArrayList<>();
        for (UserResult userResult : usersResults){
            tests = new ArrayList<>();
            for(ExamUser examUser:userResult.getExamsUser()){
                TestResultDto test = new TestResultDto();
                test.setName(examUser.getExam().getName());
                test.setComment(examUser.getExam().getDescription());
                Status status = examUser.getStatus();
                test.setStatus(status);
                tests.add(test);
            }
            for(String document:documents){
                if( userResult.getUser().getDocument().equals(document.trim()) ){
                    ResultDto result = new ResultDto();
                    result.setDocument(document);
                    result.setTests(tests);
                    resutls.add(result);
                }  
            }
        }
        response.setResults(resutls);
        return response;
    }
}

