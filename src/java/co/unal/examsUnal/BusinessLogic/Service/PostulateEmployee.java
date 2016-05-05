/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Service;

import co.unal.examsUnal.BusinessLogic.Controller.Management.ExamController;
import co.unal.examsUnal.BusinessLogic.Controller.Management.ExamRegisterController;
import co.unal.examsUnal.BusinessLogic.Controller.User.UserController;
import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.DataAccess.Entity.User;
import co.unal.examsUnal.Utilities.Util.PostulateEmployeeRequestDto;
import co.unal.examsUnal.Utilities.Util.PostulateEmployeeResponseDto;
import java.util.Arrays;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author yeisondavid
 */
@WebService(serviceName = "PostulateEmployee")
public class PostulateEmployee {


    /**
     * Web service operation
     * @param parameter
     * @return 
     */
    @WebMethod(operationName = "postulate")
    public PostulateEmployeeResponseDto postulate(@WebParam(name = "parameter") PostulateEmployeeRequestDto parameter) {
        PostulateEmployeeResponseDto result = new PostulateEmployeeResponseDto();
        int countFeatures = 0;
        UserController myUserController = new UserController();
        PostulateEmployeeRequestDto.Employee myEmployee = parameter.getEmployee();
        int gender = myEmployee.getGender().compareTo(PostulateEmployeeRequestDto.Gender.MALE) == 0 ? 1 : 0; 

        User myUser = myUserController.login(myEmployee.getDocument(), "password");
        if ( myUser == null )
            myUser = myUserController.register(myEmployee.getDocument(),myEmployee.getFirstName()+" "+myEmployee.getLastName(), myEmployee.getEmail(), "password", gender,"user",myEmployee.getDocument());
        ExamRegisterController myExamRC = new ExamRegisterController();
        ExamController myExamC = new ExamController();
        Exam myExam;
        try
        {
            for( String nameExam : parameter.getFeatures()  )
            {   
                myExam = myExamC.findByName(nameExam.trim());
                myExamRC.unSubcribeExam( myUser.getIdAuthentication(), myExam.getExamId());
                myExamRC.RegisterExam( myUser, myExam);
                countFeatures++;
            }
            result.setSuccess(true);
            result.setErrorMessage("");
        }
        catch(Exception e)
        {
            result.setSuccess(false);
            result.setErrorMessage("error in web service postulateEmployee, feature was not found , added features:"+countFeatures + "\nException: " + Arrays.toString(e.getStackTrace()));
        }
        return result;
    }
}
