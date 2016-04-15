/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Service;

import co.unal.examsUnal.BusinessLogic.Controller.Management.ExamController;
import co.unal.examsUnal.Utilities.Util.ExamResult;
import co.unal.examsUnal.Utilities.Util.ExamUser;
import co.unal.examsUnal.Utilities.Util.UserResult;
import java.util.Collection;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author alej0
 */
@WebService(serviceName = "UsersExams")
public class UsersExams {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "getUsers")
    public String getUsers(/*@WebParam(name = "name") String txt*/) {
        ExamController examController = new ExamController();
        Collection<UserResult> usersResults = examController.getUsersResults();
        String results = "";
        String exams;
        for(UserResult userResult: usersResults){
            results += "$$" + userResult.getUser().getName() + "&&" + userResult.getUser().getEmail()+ "&&"; //+ userResult.getExamsUser() + "&&" + examResult.getFailed()
            exams = "";
            for( ExamUser examUser: userResult.getExamsUser() ){
                exams += "%%" + examUser.getExam().getName() + "%%" + examUser.getExam().getDescription() + "%%" + examUser.isApproved();
            }
            exams = exams.length() > 2 ? exams.substring(2) : "";
            results += exams;
        }
        results = results.length() > 2 ? results.substring(2) : "";
        return results;
    }
}
