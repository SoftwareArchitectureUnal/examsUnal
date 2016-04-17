/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Service;

import co.unal.examsUnal.BusinessLogic.Controller.Management.ExamController;
import co.unal.examsUnal.Utilities.Util.ExamData;
import co.unal.examsUnal.Utilities.Util.UserExamResult;
import co.unal.examsUnal.Utilities.Util.UserResult;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author alej0
 */
@WebService(serviceName = "UsersExams")
public class UsersExams {

    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "getUsers")
    public ArrayList<UserExamResult> getUsers(/*@WebParam(name = "name") String txt*/) {
        ExamController examController = new ExamController();
        Collection<UserResult> usersResults = examController.getUsersResults();
        ArrayList<UserExamResult> userExamResults = new ArrayList<>();
        usersResults.stream().forEach((userResult) -> {
            ArrayList<ExamData> examsData = new ArrayList<>();
            userResult.getExamsUser().stream().forEach((examUser) -> {
                examsData.add( new ExamData(examUser.getExam().getName(), examUser.getExam().getDescription(), examUser.isApproved()) );
            });
            userExamResults.add( new UserExamResult(userResult.getUser().getName(), userResult.getUser().getEmail(), examsData) );
        });
        return userExamResults;
    }
}
