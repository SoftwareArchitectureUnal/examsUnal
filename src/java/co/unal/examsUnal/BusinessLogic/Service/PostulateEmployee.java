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
     */
    @WebMethod(operationName = "postulate")
    public PostulateEmployeeResponseDto postulate(@WebParam(name = "parameter") PostulateEmployeeRequestDto parameter) {
        PostulateEmployeeResponseDto result = new PostulateEmployeeResponseDto();
        UserController myUserController = new UserController();
        PostulateEmployeeRequestDto.Employee myEmployee = parameter.getEmployee();
        int gender = myEmployee.getGender().compareTo(PostulateEmployeeRequestDto.Gender.MALE) == 0 ? 1 : 0;
        User myUser = myUserController.register(parameter.getUserName(), myEmployee.getFirstName()+" "+myEmployee.getLastName(), myEmployee.getEmail(), parameter.getPassword(), gender,"user");
        ExamRegisterController myExamRC = new ExamRegisterController();
        ExamController myExamC = new ExamController();
        for( String nameExam : parameter.getFeatures()  )
        {
            myExamRC.RegisterExam( myUser, myExamC.findByName(nameExam));
        }
        //TODO
        result.setSuccess(true);
        result.setErrorMessage("");
        return result;
    }
}