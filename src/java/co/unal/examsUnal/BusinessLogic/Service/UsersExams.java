/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Service;

import co.unal.examsUnal.BusinessLogic.Controller.Management.ExamController;
import co.unal.examsUnal.Utilities.Util.Tests;
import co.unal.examsUnal.Utilities.Util.UserExamResult;
import co.unal.examsUnal.Utilities.Util.UserResult;
import java.util.ArrayList;
import java.util.Collection;
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
     * @param document
     * @return 
     */
    @WebMethod(operationName = "getUserByDoc")
    public UserExamResult getUserByDoc(String document) {
        ExamController examController = new ExamController();
        Collection<UserResult> usersResults = examController.getUsersResults();
        UserExamResult userExamResult = null;
        for (UserResult userResult : usersResults){
            ArrayList<Tests> tests = new ArrayList<>();
            userResult.getExamsUser().stream().forEach((examUser) -> {
                tests.add(new Tests(examUser.getExam().getName(), examUser.getStatus(), examUser.getExam().getDescription() ) );
            });
            if( userResult.getUser().getName().equals(document) ){
                userExamResult = new UserExamResult(userResult.getUser().getName(), tests);
            }
        }
        System.out.println("description " + userExamResult.getTests().get(0).getComment());
        return userExamResult;
    }
}
